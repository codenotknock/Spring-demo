package org.codenotknock.juc4_synchronized;

import lombok.extern.slf4j.Slf4j;

import static org.codenotknock.juc2.util.Sleeper.sleep;

/**
 * 练习——synchronized究竟锁住了谁：8
 */

@Slf4j(topic = "c.Synchronized_eleven")
public class SynchronizedDemo11 {
    public static synchronized void a() {
        sleep(1);
        log.debug("1");
    }
    public static synchronized void b() {
        log.debug("2");
    }

    public static void main(String[] args) {
        SynchronizedDemo11 n1 = new SynchronizedDemo11();
        SynchronizedDemo11 n2 = new SynchronizedDemo11();
        new Thread(()->{ n1.a(); }).start();
        new Thread(()->{ n2.b(); }).start();

        // 锁住的都是类对象，而类对象只有一个：SynchronizedDemo11.class
        // 故是同一个对象，互斥
        // 答案：2 1秒后1 或 1秒后1 2
    }
}
