package org.codenotknock.order.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @author xiaofu
 * @date 2023/12/20 2:10
 */

@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private RestTemplate restTemplate;


    @PostMapping("/add")
    public String add() {
        System.out.println("下单成功！");
        String msg = restTemplate.getForObject("http://stock-service/stock/reduce", String.class);
        return "下单成功！" + msg ;
    }
}
