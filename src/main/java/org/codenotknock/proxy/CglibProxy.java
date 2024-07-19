package org.codenotknock.proxy;


import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

public class CglibProxy implements MethodInterceptor {
    /**
     * 生成 CGLIB 动态代理类方法
     * @param target
     * @return
     */
    public Object getProxy(Object target) {
        // 增强器类，用来创建动态代理类
        Enhancer enhancer = new Enhancer();

        // 设置代理类的父类字节码对象
        enhancer.setSuperclass(target.getClass());

        // 设置回调
        enhancer.setCallback(this);

        // 创建动态代理对象并返回
        return enhancer.create();

    }


    /**
     * @param o         代理对象
     * @param method      目标对象中的方法的Method实例
     * @param objects     实际参数
     * @param methodProxy   代理类对象中的方法的Method实例
     * @return
     * @throws Throwable
     */
    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {

        System.out.println("前置输出-外卖骑手接单.....");
        Object result = methodProxy.invokeSuper(o, objects);
        System.out.println("后置输出-外卖骑手送单成功.....跑腿费来喽！！.....");
        return result;
    }

}
