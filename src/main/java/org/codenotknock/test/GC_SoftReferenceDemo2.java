package org.codenotknock.test;

import java.lang.ref.SoftReference;

public class GC_SoftReferenceDemo2 {
    public static void main(String[] args) {
        byte[] bytes = new byte[100 * 1024 * 1024];
        // 创建一个软引用的对象：真实数据bytes
        SoftReference<byte[]> softReference = new SoftReference<byte[]>(bytes);
        bytes = null;   // 释放强引用
        System.out.println(softReference);

        byte[] bytes2 = new byte[100 * 1024 * 1024];
        System.out.println(softReference.get());
        // -Xmx200m 时 内存不足，软引用被回收，故输入null
//        java.lang.ref.SoftReference@74a14482
//        null

        // 如果想要将软引用对象释放掉，需要先将softReference置为null
        // 但softReference也可能不被回收：内存不足时softReference被回收，内存充足不被回收
        softReference = null;

        /* 为了解决上面情况，SoftReference提供了一套队列机制：
            1. 软引用创建时，通过构造器传入引用队列
            2. 在软引用中包含的对象被回收时，该软引用对象会被放入引用队列
            3. 通过代码遍历引用队列，将SoftReference的强引用删除
        软引用包含的对象被回收时，SoftReference对象就会被放入队列中，便于释放空的SoftReference
         */
    }
}
