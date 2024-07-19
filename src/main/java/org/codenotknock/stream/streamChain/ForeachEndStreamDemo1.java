package org.codenotknock.stream.streamChain;

import org.codenotknock.stream.vo.doamin.User;

import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Stream;

/**
 * @author xiaofu
 * @date 2023/12/16 4:25
 * @description Stream 流的终结者操作 forEach
 * forEach() 方法会遍历流中的每个元素，并对每个元素执行给定的操作。 无返回结果
 */
public class ForeachEndStreamDemo1 {

    public static void main(String[] args) {
        List<User> users = StreamDemo1.getUsers();
        // forEach 的参数类型为 Consumer 接口类型
        // Consumer 接口是一个函数式接口，它定义了一个操作，该操作接受一个输入参数并且不返回任何结果。
        // 它有一个抽象方法 accept(T t) ，应用于流中的每个元素,该方法接受一个参数并执行操作。可以使用Lambda表达式来实现 Consumer 接口
        // 无返回结果

        //  输出用户的所有书籍名称
        users.stream()
                .flatMap(user -> user.getBooks().stream())
                .distinct()
                .map(b -> b.getTitle())
                .forEach(new Consumer<String>() {
                    @Override
                    public void accept(String s) {
                        System.out.println(s);
                    }
                });
    }

}
