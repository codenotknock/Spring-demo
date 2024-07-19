package org.codenotknock.juc10_JUCPackeage;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * @author xiaofu
 * ThreadPoolExecutor 线程池
 *
 * invokeAny 方法  执提交 tasks 中所有任务，哪个任务先成功执行完毕，返回此任务执行结果，其它任务取消
 *     <T> T invokeAny(Collection<? extends Callable<T>> tasks)
 *         throws InterruptedException, ExecutionException;
 * 设置一个超时时间
 *          <T> T invokeAny(Collection<? extends Callable<T>> tasks,
 *                     long timeout, TimeUnit unit)
 *         throws InterruptedException, ExecutionException, TimeoutException;
 */
public class Demo11PoolInvokeAny {
    public static void main(String[] args) throws ExecutionException, InterruptedException, TimeoutException {
        ExecutorService executorService = Executors.newFixedThreadPool(2);

        List<Callable<Integer>> callables = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            int finalI = i;
            callables.add(() -> {
                    System.out.println(finalI);
                    Thread.sleep(new Random().nextInt(100)+1);
                    return finalI;
            });
        }
        Integer res = executorService.invokeAny(callables);
        System.out.println("res：" + res);

        List<Callable<Integer>> callables2 = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            int finalI = i;
            callables2.add(() -> {
                Thread.sleep(50);
                System.out.println("r"+finalI);
                return finalI;
            });
        }

        Integer res2 = executorService.invokeAny(callables2, 200, TimeUnit.MILLISECONDS);
        System.out.println("res2：" + res2);
        executorService.shutdown();

    }
}
