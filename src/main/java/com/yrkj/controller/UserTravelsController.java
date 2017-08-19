package com.yrkj.controller;

import com.yrkj.model.User.UserTravels;
import com.yrkj.model.core.ActionResult;
import com.yrkj.service.UserTravelsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
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

    @ApiOperation(value = "游迹签到",notes = "游迹签到")
    @RequestMapping(value = "/checkin", method = RequestMethod.POST)
    public ActionResult checkin(@RequestBody UserTravels travels) {
        return userTravelsService.insertUserTravels(travels);
    }
}
