package org.codenotknock.test;

import org.codenotknock.domain.Diner;
import org.codenotknock.proxy.CglibProxy;

public class CglibProxyTest {
  public static void main(String [] args){
        // 目标对象
        Diner diner = new Diner();
        System.out.println(diner.getClass());
        // 代理对象
        Diner proxy = (Diner) new CglibProxy().getProxy(diner);
        System.out.println(proxy.getClass());

        proxy.mealToSell();
        while (true) {}
    }
            
}
