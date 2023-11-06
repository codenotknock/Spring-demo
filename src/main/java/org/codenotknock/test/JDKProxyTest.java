package org.codenotknock.test;

import org.codenotknock.domain.FastRestaurant;
import org.codenotknock.domain.Restaurant;
import org.codenotknock.proxy.ProxyFactory;

public class JDKProxyTest {
    public static void main(String[] args) {
        Restaurant restaurant = new FastRestaurant();

        System.out.println(restaurant.getClass());

        Restaurant proxy = (Restaurant) new ProxyFactory(restaurant).getProxyInstance();

        proxy.mealToSell();

        System.out.println(proxy.getClass());

        while (true){}
    }
}
