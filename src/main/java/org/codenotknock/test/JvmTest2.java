package org.codenotknock.test;

public class JvmTest2 {
    public static void main(String [] args) {
        int i = 0, j = 0, k = 0;
        i ++;
        j = j + 1;
        k += 1;
        /*
        思考：分析三种“+1”操作性能的高低
        提示：一般来说，字节码对应的条数越多性能越差
            分析一下对应的字节码文件
             0 iconst_0
             1 istore_1
             2 iconst_0
             3 istore_2
             4 iconst_0
             5 istore_3
             6 iinc 1 by 1
             9 iload_2
            10 iconst_1
            11 iadd
            12 istore_2
            13 iinc 3 by 1
            16 return

         */
    }
}
