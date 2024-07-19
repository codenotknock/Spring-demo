package org.codenotknock.juc8_happyLock;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @author xiaofu
 * AtomicInteger、AtomicLong、AtomicBoolean
 */

public class Demo2AtomicCon {
    public static void main(String[] args) {
        // private volatile int value;
        AtomicInteger atomicInteger = new AtomicInteger(0);
        // private volatile long value;
        AtomicLong atomicLong = new AtomicLong(1);
        // private volatile int value;
        AtomicBoolean atomicBoolean = new AtomicBoolean(true);

        // 保证 ++ 和 获取 这两步为原子操作
        System.out.println(atomicInteger.incrementAndGet());    // ++i 1
        System.out.println(atomicInteger.getAndIncrement());    // i++ 1
        System.out.println(atomicInteger.get());    // 2

        System.out.println(atomicInteger.getAndAdd(9)); // 2
        System.out.println(atomicInteger.addAndGet(9)); // 20

        System.out.println(atomicInteger.updateAndGet(a -> a * 10)); // 200


    }
}
