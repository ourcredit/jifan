package com.yrkj.model.SysRole;

import com.yrkj.model.core.BaseModel;

/**
 * Created by xuenianxiang on 2017/6/12.
 */

public class SysRole extends BaseModel{

    /**
     * 角色编码
     */
    private String code;

    /**
     * 角色名称
     */
    private String name;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
