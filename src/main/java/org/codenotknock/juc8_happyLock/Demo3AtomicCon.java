package org.codenotknock.juc8_happyLock;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.IntUnaryOperator;

/**
 * @author xiaofu
 * AtomicInteger、AtomicLong、AtomicBoolean
 */
public class Demo3AtomicCon {
    public static void main(String[] args) {
        AtomicInteger value = new AtomicInteger(9);
        Demo3AtomicCon.updateAndGet(value, operand -> operand + 9);
        System.out.println(value);
    }
    private static void updateAndGet(AtomicInteger value, IntUnaryOperator updateFunction) {
        while (true) {
            int prev = value.get();
            int next = updateFunction.applyAsInt(prev);
            if (value.compareAndSet(prev, next)) {
                break;
            }
            // AtomicInteger.updateAndGet 内部思想实现是一致的
            /*
                public final int updateAndGet(IntUnaryOperator updateFunction) {
                    int prev, next;
                    do {
                        prev = get();
                        next = updateFunction.applyAsInt(prev);
                    } while (!compareAndSet(prev, next));
                    return next;
                }

             */

        }
    }
}
