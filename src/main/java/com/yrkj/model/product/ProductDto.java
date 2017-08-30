package com.yrkj.model.product;

import java.util.Date;
import java.util.List;

/**
 * Created by xuenianxiang on 2017/7/17.
 */
public class ProductDto {

    private int is_badge;

    private String badge_name;

    private String badge_image;

    private String badge_introduce;

    private String badge_category1;

    private String badge_category2;

    private String badge_category3;

    private Long id;


    public int getIs_perfect() {
        return is_perfect;
    }

    public void setIs_perfect(int is_perfect) {
        this.is_perfect = is_perfect;
    }

    private int is_perfect;
    /**
     * 类型(单一、组合)
     */
    private int type;

    /**
     * 商品名
     */
    private String name;

    /**
     * 价格
     */
    private float price;

    /**
     * 库存
     */
    private int inventory;

    /**
     * 图1
     */
    private String image1;

    /**
     * 图2
     */
    private String image2;

    /**
     * 标签
     */
    private String tag;

    /**
     * 介绍
     */
    private String content;

    /**
     * 发布状态
     */
    private int status;


    private List list;

    /**
     * 上线时间
     */
    private Date online_time;

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

    public String getBadge_category1() {
        return badge_category1;
    }

    public void setBadge_category1(String badge_category1) {
        this.badge_category1 = badge_category1;
    }

    public String getBadge_category2() {
        return badge_category2;
    }

    public void setBadge_category2(String badge_category2) {
        this.badge_category2 = badge_category2;
    }

    public String getBadge_category3() {
        return badge_category3;
    }

    public void setBadge_category3(String badge_category3) {
        this.badge_category3 = badge_category3;
    }
}
