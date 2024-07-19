package org.codenotknock.juc3_thread;

import lombok.extern.slf4j.Slf4j;


/**
 * 创建线程方法二：使用或继承Runnable 配合Thread
 * 把 线程Thread 和 任务Runnable 分开
 */
@Slf4j(topic = "c.RunnableDemo2_Runnable")
public class RunnableDemo2 {
    /*
        15:22:29 [RunnableTread_one] c.RunnableDemo2_Runnable - Thread[RunnableTread_one,5,main]执行任务...
        15:22:29 [main] c.RunnableDemo2_Runnable - Thread[main,5,main]执行任务...
     */
    public static void main(String[] args) {

        // 创建任务
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                log.debug(Thread.currentThread() + "执行任务...");  // RunnableTread_one 线程所运行
            }
        };
        // 创建线程对象
        Thread t = new Thread(runnable, "RunnableTread_one");
        // 启动线程
        t.start();
        log.debug(Thread.currentThread() + "执行任务...");  // main 主线程所运行
    }

}
