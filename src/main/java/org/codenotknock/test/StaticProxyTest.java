package org.codenotknock.test;

import org.codenotknock.domain.FastRestaurant;
import org.codenotknock.domain.Restaurant;
import org.codenotknock.proxy.RestaurantProxy;

public class StaticProxyTest {
    public static void main(String [] main){
        Restaurant restaurant = new FastRestaurant();
        RestaurantProxy proxy = new RestaurantProxy(restaurant);
        proxy.mealToSell();
    }
}
