package com.yrkj.model.postages;

import com.yrkj.model.core.BaseModel;

/**
 * Created by 45425 on 2017/8/1.
 */
public class Postage extends BaseModel {
    private String province;

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    private double price;
}
