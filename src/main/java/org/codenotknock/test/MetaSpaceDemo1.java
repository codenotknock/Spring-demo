package org.codenotknock.test;

import jdk.internal.org.objectweb.asm.ClassWriter;
import net.bytebuddy.jar.asm.Opcodes;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author xiaofu
 * @date 2023/11/21 18:11
 */


public class MetaSpaceDemo1 extends ClassLoader{

    // 看一下的方法区的溢出
    // jdk8之前是：永久代溢出java.lang.OutOfMemoryError: PermGen space ： 运行了是十几万次就报错了。原因是jak7将方法区存放在堆区域中的永久代空间,大小由参数 -XX:MaxPermSize=大小 进行控制堆的大小
    // jdk8及其之后，运行一直没有报错java.lang.OutOfMemoryError: Metaspace。原因是jdk8将方法区存放在元空间中，元空间位于操作系统维护的直接内存中，默认不超过操作系统承受内存上限可以一直分配。大小由参数 -XX:MaxMetaspaceSize=大小 进行控制元空间大小
    // 避免运行时间过长，可以先把堆内存设置的小一些 或者放比较大的数据 比如100M的数据
    public static void main(String [] args) throws IOException {
        System.in.read();
        MetaSpaceDemo1 metaSpaceDemo1 = new MetaSpaceDemo1();
        int cnt = 0;
        while (true) {
            String name = "Class" + cnt;
            ClassWriter classWriter = new ClassWriter(0);
            classWriter.visit(Opcodes.V1_8,Opcodes.ACC_PUBLIC,name,null,"java/lang/Object",null);
            byte[] bytes = classWriter.toByteArray();

            metaSpaceDemo1.defineClass(name, bytes, 0, bytes.length);
            System.out.println(++cnt);
        }

    }

}
