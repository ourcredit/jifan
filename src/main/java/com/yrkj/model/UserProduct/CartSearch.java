package com.yrkj.model.UserProduct;

/**
 * Created by xuenianxiang on 2017/8/1.
 */
public class CartSearch {

    private int pageNum;

    private int pageSize;

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

    public String getOpen_id() {
        return open_id;
    }

    public void setOpen_id(String open_id) {
        this.open_id = open_id;
    }
}
