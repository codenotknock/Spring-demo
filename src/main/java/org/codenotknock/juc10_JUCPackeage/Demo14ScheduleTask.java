package org.codenotknock.juc10_JUCPackeage;

import java.time.DayOfWeek;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @author xiaofu
 *
 * 线程池的应用：模拟定时任务
 */

public class Demo14ScheduleTask {

    // 每周五18:00执行任务
    public static void main(String[] args) {
        // 当前时间
        LocalDateTime now = LocalDateTime.now();
        // 修改到本周的 周五18:00
        LocalDateTime time = now.withHour(18).withMinute(0).withSecond(0).withNano(0).with(DayOfWeek.FRIDAY);
        if (now.compareTo(time) > 0) {
            // 如果已经超过周五了 需要加一周
            time = time.plusWeeks(1);
        }
        long millis = Duration.between(now, time).toMillis();

        long period = 1000 * 60 * 60 * 24 * 7;
        ScheduledExecutorService pool = Executors.newScheduledThreadPool(1);
        pool.scheduleAtFixedRate(() -> {
            System.out.println("running... ");
        }, millis, period, TimeUnit.MILLISECONDS);
    }
}
