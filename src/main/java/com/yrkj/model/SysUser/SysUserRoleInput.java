package com.yrkj.model.SysUser;

import java.util.Date;
import java.util.List;

/**
 * Created by xuenianxiang on 2017/6/27.
 */
public class SysUserRoleInput {

    private List<Long> users;

    private List<Long> roles;

    private String create_by;

    private Date create_time;

    public List<Long> getUsers() {
        return users;
    }

    public void setUsers(List<Long> users) {
        this.users = users;
    }

    public List<Long> getRoles() {
        return roles;
    }

    public void setRoles(List<Long> roles) {
        this.roles = roles;
    }

    public String getCreate_by() {
        return create_by;
    }

    public void setCreate_by(String create_by) {
        this.create_by = create_by;
    }

    public Date getCreate_time() {
        return create_time;
    }

    public void setCreate_time(Date create_time) {
        this.create_time = create_time;
    }
}
