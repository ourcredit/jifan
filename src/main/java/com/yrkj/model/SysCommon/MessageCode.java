package com.yrkj.model.SysCommon;

import java.util.Date;

/**
 * Created by xuenianxiang on 2017/7/23.
 */
public class MessageCode {

    private String mobile;

    private String code;

    private Date create_time;

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Date getCreate_time() {
        return create_time;
    }

    public void setCreate_time(Date create_time) {
        this.create_time = create_time;
    }
}
