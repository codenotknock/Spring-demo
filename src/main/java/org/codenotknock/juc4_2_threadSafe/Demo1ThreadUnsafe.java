package org.codenotknock.juc4_2_threadSafe;

import java.util.ArrayList;

/**
 * 成员变量的线程安全问题
 */

public class Demo1ThreadUnsafe {


    static final int THREAD_NUMBER = 2;
    static final int LOOP_NUMBER = 200;
    ArrayList<String> list = new ArrayList<>();
    public static void main(String[] args) {
        /* 都是先add 然后 remove，那么为什么报错呢?
         比如2个线程同时add，会导致add list的同一个位置，就会导致最终只添加了1个元素并不是2个
         但是remove的时候可能删除2个，就会导致list元素可能不足

         */
        Demo1ThreadUnsafe test = new Demo1ThreadUnsafe();
        for (int i = 0; i < THREAD_NUMBER; i++) {
            new Thread(() -> {
                test.method1(LOOP_NUMBER);
            }, "Thread" + i).start();
        }
    }
    public void method1(int loopNumber) {
        for (int i = 0; i < loopNumber; i++) {
            // 临界区, 会产生竞态条件
            method2();
            method3();
        }
    }
    private void method2() {
        list.add("1");
    }
    private void method3() {
        list.remove(0);
    }

}
