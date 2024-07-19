package org.codenotknock.juc10_JUCPackeage;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author xiaofu
 * ThreadPoolExecutor
 *
 * CacheThreadPool  同步交接队列synchronousQueue
 *    ● 核心线程数是 0，同步交接队列
 *   ○ 全部都是救急线程（60s 后可以回收）.最大线程数是 Integer.MAX_VALUE
 *   ○ 救急线程可以无限创建
 * Executors.newCachedThreadPool()
 *
 */
public class Demo7CacheThreadPool {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newCachedThreadPool();
        for (int i = 0; i < 50; i++) {
            int finalI = i;
            executorService.execute(()->{
                System.out.println(finalI);
            });
        }
        executorService.shutdown();

    }
}
