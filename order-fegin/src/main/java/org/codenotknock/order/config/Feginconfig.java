package org.codenotknock.order.config;

import feign.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author xiaofu
        * 远程调用的全局配置 日志
        * 全局配置：当使用 @Configuration 时，会将其配置作用于所有的服务提供方
        * 局部配置：针对某一个服务进行配置，就不要加注解 @Configuration
 */
@Configuration
public class Feginconfig {

    @Bean
    public Logger.Level feginLoggerLevel() {
        return Logger.Level.FULL;
    }

}
