package org.codenotknock.stream.streamChain;

import org.codenotknock.stream.vo.doamin.User;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Stream;

/**
 * @author xiaofu
 * @date 2023/12/16 4:14
 * @description Stream 流的中间操作 flatMap
 * flatMap() 方法将一个流中的每个元素转换为另一个流，然后将这些流合并成一个流；
 *       它接受一个函数作为参数，这个函数会将流中的每个元素映射为一个新的流，最终将这些新的流合并成一个流
 */
public class FlatMapStreamDemo9 {

    public static void main(String[] args) {
        List<User> users = StreamDemo1.getUsers();
        // flatMap 有一个 Function 接口类型的参数
        // Function 接口只定义了一个抽象方法 apply()，  apply() 方法返回转换后的结果
        // 因此可以使用 lambda 表达式或方法引用来创建 Function 对象，使得代码更加简洁和易读。

        //  输出用户的所有书籍
        users.stream()
                .flatMap(new Function<User, Stream<?>>() {
                    @Override
                    public Stream<?> apply(User user) {
                        return user.getBooks().stream();
                    }
                })  // 将users的List<book> books 字段值转换为 Stream 流拼接到user流中
                .forEach(System.out::println);  // Book 类型，最终的流是所有 user 的 Books 总和

        //  输出用户的所有书籍名称
        users.stream()
                .flatMap(user -> user.getBooks().stream())
                .distinct()
                .map(b -> b.getTitle())
                .forEach(System.out::println);
    }

}
