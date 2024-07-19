package org.codenotknock.springboot.controller;


import org.codenotknock.springboot.handler.CurrentUserId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


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


    @PostMapping("/test1")
    public void getRequestAndResponse(HttpServletRequest request,
                                      HttpServletResponse response,
                                      HttpSession session) {
        // 能够获取到 web 请求的原生数据
        System.out.println(request);
    }

    @PostMapping("/test2")
    public void getUser(@CurrentUserId String userId) {
        // 从获取到 web 请求的原生数据 获取id  自定义参数解析器
        System.out.println(userId);
    }


}
