package org.codenotknock.juc4_3_synchronized;

import lombok.extern.slf4j.Slf4j;
import org.openjdk.jol.info.ClassLayout;

/**
 * 测试偏向锁的膨胀为轻量级锁
 * 偏向锁 -> 无偏向锁
 * 多个线程使用一个对象加锁，但是没有竞争
 *
 * 注意：先添加虚拟机参数：-XX:BiasedLockingStartupDelay=0
 */

@Slf4j(topic = "c.Biased2")
public class BiasedDemo4 {
    public static void main(String[] args) {
        Bride bride = new Bride();

        new Thread(()->{
            // 0x0000000000000005    101   偏向锁
            log.debug(ClassLayout.parseInstance(bride).toPrintable());
            synchronized (bride) {
                // 0x000002485aa08005   101  偏向锁：偏向于此ThreadID的线程
                log.debug(ClassLayout.parseInstance(bride).toPrintable());
            }
            // 0x000002485aa08005    101  偏向锁：偏向于此ThreadID的线程
            log.debug(ClassLayout.parseInstance(bride).toPrintable());

            synchronized (BiasedDemo4.class){
                BiasedDemo4.class.notify();
            }
        }, "t1").start();

        new Thread(()->{

            synchronized (BiasedDemo4.class){
                try {
                    BiasedDemo4.class.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            // 0x000002485aa08005    101  偏向锁：偏向于t1的线程
            log.debug(ClassLayout.parseInstance(bride).toPrintable());
            synchronized (bride) {
                // 0x0000009ce1afee30   00   升级为了轻量级锁
                log.debug(ClassLayout.parseInstance(bride).toPrintable());
            }
            // 0x0000000000000001    001   解锁后：无偏向锁了，正常对象
            log.debug(ClassLayout.parseInstance(bride).toPrintable());
        }, "t2").start();

    }
}

class Bride {

}
