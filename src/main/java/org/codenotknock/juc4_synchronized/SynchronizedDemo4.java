package org.codenotknock.juc4_synchronized;

import lombok.extern.slf4j.Slf4j;

/**
 * 练习——synchronized究竟锁住了谁：1
 */

@Slf4j(topic = "c.Synchronized_four")
public class SynchronizedDemo4 {
    public synchronized void a() {
        log.debug("1");
    }
    public synchronized void b() {
        log.debug("2");
    }
    public static void main(String[] args) {
        SynchronizedDemo4 n1 = new SynchronizedDemo4();
        new Thread(()->{ n1.a(); }).start();
        new Thread(()->{ n1.b(); }).start();
        // a、b方法相当于锁住了this对象
        // 获取的是同一个对象锁：this
        // 答案是12或21  取决于cpu先调度哪一个线程
        // 但第一个线程先启动，cpu先调度第一个线程的可能性更大

    }
}
