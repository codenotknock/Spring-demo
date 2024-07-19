package org.codenotknock.juc8_happyLock;

import sun.misc.Unsafe;

import java.lang.reflect.Field;

/**
 * @author xiaofu
 * 底层的UnSafe对象
 *
 * 模拟实现原子整数
 */

public class Demo13UnSafe {
    public static void main(String[] args) throws NoSuchFieldException {
        Account.demo(new MyAtomicInteger(1000));

    }
}

class MyAtomicInteger implements Account{
    private volatile int value;
    private static final long valueOffset;
    static final Unsafe UNSAFE;

    static {
        UNSAFE = Cas_UnSafe1.getUnsafe();
        try {
            valueOffset = UNSAFE.objectFieldOffset(MyAtomicInteger.class.getDeclaredField("value"));
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public int getValue(){
        return value;
    }
    public void decrement(int amount) {
        while (true) {
            int prev = this.value;
            int next = prev - amount;
            if (UNSAFE.compareAndSwapInt(this, valueOffset, prev, next)) {
                break;
            }
        }
    }

    public MyAtomicInteger(int value) {
        this.value = value;
    }
    @Override
    public Integer getBalance() {
        return getValue();
    }

    @Override
    public void withdraw(Integer amount) {
        decrement(amount);
    }
}


class Cas_UnSafe1 {
    static Unsafe unsafe;
    static {
        try {
            Field theUnsafe = Unsafe.class.getDeclaredField("theUnsafe");
            theUnsafe.setAccessible(true);
            unsafe = (Unsafe) theUnsafe.get(null);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            throw new Error(e);
        }
    }
    static Unsafe getUnsafe() {
        return unsafe;
    }
}