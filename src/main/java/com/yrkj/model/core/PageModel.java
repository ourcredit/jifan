package com.yrkj.model.core;

import java.io.Serializable;
import java.util.List;

/**
 * Created by xuenianxiang on 2017/4/7.
 */
public class PageModel implements Serializable {

    private  Boolean success;

    private  Object result;

    private  String error;

    private  boolean unAuthorizedRequest;

    private long total;

    public PageModel(Boolean state, Object result, long total,String msg){
        this.success = state;
        this.result = result;
        this.total = total;
        this.error = msg;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public Object getResult() {
        return result;
    }

    public void setResult(Object result) {
        this.result = result;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public boolean isUnAuthorizedRequest() {
        return unAuthorizedRequest;
    }

    public void setUnAuthorizedRequest(boolean unAuthorizedRequest) {
        this.unAuthorizedRequest = unAuthorizedRequest;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }
}
