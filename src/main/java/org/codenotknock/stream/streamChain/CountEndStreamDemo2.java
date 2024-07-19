package org.codenotknock.stream.streamChain;

import org.codenotknock.stream.vo.doamin.User;

import java.util.List;
import java.util.function.Consumer;

/**
 * @author xiaofu
 * @date 2023/12/16 4:28
 * @description Stream 流的终结者操作 count
 * count() 方法用于返回流中的元素数量。返回一个 long 类型的结果
 */
public class CountEndStreamDemo2 {

    public static void main(String[] args) {
        List<User> users = StreamDemo1.getUsers();
        // count 它不接受任何参数
        // 返回流中元素的数量，返回一个 long 类型的值。

        //  输出用户的所有书籍的数量
        long count = users.stream()
                .flatMap(user -> user.getBooks().stream())
                .count();
        System.out.println(count);
    }

}
