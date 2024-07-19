package org.codenotknock.juc4_synchronized;

import lombok.extern.slf4j.Slf4j;

import static org.codenotknock.juc2.util.Sleeper.sleep;

/**
 * 练习——synchronized究竟锁住了谁：3
 */

@Slf4j(topic = "c.Synchronized_six")
public class SynchronizedDemo6 {
    public synchronized void a() {
        sleep(1);
        log.debug("1");
    }
    public synchronized void b() {
        log.debug("2");
    }

    public void c() {
        log.debug("3");
    }
    public static void main(String[] args) {
        SynchronizedDemo6 n1 = new SynchronizedDemo6();
        new Thread(()->{ n1.a(); }).start();
        new Thread(()->{ n1.b(); }).start();
        new Thread(()->{ n1.c(); }).start();
        // c方法没有synchronized修饰，是单独的，并行执行—所以什么时候都有可能执行，取决于cpu的调度。
        // 答案：在1的前面，因为1方法中又sleep(1)
    }
}
