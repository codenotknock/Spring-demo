package org.codenotknock.springboot.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class HelloController {

    @Value("${student.firstName}")
    private String firstName;
    @Value("${student.name}")
    private String name;

    @PostMapping("/hello")
    public String hello() {
        return "hello " + name;
    }

    @Autowired
    private Student student;

    @PostMapping("/hello1")
    public String hello1 (){
        return student.toString();
    }

}
