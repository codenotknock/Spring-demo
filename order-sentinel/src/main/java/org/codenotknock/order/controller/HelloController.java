package org.codenotknock.order.controller;

import com.alibaba.csp.sentinel.Entry;
import com.alibaba.csp.sentinel.SphU;
import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.alibaba.csp.sentinel.slots.block.RuleConstant;
import com.alibaba.csp.sentinel.slots.block.degrade.DegradeRule;
import com.alibaba.csp.sentinel.slots.block.degrade.DegradeRuleManager;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRule;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRuleManager;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

/**
 * @author xiaofu
 * sentinel 的简单使用
 */

@RestController
public class HelloController {
    private static final String RESOURCE_NAME = "hello";
    private static final String USER_RESOURCE_NAME = "user";
    private static final String DEGRADE_RESOURCE_NAME = "degrade";



    @PostMapping("/hello")
    public String hello() {
        Entry entry = null;

        try {
            // sentinel 针对资源进行限制  资源名称要和接口的地址一致  针对接口进行限流
            entry = SphU.entry(RESOURCE_NAME);
            // 被保护的业务逻辑
            String str = "hello world!";
            System.out.println("================" + str + "============");
            return str;
        } catch (BlockException e) {
            System.out.println("出师不利。。。被限制了");
            return "被流控了！！";
        } finally {
            if (null != entry) {
                entry.exit();
            }
        }
    }


//    spring 的初始化方法
    @PostConstruct  // spring init
    public static  void initFlowRules() {
        // 流控规则
        List<FlowRule> rules = new ArrayList<FlowRule>();

        // 流量控制 新建一个流控规则
        FlowRule flowRule = new FlowRule();
        // 设置受保护的资源
        flowRule.setResource(RESOURCE_NAME);
        // 设置规则 qbs
        flowRule.setGrade(RuleConstant.FLOW_GRADE_QPS);
        // 设置阈值(单机阈值)
        flowRule.setCount(1);

        rules.add(flowRule);

        // 加载配置好的规则
        FlowRuleManager.loadRules(rules);

    }

    /**
     * @SentinelResource 改善接口中资源定义和被流控降级后的处理方法  基于切面所实现的
     * 使用：1.引入依赖 2.配置bean sentinelResourceAspect
     * vlue 定义资源     blockHandler 设置流控降级后的处理方法（默认该方法必须声明在同一个类中）
     * blockHandler 1.必须是public  2.返回值要和源方法一致  3.保护原方法的参数和BlockException
     * fallback : 当接口出现了异常 fallck 可以处理异常
     * 流控降级 blockHandler 的方法优先级高于 fallback
     * exceptionsToIgnore : 排除异常，不做处理
     */
    @PostMapping("/hello2")
    @SentinelResource(value = USER_RESOURCE_NAME, blockHandler = "blockHandlerForHello2",
                    fallback = "fallbackHandleForHello2", exceptionsToIgnore = {ClassNotFoundException.class}
    )
    public String hello2() {
        int x = 1/0;
        return "hello world!";
    }


    public String blockHandlerForHello2(BlockException ex) {
//        1.必须是public  2.返回值要和源方法一致  3.保护原方法的参数和BlockException
        ex.printStackTrace();
        return "被流控了！！";
    }

    public String fallbackHandleForHello2(Throwable e) {
        // 如果不在同一个类中，和 blockHandler 规则一致
        e.printStackTrace();
        return "诶！！异常了！";
    }


    @PostConstruct
    public static  void initDegradeRules() {
        // 降级规则
        List<DegradeRule> rules = new ArrayList<DegradeRule>();
        // 降级处理 新建一个降级规则
        DegradeRule degradeRule = new DegradeRule();
        // 设置受保护的资源
        degradeRule.setResource(RESOURCE_NAME);
        // 设置规则 异常数量
        degradeRule.setGrade(RuleConstant.DEGRADE_GRADE_EXCEPTION_COUNT);
        // 设置异常数量 2
        degradeRule.setCount(2);
        // 触发熔断的最小请求数量 2
        degradeRule.setMinRequestAmount(2);
        // 统计时长：在多少时间内的触发  单位ms
//        degradeRule.setStatIntervalMs(60 * 1000);


        // 熔断持续时间 10s ，触发熔断之后再次请求接口会调用降级处理方法 blockHandler
        // 10s 之后 会进入半开状态，恢复接口请求，但如果第一次就异常，会直接进入再次熔断状态（不会根据设置判定）
        degradeRule.setTimeWindow(10);

        rules.add(degradeRule);

        // 加载配置好的规则
        DegradeRuleManager.loadRules(rules);

    }
}
