package org.codenotknock.juc3_thread;

import lombok.extern.slf4j.Slf4j;
import org.codenotknock.juc2.Cons;
import org.codenotknock.juc2.util.FileReader;

/**
 * start 方法 启动线程
 * run 方法 线程执行的任务内容
 */
@Slf4j(topic = "c.method_start")
public class Demo1_start_run {
    public static void main(String[] args) {
        Thread t1 = new Thread("one"){
            @Override
            public void run() {
                FileReader.read(Cons.Path1);
                log.debug(Thread.currentThread() + " running ... ");
            }
        };
//        t1.run();
        /*
            直接run有什么影响呢？
           会由main方法执行run方法，并不会启动新的子线程。相当于一个执行了普通的方法
         */
        t1.start();

        log.debug(Thread.currentThread() + " running ... ");
    }
}
