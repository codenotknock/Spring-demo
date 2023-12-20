package org.codenotknock.springboot.controller;


import org.apache.ibatis.annotations.Mapper;
import org.codenotknock.springboot.mapper.StudentMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class HelloControllerTest {
    @Test
    public void test1() {
        System.out.println("1234...  ");
    }

    @Autowired
    private StudentMapper studentMapper;
    @Test
    public void findAllTest() {
        System.out.println(studentMapper.findAll());
    }
}