package com.yrkj.controller;

import com.yrkj.model.category.Category;
import com.yrkj.model.core.ActionResult;
import com.yrkj.model.core.IdModel;
import com.yrkj.service.CategoryService;
import io.jsonwebtoken.Claims;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 * Created by xuenianxiang on 2017/7/13.
 */
@RestController
@RequestMapping("/api/category")
@EnableSwagger2
@Api(description = "分类管理接口")
public class CategoryController {

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private CategoryService categoryService;

    @ApiOperation(value = "创建分类",notes = "创建分类(一级分类pid传0)")
    @ApiImplicitParams({@ApiImplicitParam(name = "authorization", value = "Bearer {token}", required = true, dataType = "String",paramType = "header")})
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ActionResult add(@RequestBody Category category) {

        Claims claims = (Claims)request.getAttribute("claims");

        category.setCreate_by(claims.getSubject());
        category.setCreate_time(new Date());

        return categoryService.add(category);
    }

    @ApiOperation(value = "更新分类",notes = "更新分类")
    @ApiImplicitParams({@ApiImplicitParam(name = "authorization", value = "Bearer {token}", required = true, dataType = "String",paramType = "header")})
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public ActionResult update(@RequestBody Category category) {

        Claims claims = (Claims)request.getAttribute("claims");

        category.setUpdate_by(claims.getSubject());
        category.setUpdate_time(new Date());

        return categoryService.update(category);
    }

    @ApiOperation(value = "删除分类",notes = "删除分类")
    @ApiImplicitParams({@ApiImplicitParam(name = "authorization", value = "Bearer {token}", required = true, dataType = "String",paramType = "header")})
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public ActionResult delete(@RequestBody IdModel model)  {

        Claims claims = (Claims)request.getAttribute("claims");

        Category category = new Category();
        category.setId(model.getId());
        category.setUpdate_by(claims.getSubject());
        category.setUpdate_time(new Date());

        return categoryService.delete(category);
    }

    @ApiOperation(value = "根据pid获取分类列表",notes = "根据pid获取分类列表")
    @ApiImplicitParams({@ApiImplicitParam(name = "authorization", value = "Bearer {token}", required = true, dataType = "String",paramType = "header")})
    @RequestMapping(value = "/getAllByPid", method = RequestMethod.POST)
    public ActionResult getAllByPid(@RequestParam Long pid) {

        return categoryService.getAllByPid(pid);
    }

    @ApiOperation(value = "获取所有分类",notes = "获取所有分类")
    @ApiImplicitParams({@ApiImplicitParam(name = "authorization", value = "Bearer {token}", required = true, dataType = "String",paramType = "header")})
    @RequestMapping(value = "/getAll", method = RequestMethod.POST)
    public ActionResult getAll() {
        return categoryService.getAll();
    }

}
