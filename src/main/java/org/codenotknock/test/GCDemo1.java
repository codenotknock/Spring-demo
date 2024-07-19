package org.codenotknock.test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author xiaofu
 * @date 2023/11/24 20:23
 */

public class GCDemo1 {
    /* 虚拟机参数
    -Xms60m
    -Xmx60m
    -Xmn20m
    -XX:SurvivorRatio=3
    -XX:+PrintGCDetails
     */
    // 由比例3和新生代内存大小20m 可知伊甸园区和幸存区大小为12m 4m 4m
    /* 垃圾回收器
        -XX:+UseSerialGC
        -XX:+UseParNewGC -XX:+UseConcMarkSweepGC
        -XX:+UseParallelGC 或 -XX:UseParallelOldGC

     */
    public static void main(String[] args) throws IOException {
        List<Object> list = new ArrayList<>();
        int cnt = 1;
        while (true) {
            System.in.read();
            System.out.println(cnt++);

            list.add(new byte[1 * 1024 * 1024]);
        }
    }
}
