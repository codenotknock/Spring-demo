package org.codenotknock.stream.streamChain;

import org.codenotknock.stream.vo.doamin.User;

import java.util.List;

/**
 * @author xiaofu
 * @date 2023/12/16 4:07
 * @description Stream 流的中间操作 limit
 * limit() 方法用于限制流中元素的个数
 */
public class LimitStreamDemo7 {

    public static void main(String[] args) {
        List<User> users = StreamDemo1.getUsers();
        // limit 有一个 long 类型的参数 maxSize
        // 用于限制流中元素的个数，表示限制流中元素的数量为 maxSize。

        //  输出年龄最大的3个用户的姓名
        users.stream()
                .sorted((u1, u2) -> u1.getAge() - u2.getAge())
                .map(user -> user.getName())
                .limit(3)
                .forEach(System.out::println);

    }

}
