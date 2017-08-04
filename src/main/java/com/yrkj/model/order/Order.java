package com.yrkj.model.order;

import java.util.Date;
import java.util.List;

/**
 * Created by xuenianxiang on 2017/8/2.
 */
public class Order {

    private Long id;

    private String order_num;

    private String order_name;

    private String open_id;

    private int order_state;

    private Float product_cost;

    private String courier_company;

    private String courier_order;

    private String courier_time;

    private Float courier_cost;

    private Date create_time;

    private Date update_time;

    private List list;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOrder_num() {
        return order_num;
    }

    public void setOrder_num(String order_num) {
        this.order_num = order_num;
    }

    public String getOrder_name() {
        return order_name;
    }

    public void setOrder_name(String order_name) {
        this.order_name = order_name;
    }

    public String getOpen_id() {
        return open_id;
    }

    public void setOpen_id(String open_id) {
        this.open_id = open_id;
    }

    public int getOrder_state() {
        return order_state;
    }

    public void setOrder_state(int order_state) {
        this.order_state = order_state;
    }

    public Float getProduct_cost() {
        return product_cost;
    }

    public void setProduct_cost(Float product_cost) {
        this.product_cost = product_cost;
    }

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

    public String getCourier_time() {
        return courier_time;
    }

    public void setCourier_time(String courier_time) {
        this.courier_time = courier_time;
    }

    public Float getCourier_cost() {
        return courier_cost;
    }

    public void setCourier_cost(Float courier_cost) {
        this.courier_cost = courier_cost;
    }

    public Date getCreate_time() {
        return create_time;
    }

    public void setCreate_time(Date create_time) {
        this.create_time = create_time;
    }

    public Date getUpdate_time() {
        return update_time;
    }

    public void setUpdate_time(Date update_time) {
        this.update_time = update_time;
    }

    public List getList() {
        return list;
    }

    public void setList(List list) {
        this.list = list;
    }
}
