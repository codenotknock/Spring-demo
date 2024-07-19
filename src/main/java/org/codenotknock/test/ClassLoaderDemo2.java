package org.codenotknock.test;

import jdk.nashorn.internal.runtime.ScriptEnvironment;

import java.io.IOException;

public class ClassLoaderDemo2 {
    public static void main(String [] args) throws IOException {

        //
        ClassLoader classLoader = ScriptEnvironment.class.getClassLoader();
        System.out.println(classLoader);   // sun.misc.Launcher$ExtClassLoader@5e481248
    }
}
