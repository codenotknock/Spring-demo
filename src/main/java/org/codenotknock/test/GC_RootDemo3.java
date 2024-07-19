package org.codenotknock.test;

import java.io.IOException;

public class GC_RootDemo3 {
    public static A1 a2 = null;
    public static void main(String[] args) throws IOException {
        // Java虚拟机参数：查看垃圾回收日志    -verbose:gc
            A1 a1 = new A1();
            B1 b1 = new B1();
            a1.b1 = b1;
            b1.a1 = a1;
            a2 = a1;
            System.in.read();

    }
}
