package org.codenotknock.juc3_thread;

import lombok.extern.slf4j.Slf4j;

/**
 * 创建线程方法一：直接使用Thread或继承Thread
 */

@Slf4j(topic = "c.ThreadDemo1_Thread")
public class ThreadDemo1 {

    /* 结果
        15:11:14 [main] c.ThreadDemo1_Thread - Thread[main,5,main]执行任务...
        15:11:14 [one] c.ThreadDemo1_Thread - Thread[one,5,main]执行任务...
     */
    public static void main(String[] args) {
        Thread t1 = new Thread("one"){
            @Override
            public void run() {
                log.debug(Thread.currentThread() + "执行任务...");  // one 线程所运行
            }
        };
        t1.start();
        log.debug(Thread.currentThread() + "执行任务...");  // main 主线程所运行
    }
}
