package org.codenotknock.test;

public class JvmTest {
    public static void main(String [] args) {
        int i = 0;
        i = i++;
        System.out.println(i);
        /*
         结果为什么是0呢？ 肯定很疑惑
            这里我们看一下对应字节码文件，就很清楚了
             0 iconst_0 将常量值0压入操作数栈中
             1 istore_1 将栈顶的值存储到局部变量表数组1的位置（将0存储到变量1，操作是将栈顶元素弹出，操作栈中没有这个值了)
             2 iload_1  将局部变量1的值加载到操作数栈中（将局部变量表数组下标1的值加载到操作栈中，变量表中的值依旧存在）
             3 iinc 1 by 1 将局部变量1的值增加1（比较特殊，直接将局部变量的值+1，而不是栈中的值，此时局部变量i为1）
             6 istore_1 将栈顶的值存储到局部变量表数组1的位置（这时栈顶的0弹出将局部变量i的值1覆盖）
             7 getstatic #2 <java/lang/System.out : Ljava/io/PrintStream;> 从类的常量池中获取 System.out 的静态字段
            10 iload_1 将局部变量1的值加载到操作数栈中
            11 invokevirtual #3 <java/io/PrintStream.println : (I)V> 将栈顶的整数值作为参数传递给该方法
            14 return 表示方法返回

               主要原因是iinc 1 by 1操作在 istore_1 的操作之前，导致1被0所覆盖掉了

         */



    }
}
