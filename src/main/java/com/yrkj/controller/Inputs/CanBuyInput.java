package com.yrkj.controller.Inputs;

public class CanBuyInput {
    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    private  Long   productId;
    private  Integer count;
}
