package com.yrkj.controller;

import com.yrkj.model.badge.BadgeSearch;
import com.yrkj.model.badge.UserAchievementSearch;
import com.yrkj.model.core.ActionResult;
import com.yrkj.model.core.OpenIdModel;
import com.yrkj.model.core.PageModel;
import com.yrkj.service.BadgeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Created by xuenianxiang on 2017/8/17.
 */
@RestController
@RequestMapping("/wx/badge")
@EnableSwagger2
@Api(description = "用户勋章接口")
public class BadgeController {

    @Autowired
    private BadgeService badgeService;

    @ApiOperation(value = "获取用户勋章接口",notes = "获取用户勋章接口")
    @RequestMapping(value = "/getUserBadges", method = RequestMethod.POST)
    public PageModel getUserBadges(@RequestBody BadgeSearch model) {
        return badgeService.getUserBadges(model);
    }

    @ApiOperation(value = "获取用户成就列表接口",notes = "获取用户成就列表接口")
    @RequestMapping(value = "/getUserAchievement", method = RequestMethod.POST)
    public PageModel getUserAchievement(@RequestBody UserAchievementSearch model) {
        return badgeService.getUserAchievement(model);
    }
    
    @ApiOperation(value = "获取用户成就详情接口",notes = "获取用户成就详情接口")
    @RequestMapping(value = "/getAchievementInfo", method = RequestMethod.POST)
    public ActionResult getAchievementInfo(@RequestBody OpenIdModel model) {
        return badgeService.getUserAchievementInfo(model);
    }
}
