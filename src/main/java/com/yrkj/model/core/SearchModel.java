package com.yrkj.model.core;

import java.io.Serializable;

/**
 * Created by xuenianxiang on 2017/4/7.
 */
public class SearchModel implements Serializable {

    private int pageNum;

    private int pageSize;

    private String name;

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
