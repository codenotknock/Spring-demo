package org.codenotknock.juc6_ReentrantLock;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @author xiaofu
 * 模拟一下：ReentrantLock 是可重入的锁
 * ReentrantLock 和 synchronized 区别
 * ● ReentrantLock 可中断、超时等待、支持多条件变量；
 * ● ReentrantLock 可以设置公平锁/非公平锁；synchronized 是非公平锁
 * ● ReentrantLock 需要手动获取/释放锁；synchronized 是自动的
 * ● ReentrantLock、synchronized 都是可重入锁
 */

@Slf4j(topic = "c.ReentrantLock1")
public class Demo1ReentrantLock {
    private static ReentrantLock lock = new ReentrantLock();

    public static void main(String[] args) {
//        模拟一下：ReentrantLock 是可重入的锁
        // lock 对象可以看做一个锁对象，类似与 monitor 对象
        lock.lock();
        try{
            log.debug("enter main...");
            m1();

        } finally {
            lock.unlock();
        }

    }
    public static void m1() {
        lock.lock();
        try{
            log.debug("enter m1...");
            m2();
        } finally {
            lock.unlock();
        }
    }
    public static void m2() {
        lock.lock();
        try{
            log.debug("enter m2...");

        } finally {
            lock.unlock();
        }
    }

}
