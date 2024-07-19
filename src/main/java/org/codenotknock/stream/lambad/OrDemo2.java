package org.codenotknock.stream.lambad;

import org.codenotknock.stream.streamChain.StreamDemo1;
import org.codenotknock.stream.vo.doamin.User;

import java.util.List;
import java.util.function.IntPredicate;
import java.util.function.Predicate;

/**
 * @author xiaofu
 * Predicate 接口中默认的方法 or
 */
public class OrDemo2 {
    private static void orTest() {
        List<User> users = StreamDemo1.getUsers();
        users.stream()
                .filter(u -> u.getAge() > 26 || u.getBooks().size() > 2)
                .forEach(u -> System.out.println(u.getName()));

        users.stream()
                .filter(new Predicate<User>() {
                    @Override
                    public boolean test(User user) {
                        return user.getAge() > 26;
                    }
                }.or(new Predicate<User>() {
                            @Override
                            public boolean test(User user) {
                                return user.getBooks().size() > 2;
                            }
                        }))
                .forEach(user -> System.out.println(user.getName()));

        users.stream()
                .filter(((Predicate<User>) user -> user.getAge() > 26).or(user -> user.getBooks().size() > 2))
                .forEach(user -> System.out.println(user.getName()));
    }

    private static void orTest2(IntPredicate predicate1, IntPredicate predicate2) {
        int[] arr = {1, 2, 3, 4, 5, 6, 7, 8};
        for (int x : arr) {
            if (predicate1.or(predicate2).test(x)) {
                System.out.println(x);
            }
        }
    }

    public static void main(String[] args) {
        OrDemo2.orTest();

        OrDemo2.orTest2(value -> value > 5, value -> value % 4 == 0);

    }
}
