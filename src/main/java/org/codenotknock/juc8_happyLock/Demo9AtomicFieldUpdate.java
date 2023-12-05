package org.codenotknock.juc8_happyLock;

import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;

/**
 * @author xiaofu
 * 字段更新器
 * AtomicIntegerFieldUpdater、AtomicLongFieldUpdater、AtomicReferenceFieldUpdater
 */
public class Demo9AtomicFieldUpdate {
    public static void main(String[] args) {
        People people = new People();
        // 属性必须是 volatile 保证可见性    cas操作必须有volatile的支持，要不然怎么保证可见性呢
        // 不能是 private 修饰字段
        //    因为AtomicReferenceFieldUpdater使用反射机制来访问和修改字段的值，而私有字段是无法直接通过反射来访问的。
        AtomicReferenceFieldUpdater updater = AtomicReferenceFieldUpdater.newUpdater(People.class, Integer.class, "age");

        updater.compareAndSet(people, null, 18);
        System.out.println(people);
    }

}

class People{
     volatile String name;
     volatile Integer age;

    public People(){}
    public People(String name, Integer age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public String toString() {
        return "People{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}