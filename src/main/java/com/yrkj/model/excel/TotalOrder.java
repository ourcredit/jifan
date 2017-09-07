package com.yrkj.model.excel;

import org.jeecgframework.poi.excel.annotation.Excel;
import org.jeecgframework.poi.excel.annotation.ExcelTarget;

/**
 * Created by 45425 on 2017/9/7.
 */
@ExcelTarget("TotalOrder")
public class TotalOrder {
    @Excel(name = "市", orderNum = "1", mergeVertical = false)
    private String city_name;
    @Excel(name = "省", orderNum = "2", mergeVertical = false)
    private String province_name;
    @Excel(name = "邮费", orderNum = "3", mergeVertical = false)
    private String courier_cost;
    @Excel(name = "销售数量", orderNum = "4", mergeVertical = false)
    private String saleCount;


    @Excel(name = "价格", orderNum = "5", mergeVertical = false)
    private String salePrice;
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

    public String getCourier_cost() {
        return courier_cost;
    }

    public void setCourier_cost(String courier_cost) {
        this.courier_cost = courier_cost;
    }

    public String getSaleCount() {
        return saleCount;
    }

    public void setSaleCount(String saleCount) {
        this.saleCount = saleCount;
    }

    public String getSalePrice() {
        return salePrice;
    }

    public void setSalePrice(String salePrice) {
        this.salePrice = salePrice;
    }


}
