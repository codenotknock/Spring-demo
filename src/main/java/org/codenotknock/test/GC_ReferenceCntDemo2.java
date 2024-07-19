package org.codenotknock.test;

public class GC_ReferenceCntDemo2 {
    /*
    GC Root对象的判断，四大类：
        1. 线程Thread对象（主线程、线程），引用线程栈帧中的方法参数、局部变量等
        2. 系统类加载器加载的java.lang.Class对象
        3. 监视器对象，用来保存同步锁synchronized关键字持有的对象
        4. 本地方法调用时使用的全局对象

     */
    //  线程Thread对象（主线程、线程），引用线程栈帧中的方法参数、局部变量等
    // 这里GC Root是堆中主线程对象，引用栈帧中 A1 a1、B1 b1
    public static void main(String [] args) {
        // Java虚拟机参数：查看垃圾回收日志    -verbose:gc
        while (true) {
            A1 a1 = new A1();
            B1 b1 = new B1();
            // 循环引用
            a1.b1 = b1;
            b1.a1 = a1;

            a1 = null;
            b1 = null;

            // 手动GC，向Java虚拟机发送垃圾回收请求
            System.gc();

            // 结果：垃圾回收日志表明这段代码并没有出现内存泄漏的问题
            /*
            [GC (System.gc())  916K->916K(241664K), 0.0003394 secs]
            [Full GC (System.gc())  916K->916K(241664K), 0.0040462 secs]
            [GC (System.gc())  916K->948K(241664K), 0.0006642 secs]
            [Full GC (System.gc())  948K->916K(241664K), 0.0054779 secs]
            [GC (System.gc())  2186K->980K(241664K), 0.0003964 secs]
            [Full GC (System.gc())  980K->916K(241664K), 0.0041481 secs]
            ....

            [GC (System.gc()) 2186K->980K(241664K), 0.0003964 secs]
                GC表示Minor GC（年轻代垃圾回收），System.gc()表示通过调用System.gc()方法触发的垃圾回收。
                2186K->980K表示在进行垃圾回收前后年轻代的内存利用情况，初始内存占用2186K，回收后内存占用980K。
                (241664K)表示年轻代总共的可用内存。
                0.0003964 secs表示垃圾回收所花费的时间，单位为秒。
            [Full GC (System.gc()) 980K->916K(241664K), 0.0041481 secs]
                Full GC表示Full GC（完全垃圾回收），System.gc()同样表示通过调用System.gc()方法触发的垃圾回收。
                980K->916K表示在进行垃圾回收前后堆内存的内存利用情况，初始堆内存占用980K，回收后堆内存占用916K。
                (241664K)同样表示堆内存总共的可用内存。
                0.0041481 secs表示垃圾回收所花费的时间，单位为秒。
             */
             /*
            Java使用的是可达性分析法来判断对象是否可以被回收。可达性分析法将对象分为两类：
                垃圾回收的根对象GC Root 和 普通对象，对象和对象之间存在引用关系
                    */
        }
    }
}

class A1 {
    B1 b1;
}
class B1 {
    A1 a1;
}
