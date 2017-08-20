package com.yrkj.model.UserProduct;

import java.util.Date;

/**
 * Created by xuenianxiang on 2017/7/29.
 */
public class PerfectDto {
    private Long id;

    private String name;

    private float price;

    private String image1;

    private String tag;

    private String  badge_introduce;

    private Date online_time;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getImage1() {
        return image1;
    }

    public void setImage1(String image1) {
        this.image1 = image1;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getBadge_introduce() {
        return badge_introduce;
    }

    public void setBadge_introduce(String badge_introduce) {
        this.badge_introduce = badge_introduce;
    }

    public Date getOnline_time() {
        return online_time;
    }

    public void setOnline_time(Date online_time) {
        this.online_time = online_time;
    }
}
