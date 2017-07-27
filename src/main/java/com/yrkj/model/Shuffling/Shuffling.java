package com.yrkj.model.Shuffling;

import com.yrkj.model.core.BaseModel;

import java.util.Date;

/**
 * Created by 45425 on 2017/7/25.
 * 轮播图管理
 */
public class Shuffling extends BaseModel {
    public String getShuffling_name() {
        return shuffling_name;
    }

    public void setShuffling_name(String shuffling_name) {
        this.shuffling_name = shuffling_name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public Date getOnline_time() {
        return online_time;
    }

    public void setOnline_time(Date online_time) {
        this.online_time = online_time;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    /*轮播图名称*/
    private  String shuffling_name;
    /*轮播图url*/
    private  String url;
    /*权重*/
    private Integer  weight;
    /**
     * 上线时间
     */
    private Date online_time;
    /*图片路径*/
    private  String image;
    /*排序*/
    private Integer sort;

    public Boolean getIs_deleted() {
        return is_deleted;
    }

    public void setIs_deleted(Boolean is_deleted) {
        this.is_deleted = is_deleted;
    }

    private Boolean is_deleted;
}
