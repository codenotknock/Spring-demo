package org.codenotknock.juc8_happyLock;


import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.LongAdder;
import java.util.function.Consumer;
import java.util.function.Supplier;

/**
 * @author xiaofu
 * 原子累加器 LongAdder
 *
 * AtomicLong 与 LongAdder 性能比较
 */
public class Demo10Atomic_LongAdder {
    public static void main(String[] args) {
        for (int i = 0; i < 5; i++) {
            demo(() -> new LongAdder(), adder -> adder.increment());
        }
        /* LongAdder  设置了多个累加单元共同累加，最后再进行汇总；并发度高且性能好
            20000000 cost:67
            20000000 cost:12
            20000000 cost:13
            20000000 cost:35
            20000000 cost:12
         */
        for (int i = 0; i < 5; i++) {
            demo(() -> new AtomicLong(), adder -> adder.getAndIncrement());
        }
        /* AtomicLong   cas有竞争的时候while循环不断尝试更新，重试次数多性能就会变慢
            20000000 cost:35
            20000000 cost:12
            20000000 cost:304
            20000000 cost:301
            20000000 cost:303
            20000000 cost:315
            20000000 cost:179
         */
    }

    private static <T> void demo(Supplier<T> adderSupplier, Consumer<T> action) {
        T adder = adderSupplier.get();
        long start = System.nanoTime();
        List<Thread> ts = new ArrayList<>();
        // 4 个线程，每人累加 50 万
        for (int i = 0; i < 40; i++) {
            ts.add(new Thread(() -> {
                for (int j = 0; j < 500000; j++) {
                    action.accept(adder);
                }
            }));
        }
        ts.forEach(t -> t.start());
        ts.forEach(t -> {
            try {
                t.join();
            }
            catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        long end = System.nanoTime();
        System.out.println(adder + " cost:" + (end - start)/1000_000);
    }
}
