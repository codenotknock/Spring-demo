package org.codenotknock.test;

/**
 * @author xiaofu
 * @date 2023/11/21 22:39
 */

public class StringTableDemo {
    public static void main(String [] args) {
        String a = "1";
        String b = "2";
        String c = "12";    // 在字符串常量池中
        String d = a + b;   // 底层源码是StringBuilder,a+b用其append和toString方法得到”12“，在堆中
        // String d = new StringBuilder().append(a).append(b).toString(); 和a+b等同
        System.out.println(c == d); // false
        String f = "1" + "2";   // 利用ldc字节码指令变成”12“
        System.out.println(c == f); // true  ”12“都来自于字符串常量池

        // String.intern 方法是可以手动将字符串放入字符串常量池中；如果字符串常量池中已经有了，就直接从字符串常量池中取出
        System.out.println(c == d.intern());    // true  都来自了于字符串常量池

        String e = "5";
        String g = a + e;   // 注意：这是常量池中并没有”15“这个字符串，故intern会把堆中的地址存到字符串常量池中
        System.out.println(g.intern() == g); // true
        /* 注意点
            jdk7及之后的版本由于字符串常量池在堆上，所以intern()方法会把第一次遇到的字符串的引用放入字符串常量池
            注意是引用：字符串常量池中放的是引用！！！就是存放的是堆中的地址
         */

    }
}
