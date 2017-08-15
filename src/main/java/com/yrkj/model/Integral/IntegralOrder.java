package com.yrkj.model.Integral;

import com.yrkj.model.core.BaseModel;

import java.sql.Timestamp;

/**
 * Created by 45425 on 2017/8/15.
 */
public class IntegralOrder extends BaseModel {
    private String  order_num;//订单号
  private Integer  order_from;//积分商品id
  private String  order_name;//订单名
  private Integer  order_count;//商品个数
  private String  open_id;//用户openid
  private Integer  order_state=0;//状态
  private String  courier_company;//快递公司
  private String  courier_order;//快递单号
  private String courier_time;//快递时间
    private String address;
    private String receiver;
    private String phone;
    private Integer  order_cost;//话费积分

    public Integer getProduct_type() {
        return product_type;
    }

    public void setProduct_type(Integer product_type) {
        this.product_type = product_type;
    }

    private Integer product_type;
    public Integer getOrder_type() {
        return order_type;
    }

    public void setOrder_type(Integer order_type) {
        this.order_type = order_type;
    }

    private Integer order_type;
    public String getOrder_num() {
        return order_num;
    }

    public void setOrder_num(String order_num) {
        this.order_num = order_num;
    }

    public Integer getOrder_from() {
        return order_from;
    }

    public void setOrder_from(Integer order_from) {
        this.order_from = order_from;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getOrder_name() {
        return order_name;
    }

    public void setOrder_name(String order_name) {
        this.order_name = order_name;
    }

    public Integer getOrder_count() {
        return order_count;
    }

    public void setOrder_count(Integer order_count) {
        this.order_count = order_count;
    }

    public String getOpen_id() {
        return open_id;
    }

    public void setOpen_id(String open_id) {
        this.open_id = open_id;
    }

    public Integer getOrder_state() {
        return order_state;
    }

    public void setOrder_state(Integer order_state) {
        this.order_state = order_state;
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

    public Integer getOrder_cost() {
        return order_cost;
    }

    public void setOrder_cost(Integer order_cost) {
        this.order_cost = order_cost;
    }


}
