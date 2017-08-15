package com.yrkj.controller;

import com.yrkj.model.core.*;
import com.yrkj.service.IntegralProductService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import javax.servlet.ServletException;


/**
 * Created by xuenianxiang on 2017/7/22.
 */

@RestController
@RequestMapping("/api/mall")
@EnableSwagger2
@Api(description = "积分商品手机端")
public class IntegralMallController {

    @Autowired
    private IntegralProductService _productService;

    @ApiOperation(value = "获取产品详情",notes = "手机积分接口")
    //  @ApiImplicitParams({@ApiImplicitParam(name = "authorization", value = "Bearer {token}", required = true, dataType = "String",paramType = "header")})
    @RequestMapping(value = "/get", method = RequestMethod.GET)
    public ActionResult get(@RequestParam Long id) throws ServletException {
        return _productService.GetIntegralDetail(id);
    }

    @ApiOperation(value = "获取产品列表",notes = "手机积分接口")
    // @ApiImplicitParams({@ApiImplicitParam(name = "authorization", value = "Bearer {token}", required = true, dataType = "String",paramType = "header")})
    @RequestMapping(value = "/list", method = RequestMethod.POST)
    public PageModel list(@RequestBody SearchModel model) {
        return _productService.GetIntegralList(model);
    }

    @ApiOperation(value = "创建积分商品订单",notes = "手机积分接口")
    //  @ApiImplicitParams({@ApiImplicitParam(name = "authorization", value = "Bearer {token}", required = true, dataType = "String",paramType = "header")})
    @RequestMapping(value = "/order", method = RequestMethod.POST)
    public ActionResult order(@RequestParam Long id) throws ServletException {
        return _productService.GetIntegralDetail(id);
    }
}
