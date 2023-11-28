package org.codenotknock.juc3_thread;

import lombok.extern.slf4j.Slf4j;

/**
 * 多个线程交替执行
 */

@Slf4j(topic = "c.MultiThread")
public class ThreadMultiDemo4 {
    public static void main(String[] args) {
        new ThreadDemo().start();
        new ThreadDemo().start();
        new ThreadDemo().start();
    }
}

@Slf4j(topic = "c.MultiThread")
class ThreadDemo extends Thread {
    @Override
    public void run() {
        while (true) {
            log.debug(Thread.currentThread() + " running ...");
        }
    }
}
