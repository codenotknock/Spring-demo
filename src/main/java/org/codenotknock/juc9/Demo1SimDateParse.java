package org.codenotknock.juc9;

import lombok.extern.slf4j.Slf4j;

import java.text.SimpleDateFormat;

/**
 * @author xiaofu
 *
 */

@Slf4j(topic = "c.Demo1SimDateParse")
public class Demo1SimDateParse {
    public static void main(String[] args) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        for (int i = 0; i < 10; i++) {
            // 有很大几率出现 java.lang.NumberFormatException 或者出现不正确的日期解析结果
            // 因为 SimpleDateFormat 不是线程安全的
            new Thread(() -> {
                try {
                    log.debug("{}", sdf.parse("1951-04-21"));
                }
                catch (Exception e) {
                    log.error("{}", e);
                }
            }).start();
        }
    }
}
