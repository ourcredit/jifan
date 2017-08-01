package com.yrkj.controller;

import com.yrkj.model.Integral.IntegralProduct;
import com.yrkj.model.Integral.IntegralSearch;
import com.yrkj.model.core.*;
import com.yrkj.service.IntegralProductService;
import io.jsonwebtoken.Claims;
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
 * Created by 45425 on 2017/8/1.
 */
@RestController
@RequestMapping("/api/integral")
@EnableSwagger2
public class IntegralProductController {

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private IntegralProductService _productService;

    @ApiOperation(value = "创建产品",notes = "创建产品")
    @ApiImplicitParams({@ApiImplicitParam(name = "authorization", value = "Bearer {token}", required = true, dataType = "String",paramType = "header")})
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ActionResult add(@RequestBody IntegralProduct product) {
        Claims claims = (Claims)request.getAttribute("claims");
        product.setCreate_by(claims.getSubject());
        product.setCreate_time(new Date());
        return _productService.add(product);
    }

    @ApiOperation(value = "更新产品",notes = "更新产品")
    @ApiImplicitParams({@ApiImplicitParam(name = "authorization", value = "Bearer {token}", required = true, dataType = "String",paramType = "header")})
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public ActionResult update(@RequestBody IntegralProduct product) {

        Claims claims = (Claims)request.getAttribute("claims");

        product.setUpdate_by(claims.getSubject());
        product.setUpdate_time(new Date());

        return _productService.update(product);
    }

    @ApiOperation(value = "批量删除产品",notes = "批量删除产品")
    @ApiImplicitParams({@ApiImplicitParam(name = "authorization", value = "Bearer {token}", required = true, dataType = "String",paramType = "header")})
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public ActionResult delete(@RequestBody IdsInput input) {

        Claims claims = (Claims)request.getAttribute("claims");

        IdsModel model = new IdsModel();

        model.setList(input.getList());
        model.setUpdate_by(claims.getSubject());
        model.setUpdate_time(new Date());

        return _productService.deleteProducts(model);
    }

    @ApiOperation(value = "批量更改状态",notes = "批量更改状态")
    @ApiImplicitParams({@ApiImplicitParam(name = "authorization", value = "Bearer {token}", required = true, dataType = "String",paramType = "header")})
    @RequestMapping(value = "/updateStatus", method = RequestMethod.POST)
    public ActionResult updateStatus(@RequestBody ChangeStatusInput input) {

        Claims claims = (Claims)request.getAttribute("claims");

        ChangeStatusModel model = new ChangeStatusModel();

        model.setStatus(input.getStatus());
        model.setList(input.getList());
        model.setUpdate_by(claims.getSubject());
        model.setUpdate_time(new Date());

        return _productService.updateStatus(model);
    }

    @ApiOperation(value = "获取产品详情",notes = "获取产品详情")
    @ApiImplicitParams({@ApiImplicitParam(name = "authorization", value = "Bearer {token}", required = true, dataType = "String",paramType = "header")})
    @RequestMapping(value = "/get", method = RequestMethod.GET)
    public ActionResult get(@RequestParam Long id) throws ServletException {
        return _productService.getById(id);
    }

    @ApiOperation(value = "获取产品列表",notes = "获取产品列表")
    @ApiImplicitParams({@ApiImplicitParam(name = "authorization", value = "Bearer {token}", required = true, dataType = "String",paramType = "header")})
    @RequestMapping(value = "/list", method = RequestMethod.POST)
    public PageModel list(@RequestBody IntegralSearch model) {
        return _productService.getAll(model);
    }
}
