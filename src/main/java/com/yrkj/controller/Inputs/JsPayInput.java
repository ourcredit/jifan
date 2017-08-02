package com.yrkj.controller.Inputs;


import com.yrkj.model.UserProduct.PayProductInput;

import java.util.List;

public class JsPayInput {
    private   String user_ip;

    private  String redirect_url;

    private  String open_id;

    private List<PayProductInput> list;

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

    public String getOpen_id() {
        return open_id;
    }

    public void setOpen_id(String open_id) {
        this.open_id = open_id;
    }

    public List<PayProductInput> getList() {
        return list;
    }

    public void setList(List<PayProductInput> list) {
        this.list = list;
    }
}