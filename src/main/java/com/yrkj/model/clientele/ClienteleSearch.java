package com.yrkj.model.clientele;

import com.yrkj.model.core.SearchModel;

/**
 * Created by xuenianxiang on 2017/8/13.
 */
public class ClienteleSearch  {

    private int pageNum;

    private int pageSize;

    private String mobile;

    private String city;

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

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}
