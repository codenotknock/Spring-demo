package org.codenotknock.stream.streamChain;

import org.codenotknock.stream.vo.doamin.User;

import java.util.List;

/**
 * @author xiaofu
 * @date 2023/12/16 4:57
 * @description Stream 流的终结者操作 allMatch
 * allMatch 方法用于检查流中的所有元素是否都满足给定的条件，返回结果类型为 boolean
 */
public class AllMatchEndStreamDemo6 {

    public static void main(String[] args) {
        List<User> users = StreamDemo1.getUsers();
        // allMatch 的参数类型为 Predicate 接口类型；
        // Predicate 接口只定义了一个抽象方法 test()
        // 因此可以使用 lambda 表达式或方法引用来创建 Predicate 对象，使得代码更加简洁和易读。
        // 返回结果类型为 boolean   有不满足的条件false  全部满足的条件的则为true

        //  判断所有用户的年龄大于18吗
        boolean allMatch = users.stream()
                .allMatch(user -> user.getAge() > 18);

        System.out.println(allMatch);
    }

}
