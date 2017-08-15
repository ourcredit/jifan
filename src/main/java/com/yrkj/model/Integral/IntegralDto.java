package com.yrkj.model.Integral;

/**
 * Created by 45425 on 2017/8/15.
 */
public class IntegralDto {
    public String getProduct_name() {
        return product_name;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getLess() {
        return less;
    }

    public void setLess(Integer less) {
        this.less = less;
    }

    public Integer getProduct_type() {
        return product_type;
    }

    public void setProduct_type(Integer product_type) {
        this.product_type = product_type;
    }

    private  String product_name;
    private  String  product_price;
    private  Integer inventory;
    private  String  image1;
    private  String  image2;
    private  Long id;
    private  Integer less;
    private  Integer product_type;

}
