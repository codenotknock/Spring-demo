package org.codenotknock.stream.lambad;

import java.util.function.IntBinaryOperator;
import java.util.function.IntPredicate;

/**
 * @author xiaofu
 * @date 2023/12/15 20:41
 * @description
 */
public class LambadDemo1 {
    public static void main(String[] args) {
        // demo 匿名内部类的写法
        new Thread(() -> {
            System.out.println("run ...");
        }, "t1").start();

        calculateNum(new IntBinaryOperator() {
            @Override
            public int applyAsInt(int left, int right) {
                return left + right;
            }
        });

        int i = calculateNum((int a, int b) -> {
            return a + b;
        });
        System.out.println(i);


        printNum(new IntPredicate() {
            @Override
            public boolean test(int value) {
                return value % 2 == 0;
            }
        });
        printNum(value-> value % 2 == 0);

    }

    public static int calculateNum(IntBinaryOperator operator) {
//        它有一个抽象方法 applyAsInt(int left, int right)，用于执行对两个整数的操作并返回一个整数结果
        int a = 10;
        int b = 20;
        return operator.applyAsInt(a, b);
    }



   public static void printNum(IntPredicate predicate) {
//       IntPredicate 接口表示一个以 int 为输入的谓词，它有一个抽象方法 test(int value)，用于测试给定的整数是否满足条件
        int [] arr = {1, 2, 3, 4, 5};
       for (int i = 0; i < arr.length; i++) {
           if (predicate.test(i)) {
               System.out.println(i);
           }
       }
   }
}
