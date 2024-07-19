package org.codenotknock.stream.optional;

import org.codenotknock.stream.vo.doamin.Book;
import org.codenotknock.stream.vo.doamin.User;

import java.util.Arrays;
import java.util.Optional;

/**
 * @author xiaofu
 * @date 2023/12/16 19:06
 * @description
 * filter 过滤操作   如果不满足条件会新建一个 value 为 null 的 Optional 对象
 */
public class FilterOptDemo3 {
    public static void main(String[] args) {
//        // 构造 User 对象
        Book book = new Book("The Great Gatsby", "F. Scott Fitzgerald", 1925);
        User user = new User("John Doe", 30, Arrays.asList(book));
        user.setOccupation("Engineer");

//        User user = null;
        // 封装成 Optional 类型的对象
        Optional<User> userOptional = Optional.ofNullable(user);
        // 过滤条件
        userOptional.filter(u -> u.getAge() < 18).ifPresent(System.out::println);

        /*  filter 过滤操作   如果不满足条件会返回一个 value 为 null 的 Optional 对象
        public Optional<T> filter(Predicate<? super T> predicate) {
            Objects.requireNonNull(predicate);
            if (!isPresent())
                return this;
            else
                return predicate.test(value) ? this : empty();
        }

        // Predicate 接口只定义了一个抽象方法 test()， test() 方法返回 true，则保留该元素，否则将其过滤掉
        // 因此可以使用 lambda 表达式或方法引用来创建 Predicate 对象，使得代码更加简洁和易读。


         */

    }
}
