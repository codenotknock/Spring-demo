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
 *
 * 超时时间
 */
@Slf4j(topic = "c.ThreadPool")
public class Demo2ThreadPool {
    public static void main(String[] args) {
        ThreadPool2 threadPool = new ThreadPool2(5, 5, 500, TimeUnit.MILLISECONDS);
        for (int i = 0; i < 10; i++) {
            int j = i;
            threadPool.execute(() -> {
                log.debug("{}", j);
            });
        }
    }

}
@Slf4j(topic = "c.ThreadPool")
class ThreadPool2 {
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

    public ThreadPool2(int coreSize, int maxSize, long timeout, TimeUnit unit) {
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
            while (task != null || (task = taskQueue.poll(200, TimeUnit.MILLISECONDS)) != null) {
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