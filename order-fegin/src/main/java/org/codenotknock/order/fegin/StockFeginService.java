package org.codenotknock.order.fegin;

import org.codenotknock.order.config.Feginconfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author xiaofu
 * fegin 的使用 展示 : 底层是动态代理
 * name : 指定调用 rest 接口所对应的服务名
 * path : 指定调用 rest 接口所在的 StockController 指定的 @RequestMapping
 */

@FeignClient(name="stock-service", path = "/stock", configuration = Feginconfig.class)
public interface StockFeginService {
    // 声明需要调用的 rest 接口对应的方法
    @GetMapping("/reduce")
    String reduce();

}

/*

public class StockController {

    @GetMapping("/reduce")
    public String reduce() {
        System.out.println("库存减少！");
        return "库存减少！";
    }
}

 */

