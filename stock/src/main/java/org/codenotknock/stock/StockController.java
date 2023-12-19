package org.codenotknock.stock;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author xiaofu
 * @date 2023/12/20 2:15
 */
@RestController
@RequestMapping("/stock")
public class StockController {

    @PostMapping("/reduct")
    public String reduct() {
        System.out.println("库存减少！");
        return "库存减少！";
    }
}
