package org.codenotknock.stream.optional;

import org.codenotknock.stream.vo.doamin.Book;
import org.codenotknock.stream.vo.doamin.User;

import java.util.Arrays;
import java.util.Optional;

/**
 * @author xiaofu
 * @date 2023/12/16 19:06
 * @description
 * isPresent 判断 Optional 数据是否存在
 */
public class IsPresentOptDemo4 {
    public static void main(String[] args) {
        // 构造 User 对象
        Book book = new Book("The Great Gatsby", "F. Scott Fitzgerald", 1925);
        User user = new User("John Doe", 30, Arrays.asList(book));
        user.setOccupation("Engineer");

        // 封装成 Optional 类型的对象
        Optional<User> userOptional = Optional.ofNullable(user);
        Optional<User> userOptional2 = Optional.ofNullable(null);
        System.out.println(userOptional.isPresent());   // true
        System.out.println(userOptional2.isPresent());  // false

        /* isPresent 判断数据是否存在
        public boolean isPresent() {
            return value != null;
        }
         */

    }
}
