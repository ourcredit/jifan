package com.yrkj.model.excel;

import org.jeecgframework.poi.excel.annotation.Excel;
import org.jeecgframework.poi.excel.annotation.ExcelTarget;

/**
 * Created by 45425 on 2017/9/7.
 */
@ExcelTarget("TravelInfo")
public class TravelInfo {
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUserCount() {
        return userCount;
    }

    public void setUserCount(String userCount) {
        this.userCount = userCount;
    }

    public String getCate1() {
        return cate1;
    }

    public void setCate1(String cate1) {
        this.cate1 = cate1;
    }

    public String getCate2() {
        return cate2;
    }

    public void setCate2(String cate2) {
        this.cate2 = cate2;
    }

    public String getCate3() {
        return cate3;
    }

    public void setCate3(String cate3) {
        this.cate3 = cate3;
    }

    @Excel(name = "游迹名称", orderNum = "1", mergeVertical = false)
    private String name;
    @Excel(name = "签到人数", orderNum = "2", mergeVertical = false)
    private String userCount;
    @Excel(name = "类别", orderNum = "3", mergeVertical = false)
    private String cate1;
    @Excel(name = "地区", orderNum = "4", mergeVertical = false)
    private String cate2;
    @Excel(name = "景区", orderNum = "5", mergeVertical = false)
    private String cate3;


}
