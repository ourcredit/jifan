package com.yrkj.controller.Inputs;


import com.yrkj.model.UserProduct.PayProductInput;

import java.util.List;

public class JsPayInput {

    private   String user_ip;

    private  String redirect_url;

    private  Long order_id;

    public String getUser_ip() {
        return user_ip;
    }

    public void setUser_ip(String user_ip) {
        this.user_ip = user_ip;
    }

    public String getRedirect_url() {
        return redirect_url;
    }

    public void setRedirect_url(String redirect_url) {
        this.redirect_url = redirect_url;
    }

    public Long getOrder_id() {
        return order_id;
    }

    public void setOrder_id(Long order_id) {
        this.order_id = order_id;
    }
}