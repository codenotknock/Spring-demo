package org.codenotknock.juc7_JMM;

import lombok.extern.slf4j.Slf4j;

/**
 * @author xiaofu
 * 设计模式-两阶段终止模式
 * volatile 优雅退出
 *
 * 但是问题是：同时多个 start 就创建了多个监控线程！！
 */

@Slf4j(topic = "c.Strong_Style")
public class Demo2Style {
    // 监控线程
    private  Thread monitorThread;

    // 是否继续，volatile 保证stop线程和start线程 stop变量的可见性
    private volatile boolean stop = false;

    // 启动监控线程
    public void start() {
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
        Demo2Style demo2Style = new Demo2Style();
        demo2Style.start();
        Thread.sleep(3500);
        log.debug("stop");
        demo2Style.stop();

    }



}
