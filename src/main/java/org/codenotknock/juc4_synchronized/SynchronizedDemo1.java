package org.codenotknock.juc4_synchronized;


import lombok.extern.slf4j.Slf4j;

@Slf4j(topic = "c.Synchronized_one")
public class SynchronizedDemo1 {
    private static int cnt = 0;

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 5000; i++) cnt++;
        }, "t1");

        Thread t2 = new Thread(() -> {
            for (int i = 0; i < 5000; i++) cnt--;
        }, "t2");

        t1.start();
        t2.start();
        t1.join();
        t2.join();
        log.debug("最终结果："+cnt);
        // 无Synchronized修饰，时间片运行完后、指令乱序，最终结果很难为0

    }

}
