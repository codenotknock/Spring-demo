package org.codenotknock.test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author xiaofu
 * @date 2023/11/21 18:11
 */


public class HeapDemo1 {

    // 看一下堆的内存溢出 java.lang.OutOfMemoryError
    // 避免运行时间过长，可以先把堆内存设置的小一些 或者放比较大的数据 比如100M的数据
    public static void main(String [] args) throws IOException {

        System.in.read();
        List<Object> objectList = new ArrayList<>();
        while (true) {

            objectList.add(new byte[1024 * 1024]);
        }
    }

}
