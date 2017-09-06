package com.yrkj.model.order;

import com.yrkj.model.core.SearchModel;

import java.util.Date;

/**
 * Created by 45425 on 2017/9/6.
 */
public class OrderFilter extends SearchModel {
    public Long category1;
    public Long category2;
    public Long category3;
    public Long prov;
    public Long city;
    public String name;
    public Date start;
    public Date end;

}
