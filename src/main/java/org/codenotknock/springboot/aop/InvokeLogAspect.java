package org.codenotknock.springboot.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * @author xiaofu
 */

@Aspect  // 切面类的标识
@Component
public class InvokeLogAspect {

    @Pointcut("@annotation(org.codenotknock.springboot.aop.InvokeLog)")
    public void pt() {
        // @Pointcut 切点

    }

    @Around("pt()")
    public Object PrintInvokeLog(ProceedingJoinPoint joinPoint) {
        //  @Around 环绕通知
        Object proceed = null;

        // 目标方法调用前
        try {
            proceed = joinPoint.proceed();
            // 目标方法调用时

        } catch (Throwable e) {
            e.printStackTrace();
            // 目标方法发生异常
        }
        // 目标方法调用后

        return proceed;
    }
}
