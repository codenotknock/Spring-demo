package org.codenotknock.juc8_happyLock;

import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.atomic.AtomicStampedReference;

/**
 * @author xiaofu
 * 原子引用类型
 *  AtomicReference、AtomicMarkableReference、AtomicStampedReference
 *  CAS经典问题：ABA，是察觉不到被修改过的
 *  解决办法：加上id或版本号 用以区分;  使用AtomicStampedReference
 */
public class Demo6AtomicRef {
    static AtomicStampedReference<String> ref = new AtomicStampedReference<>("A", 0);
    public static void main(String[] args) throws InterruptedException {
        String prev = ref.getReference();
        int stamp = ref.getStamp();
        aba();
        Thread.sleep(20);
        // 这一次会失败，虽然值相同，但是版本号不同
        boolean res = ref.compareAndSet(prev, "C", stamp, stamp + 1);
        System.out.println("MAIN   A -> C    " + res);
    }
    private static void aba() throws InterruptedException {
        new Thread(() -> {
            int stamp = ref.getStamp();
            boolean res = ref.compareAndSet(ref.getReference(),"B", stamp, stamp + 1);
            System.out.println("A -> B    " + res);
        }).start();
        Thread.sleep(5);
        new Thread(() -> {
            int stamp = ref.getStamp();
            boolean res = ref.compareAndSet(ref.getReference(),"A", stamp, stamp + 1);
            System.out.println("B -> A    " + res);
        }).start();
    }
}
