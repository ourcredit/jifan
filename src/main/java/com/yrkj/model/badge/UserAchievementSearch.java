package com.yrkj.model.badge;

/**
 * Created by xuenianxiang on 2017/8/18.
 */
public class UserAchievementSearch {

    private int pageNum;

    private int pageSize;

    private int status;

    private int type;

    private int category1;

    private int category2;

    private int category3;

    private String open_id;

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getCategory1() {
        return category1;
    }

    public void setCategory1(int category1) {
        this.category1 = category1;
    }

    public int getCategory2() {
        return category2;
    }

    public void setCategory2(int category2) {
        this.category2 = category2;
    }

    public int getCategory3() {
        return category3;
    }

    public void setCategory3(int category3) {
        this.category3 = category3;
    }

    public String getOpen_id() {
        return open_id;
    }

    public void setOpen_id(String open_id) {
        this.open_id = open_id;
    }
}
