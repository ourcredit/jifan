package com.yrkj.model.order;

import java.sql.Timestamp;

/**
 * Created by 45425 on 2017/8/15.
 */
public class OrderDto {
   private Long id;
   private String order_num;
   private String order_name;
   private String open_id;
   private Integer order_state;
   private Float product_cost;
   private String courier_company;
   private String courier_order;
   private Timestamp courier_time;
   private Float courier_cost;
   private Integer province_id;
   private Integer city_id;
   private String province_name;
   private String city_name;
   private String address;
   private String receiver;
   private String phone;
   private String product_name;
   private Integer number;
   private Float price;

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

    public Integer getOrder_state() {
        return order_state;
    }

    public void setOrder_state(Integer order_state) {
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

    public Timestamp getCourier_time() {
        return courier_time;
    }

    public void setCourier_time(Timestamp courier_time) {
        this.courier_time = courier_time;
    }

    public Float getCourier_cost() {
        return courier_cost;
    }

    public void setCourier_cost(Float courier_cost) {
        this.courier_cost = courier_cost;
    }

    public Integer getProvince_id() {
        return province_id;
    }

    public void setProvince_id(Integer province_id) {
        this.province_id = province_id;
    }

    public Integer getCity_id() {
        return city_id;
    }

    public void setCity_id(Integer city_id) {
        this.city_id = city_id;
    }

    public String getProvince_name() {
        return province_name;
    }

    public void setProvince_name(String province_name) {
        this.province_name = province_name;
    }

    public String getCity_name() {
        return city_name;
    }

    public void setCity_name(String city_name) {
        this.city_name = city_name;
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

    public String getProduct_name() {
        return product_name;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }
}
