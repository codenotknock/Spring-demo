package org.codenotknock.order.ribbon.rule;

import com.alibaba.nacos.shaded.io.grpc.netty.shaded.io.netty.util.internal.ThreadLocalRandom;
import com.netflix.loadbalancer.AbstractLoadBalancerRule;
import com.netflix.loadbalancer.ILoadBalancer;
import com.netflix.loadbalancer.Server;

import java.util.List;

/**
 * @author xiaofu
 * 自定义负载均衡：选择一个随机的实例
 * nacos 2021 及之后的版本已经移除了Ribbon支持，如果需要实现负载均衡，
 * 官方推荐替代方案是采用Spring Cloud LoadBalancer
 */

public class CustomRule extends AbstractLoadBalancerRule {

    @Override
    public Server choose(Object o) {
        ILoadBalancer loadBalancer = this.getLoadBalancer();

        // 获得当前请求的服务的实例
        List<Server> reachableServers = loadBalancer.getReachableServers();
        int random = ThreadLocalRandom.current().nextInt(reachableServers.size());
        Server server = reachableServers.get(random);

        return server;
    }
}
