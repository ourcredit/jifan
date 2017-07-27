package com.yrkj.model.SysMenu;

import java.util.Date;
import java.util.List;

/**
 * Created by xuenianxiang on 2017/6/28.
 */
public class SysRoleMenuInput {

    private List<Long> roles;

    private List<Long> menus;

    private String create_by;

    private Date create_time;

    public List<Long> getRoles() {
        return roles;
    }

    public void setRoles(List<Long> roles) {
        this.roles = roles;
    }

    public List<Long> getMenus() {
        return menus;
    }

    public void setMenus(List<Long> menus) {
        this.menus = menus;
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
