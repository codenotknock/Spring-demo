package org.codenotknock.juc7_JMM;

import lombok.extern.slf4j.Slf4j;

/**
 * @author xiaofu
 * 设计模式-两阶段终止模式
 * 犹豫模式 Balking：模式用在一个线程发现另一个线程或本线程已经做了某一件相同的事，那么本线程就无需再做
 * 了，直接结束返回
 * 【如果线程已经创建过，就不需要再次创建了】  synchronized 保证线程的安全性，防止多个线程同时执行
 *  问题：线程停止了怎么办呢？  设置start 为 false
 */

@Slf4j(topic = "c.Strong_Style3")
public class Demo4Style {
    // 监控线程
    private  Thread monitorThread;

    // 这里为什么又用volatile呢，保证可见性
    private  volatile boolean stop = false;

    private  volatile boolean starting = false;

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
            while (!stop) {
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
            starting = false;
            log.info("监控线程已停止...");
        }, "monitor");
        stop = false;
        log.info("监控线程已启动...");
        monitorThread.start();

    }

    // 停止监控线程
    public void stop() {
        stop = true;
        monitorThread.interrupt();
    }

    public static void main(String[] args) throws InterruptedException {
        Demo4Style demo2Style = new Demo4Style();
        demo2Style.start();
        demo2Style.start();

        Thread.sleep(3500);
        log.debug("stop");
        demo2Style.stop();

    }



}
