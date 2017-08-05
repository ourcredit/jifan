package com.yrkj.controller.Inputs;

import com.yrkj.model.UserProduct.PayProductInput;

import java.util.List;

/**
 * Created by xuenianxiang on 2017/8/5.
 */
public class OrderInput {

    private  String open_id;

    private List<PayProductInput> list;

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
