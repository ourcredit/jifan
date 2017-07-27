package com.yrkj.model.SysUser;

import com.yrkj.model.core.BaseModel;

/**
 * Created by xuenianxiang on 2017/6/12.
 */

public class SysUser extends BaseModel{

    private String user_name;

    private String password;

    private String nick_name;

    private int status;

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNick_name() {
        return nick_name;
    }

    public void setNick_name(String nick_name) {
        this.nick_name = nick_name;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
