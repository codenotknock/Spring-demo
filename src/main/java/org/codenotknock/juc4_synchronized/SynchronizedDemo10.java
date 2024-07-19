package org.codenotknock.juc4_synchronized;

import lombok.extern.slf4j.Slf4j;

import static org.codenotknock.juc2.util.Sleeper.sleep;

/**
 * 练习——synchronized究竟锁住了谁：7
 */

@Slf4j(topic = "c.Synchronized_ten")
public class SynchronizedDemo10 {
    public static synchronized void a() {
        sleep(1);
        log.debug("1");
    }
    public synchronized void b() {
        log.debug("2");
    }

    public static void main(String[] args) {
        SynchronizedDemo10 n1 = new SynchronizedDemo10();
        SynchronizedDemo10 n2 = new SynchronizedDemo10();
        new Thread(()->{ n1.a(); }).start();
        new Thread(()->{ n2.b(); }).start();

        // 锁住的不是同一个对象，不互斥，是并行的。
        // 答案：2 1秒后1
    }
}
