package com.yrkj.controller;

import com.yrkj.model.User.User;
import com.yrkj.model.User.UserAddress;
import com.yrkj.model.User.UserDesignation;
import com.yrkj.model.core.ActionResult;
import com.yrkj.model.core.IdModel;
import com.yrkj.service.UserService;
import io.swagger.annotations.Api;

import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


/**
 * Created by xuenianxiang on 2017/7/22.
 */

@RestController
@RequestMapping("/wx/user")
@EnableSwagger2
@Api(description = "微信用户相关接口")
public class UserController {

    @Autowired
    private UserService userService;

    //用户基本信息管理

    @ApiOperation(value = "登录",notes = "登录")
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ActionResult login(@RequestBody User user) {
        return userService.login(user.getOpen_id());
    }

    @ApiOperation(value = "获取用户信息",notes = "获取用户信息")
    @RequestMapping(value = "/getById", method = RequestMethod.GET)
    public ActionResult getById(@RequestParam String openid) {
        return userService.getById(openid);
    }

    @ApiOperation(value = "保存用户信息",notes = "保存用户信息")
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public ActionResult save(@RequestBody User user) {
        return userService.save(user);
    }

    @ApiOperation(value = "获取用户成就值、积分、勋章数",notes = "获取用户成就值、积分、勋章数")
    @RequestMapping(value = "/getUserVal", method = RequestMethod.GET)
    public ActionResult getUserVal(@RequestParam String open_id) {
        return userService.getUserVal(open_id);
    }

    @ApiOperation(value = "获取用户称号列表",notes = "获取用户称号列表")
    @RequestMapping(value = "/designations", method = RequestMethod.GET)
    public ActionResult designations(@RequestParam String open_id) {
        return userService.getDesignations(open_id);
    }


    @ApiOperation(value = "更新用户称号",notes = "更新用户称号")
    @RequestMapping(value = "/updateDesignation", method = RequestMethod.POST)
    public ActionResult updateDesignation(@RequestBody UserDesignation designation) {
        return userService.updateDesignation(designation);
    }

    //收货地址管理
    @ApiOperation(value = "添加收货地址",notes = "添加收货地址")
    @RequestMapping(value = "/addAddress", method = RequestMethod.POST)
    public ActionResult addAddress(@RequestBody UserAddress address) {
        return userService.addAddress(address);
    }

    @ApiOperation(value = "修改收货地址",notes = "修改收货地址")
    @RequestMapping(value = "/updateAddress", method = RequestMethod.POST)
    public ActionResult updateAddress(@RequestBody UserAddress address) {
        return userService.updateAddress(address);
    }

    @ApiOperation(value = "删除收货地址",notes = "删除收货地址")
    @RequestMapping(value = "/deleteAddress", method = RequestMethod.POST)
    public ActionResult deleteAddress(@RequestBody IdModel idModel) {
        return userService.deleteAddress(idModel.getId());
    }

    @ApiOperation(value = "获取收货地址列表",notes = "获取收货地址列表")
    @RequestMapping(value = "/getAddressList", method = RequestMethod.GET)
    public ActionResult getAddressList(@RequestParam String open_id) {
        return userService.getAddressList(open_id);
    }

    @ApiOperation(value = "获取默认收货地址",notes = "获取默认收货地址")
    @RequestMapping(value = "/getDefaultAddress", method = RequestMethod.GET)
    public ActionResult getDefaultAddress(@RequestParam String open_id) {
        return userService.getDefaultAddress(open_id);
    }

    @ApiOperation(value = "获取收货地址详情",notes = "获取收货地址详情")
    @RequestMapping(value = "/getAddressInfo", method = RequestMethod.GET)
    public ActionResult getAddressInfo(@RequestParam Long id) {
        return userService.getAddressInfo(id);
    }


    @ApiOperation(value = "获取轮播图+banner",notes = "获取轮播图+banner")
    @RequestMapping(value = "/shuffling", method = RequestMethod.GET)
    public ActionResult shuffling() {
        return userService.getShuffling();
    }
}
