package org.codenotknock.stock;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author xiaofu
 * @date 2023/12/20 2:15
 */
@RestController
@RequestMapping("/stock")
public class StockController {
    @Value(value = "${server.port}")
    private String port;

    @GetMapping("/reduce")
    public String reduce() {
        System.out.println("库存减少！");
        return "库存减少！" + port;
    }
}
