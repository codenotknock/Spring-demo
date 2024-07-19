package org.codenotknock.juc8_happyLock;

import sun.misc.Unsafe;

import java.lang.reflect.Field;

/**
 * @author xiaofu
 * 底层的UnSafe对象
 */

public class Demo12UnSafe {
    public static void main(String[] args) throws NoSuchFieldException {
        Unsafe unsafe = Cas_UnSafe.getUnsafe();
        System.out.println(unsafe);

        Teacher teacher = new Teacher();
        // 1.获取域的偏移地址
        long id = unsafe.objectFieldOffset(Teacher.class.getDeclaredField("id"));
        long name = unsafe.objectFieldOffset(Teacher.class.getDeclaredField("name"));

        // 2.执行 cas 操作
        unsafe.compareAndSwapInt(teacher, id, 0, 9);
        unsafe.compareAndSwapObject(teacher, name, null, "小米");

        System.out.println(teacher);



    }
}

class Teacher {
    volatile int id;
    volatile String name;

    @Override
    public String toString() {
        return "Teacher{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}


class Cas_UnSafe {
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