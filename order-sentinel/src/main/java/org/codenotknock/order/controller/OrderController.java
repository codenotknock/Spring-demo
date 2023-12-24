package org.codenotknock.order.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author xiaofu
 * @date 2023/12/20 2:10
 */

@RestController
@RequestMapping("/order")
public class OrderController {
    @PostMapping("/add")
    public String add() {
        System.out.println("下单成功！");
        return "下单成功！";
    }
}
