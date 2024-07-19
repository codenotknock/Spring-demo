package org.codenotknock.juc4_2_threadSafe;

import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Random;
import java.util.Vector;

/**
 * 转账
 */

@Slf4j(topic = "c.ExerciseTransfer")
public class Demo4ExerciseTransfer {
    // 模拟2人多次转账

    private static Random random = new Random();
    private static int randomAmount() {
        return random.nextInt(100)+1;
    }

    public static void main(String[] args) throws InterruptedException {
        Account a = new Account(10000);
        Account b = new Account(10000);

        new Thread(() -> {
            for (int i = 0; i < 1000; i++) {
                a.transfer(b, randomAmount());
            }
        }, "a").start();
        new Thread(() -> {
            for (int i = 0; i < 1000; i++) {
                b.transfer(a, randomAmount());
            }
        }, "b").start();

        Thread.sleep(1000);

        log.debug("a账户：{}", a.getMoney());
        log.debug("b账户：{}", b.getMoney());
        log.debug("总额：{}", a.getMoney() + b.getMoney());

        /* 线程不安全
            21:38:05 [main] c.ExerciseTransfer - a账户：28826
            21:38:05 [main] c.ExerciseTransfer - b账户：0
            21:38:05 [main] c.ExerciseTransfer - 总额：28826

         */
    }

}

class Account{
    private int money;
    public Account (int money) {
        this.money = money;
    }
    // 获取余票数量
    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public void transfer(Account target, int amount) {
        if (this.money >= amount) {
            this.setMoney(this.getMoney() - amount);
            target.setMoney(target.getMoney() + amount);
        }
    }
}