package com.yrkj.controller;

import com.yrkj.model.SysRole.SysRole;
import com.yrkj.model.SysUser.SysUser;
import com.yrkj.model.core.*;
import com.yrkj.service.SysRoleService;
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
 * Created by xuenianxiang on 2017/6/26.
 */
@RestController
@RequestMapping("/api/sysrole")
@EnableSwagger2
public class SysRoleController {

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private SysRoleService sysRoleService;

    @ApiOperation(value = "创建角色",notes = "创建角色")
    @ApiImplicitParams({@ApiImplicitParam(name = "authorization", value = "Bearer {token}", required = true, dataType = "String",paramType = "header")})
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ActionResult add(@RequestBody SysRole role) {
        Claims claims = (Claims)request.getAttribute("claims");
        role.setCreate_by(claims.getSubject());
        role.setCreate_time(new Date());
        return sysRoleService.addRole(role);
    }

    @ApiOperation(value = "获取角色列表",notes = "获取角色列表")
    @ApiImplicitParams({@ApiImplicitParam(name = "authorization", value = "Bearer {token}", required = true, dataType = "String",paramType = "header")})
    @RequestMapping(value = "/getAll", method = RequestMethod.POST)
    public PageModel getById(@RequestBody SearchModel model) {
        return sysRoleService.getAll(model);
    }

    @ApiOperation(value = "获取角色信息",notes = "获取角色信息")
    @ApiImplicitParams({@ApiImplicitParam(name = "authorization", value = "Bearer {token}", required = true, dataType = "String",paramType = "header")})
    @RequestMapping(value = "/getById", method = RequestMethod.POST)
    public ActionResult getById(@RequestBody IdModel model) {
        return sysRoleService.getById(model);
    }

    @ApiOperation(value = "更新角色",notes = "更新角色")
    @ApiImplicitParams({@ApiImplicitParam(name = "authorization", value = "Bearer {token}", required = true, dataType = "String",paramType = "header")})
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public ActionResult update(@RequestBody SysRole role) {
        Claims claims = (Claims)request.getAttribute("claims");
        role.setUpdate_by(claims.getSubject());
        role.setUpdate_time(new Date());
        return sysRoleService.updateRole(role);
    }

    @ApiOperation(value = "批量删除角色",notes = "批量删除角色")
    @ApiImplicitParams({@ApiImplicitParam(name = "authorization", value = "Bearer {token}", required = true, dataType = "String",paramType = "header")})
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public ActionResult delete(@RequestBody IdsInput input) {
        Claims claims = (Claims)request.getAttribute("claims");
        IdsModel model = new IdsModel();
        model.setList(input.getList());
        model.setUpdate_by(claims.getSubject());
        model.setUpdate_time(new Date());
        return sysRoleService.deleteRoles(model);
    }
}
