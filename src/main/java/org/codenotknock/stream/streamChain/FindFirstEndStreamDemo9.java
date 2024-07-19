package org.codenotknock.stream.streamChain;

import org.codenotknock.stream.vo.doamin.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @author xiaofu
 * @date 2023/12/16 5:10
 * @description Stream 流的终结者操作 findFirst
 * findFirst 方法用于获取流中的第一个元素（是最新流中的第一个），返回结果类型为 Optional
 */
public class FindFirstEndStreamDemo9 {

    public static void main(String[] args) {
        List<User> users = new ArrayList<>();
        // findFirst 不接受任何参数
        // 返回结果类型为 Optional

        //  获取过滤后的流中的第一个元素
        Optional<User> user = users.stream()
                .filter(u -> u.getAge() > 30)
                .findFirst();
        user.ifPresent(System.out::println);    // 如果有数据才会输出
    }

}
