package com.yrkj.model.Integral;

import java.sql.Timestamp;

/**
 * Created by 45425 on 2017/8/15.
 */
public class CourierInput {
    public String getCourier_company() {
        return courier_company;
    }

    public void setCourier_company(String courier_company) {
        this.courier_company = courier_company;
    }

    public String getCourier_order() {
        return courier_order;
    }

    public void setCourier_order(String courier_order) {
        this.courier_order = courier_order;
    }

    public Timestamp getCourier_time() {
        return courier_time;
    }

    public void setCourier_time(Timestamp courier_time) {
        this.courier_time = courier_time;
    }

    private  String courier_company;
    private  String courier_order;
    private Timestamp courier_time;


}
