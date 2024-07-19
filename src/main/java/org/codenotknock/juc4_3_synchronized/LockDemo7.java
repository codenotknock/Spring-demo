package org.codenotknock.juc4_3_synchronized;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Fork;
import org.openjdk.jmh.annotations.Measurement;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Warmup;

import java.util.concurrent.TimeUnit;

/**
 * @author xiaofu
 * 性能比较：结果是差不多的，因为锁消除的优化
 * 锁消除——利用jmh工具:需要单独打包成jar包进行运行
 *  java -jar LockDemo7.jar
 */

@Fork(1)
@BenchmarkMode(Mode.AverageTime)
@Warmup(iterations=3)
@Measurement(iterations=5)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
public class LockDemo7 {
    static int x = 0;

    @Benchmark
    public void a() throws Exception {
        x++;
    }

    @Benchmark
    // JIT 即时编译器，会对Java字节码进一步优化【Java是解释+执行的】
    public void b() throws Exception {
        Object o = new Object();
        // JIT 会把 synchronized 优化掉，锁消除
        // 因为o这个对象没有其它线程，且这个synchronized无意义，JIT会把它优化掉
        synchronized (o) {
            x++;
        }
    }
}
