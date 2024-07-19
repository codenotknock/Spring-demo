package org.codenotknock.aop;

import org.springframework.stereotype.Service;

@Service
public class HelloService {
    public HelloService(){
        System.out.println("hello ....");
    }

    public String sayHello(String name){
        String res = "hello" + name;
        System.out.println(res);
        return res + "----" + name.length();
    }

}
