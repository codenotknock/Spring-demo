package org.codenotknock.juc7_JMM;


/**
 * @author xiaofu
 * 懒汉式单例模式：double-checked locking
 */

public class Demo5DoubleChecked_Locking {

}

/**
 * 懒汉式单例模式
 * 首次使用getInstance 时才使用 synchronized 进行加锁，后续不用加锁
 * 问题：第一个 if 使用了 INSTANCE 变量，是在同步块之外。
 *  经过jvm优化后，指令可能重排为创建 instance 在 调用构造方法的前面
 *      - 在此间隔，如果被其它线程调用使用 instance 对象，就会出现问题 （还没调用构造方法）
 *  解决方法：使用 volatile 修饰 INSTANCE
 */

final class Singleton {
    private Singleton() {}
    private static Singleton INSTANCE = null;

    public static Singleton getInstance() {
        // 这里是因为避免每次都进入synchronized，性能差
        if (INSTANCE == null) {
            synchronized (Singleton.class) {
                if (INSTANCE == null) {
                    INSTANCE = new Singleton();
                }
            }
        }
        return INSTANCE;
    }
}