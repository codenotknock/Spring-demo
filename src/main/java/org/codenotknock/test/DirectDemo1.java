package org.codenotknock.test;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;

public class DirectDemo1 {
    static int size = 100 * 1024 * 1024;    // 100M
    static List<ByteBuffer> list = new ArrayList<>();
    static int cnt = 1;
    public static void main(String[] args) throws IOException, InterruptedException {
        System.in.read();
        while (true) {
            ByteBuffer directBuffer = ByteBuffer.allocateDirect(size);
            list.add(directBuffer);
            System.out.println(cnt++);
            /*
            在无设置的情况下内存溢出：超过了操作系统设置的内存上限\JVM自动选择最大分配的大小
            java.lang.OutOfMemoryError
	           at sun.misc.Unsafe.allocateMemory(Native Method)

	         如何手动设置内存大小呢？
	         -XX:MaxDirectMemorySize=大小
	         默认字节 单位k或K表示千字节，m或M表示兆字节，g或G表示千兆字节。默认不设置该参数情况下，JVM自动选择最大分配的大小
             */
        }
    }
}
