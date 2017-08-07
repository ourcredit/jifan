package com.yrkj.model.order;

import java.util.Date;

/**
 * Created by xuenianxiang on 2017/8/7.
 */
public class UserIntegration {

    private Long id;

    private String open_id;

    private int integration_val;

    private String remark;

    private Date create_time;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOpen_id() {
        return open_id;
    }

    public void setOpen_id(String open_id) {
        this.open_id = open_id;
    }

    public int getIntegration_val() {
        return integration_val;
    }

    public void setIntegration_val(int integration_val) {
        this.integration_val = integration_val;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Date getCreate_time() {
        return create_time;
    }

    public void setCreate_time(Date create_time) {
        this.create_time = create_time;
    }
}
