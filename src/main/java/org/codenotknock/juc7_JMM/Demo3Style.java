package org.codenotknock.juc7_JMM;

import lombok.extern.slf4j.Slf4j;

/**
 * @author xiaofu
 * 设计模式-两阶段终止模式
 * 犹豫模式 Balking：模式用在一个线程发现另一个线程或本线程已经做了某一件相同的事，那么本线程就无需再做
 * 了，直接结束返回
 * 【如果线程已经创建过，就不需要再次创建了】  synchronized 保证线程的安全性，防止多个线程同时执行
 *  问题：线程停止了怎么办呢？
 */

@Slf4j(topic = "c.Strong_Style2")
public class Demo3Style {
    // 监控线程
    private  Thread monitorThread;

    // 是否继续，volatile 保证stop线程和start线程 stop变量的可见性
    private  boolean stop = false;
    private  boolean starting = false;

    // 启动监控线程
    public void start() {
        // synchronized 代码块越多，上锁时间越长，尽可能既能保证安全性又锁较少的代码，提高并发度和性能
        synchronized (this) {
            if (starting) {
                return;
            }
            starting = true;
        }

        monitorThread = new Thread(() -> {
            while (true) {
                Thread current = Thread.currentThread();
                // 是否被打断
                if (stop) {
                    log.debug("料理后事");
                    break;
                }
                try {
                    Thread.sleep(1000);
                    log.debug("执行监控记录");
                } catch (InterruptedException e) {
                    // sleep 异常后会清除打断标记，重置标记
//                    current.interrupt();
                    e.printStackTrace();
                }
            }
        }, "monitor");
        monitorThread.start();

    }

    // 停止监控线程
    public void stop() {
        stop = true;
        monitorThread.interrupt();
    }

    public static void main(String[] args) throws InterruptedException {
        Demo3Style demo2Style = new Demo3Style();
        demo2Style.start();
        demo2Style.start();

        Thread.sleep(3500);
        log.debug("stop");
        demo2Style.stop();

    }



}
