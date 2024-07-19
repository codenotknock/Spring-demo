package org.codenotknock.stream.optional;

import org.codenotknock.stream.vo.doamin.User;

import java.util.Optional;

/**
 * @author xiaofu
 * @date 2023/12/16 18:33
 * @description
 * Optional.ofNullable(user)  安全的将对象封装成 Optional 类型
 * ifPresent 方法：安全的处理操作
 * 安全：是指优雅的避免了空指针异常
 */
public class CreatOptDemo1 {
    public static void main(String[] args) {
        User user = new User();
//        if (null != user) {
//            ...
//        }
//        Optional<User> o = Optional.of(null);   // NullPointerException
        Optional<User> userOptional = Optional.ofNullable(user);    // 无论是否为 null， 都封装成功
        /* ofNullable 方法源码部分代码
            return value == null ? empty() : of(value);
        public static<T> Optional<T> empty()：EMPTY
            private static final Optional<?> EMPTY = new Optional<>();
         */
        userOptional.ifPresent(u -> System.out.println(u.getName()));   // user 若不为空，输出名字
    }

    public static Optional<User> getUserOptional() {
        User user = new User();
        return Optional.ofNullable(user);
        // Mybatis 从3.5版本开始支持Optional了，可以把数据封装成Optional类型返回
    }
}
