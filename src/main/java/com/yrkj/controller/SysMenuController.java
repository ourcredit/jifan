package com.yrkj.controller;

import com.yrkj.model.SysMenu.SysRoleMenuInput;
import com.yrkj.model.core.ActionResult;
import com.yrkj.model.core.IdModel;
import com.yrkj.service.SysMenuService;
import io.jsonwebtoken.Claims;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 * Created by xuenianxiang on 2017/6/29.
 */

@RestController
@RequestMapping("/api/sysmenu")
@EnableSwagger2
public class SysMenuController {

    @Autowired
    private SysMenuService sysMenuService;

    @Autowired
    private HttpServletRequest request;

    @ApiOperation(value = "获取所有菜单",notes = "获取所有菜单")
    @ApiImplicitParams({@ApiImplicitParam(name = "authorization", value = "Bearer {token}", required = true, dataType = "String",paramType = "header")})
    @RequestMapping(value = "/getAll", method = RequestMethod.POST)
    public ActionResult getAll() {
        return sysMenuService.getAllMenu();
    }

    @ApiOperation(value = "根据角色获取已有菜单",notes = "根据角色获取已有菜单")
    @ApiImplicitParams({@ApiImplicitParam(name = "authorization", value = "Bearer {token}", required = true, dataType = "String",paramType = "header")})
    @RequestMapping(value = "/getMenuByRole", method = RequestMethod.POST)
    public ActionResult getMenuByRole(@RequestBody IdModel model) {
        return sysMenuService.getMenuByRoleId(model.getId());
    }

    @ApiOperation(value = "根据个人权限获取菜单",notes = "根据个人权限获取菜单")
    @ApiImplicitParams({@ApiImplicitParam(name = "authorization", value = "Bearer {token}", required = true, dataType = "String",paramType = "header")})
    @RequestMapping(value = "/getMenuPerson", method = RequestMethod.POST)
    public ActionResult getMenuPerson(){
        Claims claims = (Claims)request.getAttribute("claims");

        Object user_id = claims.get("user_id");

        return sysMenuService.getMenuByUserId(Long.parseLong(user_id.toString()));
    }

    @ApiOperation(value = "分配菜单",notes = "分配菜单")
    @ApiImplicitParams({@ApiImplicitParam(name = "authorization", value = "Bearer {token}", required = true, dataType = "String",paramType = "header")})
    @RequestMapping(value = "/allot", method = RequestMethod.POST)
    public ActionResult allot(@RequestBody SysRoleMenuInput input) {
        Claims claims = (Claims)request.getAttribute("claims");
        input.setCreate_by(claims.getSubject());
        input.setCreate_time(new Date());
        return sysMenuService.addRoleMenu(input);
    }

}
