package org.codenotknock.mybatisPlus.mapper;

import org.codenotknock.mybatisPlus.vo.domain.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class UserMapperTest {
    @Autowired
    private UserMapper userMapper;

    @Test
    void queryById() {
        User user = userMapper.selectById(1L);
        System.out.println(user);
    }
    @Test
    void queryById2() {
        System.out.println("sadasdas");
    }
}
