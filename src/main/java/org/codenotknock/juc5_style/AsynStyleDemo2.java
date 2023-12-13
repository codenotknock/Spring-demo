package org.codenotknock.juc5_style;

import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * @author xiaofu
 * 异步之工作线程
 * 饥饿现象: 怎么解决：做好分工，不同的任务、不同的线程池
 */

@Slf4j(topic = "c.AsynStyleDemo2")
public class AsynStyleDemo2 {
    static final List<String> MENU = Arrays.asList("地三鲜", "宫保鸡丁", "辣子鸡丁", "烤鸡翅");
    static Random RANDOM = new Random();
    static String cooking() {
        return MENU.get(RANDOM.nextInt(MENU.size()));
    }
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        executorService.execute(() -> {
            log.debug("处理点餐...");
            Future<String> f = executorService.submit(() -> {
                log.debug("做菜");
                return cooking();
            });
            try {
                log.debug("上菜: {}", f.get());
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        });
        /* executorService.execute(() -> {
             log.debug("处理点餐...");
             Future<String> f = executorService.submit(() -> {
             log.debug("做菜");
             return cooking();
             });
             try {
             log.debug("上菜: {}", f.get());
             } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
             }
           });*/

    }

}
