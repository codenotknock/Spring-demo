package org.codenotknock.juc4_synchronized;

import lombok.extern.slf4j.Slf4j;

import static org.codenotknock.juc2.util.Sleeper.sleep;

/**
 * 练习——synchronized究竟锁住了谁：6
 */

@Slf4j(topic = "c.Synchronized_nine")
public class SynchronizedDemo9 {
    public static synchronized void a() {
        sleep(1);
        log.debug("1");
    }
    public static synchronized void b() {
        log.debug("2");
    }

    public static void main(String[] args) {
        SynchronizedDemo9 n1 = new SynchronizedDemo9();
        new Thread(()->{ n1.a(); }).start();
        new Thread(()->{ n1.b(); }).start();

        // synchronized 修饰static方法 锁住的是SynchronizedDemo8类对象
        // 锁住的都是SynchronizedDemo8类对象 互斥
        // 故并不是锁住同一个对象，不互斥，是并行的。
        // 答案：2 1秒后1 或 1秒后1 2
    }
}
