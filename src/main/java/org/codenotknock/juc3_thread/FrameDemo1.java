package org.codenotknock.juc3_thread;

/**
 * 单线程测试
 * 栈帧：一个方法一个栈帧，每个栈帧对应一次方法的调用
 * 只能有一个活动栈帧
 * 通过debug观察栈帧变化可以知道栈帧是先进后出的且每个栈帧中都存储了局部变量
 */

public class FrameDemo1 {
    public static void main(String[] args) {
        method1(9);
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
