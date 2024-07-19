package org.codenotknock.juc8_happyLock;

import java.util.concurrent.atomic.AtomicMarkableReference;
import java.util.concurrent.atomic.AtomicStampedReference;

/**
 * @author xiaofu
 * 原子引用类型
 *  AtomicReference、AtomicMarkableReference、AtomicStampedReference
 *
 *    使用 AtomicMarkableReference  存储了一个引用对象和一个布尔型标记值。
 *          在进行更新操作时，如果当前的标记值与期望的标记值相同，那么才会进行更新操作。此外，更新操作还会同时修改标记值。
 */
public class Demo7AtomicRef {

    public static void main(String[] args) throws InterruptedException {
        Bag bag = new Bag("空的");
        AtomicMarkableReference<Bag> ref = new AtomicMarkableReference<>(bag, true);

        Bag prev = ref.getReference();
        System.out.println(prev);
        Thread.sleep(25);

        // 标记状态和期待状态相同：会进行更新操作
        boolean res = ref.compareAndSet(prev, new Bag("空的"), true, false);
        System.out.println("背包满了吗："+ ref.getReference() + "   " + res );


    }
}

class Bag {
    private String status;
    Bag(String status) {
        this.status = status;
    }
}