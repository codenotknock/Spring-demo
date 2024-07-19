package org.codenotknock.stream.optional;

import org.codenotknock.stream.vo.doamin.Book;
import org.codenotknock.stream.vo.doamin.User;

import java.util.Arrays;
import java.util.Optional;
import java.util.function.Supplier;

/**
 * @author xiaofu
 * @date 2023/12/16 19:10
 * @description
 *
 */
public class GetOptDemo2 {
    public static void main(String[] args) {
        // 构造 User 对象
//        Book book = new Book("The Great Gatsby", "F. Scott Fitzgerald", 1925);
//        User user = new User("John Doe", 30, Arrays.asList(book));
//        user.setOccupation("Engineer");

        User user = null;
        // 封装成 Optional 类型的对象
        Optional<User> userOptional = Optional.ofNullable(user);
        // 获取数据 orElseGet   若为 null 则返回给新建时的默认值  例如 User(name=null, age=0, books=null)
        User user1 = userOptional.orElseGet(() -> new User());
        System.out.println(user1);
        /*  orElseGet
        public T orElseGet(Supplier<? extends T> other) {
            return value != null ? value : other.get();
        }
        Supplier接口是一个函数式接口，它只有一个抽象方法 get()：主要作用是提供一个获取指定类型对象的函数式操作方法
        @FunctionalInterface
        public interface Supplier<T> {
            T get();
        }

         */

      try {
          User user2 = userOptional.orElseThrow(new Supplier<Throwable>() {
              @Override
              public Throwable get() {
                  return new RuntimeException("user 为空");
              }
          });
          System.out.println(user2);
      } catch (Throwable e) {
          e.printStackTrace();
      }
       /*  orElseThrow 获取数据，若为 null ,抛出异常
        public <X extends Throwable> T orElseThrow(Supplier<? extends X> exceptionSupplier) throws X {
            if (value != null) {
                return value;
            } else {
                throw exceptionSupplier.get();
            }
        }
        Supplier接口是一个函数式接口，它只有一个抽象方法 get()：主要作用是提供一个获取指定类型对象的函数式操作方法
        @FunctionalInterface
        public interface Supplier<T> {
            T get();
        }

         */
    }
}
