package com.yrkj.model.category;

import com.yrkj.model.core.BaseModel;

/**
 * Created by xuenianxiang on 2017/7/13.
 */
public class Category extends BaseModel {

    private Long pid;

    private String name;

    public Long getPid() {
        return pid;
    }

    public void setPid(Long pid) {
        this.pid = pid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
