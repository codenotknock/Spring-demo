package org.codenotknock.stream.lambad;

import org.codenotknock.stream.streamChain.StreamDemo1;
import org.codenotknock.stream.vo.doamin.User;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author xiaofu
 * parallel、parallelStream 方法用于从集合中创建一个并行流
 * 并行流的操作 :将一个数据集拆分成多个小的数据集，
 *                  并在多个线程上同时对这些小数据集进行处理，最后再将结果合并起来的过程
 *
 */
public class ParallelStreamExample {
    public static void main(String[] args) {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

        // 串行流
        System.out.println("Serial Stream:");
        numbers.stream()
                .map(n -> n * n)
                .forEach(System.out::println);

        // 并行流
        System.out.println("\nParallel Stream:");
        numbers.parallelStream()
                .peek(n -> System.out.println(n + "：" + Thread.currentThread()))
                .map(n -> n * n)
                .forEach(System.out::println);
        System.out.println("\nParallel Stream:");
        numbers.stream().parallel()
                .peek(n -> System.out.println(n + "：" + Thread.currentThread()))
                .map(n -> n * n)
                .forEach(System.out::println);
    }



    /**
     * @des  基本数据类型的装箱和拆箱，有一定的性能损耗
     * @param
     * @return
     * 解决方法：mapToInt、mapToLong、mapToDouble、flatMapToInt、、flatMapToLong 等等
     * 注意基本数据类型再使用 boxed 方法进行装箱操作
     */
    private static void test() {
        List<User> users = StreamDemo1.getUsers();
        List<Integer> list = users.stream()
                .mapToInt(User::getAge)
                .map(age -> age + 1)
                .filter(age -> age > 30)
                .boxed()
                .collect(Collectors.toList());
        /* boxed 方法装箱操作 这里是int的装箱操作
        abstract class IntPipeline<E_IN>
        extends AbstractPipeline<E_IN, Integer, IntStream>
        implements IntStream {...
            @Override
            public final Stream<Integer> boxed() {
                return mapToObj(Integer::valueOf);
            }
            ...
        }

         */
    }
}