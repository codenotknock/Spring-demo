package org.codenotknock.juc4_synchronized;

import lombok.extern.slf4j.Slf4j;

/**
 * 用面向对象的方法，保证共享资源
 */
@Slf4j(topic = "c.Synchronized_three")
public class SynchronizedDemo3 {
    public static void main(String[] args) throws InterruptedException {
        PublicResource publicResource = new PublicResource();

        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 5000; i++) publicResource.inc();
        }, "t1");

        Thread t2 = new Thread(() -> {
            for (int i = 0; i < 5000; i++) publicResource.dec();
        }, "t2");

        t1.start();
        t2.start();
        t1.join();
        t2.join();
        log.debug("最终结果："+publicResource.getCnt());

    }

}

class PublicResource{
    private int cnt = 0;

    public void inc(){
        synchronized(this) {
            cnt++;
        }
    }
    public void dec(){
        synchronized (this) {
            cnt--;
        }
    }

    public int getCnt(){
        synchronized (this) {
            return cnt;
            // 这里为什么加锁呢？
            // 原因是为了保证获取到的是结果值，而不是中间值；其实也有点多余，因为用了join方法
        }
    }
}