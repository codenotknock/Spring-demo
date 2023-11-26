package org.codenotknock.test;

import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;
import java.util.ArrayList;

public class GC_SoftReferenceDemo3 {

    public static void main(String [] args) {
        ArrayList<SoftReference> softReferences = new ArrayList<>();
        ReferenceQueue<byte[]> referenceQueue = new ReferenceQueue<>();
        for (int i = 0; i < 10; i++) {
            byte[] bytes = new byte[100 * 1024 * 1024];
            SoftReference stRef = new SoftReference<byte[]>(bytes, referenceQueue);
            softReferences.add(stRef);

        }
        SoftReference<byte[]> ref = null;
        int cnt = 0;
        while ((ref = (SoftReference<byte[]>) referenceQueue.poll()) != null) {
            cnt++;
        }
        System.out.println(cnt);
        // 答案是9，原因是-Xmx200m  堆内存最大200m只能存放一个对象，
        //      前9个对象被回收后放入队列中，只有最后一个对象没有被放入队列
    }
}
