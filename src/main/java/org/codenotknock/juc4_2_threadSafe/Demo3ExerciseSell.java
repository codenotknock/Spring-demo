package org.codenotknock.juc4_2_threadSafe;

import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Random;
import java.util.Vector;

/**
 * 卖票
 */

@Slf4j(topic = "c.ExerciseSell")
public class Demo3ExerciseSell {
    // 模拟多人买票

    private static Random random = new Random();
    private static int randomAmount() {
        return random.nextInt(5)+1;
    }

    public static void main(String[] args) {
        TicketWindow window = new TicketWindow(10000);
        // 这里的vector 很妙，线程安全的
        List<Integer> amountList = new Vector<>();
        List<Thread> threadList = new Vector<>();


        for (int i = 0; i < 4000; i++) {
            Thread thread = new Thread(() -> {
                // 买票
                int n = window.sell(randomAmount());
                amountList.add(n);
            }, "t" + i);
            threadList.add(thread);
            thread.start();
        }
        // 如果线程是安全的则 余票+卖出票=总票数
        threadList.forEach(thread -> {
            try {
                thread.join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

        log.debug("卖出的票：{}", amountList.stream().mapToInt(i -> i).sum());
        log.debug("余票：{}", window.getCnt());
        log.debug("总票数：{}", window.getCnt()+amountList.stream().mapToInt(i -> i).sum());
    /* 线程是不安全
        19:27:07 [main] c.ExerciseSell - 卖出的票：10001
        19:27:07 [main] c.ExerciseSell - 余票：0
        19:27:07 [main] c.ExerciseSell - 总票数：10001
     */
    }

}

class TicketWindow{
    private int cnt;
    public TicketWindow (int cnt) {
        this.cnt = cnt;
    }
    // 获取余票数量
    public int getCnt() {
        return cnt;
    }
    public int sell(int amount) {
        if (this.cnt >= amount) {
            cnt -=  amount;
            return amount;
        } else {
            return 0;
        }
    }
}