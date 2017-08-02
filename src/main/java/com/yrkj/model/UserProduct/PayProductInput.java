package com.yrkj.model.UserProduct;

/**
 * Created by xuenianxiang on 2017/8/2.
 */
public class PayProductInput {

    private Long product_id;

    private int number;

    private float price;

    public Long getProduct_id() {
        return product_id;
    }

    public void setProduct_id(Long product_id) {
        this.product_id = product_id;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }
}
