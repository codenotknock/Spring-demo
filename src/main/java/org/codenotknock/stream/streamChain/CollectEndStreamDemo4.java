package org.codenotknock.stream.streamChain;

import org.codenotknock.stream.vo.doamin.Book;
import org.codenotknock.stream.vo.doamin.User;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @author xiaofu
 * @date 2023/12/16 4:37
 * @description Stream 流的终结者操作 collect
 * collect 方法将流中的元素收集到一个集合或者聚合操作的结果中。返回结果类型为集合类型
 */
public class CollectEndStreamDemo4 {

    public static void main(String[] args) {
        List<User> users = StreamDemo1.getUsers();
        // collect 接受一个 Collector 接口参数
        // Collector是一个用于描述元素收集过程的接口，Java提供了一些内置的 Collector 可以使用，也可以自定义Collector以满足特定需求。
        // 返回结果类型为集合类型

        //  获取所有用户的名字 list
        List<String> list = users.stream()
                .map(user -> user.getName())
                .collect(Collectors.toList());
        System.out.println(list);

        //  获取用户和他看的书 map name:books
        Map<String, List> map = users.stream()
                .collect(Collectors.toMap(new Function<User, String>() {
                    @Override
                    public String apply(User user) {
                        return user.getName();
                    }
                }, new Function<User, List>() {
                    @Override
                    public List apply(User user) {
                        return user.getBooks();
                    }
                }));
        System.out.println(map);


        // 根据所有用户的书获取 作者和他的书
        // Collectors.groupingBy方法返回一个Collector实例，它接受一个分类函数作为参数，用于将元素分组。
        // Collectors.mapping方法将每个Book对象的书名映射为一个字符串，然后使用Collectors.toList方法将字符串收集到一个列表中
        Map<String, List<String>> map2 = users.stream()
                .flatMap(user -> user.getBooks().stream())
                .collect(Collectors.groupingBy(Book::getAuthor,
                        Collectors.mapping(Book::getTitle, Collectors.toList())));
        System.out.println(map2);

    }


}
