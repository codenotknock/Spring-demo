package org.codenotknock.juc4_synchronized;

import lombok.extern.slf4j.Slf4j;

import static org.codenotknock.juc2.util.Sleeper.sleep;

/**
 * 练习——synchronized究竟锁住了谁：4
 */

@Slf4j(topic = "c.Synchronized_seven")
public class SynchronizedDemo7 {
    public synchronized void a() {
        sleep(1);
        log.debug("1");
    }
    public synchronized void b() {
        log.debug("2");
    }

    public static void main(String[] args) {
        SynchronizedDemo7 n1 = new SynchronizedDemo7();
        SynchronizedDemo7 n2 = new SynchronizedDemo7();
        new Thread(()->{ n1.a(); }).start();
        new Thread(()->{ n2.b(); }).start();

        // 这是两个对象，故分别锁住的是各自的this对象。没有互斥，是并行的
        // 答案：2 1秒后1
    }
}
