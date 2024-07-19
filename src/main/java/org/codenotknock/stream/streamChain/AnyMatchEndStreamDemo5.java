package org.codenotknock.stream.streamChain;

import org.codenotknock.stream.vo.doamin.User;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

/**
 * @author xiaofu
 * @date 2023/12/16 4:52
 * @description Stream 流的终结者操作 anyMatch
 * anyMatch 方法用于检查流中是否至少存在一个元素匹配给定的条件，返回结果类型为 boolean
 */
public class AnyMatchEndStreamDemo5 {

    public static void main(String[] args) {
        List<User> users = StreamDemo1.getUsers();
        // anyMatch 的参数类型为 Predicate 接口类型；
        // Predicate 接口只定义了一个抽象方法 test()
        // 因此可以使用 lambda 表达式或方法引用来创建 Predicate 对象，使得代码更加简洁和易读。
        // 返回结果类型为 boolean   全部不满足条件false  有满足的条件的则为true

        //  是否有用户的年龄小于20
        boolean anyMatch = users.stream()
                .anyMatch(user -> user.getAge() < 20);

        System.out.println(anyMatch);
    }

}
