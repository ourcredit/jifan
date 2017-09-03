package com.yrkj.controller;

import com.yrkj.model.User.UserTravels;
import com.yrkj.model.core.ActionResult;
import com.yrkj.service.UserTravelsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Created by xuenianxiang on 2017/8/19.
 */
@RestController
@RequestMapping("/wx/travels")
@EnableSwagger2
@Api(description = "用户游迹接口")
public class UserTravelsController {

    @Autowired
    private UserTravelsService userTravelsService;

    @ApiOperation(value = "获取游迹列表数据",notes = "获取游迹列表数据")
    @RequestMapping(value = "/travels", method = RequestMethod.GET)
    public ActionResult travels() {
        return userTravelsService.getTravels();
    }

    @ApiOperation(value = "获取用户已签到游迹",notes = "获取用户已签到游迹")
    @RequestMapping(value = "/userTravels", method = RequestMethod.GET)
    public ActionResult userTravels(@RequestParam String open_id) {
        return userTravelsService.getUserTravels(open_id);
    }

    @ApiOperation(value = "游迹签到",notes = "游迹签到")
    @RequestMapping(value = "/checkin", method = RequestMethod.POST)
    public ActionResult checkin(@RequestBody UserTravels travels) {
        return userTravelsService.insertUserTravels(travels);
    }
    @ApiOperation(value = "游迹是否已签到",notes = "游迹签到")
    @RequestMapping(value = "/issign", method = RequestMethod.POST)
    public ActionResult checkin(@RequestParam String openId) {
        return userTravelsService.IsSingIn(openId);
    }
}
