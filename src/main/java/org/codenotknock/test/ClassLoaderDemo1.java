package org.codenotknock.test;

import java.io.IOException;

public class ClassLoaderDemo1 {
    public static void main(String [] args) throws IOException, ClassNotFoundException {
//        // BootStrap启动类加载器由Java虚拟机实现，，是无法拿到的，故为空
//        ClassLoader classLoader = String.class.getClassLoader();
//        System.out.println(classLoader);

        Class <?> clazz = Class.forName("org.codenotknock.classLoader.A");
        System.out.println(clazz);
        System.in.read();
    }
}
