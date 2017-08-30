package com.yrkj.model.UserProduct;

import com.yrkj.model.core.SearchModel;

/**
 * Created by xuenianxiang on 2017/7/30.
 */
public class UserProductSearch extends SearchModel {

    private Integer price_up;

    private Integer time_up;

    private Integer sales_up;

    private long category1;

    private long category2;

    private long category3;

    public Integer getPrice_up() {
        return price_up;
    }

    public void setPrice_up(Integer price_up) {
        this.price_up = price_up;
    }

    public Integer getTime_up() {
        return time_up;
    }

    public void setTime_up(Integer time_up) {
        this.time_up = time_up;
    }

    public Integer getSales_up() {
        return sales_up;
    }

    public void setSales_up(Integer sales_up) {
        this.sales_up = sales_up;
    }

    public long getCategory1() {
        return category1;
    }

    public void setCategory1(long category1) {
        this.category1 = category1;
    }

    public long getCategory2() {
        return category2;
    }

    public void setCategory2(long category2) {
        this.category2 = category2;
    }

    public long getCategory3() {
        return category3;
    }

    public void setCategory3(long category3) {
        this.category3 = category3;
    }
}
