package org.codenotknock.juc10_JUCPackeage;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashSet;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author xiaofu
 * 自定义模拟线程池
 */
@Slf4j(topic = "c.ThreadPool")
public class Demo1ThreadPool {
    public static void main(String[] args) {
        ThreadPool threadPool = new ThreadPool(5, 5, 500, TimeUnit.MILLISECONDS);
        for (int i = 0; i < 10; i++) {
            int j = i;
            threadPool.execute(() -> {
                log.debug("{}", j);
            });
        }
    }

}
@Slf4j(topic = "c.ThreadPool")
class ThreadPool {
    // 任务队列
    private BlockingQueue<Runnable> taskQueue;

    // 线程集合
    private HashSet<Worker> workers = new HashSet<>();

    // 核心线程数量
    private int coreSize;

    // 最大线程数量
    private int maxSize;

    // 获取任务的超时时间
    private long timeout;

    private TimeUnit unit;

    public ThreadPool(int coreSize, int maxSize, long timeout, TimeUnit unit) {
        this.taskQueue = new BlockingQueue<>(25);
        this.coreSize = coreSize;
        this.maxSize = maxSize;
        this.timeout = timeout;
        this.unit = unit;
    }

    // 执行任务
    public void execute(Runnable task) {
        // 任务数没有超过线程数量时直接执行，超过时加入任务队列
        synchronized(workers) {
            if (workers.size() < coreSize) {
                Worker worker = new Worker(task);
                log.debug("新增worker {}", worker);
                workers.add(worker);
                worker.start();
            } else {
                log.debug("加入任务队列 {}", task);
                taskQueue.put(task);
            }
        }
    }

    class Worker extends Thread {
        private Runnable task;
        public Worker(Runnable task) {
            this.task = task;
        }

        @Override
        public void run() {
            // 执行任务
            // 1.task 不为空，直接执行。
            // 2. 执行完毕 taskQueue 不为空， 继续执行
            while (task != null || (task = taskQueue.take()) != null) {
                try {
                    log.debug("run..... {}", task);
                    task.run();
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    log.debug("任务完成..... {}", task);
                    task = null;
                }
            }
            synchronized (workers) {
                log.debug("结束..... {}", workers);
                workers.remove(this);
            }

        }
    }

}
class BlockingQueue <T> {
    // 任务队列
    private Deque<T> queue = new ArrayDeque<>();

    // 锁
    private ReentrantLock lock = new ReentrantLock();

    // 生产者条件变量
    private Condition fullWaitSet = lock.newCondition();

    // 消费者条件变量
    private Condition emptyWaitSet = lock.newCondition();

    // 容量
    private int capacity;

    public BlockingQueue(int capacity) {
        this.capacity = capacity;
    }

    // 带超时的 阻塞获取
    public T poll(long timeout, TimeUnit unit) {
        lock.lock();
        try {
            long nanos = unit.toNanos(timeout);
            while (queue.isEmpty()) {
                try {
                    // 返回的是剩余的时间
                    if (nanos <= 0) {
                        return null;
                    }
                    nanos = emptyWaitSet.awaitNanos(nanos);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            T first = queue.removeFirst();
            fullWaitSet.signal();
            return first;
        } finally {
            lock.unlock();
        }

    }

    // 阻塞获取
    public T take() {
        lock.lock();
        try {
            while (queue.isEmpty()) {
                try {
                    emptyWaitSet.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            T first = queue.removeFirst();
            fullWaitSet.signal();
            return first;
        } finally {
            lock.unlock();
        }
    }

    public void put(T element) {
        lock.lock();
        try {
            while (queue.size() == capacity) {
                try {
                    fullWaitSet.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            queue.addLast(element);
            emptyWaitSet.signal();
        } finally {
            lock.unlock();
        }
    }

    public int size() {
        lock.lock();
        try {
            return queue.size();
        } finally {
            lock.unlock();
        }
    }

}