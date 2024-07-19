package org.codenotknock.juc10_JUCPackeage;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author xiaofu
 * ThreadPoolExecutor
 *
 * SingleThreadPool  单线程，无界的阻塞队列LinkedBlockingQueue
 * Executors.newSingleThreadExecutor()
 * 自己创建一个单线程串行执行任务，如果任务执行失败而终止那么没有任何补救措施，而线程池还会新建一个线程，保证池的正常工作
 * Executors.newSingleThreadExecutor() 线程个数始终为1，不能修改
 * FinalizableDelegatedExecutorService 应用的是装饰器模式，只对外暴露了 ExecutorService 接口，因
 * 此不能调用 ThreadPoolExecutor 中特有的方法
 *
 * Executors.newFixedThreadPool(1) 初始时为1，以后还可以修改
 * 对外暴露的是 ThreadPoolExecutor 对象，可以强转后调用 setCorePoolSize 等方法进行修改
 *
 */
public class Demo6SingleThreadPool {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        for (int i = 0; i < 50; i++) {
            int finalI = i;
            executorService.execute(()->{
                System.out.println(finalI);
            });
        }
        executorService.shutdown();

    }
}
