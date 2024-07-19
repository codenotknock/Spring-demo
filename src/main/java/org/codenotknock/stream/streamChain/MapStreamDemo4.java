package org.codenotknock.stream.streamChain;

import org.codenotknock.stream.vo.doamin.User;

import java.util.List;

/**
 * @author xiaofu
 * @date 2023/12/16 3:51
 * @description Stream 流的中间操作 map
 * map() 方法用于将元素按照指定的方式转换成另外一种类型
 */
public class MapStreamDemo4 {

    public static void main(String[] args) {
        List<User> users = StreamDemo1.getUsers();
        // map 的参数类型为 Function 接口类型
        // Function 接口只定义了一个抽象方法 apply()，  apply() 方法返回转换后的结果
        // 因此可以使用 lambda 表达式或方法引用来创建 Function 对象，使得代码更加简洁和易读。

        // 输出字符串：用户的姓名年龄
        users.stream()
                .map(user -> user.getName() + user.getAge())
                .forEach(System.out::println);

        // 输出年龄+1
        users.stream()
                .map(user -> user.getAge())
                .map(a -> a + 1)
                .forEach(System.out::println);


        // 输出年龄大于30的用户的姓名
        users.stream()
                .filter(user -> user.getAge() > 30)
                .map(user -> user.getName())
                .forEach(System.out::println);
    }

}
