package org.codenotknock.juc_method;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.locks.LockSupport;


/**
 * @author xiaofu
 * LockSupport 类中的静态方法 park/unPark方法
 * 暂停当前线程、恢复某个线程的运行
 *
 * park对象内部
 * counter、_mutex 互斥锁、 _cond
 * 如果先调用unPark 再调用park 此时线程无需进入_cond 中阻塞，直接运行
 */

@Slf4j(topic = "c.method_park")
public class Demo1Park {
    public static void main(String[] args) throws InterruptedException {
        TestPark t1 = new TestPark("t1");
        t1.start();
        LockSupport.unpark(t1);

    }

}

@Slf4j(topic = "c.method_park")
class TestPark extends Thread {
    public TestPark(String name) {
        super(name);
    }
    @SneakyThrows
    @Override
    public void run() {
        log.debug(Thread.currentThread().getName() + " start ...");
        sleep(2000);

        log.debug("park Test...");
        LockSupport.park();
        LockSupport.park();

        log.debug("unPark Test...");
    }
}

