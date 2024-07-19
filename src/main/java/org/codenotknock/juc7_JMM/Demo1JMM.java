package org.codenotknock.juc7_JMM;

import lombok.extern.slf4j.Slf4j;

import static java.lang.Thread.sleep;

/**
 * @author xiaofu
 * JMM 原子性、可见性、有序性
 * volatile保证的是可见性，不能保证原子性
 * synchronized 保证可见性、原子性
 * 有序性：jvm可能对指令做重排序以提高效率，但多线程下，指令重排序会导致预期错误
 * 禁用指令重排：volatile 修饰变量
 */

@Slf4j(topic = "c.Demo1JMM")
public class Demo1JMM {
    int num = 0;
    boolean ready = false;

    public static void main(String[] args) throws InterruptedException {
        Demo1JMM demo1JMM = new Demo1JMM();
        Res res = new Res();
        new Thread(() -> {
            demo1JMM.m2(res);
            log.debug("aaaa{}",res.r1);

        }).start();
        new Thread(() -> {
            demo1JMM.m1(res);
            log.debug("bbbb{}",res.r1);
        }).start();

        sleep(200);
        log.debug("res{}",res.r1);
        // res最终结果可能为0、1、18 [指令重排]
    }


    public void m1(Res res) {
        if (ready) {
            res.r1 = num + num;
        } else {
            res.r1 = 1;
        }
    }
    public void m2(Res res) {
        num = 9;
        ready = true;
    }


}


class Res {
    int r1;
}