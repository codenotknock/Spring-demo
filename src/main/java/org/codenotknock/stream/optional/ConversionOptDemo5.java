package org.codenotknock.stream.optional;

import org.codenotknock.stream.vo.doamin.Book;
import org.codenotknock.stream.vo.doamin.User;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

/**
 * @author xiaofu
 * @date 2023/12/16 19:11
 * @description
 * map 用于将 Optional 对象中的值转换为另外一种类型
 */

public class ConversionOptDemo5 {
    public static void main(String[] args) {
        // 构造 User 对象
        Book book = new Book("The Great Gatsby", "F. Scott Fitzgerald", 1925);
        User user = new User("John Doe", 30, Arrays.asList(book));
        user.setOccupation("Engineer");

        // 封装成 Optional 类型的对象
        Optional<User> userOptional = Optional.ofNullable(user);

        // 类型转换 有点像 Stream 流中的 map 操作
        Optional<List<Book>> booksOptional = userOptional.map(new Function<User, List<Book>>() {
            @Override
            public List<Book> apply(User user) {
                return user.getBooks();
            }
        });
        booksOptional.ifPresent(System.out::println);
        /* map 将 value 值转换为其它类型   注意：如果 value 为空，则返回一个空的Optional对象返回
        public<U> Optional<U> map(Function<? super T, ? extends U> mapper) {
            Objects.requireNonNull(mapper);
            if (!isPresent())
                return empty();
            else {
                return Optional.ofNullable(mapper.apply(value));
            }
        }
        // Function 接口只定义了一个抽象方法 apply()，  apply() 方法返回转换后的结果
        // 因此可以使用 lambda 表达式或方法引用来创建 Function 对象，使得代码更加简洁和易读。
         */

    }
}
