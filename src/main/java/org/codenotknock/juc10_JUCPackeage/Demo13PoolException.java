package org.codenotknock.juc10_JUCPackeage;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * @author xiaofu
 *
 *
 * 线程池中的异常信息 解决办法：
 *      1. 使用 try catch 代码块
 *      2. 使用 callable 捕获接收异常信息
 */

public class Demo13PoolException {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(2);

        Future<Boolean> submit = executorService.submit(() -> {
            int res = 1 / 0;
            System.out.println("runing..." + res);
            return true;
        });
        System.out.println(submit.get());

    }
}
