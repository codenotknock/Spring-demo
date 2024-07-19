package org.codenotknock.juc6_Lock;

import lombok.extern.slf4j.Slf4j;

import static java.lang.Thread.sleep;

/**
 * @author xiaofu
 * 活锁：改变了多方的结束条件，导致线程都结束不了，一直在运行中
 */

@Slf4j(topic = "c.LiveLock")
public class Demo4LiveLock {
    private static volatile int count = 99;
    private static Object lock = new Object();
    public static void main(String[] args) {
        new Thread(() -> {
            while (count > 88) {
                count--;
                try {
                    sleep(20);
                    log.debug("t1_count:{}", count);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "t1").start();

        new Thread(() -> {
            while (count < 111) {
                count++;
                try {
                    sleep(20);
                    log.debug("t2_count:{}", count);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "t2").start();
    }
}

