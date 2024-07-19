package org.codenotknock.juc4_synchronized;

import lombok.extern.slf4j.Slf4j;

import static org.codenotknock.juc2.util.Sleeper.sleep;

/**
 * 练习——synchronized究竟锁住了谁：5
 */

@Slf4j(topic = "c.Synchronized_eight")
public class SynchronizedDemo8 {
    public static synchronized void a() {
        sleep(1);
        log.debug("1");
    }
    public synchronized void b() {
        log.debug("2");
    }

    public static void main(String[] args) {
        SynchronizedDemo8 n1 = new SynchronizedDemo8();
        new Thread(()->{ n1.a(); }).start();
        new Thread(()->{ n1.b(); }).start();

        // a方法是static方法，synchronized 修饰static方法 锁住的是SynchronizedDemo8类对象
        // b方法是普通方法，synchronized 修饰普通方法 锁住的是this对象
        // 故并不是锁住同一个对象，不互斥，是并行的。
        // 答案：2 1秒后1
    }
}
