package org.codenotknock.stream.streamChain;

import org.codenotknock.stream.vo.doamin.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.BinaryOperator;

/**
 * @author xiaofu
 * @date 2023/12/16 5:10
 * @description Stream 流的终结者操作 reduce
 * reduce 方法用它可以将流中的所有元素按照指定的规约函数进行合并，最终返回一个包含合并结果的 Optional 对象或具体的值
 */
public class ReduceEndStreamDemo9 {

    public static void main(String[] args) {
        List<User> users = StreamDemo1.getUsers();
        // T reduce(T identity, BinaryOperator<T> accumulator);
        //      - 接受一个初始值和一个二元操作符，对流中的元素进行积累操作，返回合并结果。
        // Optional<T> reduce(BinaryOperator<T> accumulator);
        //      - 流的第一个值默认为初值，然后对流中的元素进行积累操作，返回合并结果。
        //  BinaryOperator 接口，用于对两个参数进行操作并返回结果。常用于对两个参数进行合并、累加等操作
        //      - 接口定义了一个名为 apply 的抽象方法. 可使用 lambda 表达式


        // 获取所有用户年龄的和
        Integer sumAge = users.stream()
                .map(user -> user.getAge())
                .reduce(0, new BinaryOperator<Integer>() {
                    @Override
                    public Integer apply(Integer res, Integer age) {
                        return res + age;
                    }
                });
        /* T reduce(T identity, BinaryOperator<T> accumulator)：接受一个初始值和一个二元操作符，对流中的元素进行累积操作，返回合并结果。
        上面代码中 reduce 的操作和下面是一样的
          Integer res =  Integer identity;
          for (Integer age : ages) {
                res  = accumulator.apply(res, age);
          }
          return res;
         */

        System.out.println("年龄总和：" + sumAge);

        // 用户的最大年龄值
        Integer maxAge = users.stream()
                .map(user -> user.getAge())
                .reduce(Integer.MIN_VALUE, (res, age1) -> res > age1 ? res : age1);
        System.out.println("最大年龄：" + maxAge);


        // 用户的最小年龄值
        Optional<Integer> minAge = users.stream()
                .map(user -> user.getAge())
                .reduce((age, age1) -> age < age1 ? age : age1);
        minAge.ifPresent(System.out::println);
        /*  Optional<T> reduce(BinaryOperator<T> accumulator);
                使用指定的初始值和二元操作符对流中的元素进行逐个合并。
           相当于下面的一段代码，传1个参数和2个参数 其实逻辑都是相似的
           boolean foundAny = false;
           T result = null;
           for (T element : this.stream) {
                if (!foundAny) {
                foundAny = true;
                result = element;
           } else {
                result = accumulator.apply(result, element);
           }
           return foundAny ? Optional.of(result) : Optional.empty();
         */

    }

}
