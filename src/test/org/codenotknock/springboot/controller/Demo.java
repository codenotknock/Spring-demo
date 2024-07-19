package org.codenotknock.springboot.controller;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;

/**
 * @author xiaofu
 */
@SpringBootTest
public class Demo {
    @Autowired
    private StringRedisTemplate redisTemplate;

    @Test
    public void redisTest() {
        redisTemplate.opsForValue().set("name", "xiaofu");
    }
    @Test
    public void redisTest2() {
        String name = redisTemplate.opsForValue().get("name");
        System.out.println(name);
    }

}
