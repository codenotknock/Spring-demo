package org.codenotknock.juc6_ReentrantLock;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.locks.ReentrantLock;

import static java.lang.Thread.sleep;

/**
 * @author xiaofu
 * 模拟一下：ReentrantLock 是可中断的锁  lockInterruptibly方法
 */

@Slf4j(topic = "c.ReentrantLock2")
public class Demo2ReentrantLock {
    private static ReentrantLock lock = new ReentrantLock();

    public static void main(String[] args) throws InterruptedException {
//        lock.lock();  // lock这个方法也是可被打断的
        // 为什么使用的是lockInterruptibly方法呢？ （使用lock.lock()方法 线程被打断后:   java: 在相应的 try 语句主体中不能抛出异常错误java.lang.InterruptedException）
        /*
        在Java中，不能在try语句主体中直接抛出InterruptedException异常。这是因为lock.lock()方法会在线程被中断时抛出InterruptedException异常，
        而InterruptedException是一个已检查异常，必须进行显式的处理。

        为了正确处理InterruptedException异常，你需要使用try-catch语句包裹调用lock.lock()的代码块，
        并在catch块中进行相应的处理。例如，你可以在catch块中设置标志位来记录线程被中断的状态，并在适当的时候结束线程的执行。

        lock.lockInterruptibly()方法会响应线程的中断，并在被中断时抛出InterruptedException异常
        */
        lock.lock();
        Thread t1 = new Thread(() -> {
            try {

                log.debug("尝试获取锁中 ...");
                // 如果没有竞争就获取lock对象锁
                // 如果有竞争就进入阻塞队列等待，可以被其他线程interrupt 方法打断
                lock.lockInterruptibly();


            } catch (InterruptedException e) {
                log.debug("被打断了，没有获得锁中 ...");
                e.printStackTrace();
            } finally {
                // 想起一个注意点：finally块中的return语句会覆盖任何在try块或catch块中的返回值，因为会被finally覆盖
                log.debug("解锁中 ...");
                lock.unlock();
            }
        }, "t1");
        t1.start();

        sleep(2000);
        log.debug("打断t1线程操作...");
        t1.interrupt(); // 停止无限期的等待
    }
}