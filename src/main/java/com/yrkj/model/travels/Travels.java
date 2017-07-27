package com.yrkj.model.travels;

import com.yrkj.model.core.BaseModel;

/**
 * Created by xuenianxiang on 2017/7/20.
 */
public class Travels extends BaseModel {

    private String name;

    private String url;

    private String image;

    private String content;

    private Long category1;

    private Long category2;

    private Long category3;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Long getCategory1() {
        return category1;
    }

    public void setCategory1(Long category1) {
        this.category1 = category1;
    }

    public Long getCategory2() {
        return category2;
    }

    public void setCategory2(Long category2) {
        this.category2 = category2;
    }

    public Long getCategory3() {
        return category3;
    }

    public void setCategory3(Long category3) {
        this.category3 = category3;
    }
}
