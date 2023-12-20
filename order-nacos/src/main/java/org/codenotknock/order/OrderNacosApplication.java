package org.codenotknock.order;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class OrderNacosApplication {
    public static void main(String[] args) {
        SpringApplication.run(OrderNacosApplication.class, args);
    }

    // 实际开发中写在配置类中，这里仅仅测试
    // 通过构造器构造一个 RestTemplate
    @Bean
    @LoadBalanced   // 负载均衡 nacos 默认 Ribbon
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        RestTemplate restTemplate = builder.build();
        return restTemplate;
    }
}
