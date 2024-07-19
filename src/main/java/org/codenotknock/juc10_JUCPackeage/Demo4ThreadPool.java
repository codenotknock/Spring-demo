package org.codenotknock.juc10_JUCPackeage;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author xiaofu
 * ThreadPoolExecutor
 *
 * 构造参数
 * public ThreadPoolExecutor(int corePoolSize,  	// 核心线程数目：最多保留的线程数
 *                          int maximumPoolSize,	// 最大线程数目（核心线程+救急线程）
 *                          long keepAliveTime,	// 生存时间：针对救急线程
 *                          TimeUnit unit,			// 时间单位
 *                          BlockingQueue<Runnable> workQueue,	// 阻塞队列
 *                          ThreadFactory threadFactory,	// 线程工厂：可为线程创建时起名字
 *                          RejectedExecutionHandler handler)	// 拒绝策略
 *
 * 三种队列
 * ● 直接交接：SynchronousQueue
 * ● 无界队列：LinkedBlockingQueue
 * ● 有界的队列：ArrayBlockingQueue
 * 四种jdk拒绝策略
 * ● AbortPolicy 让调用者抛出 RejectedExecutionException 异常，这是默认策略
 * ● CallerRunsPolicy 让调用者运行任务
 * ● DiscardPolicy 放弃本次任务
 * ● DiscardOldestPolicy 放弃队列中最早的任务，然后尝试重新提交被拒绝的任务
 *
 */

public class Demo4ThreadPool {
    public static void main(String[] args) {
        ArrayBlockingQueue<Runnable> queue = new ArrayBlockingQueue<Runnable>(4);
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(2,
                5,
                1000,
                TimeUnit.MILLISECONDS,
                queue,
                new ThreadFactory() {
                    private AtomicInteger count = new AtomicInteger(1);
                    @Override
                    public Thread newThread(Runnable r) {
                        return new Thread(r,+count.getAndIncrement()+"好名字 ");
                    }
                });

        for (int i = 0; i < 20; i++) {
            // 因为默认拒绝策略的原因，超过最大限制 5+4=9 会抛异常
            int finalI = i;
            threadPoolExecutor.execute(() -> {
                try {
                    Thread.sleep(1000);
                    System.out.println(Thread.currentThread().getName() + finalI);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }
    }
}
