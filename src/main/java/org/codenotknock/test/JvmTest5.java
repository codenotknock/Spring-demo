package org.codenotknock.test;

public class JvmTest5 {
    public static void main(String [] args) {
//        new B();
//        System.out.println(B.i);  // new B() 初始化B  222
        // 访问父类的静态变量。只初始化父类，故B.i为11
        System.out.println(B.i);
    }
}

class A {
    static int i = 1;
    static {
        i = 11;
    }
}

class B extends A {
    static {
        i = 222;
    }
}


class Demo99{
    public static void main(String [] args) {
        // 数组的创建不会导致数组中的类进行初始化：new的是数组
        NewDemo99[] arr = new NewDemo99[3];

    }
}

class NewDemo99{
    static {
        System.out.println("NewDemo99 static 初始化...");
    }
}


class Demo98{
    public static void main(String [] args) {
        // final修饰的变量右边不是一个单纯的常量，如果需要执行指令才能得出结果，则会执行clinit方法进行初始化
        System.out.println(NewDemo98.i);

    }
}
class NewDemo98{
    static final int i = Math.max(1,98);
    static {
        System.out.println("NewDemo98 static 初始化...");
    }
}
