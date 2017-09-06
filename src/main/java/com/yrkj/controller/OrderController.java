package com.yrkj.controller;

import com.yrkj.model.Integral.CourierInput;
import com.yrkj.model.Integral.IntegralSearch;
import com.yrkj.model.core.*;
import com.yrkj.model.order.OrderFilter;
import com.yrkj.model.product.Product;
import com.yrkj.model.product.ProductSearch;
import com.yrkj.service.OrderService;
import com.yrkj.service.ProductService;
import io.jsonwebtoken.Claims;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;



/**
 * Created by xuenianxiang on 2017/6/18.
 */

@RestController
@RequestMapping("/api/order")
@EnableSwagger2
@Api(description = "商品管理接口")
public class OrderController {

    @Autowired
    private HttpServletRequest request;
    @Autowired
    private OrderService _orderService;

    @ApiOperation(value = "获取订单列表",notes = "获取订单列表")
    @ApiImplicitParams({@ApiImplicitParam(name = "authorization", value = "Bearer {token}", required = true, dataType = "String",paramType = "header")})
    @RequestMapping(value = "/scanorder", method = RequestMethod.POST)
    public PageModel ScanOrders(@RequestBody OrderFilter input) {
        return _orderService.selectOrdersByScan(input);
    }
    @ApiOperation(value = "获取汇总列表",notes = "获取订单列表")
    @ApiImplicitParams({@ApiImplicitParam(name = "authorization", value = "Bearer {token}", required = true, dataType = "String",paramType = "header")})
    @RequestMapping(value = "/statics", method = RequestMethod.POST)
    public PageModel Statisc(@RequestBody OrderFilter input) {
        return _orderService.selectOrdersByTotal(input);
    }
}
