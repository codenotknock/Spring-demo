package org.codenotknock.juc8_happyLock;


import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author xiaofu
 * cas（无锁-乐观锁） 相对于 synchronized 性能较好
 * CAS、volatile   cas操作需要volatile的支持，因为cas每次比较需要获取到最新的值
 *
 * compareAndSet 一直再循环为什么性能高呢？
 * 即使重试失败，线程始终在高速运行，多核cpu；单核cpu因为没有时间片会发生线程上下文切换，性能还是慢
 * 而synchronized会让线程发生上下文切换
 *
 */

public class Demo1Account{
    public static void main(String[] args) {
        AccountUnsafe account = new AccountUnsafe(10000);
        Account.demo(account);

        AccountSafe account1 = new AccountSafe(10000);
        Account.demo(account1);
    }
}

class AccountSafe implements Account {
    // AtomicInteger 的值 在源码中可以看到是被volatile修饰的，保证其可见性。
    // cas操作需要volatile的支持，因为cas每次比较需要获取到最新的值
    private AtomicInteger balance;

    public AccountSafe(Integer balance) {
        this.balance = new AtomicInteger(balance);
    }

    @Override
    public Integer getBalance() {
        return balance.get();
    }

    @Override
    public void withdraw(Integer amount) {
        while (true) {
            int prev = balance.get();
            int next = prev - amount;
            // compareAndSet 内部是原子的： compare and swap   compare 是和最新值对比
            if (balance.compareAndSet(prev, next)) {
                break;
            }
        }
    }
}
class AccountUnsafe implements Account {
    // 不安全，使用 synchronized 修饰
    private Integer balance;
    public AccountUnsafe(Integer balance) {
        this.balance = balance;
    }
    @Override
    public synchronized Integer getBalance() {
        return balance;
    }
    @Override
    public synchronized void withdraw(Integer amount) {
        balance -= amount;
    }
}
interface Account {
    // 获取金额
    Integer getBalance();

    // 取款
    void withdraw(Integer amount);
    /**
     * 方法内会启动 1000 个线程，每个线程做 -10 元 的操作
     * 如果初始余额为 10000 那么正确的结果应当是 0
     */
    static void demo(Account account) {
        List<Thread> ts = new ArrayList<>();
        long start = System.nanoTime();
        for (int i = 0; i < 1000; i++) {
            ts.add(new Thread(() -> {
                account.withdraw(10);
            }));
        }
        ts.forEach(Thread::start);
        ts.forEach(t -> {
            try {
                t.join();
            }
            catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        long end = System.nanoTime();
        System.out.println(account.getBalance() + " cost: " + (end-start)/1000_000 + " ms");
    }

}