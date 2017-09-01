package com.yrkj.model.travels;

import java.util.Date;

/**
 * Created by 45425 on 2017/9/1.
 */
public class UserTravelInput {
    private  String open_id;
    private String left;

    public String getOpen_id() {
        return open_id;
    }

    public void setOpen_id(String open_id) {
        this.open_id = open_id;
    }

    public String getLeft() {
        return left;
    }

    public void setLeft(String left) {
        this.left = left;
    }

    public String getRight() {
        return right;
    }

    public void setRight(String right) {
        this.right = right;
    }

    private String right;
}
