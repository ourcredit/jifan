package com.yrkj.controller;

import com.yrkj.model.core.*;
import com.yrkj.model.travels.Travels;
import com.yrkj.model.travels.TravelsSearch;
import com.yrkj.service.TravelsService;
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
 * Created by xuenianxiang on 2017/7/21.
 */
@RestController
@RequestMapping("/api/travels")
@EnableSwagger2
@Api(description = "游迹管理接口")
public class TravelsController {

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private TravelsService travelsService;

    @ApiOperation(value = "创建游迹",notes = "创建游迹")
    @ApiImplicitParams({@ApiImplicitParam(name = "authorization", value = "Bearer {token}", required = true, dataType = "String",paramType = "header")})
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ActionResult add(@RequestBody Travels travels) {

        Claims claims = (Claims)request.getAttribute("claims");

        travels.setCreate_by(claims.getSubject());
        travels.setCreate_time(new Date());

        return travelsService.add(travels);
    }

    @ApiOperation(value = "更新游迹",notes = "更新游迹")
    @ApiImplicitParams({@ApiImplicitParam(name = "authorization", value = "Bearer {token}", required = true, dataType = "String",paramType = "header")})
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public ActionResult update(@RequestBody Travels travels) {

        Claims claims = (Claims)request.getAttribute("claims");

        travels.setUpdate_by(claims.getSubject());
        travels.setUpdate_time(new Date());

        return travelsService.update(travels);
    }

    @ApiOperation(value = "批量删除游迹",notes = "批量删除游迹")
    @ApiImplicitParams({@ApiImplicitParam(name = "authorization", value = "Bearer {token}", required = true, dataType = "String",paramType = "header")})
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public ActionResult delete(@RequestBody IdsInput input) {

        Claims claims = (Claims)request.getAttribute("claims");

        IdsModel model = new IdsModel();

        model.setList(input.getList());
        model.setUpdate_by(claims.getSubject());
        model.setUpdate_time(new Date());

        return travelsService.delete(model);
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

        return travelsService.updateStatus(model);
    }

    @ApiOperation(value = "获取游迹详情",notes = "获取游迹详情")
    @ApiImplicitParams({@ApiImplicitParam(name = "authorization", value = "Bearer {token}", required = true, dataType = "String",paramType = "header")})
    @RequestMapping(value = "/get", method = RequestMethod.GET)
    public ActionResult get(@RequestParam Long id) throws ServletException {
        return travelsService.getById(id);
    }

    @ApiOperation(value = "获取游迹列表",notes = "获取游迹列表")
    @ApiImplicitParams({@ApiImplicitParam(name = "authorization", value = "Bearer {token}", required = true, dataType = "String",paramType = "header")})
    @RequestMapping(value = "/list", method = RequestMethod.POST)
    public PageModel list(@RequestBody TravelsSearch model) {
        return travelsService.getAll(model);
    }
}
