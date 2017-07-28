package com.yrkj.controller;

import com.yrkj.model.Shuffling.Shuffling;
import com.yrkj.model.Shuffling.ShufflingSearch;
import com.yrkj.model.core.*;
import com.yrkj.model.product.Product;
import com.yrkj.model.product.ProductSearch;
import com.yrkj.service.ProductService;
import com.yrkj.service.ShufflingService;
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



@RestController
@RequestMapping("/api/shuffling")
@EnableSwagger2
public class ShufflingController {

    @Autowired
    private HttpServletRequest request;
    private ShufflingService _shufflingService;
    @Autowired
    public ShufflingController(ShufflingService shufflingService){
        _shufflingService=shufflingService;
    }

    @ApiOperation(value = "创建轮播图",notes = "创建轮播图")
    @ApiImplicitParams({@ApiImplicitParam(name = "authorization", value = "Bearer {token}", required = true, dataType = "String",paramType = "header")})
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ActionResult add(@RequestBody Shuffling shuffling) {
        Claims claims = (Claims)request.getAttribute("claims");
        shuffling.setCreate_by(claims.getSubject());
        shuffling.setCreate_time(new Date());
        return _shufflingService.add(shuffling);
    }

    @ApiOperation(value = "更新轮播图",notes = "更新轮播图")
    @ApiImplicitParams({@ApiImplicitParam(name = "authorization", value = "Bearer {token}", required = true, dataType = "String",paramType = "header")})
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public ActionResult update(@RequestBody Shuffling shuffling) {
        Claims claims = (Claims)request.getAttribute("claims");
        shuffling.setUpdate_by(claims.getSubject());
        shuffling.setUpdate_time(new Date());
        return _shufflingService.update(shuffling);
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
        return _shufflingService.updateStatus(model);
    }

    @ApiOperation(value = "批量删除轮播图",notes = "批量删除轮播图")
    @ApiImplicitParams({@ApiImplicitParam(name = "authorization", value = "Bearer {token}", required = true, dataType = "String",paramType = "header")})
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public ActionResult delete(@RequestBody IdsInput input) {
        Claims claims = (Claims)request.getAttribute("claims");
        IdsModel model = new IdsModel();
        model.setList(input.getList());
        model.setUpdate_by(claims.getSubject());
        model.setUpdate_time(new Date());
        return _shufflingService.delete(model);
    }

    @ApiOperation(value = "获取轮播图详情",notes = "获取轮播图详情")
    @ApiImplicitParams({@ApiImplicitParam(name = "authorization", value = "Bearer {token}", required = true, dataType = "String",paramType = "header")})
    @RequestMapping(value = "/get", method = RequestMethod.GET)
    public ActionResult get(@RequestParam Long id) throws ServletException {
        return _shufflingService.GetDetail(id);
    }

    @ApiOperation(value = "获取轮播图列表",notes = "获取轮播图列表")
    @ApiImplicitParams({@ApiImplicitParam(name = "authorization", value = "Bearer {token}", required = true, dataType = "String",paramType = "header")})
    @RequestMapping(value = "/list", method = RequestMethod.POST)
    public PageModel list(@RequestBody ShufflingSearch model) {
        return _shufflingService.getAll(model);
    }


}
