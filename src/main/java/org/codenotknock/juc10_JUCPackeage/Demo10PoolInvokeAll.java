package org.codenotknock.juc10_JUCPackeage;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author xiaofu
 * ThreadPoolExecutor 线程池
 *
 * invokeAll 方法  执行给定的任务集合，并返回一个包含Future对象的列表
 * 不带超时参数，它会阻塞当前线程，直到所有的任务都执行完成才会继续向下执行。
 *      <T> List<Future<T>> invokeAll(Collection<? extends Callable<T>> tasks)
 *         throws InterruptedException;
 *
 * 设置一个超时时间，如果在指定的时间内任务没有全部执行完成，
 *      那么未完成的任务将被取消。该方法也会阻塞当前线程，直到所有任务都执行完成或超时。
 *      <T> List<Future<T>> invokeAll(Collection<? extends Callable<T>> tasks,
 *                                   long timeout, TimeUnit unit)
 *         throws InterruptedException;
 */
public class Demo10PoolInvokeAll {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(2);

        List<Callable<Integer>> callables = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            int finalI = i;
            callables.add(() -> {
                    System.out.println(finalI);
                    return finalI;
            });
        }
        List<Future<Integer>> futures = executorService.invokeAll(callables);
        for (Future<Integer> future : futures) {
            System.out.println("res：" + future.get() );
        }

        List<Callable<Integer>> callables2 = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            int finalI = i;
            callables2.add(() -> {
                Thread.sleep(50);
                System.out.println("r"+finalI);
                return finalI;
            });
        }

        List<Future<Integer>> futures2 = executorService.invokeAll(callables2, 500, TimeUnit.MILLISECONDS);
        for (Future<Integer> future : futures2) {
            System.out.println("res2：" + future.get() );
        }

        executorService.shutdown();

    }
}
