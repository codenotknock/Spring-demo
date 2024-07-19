package org.codenotknock.juc6_Lock;

import lombok.extern.slf4j.Slf4j;

/**
 * @author xiaofu
 * 死锁 代码示例
 */

@Slf4j(topic = "c.DeadLock")
public class Demo2DeadLock {
    public static void main(String[] args) {
        Object a = new Object();
        Object b = new Object();

        new Thread(() -> {
            synchronized(a) {
                try {
                    log.debug("线程t1获取a对象锁....");
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                // 会在获取b对象锁时阻塞...
                synchronized (b) {
                    log.debug("线程t1获取b对象锁....");
                }
            }
        }, "t1").start();


        new Thread(() -> {
            synchronized(b) {
                try {
                    log.debug("线程t2获取b对象锁....");
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                // 会在获取a对象锁时阻塞...
                synchronized (a) {
                    log.debug("线程t2获取a对象锁....");
                }
            }
        }, "t2").start();

    }
}
