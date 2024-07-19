package org.codenotknock.juc3_thread;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * 创建线程方法三：FutureTask 配合 Callable
 * FutureTask 能够接收Callable类型的参数，处理返回的结果
 * - Callable比Runnable多了一个返回结果的值并且可以抛出异常
 * - Callable 重写 call 方法
 */
@Slf4j(topic = "c.CallableDemo3_CallableFutureTask")
public class CallableDemo3{
        /* 结果：
        15:51:36 [CallableFutureTask_one] c.CallableDemo3_CallableFutureTask - Thread[CallableFutureTask_one,5,main]执行任务...
        15:51:38 [main] c.CallableDemo3_CallableFutureTask - Thread[main,5,main]执行任务...99
         */
        public static void main(String[] args) throws ExecutionException, InterruptedException {
                FutureTask<Integer> task = new FutureTask<>(new Callable<Integer>() {
                        @Override
                        public Integer call() throws Exception {
                                log.debug(Thread.currentThread() + "执行任务...");  // CallableFutureTask_one 线程所运行
                                Thread.sleep(2000);
                                return 99;
                        }
                });

                Thread t = new Thread(task,"CallableFutureTask_one");
                t.start();
                // task.get 会阻塞直到 CallableFutureTask_one 线程的返回结果
                log.debug(Thread.currentThread() + "执行任务..." + task.get());  // main 主线程所运行
        }
}
