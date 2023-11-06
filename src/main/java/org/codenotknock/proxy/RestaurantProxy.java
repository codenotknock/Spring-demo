package org.codenotknock.proxy;

import org.codenotknock.domain.Restaurant;

public class RestaurantProxy {

    private Restaurant restaurant;
    public RestaurantProxy(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    public void mealToSell() {
        mealToSellBefore();
        restaurant.mealToSell();
        mealToSellAfter();
    }
    private void mealToSellBefore() {
        System.out.println("外卖骑手接单.....");
    }
    private void mealToSellAfter() {
        System.out.println("外卖骑手送单成功.....跑腿费来喽！！");
    }

}
