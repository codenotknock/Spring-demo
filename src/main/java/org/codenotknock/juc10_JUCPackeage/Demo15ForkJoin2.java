package org.codenotknock.juc10_JUCPackeage;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;
/**
 * @author xiaofu
 * 改进版： 其实是算法的改进，提高并行度
 */

public class Demo15ForkJoin2 {
    public static void main(String[] args) {
        ForkJoinPool pool = new ForkJoinPool();
        Integer invoke = pool.invoke(new MyTask2(1, 5));
        System.out.println(invoke);

        // 任务拆分 MyTask2 5 + 4 + 3 + 2 + 1

    }


}
// 1~n 结果和
class MyTask2 extends RecursiveTask<Integer> {
    private int start;
    private int end;

    public MyTask2(int start, int end) {
        this.start = start;
        this.end = end;
    }

    @Override
    protected Integer compute() {
        if (start == end) {
            return start;
        }
        if (end - start == 1) {
            return start + end;
        }
        int mid = (end +start) / 2;
        MyTask2 MyTask2 = new MyTask2(start, mid);
        MyTask2.fork();  // 让一个线程去执行  线程来自ForkJoinPool

        MyTask2 MyTask22 = new MyTask2(mid + 1, end);
        MyTask22.fork();  // 让一个线程去执行  线程来自ForkJoinPool

        int res =  MyTask22.join() + MyTask2.join();  // 获取线程的结果
        return res;
    }
}
