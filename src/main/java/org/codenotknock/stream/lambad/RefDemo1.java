package org.codenotknock.stream.lambad;


import org.codenotknock.stream.streamChain.StreamDemo1;
import org.codenotknock.stream.vo.doamin.User;

import java.util.List;


/**
 * @author xiaofu
 * 方法引用 进一步简化代码
 */
public class RefDemo1 {

    /**
     * @des 方法引用之引用类的静态方法  格式为 类名::方法名
     * @param
     * @return
     */

    private static void refTest1() {
        List<User> users = StreamDemo1.getUsers();
        users.stream()
                .map(User::getAge)
                .map(String::valueOf)
                .forEach(System.out::println);
    }
    
    /**  
     * @des 方法引用之引用对象的实例方法  格式为 对象名::方法名
     * @param
     * @return 
     */

    private static void refTest2() {
        RefDemo1 refDemo1 = new RefDemo1();
        List<User> users = StreamDemo1.getUsers();

        users.stream()
                .map(User::getName)
                .forEach(refDemo1::p);

    }


    /**
     * @des  方法引用之引用类的实例方法  格式为 类名::方法名
     * @param
     * @return
     */

    private static void refTest3() {
        RefDemo1 refDemo1 = new RefDemo1();
        List<User> users = StreamDemo1.getUsers();

        users.stream()
                .map(User::getName)
                .forEach(System.out::println);
    }


    /**
     * @des  方法引用之构造器引用  格式为 类名::new
     * @param
     * @return
     */

    private static void refTest4() {
        List<User> users = StreamDemo1.getUsers();
        users.stream()
                .map(User::getName)
                .map(StringBuilder::new)
                .map(sb -> sb.append("_xiaofu").toString())
                .forEach(System.out::println);

    }



    public static void main(String[] args) {
        RefDemo1.refTest1();
        System.out.println("----------------------------------------------");
        RefDemo1.refTest2();
        System.out.println("----------------------------------------------");
        RefDemo1.refTest3();
        System.out.println("----------------------------------------------");
        RefDemo1.refTest4();

    }

    public void p(String s)
    {
        System.out.println(s);
    }
}
