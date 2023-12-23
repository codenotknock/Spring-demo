package org.codenotknock.order.ribbon;

import com.netflix.loadbalancer.AbstractLoadBalancerRule;
import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RibboinRandomRuleConfig {

    @Bean
    public IRule iRule() {
        // 方法名只能叫 iRule
        return new RandomRule();
    }
}
