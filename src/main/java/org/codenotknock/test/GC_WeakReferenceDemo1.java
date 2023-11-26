package org.codenotknock.test;

import java.lang.ref.WeakReference;

/**
 * @author xiaofu
 * @date 2023/11/23 23:09
 */

public class GC_WeakReferenceDemo1 {

    public static void main(String[] args) {
        byte[] bytes = new byte[100 * 1024 * 1024];
        WeakReference<byte[]> weakReference = new WeakReference<>(bytes);
        bytes = null;
        System.out.println(weakReference.get()); // [B@74a14482
        System.gc();
        System.out.println(weakReference.get()); // null 被回收掉
    }
}
