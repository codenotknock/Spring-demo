package org.codenotknock.juc10_JUCPackeage;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @author xiaofu
 * ThreadPoolExecutor
 *
 * ScheduledThreadPool  DelayedWorkQueue
 * ScheduledThreadPool：支持定期和周期性执行任务，也会回收、DelayWorkQueue
 * Executors.newScheduledThreadPool(n)
 * schedule 延时执行任务
 *       public ScheduledFuture<?> schedule(Runnable command,
 *                                        long delay, TimeUnit unit);
 * scheduleAtFixedRate 定时执行任务：按照固定的时间间隔重复执行任务，不管前一个任务是否完成
 *    public ScheduledFuture<?> scheduleAtFixedRate(Runnable command,  Runnable线程任务
 *                                                   long initialDelay,  首次执行任务的延迟时间
 *                                                   long period,  任务之间的间隔时间
 *                                                   TimeUnit unit);  时间单位
 *  scheduleWithFixedDelay  定期执行任务：每次任务完成后延迟指定的时间间隔，然后再执行下一个任务
 *      public ScheduledFuture<?> scheduleWithFixedDelay(Runnable command,
 *                                                      long initialDelay,  首次执行任务的延迟时间
 *                                                      long delay,  连续任务之间的延迟时间
 *                                                      TimeUnit unit);
 *
 *  问题： executorService.shutdown();
 *  在其后添加了关闭线程池语句会导致定时任务不能被执行，因为线程池都没了！
 *
 */
public class Demo8ScheduledThreadPool {
    public static void main(String[] args) {
        ScheduledExecutorService executorService = Executors.newScheduledThreadPool(1);
        for (int i = 0; i < 5; i++) {
            int finalI = i;
            executorService.execute(()->{
                System.out.println(finalI);
            });
        }
        // 延时执行任务
        executorService.schedule(() -> {
            System.out.println("delay ... ");
        }, new Random().nextInt(1000), TimeUnit.MILLISECONDS);

        // 定时任务：按照固定的时间间隔重复执行任务，不管前一个任务是否完成
        executorService.scheduleAtFixedRate(() -> {
            System.out.println("schedule running....");
        }, 1, 1, TimeUnit.SECONDS);

        // 定时任务：每次任务完成后延迟指定的时间间隔，然后再执行下一个任务
        executorService.scheduleWithFixedDelay(() -> {
            System.out.println("schedule222 runing....");
        }, 1, 1, TimeUnit.SECONDS);

//        executorService.shutdown();

    }
}
