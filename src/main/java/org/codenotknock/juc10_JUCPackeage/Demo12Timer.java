package org.codenotknock.juc10_JUCPackeage;

import lombok.SneakyThrows;

import java.sql.Time;
import java.util.Timer;
import java.util.TimerTask;

/**
 * @author xiaofu
 * Timer 任务调度：来实现定时功能，但其实是串行的，所以前面的任务执行时间会影响到后面任务的开始时间
 * 比如：任务1设置在10s时开始， 任务2设置在5s时开始，但任务2执行了10s，这时任务1只好推迟在15s时再开始
 *
 * 如果前面的任务出现了异常，后面的任务都没办法执行
 * Timer非常脆弱，已经过时了
 */

public class Demo12Timer {
    public static void main(String[] args) {
        Timer timer = new Timer();
        TimerTask timerTask1 = new TimerTask() {
            @Override
            public void run() {
                System.out.println("timerTask1 ...");
            }
        };
        TimerTask timerTask2 = new TimerTask() {
            @SneakyThrows
            @Override
            public void run() {
                Thread.sleep(1000);
                System.out.println("timerTask2 ...");
            }
        };
        System.out.println("start... ");
        timer.schedule(timerTask1, 1000);
        timer.schedule(timerTask2, 500);

    }
}
