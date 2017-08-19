package com.yrkj.controller;

import com.yrkj.model.Integral.IntegralOrder;
import com.yrkj.model.core.*;
import com.yrkj.service.IntegralProductService;
import com.yrkj.utils.DatetimeUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import javax.servlet.ServletException;
import java.util.Date;


/**
 * Created by xuenianxiang on 2017/7/22.
 */

@RestController
@RequestMapping("/mobile/mall")
@EnableSwagger2
@Api(description = "积分商品手机端")
public class IntegralMallController {

    @Autowired
    private IntegralProductService _productService;

    @ApiOperation(value = "获取产品详情",notes = "手机积分接口")
    @RequestMapping(value = "/get", method = RequestMethod.GET)
    public ActionResult get(@RequestParam Long id) throws ServletException {
        return _productService.GetIntegralDetail(id);
    }

    @ApiOperation(value = "获取产品列表",notes = "手机积分接口")
    @RequestMapping(value = "/list", method = RequestMethod.POST)
    public PageModel list(@RequestBody SearchModel model) {
        return _productService.GetIntegralList(model);
    }

    @ApiOperation(value = "创建积分商品订单",notes = "手机积分接口")
    @RequestMapping(value = "/order", method = RequestMethod.POST)
    public ActionResult order(@RequestBody IntegralOrder model) throws ServletException {
        String num= "JG" + DatetimeUtil.formatDate(new Date(), DatetimeUtil.TIME_STAMP_PATTERN);
        model.setOrder_num(num);
        model.setOrder_state(0);
        model.setCreate_time(new Date());
        return _productService.InsertOrder(model);
    }

    /**
     * 积分抽奖
     * @param open_id
     * @return
     */
    @ApiOperation(value = "积分商城抽奖",notes = "积分商城抽奖")
    @RequestMapping(value = "/lottery", method = RequestMethod.POST)
    public ActionResult lottery(@RequestParam String open_id){
        return _productService.lottery(open_id);
    }

}
