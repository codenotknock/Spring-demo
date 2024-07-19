package org.codenotknock.stream.streamChain;

import org.codenotknock.stream.vo.doamin.User;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

/**
 * @author xiaofu
 * @date 2023/12/16 4:30
 * @description Stream 流的终结者操作 min、max
 * min和 max 方法来找到最小值和最大值，返回结果类型为 Optional
 */
public class MinMaxEndStreamDemo3 {

    public static void main(String[] args) {
        List<User> users = StreamDemo1.getUsers();
        // min、max 的参数类型为 Comparator 接口类型
        // 实现 Comparator 对象的 compare 抽象方法 进行自定义排序，可使用 lambda 表达式
        // 返回结果类型为 Optional

        //  输出用户的最小年龄
        Optional<Integer> min = users.stream()
                .map(user -> user.getAge())
                .min(new Comparator<Integer>() {
                    @Override
                    public int compare(Integer o1, Integer o2) {
                        return o1 - o2;
                    }
                });
        System.out.println(min.get());

        //  输出用户的最大年龄
        Optional<User> maxUser = users.stream()
                .max((u1, u2) -> u1.getAge() - u2.getAge());
        System.out.println(maxUser.get().getAge());
    }

}
