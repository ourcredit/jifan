package com.yrkj.model.UserProduct;

import java.util.List;

/**
 * Created by xuenianxiang on 2017/8/2.
 */
public class CartDeleteInput {

    private String open_id;

    private List<Long> list;

    public String getOpen_id() {
        return open_id;
    }

    public void setOpen_id(String open_id) {
        this.open_id = open_id;
    }

    public List<Long> getList() {
        return list;
    }

    public void setList(List<Long> list) {
        this.list = list;
    }
}
