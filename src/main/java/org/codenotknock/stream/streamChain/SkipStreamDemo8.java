package org.codenotknock.stream.streamChain;

import org.codenotknock.stream.vo.doamin.User;

import java.util.List;

/**
 * @author xiaofu
 * @date 2023/12/16 4:10
 * @description Stream 流的中间操作 skip
 * skip() 方法用于跳过元素
 */
public class SkipStreamDemo8 {

    public static void main(String[] args) {
        List<User> users = StreamDemo1.getUsers();
        // limit 有一个 long 类型的参数 n
        // 用于跳过流中的前 n 个元素，表示要跳过的元素数量为 n。

        //  输出第二大的年龄数字
        users.stream()
                .map(user -> user.getAge())
                .distinct()
                .sorted()
                .skip(1)
                .limit(1)
                .forEach(System.out::println);
    }

}
