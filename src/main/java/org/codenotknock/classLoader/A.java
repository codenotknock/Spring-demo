package org.codenotknock.classLoader;

public class A {
//    怎么通过启动类加载器去加载用户jar包？
//            1. 放入jre/lib下进行扩展【不推荐：可能出错】
//            2. 使用参数 -Xbootclasspath/:jar包目录/jar包名  进行扩展
    static {
        System.out.println("A .....");
    }
}
