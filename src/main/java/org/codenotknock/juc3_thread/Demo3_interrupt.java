package org.codenotknock.juc3_thread;

import lombok.extern.slf4j.Slf4j;

/**
 * sleep 方法 线程睡眠n毫秒 ，可能会被打断interruption
 *
 * interrupt 方法 强制唤醒睡眠的线程
 */

@Slf4j(topic = "c.method_interrupt")
public class Demo3_interrupt {
    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread("one") {
            @Override
            public void run() {
                log.debug(Thread.currentThread() + " sleep-ing ... ");
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    log.debug(Thread.currentThread() + " interrupt ... ");
                }
            }
        };
        t1.start();

        Thread.sleep(200);
        log.debug(" main ----- interrupt ... ");
        t1.interrupt();

    }
}
