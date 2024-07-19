package org.codenotknock.juc9;

import lombok.extern.slf4j.Slf4j;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * @author xiaofu
 * SimpleDateFormat 不是线程安全的
 * 优化
 *      - 思路一 使用synchronized 加锁， 但是性能慢
 *      - 思路二 不可变对象：java8提供了新的日期格式化类 DateTimeFormatter  是不可变对象
 *      - 思路三：ThreadLocal 实现
 */

@Slf4j(topic = "c.Demo1SimDateParse2")
public class Demo2SimDateParse {
    public static void main(String[] args) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                synchronized (sdf) {
                    try {
                        log.debug("{}", sdf.parse("1951-04-21"));
                    }
                    catch (Exception e) {
                        log.error("{}", e);
                    }
                }

            }).start();
        }

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                LocalDate date = dtf.parse("2023-12-06", LocalDate::from);
                log.debug("{}", date);
            }).start();
        }
    }
}
