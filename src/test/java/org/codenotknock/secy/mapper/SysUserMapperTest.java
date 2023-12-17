package org.codenotknock.secy.mapper;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootTest
class SysUserMapperTest {

    @Autowired
    private SysUserMapper userMapper;


    @Test
    public void BCryptPasswordEncoder() {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        System.out.println(encoder.encode("1234"));
        System.out.println(encoder.encode("1234"));
        System.out.println(encoder.matches("1234", "$2a$10$w57Teds8h9Vx7cqZwo1p5Orf8EL4Ksg2wU3XXIDDEHr4RV5wNxhJ6"));
    }
  
}