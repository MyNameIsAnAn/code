package com.bw.jdxiangmu.gouwuche.event;

/**
 * Created by lenovo on 2018/4/9.
 */

public class PriceAndCountEvent {
    private int price;
    private int count;

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
