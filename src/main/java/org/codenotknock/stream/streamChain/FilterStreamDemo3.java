package org.codenotknock.stream.streamChain;


import org.codenotknock.stream.vo.doamin.User;

import java.util.List;

/**
 * @author xiaofu
 * @date 2023/12/16 3:48
 * @description Stream 流的中间操作 filter
 * filter() 方法用于根据指定的条件过滤元素
 */
public class FilterStreamDemo3 {
    public static void main(String[] args) {
        List<User> users = StreamDemo1.getUsers();
        // filter 的参数类型为 Predicate 接口类型
        // Predicate 接口只定义了一个抽象方法 test()， test() 方法返回 true，则保留该元素，否则将其过滤掉
        // 因此可以使用 lambda 表达式或方法引用来创建 Predicate 对象，使得代码更加简洁和易读。

        // 打印姓名长度大于3的用户
        users.stream()
                .filter(user -> user.getName().length() > 3)    // 为false的对象都被过滤掉了
                .forEach(System.out::println);

    }

}
