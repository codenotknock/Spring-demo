package org.codenotknock.test;


/**
 * 类的生命周期：初始化阶段
 * @author xiaofu
 * @date 2023/11/16 20:30
 */


public class JvmTest4 {
    /*

    0 bipush 99  将数值 99 压入操作数栈顶
    2 putstatic #2 <org/codenotknock/test/JvmTest4.a : I>   从操作数栈顶弹出数值，并将其存储到 org.codenotknock.test.JvmTest4 类的静态变量 a 中
    5 iconst_2  将整数常量值 2 压入操作数栈顶
    6 putstatic #2 <org/codenotknock/test/JvmTest4.a : I>  从操作数栈顶弹出数值，并将其存储到 org.codenotknock.test.JvmTest4 类的静态变量 a 中
    9 return  从当前方法返回


    clinit方法中的执行顺序与Java中编写的顺序一致
     */
    public static int a = 99;

    static {
        a = 2;
    }
    public static  void main(String [] args) {

    }
}

    /*
    怎样会触发类的初始化操作？
        1. 访问一个类的静态变量或者静态方法
        ● 注意：变量是final修饰的并且等号右边是常量不会触发初始化（在连接阶段就会被赋值）
        2. 调用Class.forName(String className)
        3. new  一个该类的对象时
        4. 执行Main方法的当前类

     */

class Demo{

    public static  void main(String [] args) {
        System.out.println("A");
        new Demo();
        new Demo();
        // 答案：D A C B C B
        /* 解析：执行main方法先执行初始化方法 D A
               代码块在构造方法之前被执行
         */
        /*
             0 aload_0
             1 invokespecial #6 <java/lang/Object.<init> : ()V>
             4 getstatic #1 <java/lang/System.out : Ljava/io/PrintStream;>
             7 ldc #7 <C>
             9 invokevirtual #3 <java/io/PrintStream.println : (Ljava/lang/String;)V>
            12 getstatic #1 <java/lang/System.out : Ljava/io/PrintStream;>
            15 ldc #8 <B>
            17 invokevirtual #3 <java/io/PrintStream.println : (Ljava/lang/String;)V>
            20 return

         */
    }
    public Demo(){
        System.out.println("B");
    }
    {
        System.out.println("C");
    }

    static {
        System.out.println("D");
    }

}

class Demo1{
    public static void main(String [] args){
        int i = Demo2.i;
        System.out.println(i);
    }
}

class Demo2{
    static {
        System.out.println("demo2_static 初始化...");
    }
//    public static final int i = 9;  // 不会被初始化，因为在连接阶段就完成了赋值
    public static int i = 9;  // 会触发初始化操作
}

class Demo3{
    public static void main(String [] args) throws ClassNotFoundException {
        // 只要调了了Class.forName这个方法就会被初始化，不管有没有使用clazz这个对象
        Class<?> clazz = Class.forName("org.codenotknock.test.Demo4");
    }
}

class Demo5{
    static {
        System.out.println("demo5_static 初始化...");
    }
    public static void main(String [] args) {
        new Demo6();
    }

}

class Demo6{
    static {
        System.out.println("demo6_static 初始化...");
    }

}

