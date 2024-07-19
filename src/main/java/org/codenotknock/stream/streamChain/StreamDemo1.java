package org.codenotknock.stream.streamChain;

import org.codenotknock.stream.vo.doamin.Book;
import org.codenotknock.stream.vo.doamin.User;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Predicate;

/**
 * @author xiaofu
 * @date 2023/12/16 3:42
 * @description
 */
public class StreamDemo1 {
    public static void main(String[] args) {
        List<User> users = getUsers();
        users.stream()      // 把集合转换为流
                .distinct() // Stream 的去重操作
                .filter(new Predicate<User>() {
                    @Override
                    public boolean test(User user) {
                        return user.getAge() > 30;
                    }
                })  // Predicate类，只有一个方法   匿名内部类 可使用lambda表达式
                .forEach(new Consumer<User>() {
                    @Override
                    public void accept(User user) {
                        System.out.println(user.getName());
                    }
                }); // Consumer类  内部 accept 方法 对整数进行操作    匿名内部类 可使用lambda表达式

        System.out.println("```````````````````````````");
        users.stream()
                .filter(u -> u.getAge() > 30)
                .forEach(user -> System.out.println(user.getName()));


    }

    public static List<User> getUsers() {
        List<User> users = new ArrayList<>();

        // 构造 Book 对象
        Book book1 = new Book("The Great Gatsby", "F. Scott Fitzgerald", 1925);
        Book book2 = new Book("To Kill a Mockingbird", "Harper Lee", 1960);
        Book book3 = new Book("1984", "George Orwell", 1949);
        Book book4 = new Book("Pride and Prejudice", "Jane Austen", 1813);
        Book book5 = new Book("The Lord of the Rings", "J.R.R. Tolkien", 1954);

        // 构造 User 对象
        User user1 = new User("John Doe", 30, Arrays.asList(book1, book2));
        user1.setOccupation("Engineer");
        User user2 = new User("Alice Smith", 25, Arrays.asList(book3));
        user2.setOccupation("Designer");
        User user3 = new User("Bob Johnson", 35, Arrays.asList(book4, book5));
        user3.setOccupation("Analyst");
        User user4 = new User("Emily Brown", 28, Arrays.asList(book1));
        user4.setOccupation("Analyst");
        User user5 = new User("David Wilson", 32, Arrays.asList(book2, book3, book4));
        user5.setOccupation("Programmer");

        // 添加到用户列表中
        users.add(user1);
        users.add(user2);
        users.add(user3);
        users.add(user4);
        users.add(user5);

        return users;
    }
}
