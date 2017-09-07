package com.yrkj.model.excel;

import org.jeecgframework.poi.excel.annotation.Excel;
import org.jeecgframework.poi.excel.annotation.ExcelTarget;

/**
 * Created by 45425 on 2017/9/7.
 */
@ExcelTarget("TotalOrder")
public class TotalOrder {
    @Excel(name = "市", orderNum = "1", mergeVertical = false)
    private String city_name;
    @Excel(name = "省", orderNum = "2", mergeVertical = false)
    private String province_name;
    @Excel(name = "邮费", orderNum = "3", mergeVertical = false)
    private String courier_cost;
    @Excel(name = "销售数量", orderNum = "4", mergeVertical = false)
    private String saleCount;
    @Excel(name = "价格", orderNum = "5", mergeVertical = false)
    private String salePrice;


}
