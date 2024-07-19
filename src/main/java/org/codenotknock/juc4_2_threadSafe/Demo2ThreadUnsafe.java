package org.codenotknock.juc4_2_threadSafe;

import java.util.ArrayList;

/**
 * 成员变量的线程安全问题
 * 将list修改为局部变量
 */

public class Demo2ThreadUnsafe {


    static final int THREAD_NUMBER = 2;
    static final int LOOP_NUMBER = 200;
    public static void main(String[] args) {
        /* 为什么将list修改为局部变量就安全了呢？
            list 是局部变量，每个线程调用时会创建其不同实例，没有共享
            而 method2 的参数是从 method1 中传递过来的，与 method1 中引用同一个对象
            method3 的参数分析与 method2 相同
         */
        Demo1ThreadUnsafe test = new Demo1ThreadUnsafe();
        for (int i = 0; i < THREAD_NUMBER; i++) {
            // 在 method1 方法内部创建了一个局部变量 list，而不是作为成员变量。每次进入 method1 方法时，
            // 都会创建一个新的 ArrayList 对象，使得每个线程都拥有独立的 list 对象。避免了多线程并发操作同一个对象的竞态条件
            new Thread(() -> {
                test.method1(LOOP_NUMBER);
            }, "t" + i).start();
        }
    }
    public void method1(int loopNumber) {
        ArrayList<String> list = new ArrayList<>();
        for (int i = 0; i < loopNumber; i++) {
            // 临界区, 会产生竞态条件
            method2(list);
            method3(list);
        }
    }
    private void method2(ArrayList<String> list) {
        list.add("1");
    }
    private void method3(ArrayList<String> list) {
        list.remove(0);
    }

}
