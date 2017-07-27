package com.yrkj.controller;

import com.yrkj.model.achievement.Achievement;
import com.yrkj.model.achievement.AchievementSearch;
import com.yrkj.model.core.*;
import com.yrkj.model.product.Product;
import com.yrkj.model.product.ProductSearch;
import com.yrkj.service.AchievementService;
import io.jsonwebtoken.Claims;
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
@RequestMapping("/api/achievement")
@EnableSwagger2
public class AchievementController {

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private AchievementService achievementService;

    @ApiOperation(value = "创建成就",notes = "创建成就")
    @ApiImplicitParams({@ApiImplicitParam(name = "authorization", value = "Bearer {token}", required = true, dataType = "String",paramType = "header")})
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ActionResult add(@RequestBody Achievement achievement) {

        Claims claims = (Claims)request.getAttribute("claims");

        achievement.setCreate_by(claims.getSubject());
        achievement.setCreate_time(new Date());

        return achievementService.add(achievement);
    }

    @ApiOperation(value = "更新成就",notes = "更新成就")
    @ApiImplicitParams({@ApiImplicitParam(name = "authorization", value = "Bearer {token}", required = true, dataType = "String",paramType = "header")})
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public ActionResult update(@RequestBody Achievement achievement) {

        Claims claims = (Claims)request.getAttribute("claims");

        achievement.setUpdate_by(claims.getSubject());
        achievement.setUpdate_time(new Date());

        return achievementService.update(achievement);
    }

    @ApiOperation(value = "批量删除成就",notes = "批量删除成就")
    @ApiImplicitParams({@ApiImplicitParam(name = "authorization", value = "Bearer {token}", required = true, dataType = "String",paramType = "header")})
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public ActionResult delete(@RequestBody IdsInput input) {

        Claims claims = (Claims)request.getAttribute("claims");

        IdsModel model = new IdsModel();

        model.setList(input.getList());
        model.setUpdate_by(claims.getSubject());
        model.setUpdate_time(new Date());

        return achievementService.delete(model);
    }

    @ApiOperation(value = "获取成就详情",notes = "获取成就详情")
    @ApiImplicitParams({@ApiImplicitParam(name = "authorization", value = "Bearer {token}", required = true, dataType = "String",paramType = "header")})
    @RequestMapping(value = "/get", method = RequestMethod.GET)
    public ActionResult get(@RequestParam Long id) throws ServletException {
        return achievementService.getById(id);
    }

    @ApiOperation(value = "获取成就列表",notes = "获取成就列表")
    @ApiImplicitParams({@ApiImplicitParam(name = "authorization", value = "Bearer {token}", required = true, dataType = "String",paramType = "header")})
    @RequestMapping(value = "/list", method = RequestMethod.POST)
    public PageModel list(@RequestBody AchievementSearch model) {
        return achievementService.getAll(model);
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

        return achievementService.updateStatus(model);
    }

}
