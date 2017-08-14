package com.yrkj.model.achievement;

import java.util.List;

/**
 * Created by xuenianxiang on 2017/7/21.
 */
public class AchievementDto {

    private Long id;
    //成就名称
    private String name;
    //系列名称
    private String category_name;
    //达成条件
    private String finish;
    //类型（1勋章 2游迹）
    private int type;
    //是否组合成就
    private int is_group;
    //积分
    private int integration;
    //成就值
    private int achievement_val;
    //称号
    private String designation;
    //成就图片
    private String image;
    //商城按钮图片
    private String button_image;
    //内容
    private String content;

    private String category1;

    private String category2;

    private String category3;

    private String url;

    private int status;
    //关系数量
    private int relation_count;

    private List list;

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

    public String getCategory_name() {
        return category_name;
    }

    public void setCategory_name(String category_name) {
        this.category_name = category_name;
    }

    public String getFinish() {
        return finish;
    }

    public void setFinish(String finish) {
        this.finish = finish;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getIs_group() {
        return is_group;
    }

    public void setIs_group(int is_group) {
        this.is_group = is_group;
    }

    public int getIntegration() {
        return integration;
    }

    public void setIntegration(int integration) {
        this.integration = integration;
    }

    public int getAchievement_val() {
        return achievement_val;
    }

    public void setAchievement_val(int achievement_val) {
        this.achievement_val = achievement_val;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getButton_image() {
        return button_image;
    }

    public void setButton_image(String button_image) {
        this.button_image = button_image;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCategory1() {
        return category1;
    }

    public void setCategory1(String category1) {
        this.category1 = category1;
    }

    public String getCategory2() {
        return category2;
    }

    public void setCategory2(String category2) {
        this.category2 = category2;
    }

    public String getCategory3() {
        return category3;
    }

    public void setCategory3(String category3) {
        this.category3 = category3;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getRelation_count() {
        return relation_count;
    }

    public void setRelation_count(int relation_count) {
        this.relation_count = relation_count;
    }

    public List getList() {
        return list;
    }

    public void setList(List list) {
        this.list = list;
    }
}
