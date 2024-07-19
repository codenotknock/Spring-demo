package org.codenotknock.juc6_ReentrantLock;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

import static java.lang.Thread.sleep;

/**
 * @author xiaofu
 * 模拟一下：ReentrantLock 可设置超时时间
 * trylock方法：非阻塞式地尝试获取锁。tryLock()方法会立即尝试获取锁，如果锁当前没有被其他线程持有，则获取成功并返回true，否则返回false
 * tryLock()方法有多个重载形式：
 *  - tryLock(): 尝试获取锁，如果成功返回true，否则返回false。
 *  - tryLock(long time, TimeUnit unit) throws InterruptedException: 在指定的时间范围内尝试获取锁，如果成功返回true，否则抛出InterruptedException异常。参数time表示等待时间
 */

@Slf4j(topic = "c.ReentrantLock3")
public class Demo3ReentrantLock {
    private static ReentrantLock lock = new ReentrantLock();

    public static void main(String[] args) throws InterruptedException {
        lock.lock();
        Thread t1 = new Thread(() -> {
            log.debug("尝试获取锁中 ...");
//            if (!lock.tryLock()) {
            try {
                if (!lock.tryLock(3, TimeUnit.SECONDS)) {
                    log.debug("获取锁失败 ...");
                    return;
                }
            } catch (InterruptedException e) {
                log.debug("没有得到锁，线程被打断了 ...");
                e.printStackTrace();
            }
            try {
                log.debug("获取锁成功 ...");

            } finally {
                // 想起一个注意点：finally块中的return语句会覆盖任何在try块或catch块中的返回值，因为会被finally覆盖
                log.debug("解锁中 ...");
                lock.unlock();
            }
        }, "t1");
        t1.start();

        sleep(2000);
        lock.unlock();
    }
}