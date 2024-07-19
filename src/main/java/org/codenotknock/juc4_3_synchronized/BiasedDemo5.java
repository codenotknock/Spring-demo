package org.codenotknock.juc4_3_synchronized;

import lombok.extern.slf4j.Slf4j;
import org.openjdk.jol.info.ClassLayout;

/**
 * 撤销偏向锁 - 调用 wait/notify
 * wait/notify 只有重量级锁有
 * 实验注意：使用参数 -XX:BiasedLockingStartupDelay=0
 *
 *
 */

@Slf4j(topic = "c.Biased3")
public class BiasedDemo5 {
    public static void main(String[] args) throws InterruptedException {
        Dog dog = new Dog();

        synchronized (dog) {
            // 0x000001edd32b3005 偏向锁 101
            log.debug(ClassLayout.parseInstance(dog).toPrintable());
            dog.wait(100);
            // 0x000001ededf081ca 重量级锁 10
            log.debug(ClassLayout.parseInstance(dog).toPrintable());
        }





    }
}

class Dog {

}
