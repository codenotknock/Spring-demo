package org.codenotknock.juc6_Lock;

import lombok.extern.slf4j.Slf4j;

/**
 * @author xiaofu
 * 经典问题：哲学家就餐问题
 * 五位哲学家，围坐在圆桌旁。
 * ● 他们只做两件事，思考和吃饭，思考一会吃口饭，吃完饭后接着思考。
 * ● 吃饭时要用两根筷子吃，桌上共有 5 根筷子，每位哲学家左右手边各有一根筷子。
 * ● 如果筷子被身边的人拿着，自己就得等待
 */

@Slf4j(topic = "c.DeadLock_Dining-Philosophers")
public class Demo3DeadLock_code {
    public static void main(String[] args) {
        Chopstick c1 = new Chopstick("1");
        Chopstick c2 = new Chopstick("2");
        Chopstick c3 = new Chopstick("3");
        Chopstick c4 = new Chopstick("4");
        Chopstick c5 = new Chopstick("5");

        new Philosopher("苏格拉底", c1, c2).start();
        new Philosopher("柏拉图", c2, c3).start();
        new Philosopher("亚里士多德", c3, c4).start();
        new Philosopher("赫拉克利特", c4, c5).start();
        new Philosopher("阿基米德", c5, c1).start();

    }
}

@Slf4j(topic = "c.Philosopher")
class Philosopher extends Thread {
    Chopstick left;
    Chopstick right;

    public Philosopher(String name, Chopstick left, Chopstick right) {
        super(name);
        this.left = left;
        this.right = right;
    }

    @Override
    public void run() {
        while (true) {
            synchronized (left) {
                synchronized (right) {
                    try {
                        eat();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    private void eat() throws InterruptedException {
        log.debug("eating ...");
        sleep(20);
    }
}

class Chopstick {
    String name;
    public Chopstick(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Chopstick{" +
                "name='" + name + '\'' +
                '}';
    }
}
