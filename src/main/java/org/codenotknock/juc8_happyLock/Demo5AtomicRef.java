package org.codenotknock.juc8_happyLock;

import java.util.concurrent.atomic.AtomicReference;

/**
 * @author xiaofu
 * 原子引用类型
 *  AtomicReference、AtomicMarkableReference、AtomicStampedReference
 *  CAS经典问题：ABA，是察觉不到被修改过的
 *  解决办法：加上id或版本号 用以区分
 */
public class Demo5AtomicRef {
    static AtomicReference<String> ref = new AtomicReference<>("A");
    public static void main(String[] args) throws InterruptedException {
        String prev = ref.get();
        aba();
        Thread.sleep(20);
        ref.compareAndSet(ref.get(),"C");
        System.out.println("MAIN   A -> C");
    }
    private static void aba() throws InterruptedException {
        new Thread(() -> {
            ref.compareAndSet(ref.get(),"B");
            System.out.println("A -> B");
        }).start();
        Thread.sleep(5);
        new Thread(() -> {
            ref.compareAndSet(ref.get(),"A");
            System.out.println("B -> A");
        }).start();
    }
}
