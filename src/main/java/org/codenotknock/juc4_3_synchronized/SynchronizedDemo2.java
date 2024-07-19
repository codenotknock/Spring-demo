package org.codenotknock.juc4_3_synchronized;

/**
 * 从字节码文件看 synchronized
 * 1. 是可重入锁
 * 2. 自动加锁、释放锁
 * 3. monitorenter 将lock对象 MarkWord 置为 Monitor
 * 4. monitorexit  将lock对象 MarkWord 重置，唤醒EntryList
 * 5. 只有加 synchronized 的对象，才会关联监视器monitor
 */

public class SynchronizedDemo2 {
    static final Object lock = new Object();
    static int counter = 0;
    public static void main(String[] args) {
        synchronized (lock) {
            counter++;
        }
    }
    /* 字节码
        0 getstatic #2 <org/codenotknock/juc4_3_synchronized/SynchronizedDemo2.lock : Ljava/lang/Object;>
         3 dup
         4 astore_1
         5 monitorenter
         6 getstatic #3 <org/codenotknock/juc4_3_synchronized/SynchronizedDemo2.counter : I>
         9 iconst_1
        10 iadd
        11 putstatic #3 <org/codenotknock/juc4_3_synchronized/SynchronizedDemo2.counter : I>
        14 aload_1
        15 monitorexit
        16 goto 24 (+8)
        19 astore_2
        20 aload_1
        21 monitorexit
        22 aload_2
        23 athrow
        24 return



     */

}
