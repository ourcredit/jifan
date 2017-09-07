package com.yrkj.model.excel;

import org.jeecgframework.poi.excel.annotation.Excel;
import org.jeecgframework.poi.excel.annotation.ExcelTarget;

import java.util.Date;

/**
 * Created by 45425 on 2017/9/7.
 */
@ExcelTarget("ExcelOrder")
public class ExcelOrder {
    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getCate1() {
        return cate1;
    }

    public void setCate1(String cate1) {
        this.cate1 = cate1;
    }

    public String getCate2() {
        return cate2;
    }

    public void setCate2(String cate2) {
        this.cate2 = cate2;
    }

    public String getCate3() {
        return cate3;
    }

    public void setCate3(String cate3) {
        this.cate3 = cate3;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCreate_time() {
        return create_time;
    }

    public void setCreate_time(String create_time) {
        this.create_time = create_time;
    }

    public String getCity_name() {
        return city_name;
    }

    public void setCity_name(String city_name) {
        this.city_name = city_name;
    }

    public String getProvince_name() {
        return province_name;
    }

    public void setProvince_name(String province_name) {
        this.province_name = province_name;
    }
    @Excel(name = "商品名称", orderNum = "1", mergeVertical = false)
    private String productName;
    @Excel(name = "类别", orderNum = "5", mergeVertical = false)
    private String cate1;
    @Excel(name = "地区", orderNum = "6", mergeVertical = false)
    private String cate2;
    @Excel(name = "景区", orderNum = "7", mergeVertical = false)
    private String cate3;
    @Excel(name = "价格", orderNum = "2", mergeVertical = false)
    private String price;
    @Excel(name = "购买人", orderNum = "3", mergeVertical = false)
    private String receiver;
    private String address;
    @Excel(name = "购买时间", orderNum = "4", mergeVertical = false)
    private String create_time;
    @Excel(name = "市", orderNum = "9", mergeVertical = false)
    private String city_name;
    @Excel(name = "省", orderNum = "8", mergeVertical = false)
    private String province_name;

}
