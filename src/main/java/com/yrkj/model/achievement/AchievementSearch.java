package com.yrkj.model.achievement;

import com.yrkj.model.core.SearchModel;

/**
 * Created by xuenianxiang on 2017/7/21.
 */
public class AchievementSearch extends SearchModel{

    private Integer type;

    private Long category1;

    private Long category2;

    private Long category3;

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
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
