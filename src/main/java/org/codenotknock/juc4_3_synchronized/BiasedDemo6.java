package org.codenotknock.juc4_3_synchronized;

import lombok.extern.slf4j.Slf4j;
import org.openjdk.jol.info.ClassLayout;

import java.util.Vector;


/**
 * @author xiaofu
 * 批量重偏向：虽然内多个线程访问但是没有竞争，这是偏向a线程的对象仍有机会偏向b线程
 * 撤销偏向锁阈值20次，会给对象加锁时重新偏向
 * 实验注意：添加虚拟机参数：-XX:BiasedLockingStartupDelay=0
 *
 * 批量撤销
 * 撤销偏向锁阈值超过 40 次后，jvm 会这样觉得，自己确 实偏向错了，根本就不该偏向。
 * 于是整个类的所有对象都会变为不可偏向的，新建的对象也是不可偏向的
 */

@Slf4j(topic = "c.Biased4")
public class BiasedDemo6 {
    public static void main(String[] args) {
        Vector <BigDog> list = new Vector<>();
        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 25; i++) {
                BigDog dog = new BigDog();
                list.add(dog);
                synchronized (dog) {
                    log.debug(ClassLayout.parseInstance(dog).toPrintable());
                }
            }
            synchronized (list) {
                list.notify();
            }
        }, "t1");
        t1.start();


        Thread t2 = new Thread(() -> {
            synchronized (list) {
                try {
                    list.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            log.debug("==================");
            for (int i = 0; i < 25; i++) {
                BigDog dog = list.get(i);
                log.debug(i + "/t" + ClassLayout.parseInstance(dog).toPrintable());
                // 此前是偏向于t1线程的，就涉及到偏向锁的撤销 ——> 轻量级锁   撤销是比较耗费性能的
                // 撤销次数阈值默认20，会批量重新偏向到t2线程   【批量执行的，优化性能】
                // 结果是前19个都是轻量级锁00   后面的是偏向锁101
                synchronized (dog) {
                    log.debug(i + "/t" + ClassLayout.parseInstance(dog).toPrintable());
                }
            }
        }, "t2");
        t2.start();

    }


}
class BigDog{

}