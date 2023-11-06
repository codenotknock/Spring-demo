package org.codenotknock.domain;

public class FastRestaurant implements Restaurant{
    @Override
    public void mealToSell() {
        System.out.println("餐饮店出售快餐！！");
    }
}
