package org.codenotknock.stream.streamChain;

import org.codenotknock.stream.vo.doamin.User;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author xiaofu
 * @date 2023/12/16 3:57
 * @description Stream 流的中间操作 distinct
 * distinct() 方法用于去重流中的元素
 */
public class DistinctStreamDemo5 {

    public static void main(String[] args) {
        List<User> users = StreamDemo1.getUsers();
        // distinct 无参数
        // distinct() 方法默认使用元素的 equals() 方法来判断两个元素是否相等。

        // 输出用户涉及到的职业：不能重复
        users.stream()
                .map(user -> user.getOccupation())
                .distinct()
                .forEach(System.out::println);

        // 输出用户涉及到的职业：不能重复（不区分大小写）
        users.stream()
                .map(user -> user.getOccupation())
                .map(String::toLowerCase)  // 将字符串转换为小写
                .distinct()
                .forEach(System.out::println);

    }

}
