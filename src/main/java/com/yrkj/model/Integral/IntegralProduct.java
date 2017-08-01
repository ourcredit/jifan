package com.yrkj.model.Integral;

import com.yrkj.model.core.BaseModel;

import java.util.Date;

/**
 * Created by 45425 on 2017/8/1.
 */
public class IntegralProduct extends BaseModel {
    public String getProduct_name() {
        return product_name;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }

    public Integer getProduct_type() {
        return product_type;
    }

    public void setProduct_type(Integer product_type) {
        this.product_type = product_type;
    }

    public String getProduct_price() {
        return product_price;
    }

    public void setProduct_price(String product_price) {
        this.product_price = product_price;
    }

    public Integer getInventory() {
        return inventory;
    }

    public void setInventory(Integer inventory) {
        this.inventory = inventory;
    }

    public String getImage1() {
        return image1;
    }

    public void setImage1(String image1) {
        this.image1 = image1;
    }

    public String getImage2() {
        return image2;
    }

    public void setImage2(String image2) {
        this.image2 = image2;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getOnline_time() {
        return online_time;
    }

    public void setOnline_time(Date online_time) {
        this.online_time = online_time;
    }

    private  String product_name;
    private  Integer  product_type;
    private  String  product_price;
    private  Integer inventory;
    private  String  image1;
    private  String  image2;
    private  String description;
    private  String content;

    public Integer getIs_deleted() {
        return is_deleted;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    private Integer status;
    public void setIs_deleted(Integer is_deleted) {
        this.is_deleted = is_deleted;
    }

    private Integer is_deleted;
    /**
     * 上线时间
     */
    private Date online_time;
}
