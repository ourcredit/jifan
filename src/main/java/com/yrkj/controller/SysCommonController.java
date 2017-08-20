package com.yrkj.controller;

import com.fasterxml.jackson.databind.ser.Serializers;
import com.google.gson.Gson;
import com.yrkj.mapper.SysCommonMapper;
import com.yrkj.mapper.SysUserMapper;
import com.yrkj.model.SysUser.SysUser;
import com.yrkj.model.SysUser.SysUserInput;
import com.yrkj.model.User.User;
import com.yrkj.model.User.UserPwd;
import com.yrkj.model.core.ActionResult;
import com.yrkj.service.SysCommonService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import javax.servlet.ServletException;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by xuenianxiang on 2017/6/16.
 */

@RestController
@RequestMapping("/sys")
@EnableSwagger2
@Api(description = "登录、发短信、获取城市列表、绑定手机号相关接口")
public class SysCommonController {

    @Autowired
    private SysCommonService sysCommonService;

    @Autowired
    private SysUserMapper sysUserMapper;

    @Autowired
    private SysCommonMapper sysCommonMapper;

    @ApiOperation(value = "登录",notes = "登录")
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ActionResult login(@RequestBody SysUserInput input) {

        String jwtToken = "";

        if (input.getUser_name() == null || input.getPassword() == null) {
            return new ActionResult(false,null,"请输入账号或密码!");
        }

        SysUser user = sysUserMapper.select(input);

        if (user == null) {
            return new ActionResult(false,null,"用户不存在!");
        }

        if (user.getStatus()==0){
            return new ActionResult(false,null,"用户被禁用!");
        }

        if (!user.getPassword().equals(input.getPassword())) {
            return new ActionResult(false,null,"密码错误!");
        }

        jwtToken = Jwts.builder().setSubject(user.getUser_name()).claim("user_id", user.getId()).setIssuedAt(new Date())
                .signWith(SignatureAlgorithm.HS256, "secretkey").compact();

        Map result = new HashMap();
        result.put("token",jwtToken);
        result.put("nick_name",user.getNick_name());

        return new ActionResult(result);
    }

    @ApiOperation(value = "获取省份、城市列表",notes = "获取省份、城市列表")
    @RequestMapping(value = "/positions", method = RequestMethod.GET)
    public String positions(@RequestParam Long id) {
        return new Gson().toJson(sysCommonMapper.getPositionsById(id));
    }

    @ApiOperation(value = "发短信接口",notes = "发短信接口")
    @RequestMapping(value = "/sendMessage", method = RequestMethod.POST)
    public ActionResult sendMessage(String mobile,String open_id) throws Exception{
        return sysCommonService.sendMessage(mobile,open_id);
    }

    @ApiOperation(value = "绑定手机号",notes = "绑定手机号")
    @RequestMapping(value = "/bindMobile", method = RequestMethod.GET)
    public ActionResult bindMobile(@RequestParam String mobile,@RequestParam String open_id,@RequestParam String code) {
        return sysCommonService.bindMobile(mobile,open_id,code);
    }

    @ApiOperation(value = "设置密码",notes = "设置密码")
    @RequestMapping(value = "/setPwd", method = RequestMethod.POST)
    public ActionResult setPwd(@RequestBody UserPwd pwd) {
        return sysCommonService.bindPwd(pwd);
    }

}
