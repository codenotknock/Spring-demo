package org.codenotknock.stream.streamChain;

import org.codenotknock.stream.vo.doamin.User;

import java.util.List;

/**
 * @author xiaofu
 * @date 2023/12/16 4:01
 * @description Stream 流的中间操作 sorted
 * sorted() 方法可以根据元素的自然顺序或指定的顺序对流中的元素进行排序
 */
public class SortedStreamDemo6 {

    public static void main(String[] args) {
        List<User> users = StreamDemo1.getUsers();
        // sorted 无参数时，对元素转换为 Comparable 接口类型后再进行比较
        //  - 故需要实现 Comparable 接口，子类实现 compareTo 方法
        //  - sorted 根据元素的Comparable实现进行排序
        //  sorted 有 Comparator 对象参数时
        //  - 根据 Comparator 对象的 compare 方法实现 进行自定义排序，可使用 lambda 表达式


        // 输出用户姓名：并自然排序（按照从小到大的顺序进行的：Unicode码点越小，表示的字符在自然排序中越靠前。）
        users.stream()
                .map(user -> user.getName())
                .sorted()
                .forEach(System.out::println);

        //  输出用户姓名：并按照年龄从大到校排序
        users.stream()
                .sorted((u1, u2) -> u1.getAge() - u2.getAge())
                .map(user -> user.getName())
                .forEach(System.out::println);

    }

}
