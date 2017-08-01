package com.yrkj.controller;

import com.yrkj.model.SysUser.SysUser;
import com.yrkj.model.SysUser.SysUserRoleInput;
import com.yrkj.model.core.*;
import com.yrkj.model.product.Product;
import com.yrkj.service.SysUserService;
import io.jsonwebtoken.Claims;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 * Created by xuenianxiang on 2017/6/26.
 */

@RestController
@RequestMapping("/api/sysuser")
@EnableSwagger2
@Api(description = "后台用户管理接口")
public class SysUserController {

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private SysUserService sysUserService;

    @ApiOperation(value = "创建用户",notes = "创建用户")
    @ApiImplicitParams({@ApiImplicitParam(name = "authorization", value = "Bearer {token}", required = true, dataType = "String",paramType = "header")})
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ActionResult add(@RequestBody SysUser user) {

        Claims claims = (Claims)request.getAttribute("claims");

        user.setCreate_by(claims.getSubject());
        user.setCreate_time(new Date());

        return sysUserService.addUser(user);
    }

    @ApiOperation(value = "获取用户列表",notes = "获取用户列表")
    @ApiImplicitParams({@ApiImplicitParam(name = "authorization", value = "Bearer {token}", required = true, dataType = "String",paramType = "header")})
    @RequestMapping(value = "/getAll", method = RequestMethod.POST)
    public PageModel getById(@RequestBody SearchModel model) {
        return sysUserService.getAll(model);
    }

    @ApiOperation(value = "获取用户信息",notes = "获取用户信息")
    @ApiImplicitParams({@ApiImplicitParam(name = "authorization", value = "Bearer {token}", required = true, dataType = "String",paramType = "header")})
    @RequestMapping(value = "/getById", method = RequestMethod.POST)
    public ActionResult getById(@RequestBody IdModel model) {
        return sysUserService.getById(model);
    }

    @ApiOperation(value = "更新用户",notes = "更新用户")
    @ApiImplicitParams({@ApiImplicitParam(name = "authorization", value = "Bearer {token}", required = true, dataType = "String",paramType = "header")})
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public ActionResult update(@RequestBody SysUser user) {

        Claims claims = (Claims)request.getAttribute("claims");

        user.setUpdate_by(claims.getSubject());
        user.setUpdate_time(new Date());

        return sysUserService.updateUser(user);
    }

    @ApiOperation(value = "批量删除用户",notes = "批量删除用户")
    @ApiImplicitParams({@ApiImplicitParam(name = "authorization", value = "Bearer {token}", required = true, dataType = "String",paramType = "header")})
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public ActionResult delete(@RequestBody IdsInput input) {

        Claims claims = (Claims)request.getAttribute("claims");

        IdsModel model = new IdsModel();

        model.setList(input.getList());
        model.setUpdate_by(claims.getSubject());
        model.setUpdate_time(new Date());

        return sysUserService.deleteUsers(model);
    }

    @ApiOperation(value = "分配角色",notes = "分配角色")
    @ApiImplicitParams({@ApiImplicitParam(name = "authorization", value = "Bearer {token}", required = true, dataType = "String",paramType = "header")})
    @RequestMapping(value = "/allot", method = RequestMethod.POST)
    public ActionResult allot(@RequestBody SysUserRoleInput input) {
        Claims claims = (Claims)request.getAttribute("claims");
        input.setCreate_by(claims.getSubject());
        input.setCreate_time(new Date());
        return sysUserService.addUserRole(input);
    }

}
