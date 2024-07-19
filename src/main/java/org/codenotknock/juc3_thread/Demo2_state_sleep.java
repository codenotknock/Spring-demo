package org.codenotknock.juc3_thread;

import lombok.extern.slf4j.Slf4j;

/**
 * state 方法 获取线程状态信息
 * 状态分别为： NEW, RUNNABLE, BLOCKED, WAITING, TIMED_WAITING, TERMINATED
 * 新建（New）：当线程对象被创建后，它就处于新建状态。此时线程对象已经在内存中，但尚未启动，还没有分配到CPU时间。
 *
 * 运行（Runnable）：线程获取了CPU时间，正在执行代码，或者处于就绪队列中等待获取 CPU 时间。处于运行状态的线程可能因为时间片用完或者调用了阻塞操作而转为就绪状态。
 *
 * 阻塞（Blocked）：线程因为某些原因暂时放弃 CPU 使用权，处于阻塞状态。可能是在等待某个资源（如 I/O 操作、锁），或者调用了 sleep() 方法。当等待的条件满足时，线程会转为就绪状态。
 *
 * 无限期等待（Waiting）：线程进入等待状态，等待其他线程显式地唤醒，如调用 wait() 方法。只有其他线程的通知或中断才能让线程从等待状态转为就绪状态。
 *
 * 有限期等待（Timed Waiting）：与无限期等待类似，不过有一个等待时间，在时间到达或者收到通知时会转为就绪状态。
 *
 * 终止（Terminated）：线程执行完毕或者因异常退出后，进入终止状态。处于终止状态的线程不可再次启动。
 *
 *
 * sleep 方法 线程睡眠n毫秒 ，可能会被打断interruption
 */

@Slf4j(topic = "c.method_state")
public class Demo2_state_sleep {
    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread("one") {
            @Override
            public void run() {
                log.debug(Thread.currentThread() + " running ... ");
            }
        };
        log.debug(Thread.currentThread() + " running ... " + t1.getState());    // NEW

        t1.start();

        log.debug(Thread.currentThread() + " running ... " + t1.getState());    // RUNNABLE
        Thread.sleep(200);
        log.debug(Thread.currentThread() + " running ... " + t1.getState());    // TERMINATED
    }
}
