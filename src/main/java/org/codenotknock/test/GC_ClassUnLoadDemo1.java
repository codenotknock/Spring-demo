package org.codenotknock.test;

import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;

/**
 * @author xiaofu
 * @date 2023/11/22 21:35
 */

public class GC_ClassUnLoadDemo1 {
    public static void main(String [] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException, MalformedURLException {
        ArrayList<Class<?>> classes = new ArrayList<>();
        ArrayList<URLClassLoader> urlClassLoaders = new ArrayList<>();
        ArrayList<Object> objects = new ArrayList<>();

        /*
        -XX:+TraceClassLoading -XX:+TraceClassUnloading
            类被加载和类被卸载的时候打印出日志
         */
        while (true) {
            URLClassLoader loader = new URLClassLoader(
                    new URL[]{new URL("file:E:\\A-RESTUDY\\demo\\target")}
            );
            Class<?> clazz = loader.loadClass("org.codenotknock.classLoader.A");
            Object o = clazz.newInstance();
            System.gc();
        }

    }
}
