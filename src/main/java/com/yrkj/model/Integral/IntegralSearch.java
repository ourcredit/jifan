package com.yrkj.model.Integral;

import com.yrkj.model.core.SearchModel;

/**
 * Created by 45425 on 2017/8/1.
 */
public class IntegralSearch extends SearchModel {

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    private Integer status;

    private Integer cate;

    public Integer getCate() {
        return cate;
    }

    public void setCate(Integer cate) {
        this.cate = cate;
    }
}
