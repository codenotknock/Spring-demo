package org.codenotknock.stream.streamChain;

import org.codenotknock.stream.vo.doamin.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @author xiaofu
 * @date 2023/12/16 5:03
 * @description Stream 流的终结者操作 findAny
 * findAny 方法用于获取流中的任意一个元素，返回结果类型为 Optional
 */
public class FindAnyEndStreamDemo8 {

    public static void main(String[] args) {
        List<User> users = new ArrayList<>();
        // findAny 不接受任何参数
        // 返回结果类型为 Optional

        //  随机获取一个用户
        Optional<User> user = users.stream()
                .findAny();

//        user.get();   // 如果没有数据 会有异常抛出
        /*       public T get() {
                    if (value == null) {
                        throw new NoSuchElementException("No value present");
                    }
                    return value;
                    }
         */
        user.ifPresent(System.out::println);    // 如果有数据才会输出
    }

}
