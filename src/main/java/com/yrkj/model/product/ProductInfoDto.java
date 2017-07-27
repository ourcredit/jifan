package com.yrkj.model.product;

import java.util.Date;
import java.util.List;

/**
 * Created by xuenianxiang on 2017/7/25.
 */
public class ProductInfoDto {

    private int is_badge;

    private String badge_name;

    private String badge_image;

    private String badge_introduce;

    private Long badge_category1;

    private Long badge_category2;

    private Long badge_category3;

    private Long id;

    private int type;

    private String name;

    private float price;

    private int inventory;

    private String image1;

    private String image2;

    private String tag;

    private String content;

    private int status;

    private List list;

    private Date online_time;

    public int getIs_badge() {
        return is_badge;
    }

    public void setIs_badge(int is_badge) {
        this.is_badge = is_badge;
    }

    public String getBadge_name() {
        return badge_name;
    }

    public void setBadge_name(String badge_name) {
        this.badge_name = badge_name;
    }

    public String getBadge_image() {
        return badge_image;
    }

    public void setBadge_image(String badge_image) {
        this.badge_image = badge_image;
    }

    public String getBadge_introduce() {
        return badge_introduce;
    }

    public void setBadge_introduce(String badge_introduce) {
        this.badge_introduce = badge_introduce;
    }

    public Long getBadge_category1() {
        return badge_category1;
    }

    public void setBadge_category1(Long badge_category1) {
        this.badge_category1 = badge_category1;
    }

    public Long getBadge_category2() {
        return badge_category2;
    }

    public void setBadge_category2(Long badge_category2) {
        this.badge_category2 = badge_category2;
    }

    public Long getBadge_category3() {
        return badge_category3;
    }

    public void setBadge_category3(Long badge_category3) {
        this.badge_category3 = badge_category3;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
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

    public int getInventory() {
        return inventory;
    }

    public void setInventory(int inventory) {
        this.inventory = inventory;
    }

    public String getImage1() {
        return image1;
    }

    public void setImage1(String image1) {
        this.image1 = image1;
    }

    public String getImage2() {
        return image2;
    }

    public void setImage2(String image2) {
        this.image2 = image2;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public List getList() {
        return list;
    }

    public void setList(List list) {
        this.list = list;
    }

    public Date getOnline_time() {
        return online_time;
    }

    public void setOnline_time(Date online_time) {
        this.online_time = online_time;
    }
}
