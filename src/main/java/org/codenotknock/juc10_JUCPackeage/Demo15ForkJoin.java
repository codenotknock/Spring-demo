package org.codenotknock.juc10_JUCPackeage;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

public class Demo15ForkJoin {
    public static void main(String[] args) {
        ForkJoinPool pool = new ForkJoinPool();
        Integer invoke = pool.invoke(new MyTask(5));
        System.out.println(invoke);

        // 任务拆分 MyTask 5 + 4 + 3 + 2 + 1

    }


}
// 1~n 结果和
class MyTask extends RecursiveTask<Integer> {
    private int n;

    public MyTask(int n) {
        this.n = n;
    }

    @Override
    protected Integer compute() {
        if (n == 1) {
            return 1;
        }
        MyTask myTask = new MyTask(n - 1);
        myTask.fork();  // 让一个线程去执行  线程来自ForkJoinPool

        int res = n + myTask.join();  // 获取线程的结果
        return res;
    }
}
