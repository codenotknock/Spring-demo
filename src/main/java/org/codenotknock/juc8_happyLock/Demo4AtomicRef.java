package org.codenotknock.juc8_happyLock;

import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicMarkableReference;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.atomic.AtomicStampedReference;
import java.util.function.IntUnaryOperator;

/**
 * @author xiaofu
 * 原子引用类型
 *  AtomicReference、AtomicMarkableReference、AtomicStampedReference
 */
@Slf4j(topic = "c.AtomicRef1")
public class Demo4AtomicRef {
    public static void main(String[] args) {
        DecimalAccountCas accountCas = new DecimalAccountCas(new BigDecimal("1000.9999"));
        for (int i = 0; i < 6; i++) {
            new Thread(() -> {
                    accountCas.withdraw(new BigDecimal(100));
            }, "t" + i).start();
        }

    }
}

class DecimalAccountCas implements DecimalAccount {
    private AtomicReference<BigDecimal> balance;
    public DecimalAccountCas(BigDecimal balance) {
        // 保护BigDecimal的这个对象
        this.balance = new AtomicReference<>(balance);

    }

    @Override
    public BigDecimal getBalance() {
        return balance.get();
    }
    @Override
    public void withdraw(BigDecimal admount) {
        while(true) {
            BigDecimal prev = balance.get();
            BigDecimal next = prev.subtract(admount);
            if (balance.compareAndSet(prev, next)) {
                break;
            }
        }
    }
}

interface DecimalAccount {
    BigDecimal getBalance();
    void withdraw(BigDecimal admount);
}