package com.yrkj.model.core;

import java.util.List;

/**
 * Created by xuenianxiang on 2017/7/19.
 */
public class ChangeStatusInput {

    private List<Long> list;

    private int status;

    public List<Long> getList() {
        return list;
    }

    public void setList(List<Long> list) {
        this.list = list;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
