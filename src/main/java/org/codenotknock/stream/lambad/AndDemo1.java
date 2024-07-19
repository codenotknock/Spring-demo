package org.codenotknock.stream.lambad;

import org.codenotknock.stream.streamChain.StreamDemo1;
import org.codenotknock.stream.vo.doamin.User;

import java.util.List;
import java.util.function.IntPredicate;
import java.util.function.Predicate;

/**
 * @author xiaofu
 * Predicate 接口中默认的方法 and
 */
public class AndDemo1 {
    private static void andTest() {
        List<User> users = StreamDemo1.getUsers();
        users.stream()
                .filter(u ->  u.getAge() > 26 && u.getName().length() > 6)
                .forEach(user -> System.out.println(user.getName()));

        users.stream()
                .filter(new Predicate<User>() {
                    @Override
                    public boolean test(User user) {
                        return user.getAge() > 26;
                    }
                }.and(new Predicate<User>() {
                            @Override
                            public boolean test(User user) {
                                return user.getName().length() > 6;
                            }
                        }))
                .forEach(user -> System.out.println(user.getName()));

        users.stream()
                .filter(((Predicate<User>) user -> user.getAge() > 26).and(user -> user.getName().length() > 6))
                .forEach(user -> System.out.println(user.getName()));


    }

    private static void andTest2(IntPredicate predicate1, IntPredicate predicate2) {
        int[] arr = {1, 2, 3, 4, 5, 6, 7, 8};
        for (int x: arr) {
            if (predicate1.and(predicate2).test(x)) {
                System.out.println(x);
            }
        }
    }

    public static void main(String[] args) {
        AndDemo1.andTest();

        AndDemo1.andTest2(value -> value > 3, value -> value % 3 == 0);
    }

}
