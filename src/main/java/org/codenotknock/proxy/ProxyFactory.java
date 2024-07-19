package org.codenotknock.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class ProxyFactory {
    // 目标方法
    public Object target;
    public ProxyFactory(Object target) {
        this.target = target;
    }

    public Object getProxyInstance() {
        return Proxy.newProxyInstance(
                // 目标对象的类加载器
                target.getClass().getClassLoader(),
                // 目标对象的接口类型
                target.getClass().getInterfaces(),
                // 事件处理器
                new InvocationHandler() {
                    /**
                     *
                     * @param proxy  代理对象
                     * @param method 代理对象调用的方法
                     * @param args   代理对象调用方法时实际的参数
                     * @return
                     * @throws Throwable
                     */
                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        System.out.println("前置增强");
                        method.invoke(target, args);
                        System.out.println("后置增强");
                        return null;
                    }
                }
        );
    }
}
