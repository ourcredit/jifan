package com.yrkj.model.product;

/**
 * Created by xuenianxiang on 2017/8/31.
 */
public class ProductCode {

    private Long product_id;

    private String code;

    private int is_used;

    public Long getProduct_id() {
        return product_id;
    }

    public void setProduct_id(Long product_id) {
        this.product_id = product_id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public int getIs_used() {
        return is_used;
    }

    public void setIs_used(int is_used) {
        this.is_used = is_used;
    }
}
