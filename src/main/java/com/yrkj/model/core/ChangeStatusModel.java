package com.yrkj.model.core;

import java.util.Date;
import java.util.List;

/**
 * Created by xuenianxiang on 2017/7/19.
 */
public class ChangeStatusModel {

    private List<Long> list;

    private int status;

    private String update_by;

    private Date update_time;

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

    public String getUpdate_by() {
        return update_by;
    }

    public void setUpdate_by(String update_by) {
        this.update_by = update_by;
    }

    public Date getUpdate_time() {
        return update_time;
    }

    public void setUpdate_time(Date update_time) {
        this.update_time = update_time;
    }
}
