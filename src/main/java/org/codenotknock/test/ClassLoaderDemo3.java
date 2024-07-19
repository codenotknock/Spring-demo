package org.codenotknock.test;

import jdk.nashorn.internal.runtime.ScriptEnvironment;

import java.io.IOException;

public class ClassLoaderDemo3 {
    public static void main(String [] args) throws IOException, ClassNotFoundException {

        // 获取到了启动类加载器bootstrap
        ClassLoader classLoader = ClassLoaderDemo1.class.getClassLoader();
        System.out.println(classLoader);

        // 用启动类加载器bootstrap 加载 String 类
        Class<?> clazz = classLoader.loadClass("java.lang.String");
        System.out.println(clazz.getClassLoader());

        // 双亲委派机制避免了重复加载
        System.in.read();
        // 利用Arthas的 classloader -t 命令查看类加载器的父子关系
    }
}
