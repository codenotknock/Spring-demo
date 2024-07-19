package org.codenotknock.test;

import java.io.IOException;

/**
 * @author xiaofu
 * @date 2023/11/21 18:06
 */

public class FrameDemo2 {

    // 看一下栈的内存溢出 java.lang.StackOverflowError
    private static void dfs(int i){
        dfs(i++);
    }

    public static void main(String [] args) {
        int i = 1;
        dfs(i);
    }

}
