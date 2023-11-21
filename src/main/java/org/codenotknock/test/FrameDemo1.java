package org.codenotknock.test;

import java.io.IOException;

/**
 * @author xiaofu
 * @date 2023/11/20 22:40
 */

public class FrameDemo1 {
    public static void main(String[] args) throws IOException {
        A1();
        System.in.read();
    }
    public static void A1(){
        System.out.println("A1 ...");
        A2();
    }

    public static void A2(){
        System.out.println("A2 ...");
        A3();
    }

    public static void A3(){
        System.out.println("A3 ...");
        A4();
    }

    public static void A4(){
        System.out.println("A4 ...");
        throw new RuntimeException("看一下异常抛出的顺序：栈先进后出");
    }
}
