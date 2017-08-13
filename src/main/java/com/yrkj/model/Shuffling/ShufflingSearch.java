package com.yrkj.model.Shuffling;

import com.yrkj.model.core.SearchModel;

/**
 * Created by 45425 on 2017/7/25.
 */
public class ShufflingSearch extends SearchModel {

    private  Integer cate;

    private Integer status;

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getCate() {
        return cate;
    }

    public void setCate(Integer cate) {
        this.cate = cate;
    }

}
