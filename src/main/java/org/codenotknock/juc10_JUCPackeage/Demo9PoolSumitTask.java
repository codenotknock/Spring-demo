package org.codenotknock.juc10_JUCPackeage;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author xiaofu
 * ThreadPoolExecutor 线程池
 *
 * submit 方法
 *  <T> Future<T> submit(Callable<T> task);
 *  Future<?> submit(Runnable task);
 *  <T> Future<T> submit(Runnable task, T result);
 */
public class Demo9PoolSumitTask {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        for (int i = 0; i < 50; i++) {
            int finalI = i;
            executorService.submit(()->{
                System.out.println(finalI);
            });

            Future<String> future = executorService.submit(() -> {
                System.out.println(finalI);
                return "ok";
            });
            System.out.println(i+" submit_future："+future.get());

            AtomicInteger res = new AtomicInteger(1);
            Future<AtomicInteger> future2 = executorService.submit(() -> {
                System.out.println(finalI);
                res.getAndIncrement();
            }, res);
            System.out.println(future2.get());  // 2 2 2 2  2...


        }
        executorService.shutdown();

    }
}
