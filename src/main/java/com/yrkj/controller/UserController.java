package com.yrkj.controller;

import com.yrkj.model.User.User;
import com.yrkj.model.core.ActionResult;
import com.yrkj.model.product.Product;
import com.yrkj.service.UserService;
import io.jsonwebtoken.Claims;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Date;

/**
 * Created by xuenianxiang on 2017/7/22.
 */

@RestController
@RequestMapping("/wx/user")
@EnableSwagger2
public class UserController {

    @Autowired
    private UserService userService;

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

    @ApiOperation(value = "获取用户信息",notes = "获取用户信息")
    @RequestMapping(value = "/save", method = RequestMethod.GET)
    public ActionResult save(@RequestBody User user) {
        return userService.save(user);
    }

}
