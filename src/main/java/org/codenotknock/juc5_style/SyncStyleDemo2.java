package org.codenotknock.juc5_style;

import lombok.extern.slf4j.Slf4j;

/**
 * @author xiaofu
 * 同步模式之保护性暂停
 * Guarded Suspension，  用在一个线程等待另一个线程的执行结果
 * 加强版：超时参数
 */

@Slf4j(topic = "c.SyncStyle_Guarded2")
public class SyncStyleDemo2 {
    public static void main(String[] args) {
        // 线程一等待线程二的结果...
        GuardedObject2 guardedObject = new GuardedObject2();

        new Thread(() -> {
//            Object o = guardedObject.get(3000L);
            Object o = guardedObject.get(6000L);
            log.debug("{}线程得到结果{}", Thread.currentThread().getName(),o);
        }, "t1").start();

        new Thread(() -> {
            try {
                log.debug("{}线程产生结果中...", Thread.currentThread().getName());
                Thread.sleep(5000);
                guardedObject.complete("66666666");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "t2").start();
    }
}

class GuardedObject2 {
    // 结果
    private Object response;

    // 获取结果：添加一个超时参数，最多等待多久
    public Object get(Long timeOut) {
        // 开始时间
        long begin = System.currentTimeMillis();
        // 经历时间
        long passTime = 0;
        synchronized(this) {
            // 没有结果：被唤醒后如果没有结果还是等待
            while (response == null) {
                try {
                    this.wait(timeOut - passTime);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                passTime = System.currentTimeMillis() - begin;
                if (passTime >= timeOut) {
                    break;
                }
            }
        }
        return response;
    }

    // 产生结果
    public void complete(Object response) {
        synchronized (this) {
            this.response = response;
            this.notifyAll();
        }
    }

}