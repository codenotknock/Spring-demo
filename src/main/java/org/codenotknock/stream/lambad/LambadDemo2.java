package org.codenotknock.stream.lambad;

import java.util.function.Function;
import java.util.function.IntConsumer;

/**
 * @author xiaofu
 * @date 2023/12/16 2:41
 * @description
 */
public class LambadDemo2 {
    public static void main(String[] args) {
        Double aDouble1 = typeConver(new Function<String, Double>() {
            @Override
            public Double apply(String s) {
                return Double.valueOf(s);
            }
        });

        Double aDouble = typeConver(s -> Double.valueOf(s));


        forArr (x -> System.out.println(x));
    }

    public static <R> R typeConver(Function<String, R> function) {
        // 函数式编程 Function 接口通常用作映射或转换对象的功能接口, 有一个apply的抽象方法
//        表示一个以某种类型为输入并返回另一种类型的函数,
        String str = "1234";
        R reasult = function.apply(str);
        System.out.println(reasult);
        return reasult;
    }

    public static void forArr(IntConsumer consumer) {
//    IntConsumer 接口表示一个以 int 为输入的消费者，它有一个抽象方法 accept(int value)，用于执行对给定整数的操作{
        int []arr = {1, 2, 3, 4};
        for (int a : arr) {
            consumer.accept(a);
        }
    }
}


