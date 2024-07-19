package org.codenotknock.juc8_happyLock;

import java.util.concurrent.atomic.AtomicInteger;

import static org.codenotknock.juc2.util.Sleeper.sleep;

/**
 * @author xiaofu
 * 用CAS的方式实现锁：仅实验，不能用于实际项目
 */

public class Demo0Lock_CAS {
    private AtomicInteger state = new AtomicInteger(0);
    public void lock() {
        while (true) {
            if (state.compareAndSet(0, 1)) {
                break;
            }
        }
    }
    public void unlock() {
        System.out.println("unlock...");
        state.set(0);
    }


    public static void main(String[] args) {
        Demo0Lock_CAS lock = new Demo0Lock_CAS();
        new Thread(() -> {
            System.out.println("begin...");
            lock.lock();
            try {
                System.out.println("lock...");
                sleep(1);
            }
            finally {
                lock.unlock();
            }
        }).start();
        new Thread(() -> {
            System.out.println("begin...");
            lock.lock();
            try {
                System.out.println("lock...");
            }
            finally {
                lock.unlock();
            }
        }).start();
    }
}
