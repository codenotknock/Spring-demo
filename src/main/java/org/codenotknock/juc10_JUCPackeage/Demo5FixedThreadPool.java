package org.codenotknock.juc10_JUCPackeage;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author xiaofu
 * ThreadPoolExecutor
 *
 * FixedThreadPool  固定大小线程池、无界的阻塞队列LinkedBlockingQueue
 * Executors.newFixedThreadPool(n)
 *
 * 关闭线程池
 * ● shutdown 停止线程池，比较绅士
 * ● isShutdown 返回一个布尔值，线程池是否停止
 * ● isTerminated 返回一个布尔值，线程池是否完全停止
 * ● awaitTermination(3L, TimeUnit.SECONDS) 返回一个布尔值，3s内线程池是否完全停止
 * ● shutdownNow 会调用每个工作线程的interrupt方法来中断线程的执行中断，停止线程池，返回结果List<Runnable> 队列是还未执行的任务
 */

public class Demo5FixedThreadPool {
    public static void main(String[] args) {
        ExecutorService pool = Executors.newFixedThreadPool(3);
        for (int i = 0; i < 50; i++) {
            int finalI = i;
            pool.execute(()->{
                System.out.println(finalI);
            });
        }
        pool.shutdown();
    }
}
