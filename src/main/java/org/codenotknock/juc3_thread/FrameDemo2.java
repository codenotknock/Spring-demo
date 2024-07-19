package org.codenotknock.juc3_thread;

/**
 * 多线程测试：线程的栈内存是独立的，互不干扰
 * 虚拟机栈是线程私有的，故栈帧也是线程私有的，每个线程都有自己的虚拟机栈和栈帧空间，通过debug也能看出
 */
public class FrameDemo2 {
    public static void main(String[] args) {
        new Thread("one") {
            @Override
            public void run() {
                method1(9);
            }
        }.start();

        method1(99);
    }
    private static void method1(int x) {
        int y = x + 1;
        Object m = method2();
        System.out.println(m);
    }
    private static Object method2(){
        Object o = new Object();
        return o;
    }
}
