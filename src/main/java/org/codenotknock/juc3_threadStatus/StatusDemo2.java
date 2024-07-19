package org.codenotknock.juc3_threadStatus;

import lombok.extern.slf4j.Slf4j;

import static java.lang.Thread.sleep;

/**
 * @author xiaofu
 * RUNNABLE <—> WAITING
 * ● 调用 obj.wait() 方法时，t 线程从 调用 RUNNABLE --> WAITING
 * ● 调用 obj.notify() ， obj.notifyAll() ， t.interrupt()  方法时
 *   ○ 竞争锁成功，t 线程从 WAITING --> RUNNABLE
 *   ○ 竞争锁失败，t 线程从 WAITING --> BLOCKED
 */

@Slf4j(topic = "c.StatusDemo2")
public class StatusDemo2 {
    final static Object o = new Object();
    /*
        14:45:44 [main] c.StatusDemo2 - new后start前 +NEW
        14:45:44 [main] c.StatusDemo2 - start中 +RUNNABLE
        14:45:44 [t1] c.StatusDemo2 - 开始执行....
        14:45:44 [t1] c.StatusDemo2 - 执行等待了....
        14:45:45 [main] c.StatusDemo2 - wait中 +WAITING
        14:45:45 [t1] c.StatusDemo2 - 执行唤醒了.....
        14:45:45 [main] c.StatusDemo2 - 唤醒后 +RUNNABLE
     */

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(() -> {
            synchronized (o) {

                try {
                    log.debug("开始执行....");
                    sleep(200);
                    log.debug("执行等待了....");
                    o.wait();
                    log.debug("执行唤醒了.....");
                    while(true){}

                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }

            }
        }, "t1");


        log.debug("new后start前 +{}", t1.getState());
        t1.start();
        log.debug("start中 +{}", t1.getState());
        sleep(500);
        log.debug("wait中 +{}", t1.getState());

        synchronized (o) {
            o.notify();
        }
        sleep(100);
        log.debug("唤醒后 +{}", t1.getState());

    }

}
