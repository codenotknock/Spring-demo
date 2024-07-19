package org.codenotknock.juc4_3_synchronized;

import lombok.extern.slf4j.Slf4j;
import org.openjdk.jol.info.ClassLayout;

import static java.lang.Thread.sleep;

/**
 * 详解偏向及为什么调用hashCode会撤销偏向锁的原因
 * 偏向锁：将ThreadID 替换到 markWord 中，ID是自己的不用进行CAS操作，减少了CAS操作
 * 利用jol工具 进行解析对象头
 * -XX:BiasedLockingStartupDelay=0  设置偏向锁不延迟
 * JVM启动时并不会立即启用偏向锁，而是需要触发一定次数的计数器累加后才会启用偏向锁。
 * 因此，在程序运行初期可能无法观察到对象偏向锁状态的变化
 * -XX:-UseBiasedLocking  禁用偏向锁，没有竞争采用的就是轻量级锁了00
 */


@Slf4j(topic = "c.Biased")
public class BiasedDemo3 {

    public static void main(String[] args) throws InterruptedException {
        Cat cat = new Cat();
        // 0x0000000000000001地址最后三位是001 正常状态  而偏向锁是最后三位101
        // 原因是偏向锁不会在程序启动后立即生效，而是延迟2、3秒后生效的
        // -XX:BiasedLockingStartupDelay=0  设置偏向锁不延迟
//        log.debug(ClassLayout.parseInstance(cat).toPrintable());

        sleep(3);
        Cat cat1 = new Cat();

        // 诡异的hashCode操作，调用hashCode操作会禁用这个对象的偏向锁
        // 调用后，会填充到MarkWord头中（调用前默认为0）
        // 为什么调用hashCode之后就会禁用掉偏向锁呢？【从对象头格式思考】
        // 因为存了hashCode后，偏向锁的格式就存不了了，没空间存储偏向锁格式了，转为正常对象
        cat1.hashCode();

        // 0x0x0000000000000005 换成二进制最后三位是 101 这时是偏向锁
        log.debug(ClassLayout.parseInstance(cat1).toPrintable());


        synchronized (cat1) {
            // 0x00000269b5ba6805 加锁状态，前54位被替换成了ThreadID（二进制）
            log.debug(ClassLayout.parseInstance(cat1).toPrintable());

            // 使用参数 -XX:-UseBiasedLocking  禁用偏向锁后
            // 未加锁状态 0x0000000000000001 正常状态
            // 0x0000000cba8ff240 加锁状态，最后2位为00 轻量级锁
            // 加锁顺序：优先 偏向锁 、 轻量级锁 、 重量级锁
        }

        // 注意：处于偏向锁的对象解锁后，线程 id 仍存储于对象头中
        // 0x00000269b5ba6805 解锁状态和加锁一致，保存了这个ThreadID，偏向于这个线程
        log.debug(ClassLayout.parseInstance(cat1).toPrintable());


    }
}

class Cat{
    public void a () {}

}
