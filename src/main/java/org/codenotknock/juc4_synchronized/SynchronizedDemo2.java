package org.codenotknock.juc4_synchronized;

import lombok.extern.slf4j.Slf4j;

@Slf4j(topic = "c.Synchronized_two")
public class SynchronizedDemo2 {
    private static int cnt = 0;
    private static Object lock = new Object();

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(() -> {
            synchronized (lock) {
                for (int i = 0; i < 5000; i++) cnt++;
            }
        }, "t1");

        Thread t2 = new Thread(() -> {
            synchronized (lock) {
                for (int i = 0; i < 5000; i++) cnt--;
            }
        }, "t2");

        t1.start();
        t2.start();
        t1.join();
        t2.join();
        log.debug("最终结果："+cnt);
        // 最终结果为0，用synchronized获取同一个对象锁从而到达同步，保证了临界区内代码的原子性


        /*
        如果把 synchronized(obj) 放在 for 循环的外面，如何理解？-- 原子性
            结果对，放在里面和外面都能保证cnt++或cnt--的原子性
        如果 t1  synchronized(obj1) 而 t2 synchronized(obj2) 会怎样运作？-- 锁对象
            结果不对，两个对象相当于两个房间，各不干扰
        如果 t1  synchronized(obj) 而 t2 没有加会怎么样？如何理解？-- 锁对象
            结果不对，因为需要同一个对象锁才能保证临界区的原子性
         */
    }

}
