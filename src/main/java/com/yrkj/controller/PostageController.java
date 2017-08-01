package com.yrkj.controller;

import com.yrkj.model.core.*;
import com.yrkj.model.postages.Postage;
import com.yrkj.model.product.Product;
import com.yrkj.model.product.ProductSearch;
import com.yrkj.service.PostageService;
import com.yrkj.service.ProductService;
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
 * Created by 45425 on 2017/8/1.
 */
@RestController
@RequestMapping("/api/post")
@EnableSwagger2
public class PostageController {
    @Autowired
    private HttpServletRequest request;

    @Autowired
    private PostageService _postService;

    @ApiOperation(value = "创建邮费信息",notes = "邮费管理")
    @ApiImplicitParams({@ApiImplicitParam(name = "authorization", value = "Bearer {token}", required = true, dataType = "String",paramType = "header")})
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ActionResult add(@RequestBody Postage post) {
        Claims claims = (Claims)request.getAttribute("claims");
        post.setCreate_by(claims.getSubject());
        post.setCreate_time(new Date());
        return _postService.add(post);
    }

    @ApiOperation(value = "更新邮费信息",notes = "邮费管理")
    @ApiImplicitParams({@ApiImplicitParam(name = "authorization", value = "Bearer {token}", required = true, dataType = "String",paramType = "header")})
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public ActionResult update(@RequestBody Postage post) {
        Claims claims = (Claims)request.getAttribute("claims");
        post.setUpdate_by(claims.getSubject());
        post.setUpdate_time(new Date());
        return _postService.update(post);
    }

    @ApiOperation(value = "批量删除邮费",notes = "邮费管理")
    @ApiImplicitParams({@ApiImplicitParam(name = "authorization", value = "Bearer {token}", required = true, dataType = "String",paramType = "header")})
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public ActionResult delete(@RequestBody IdsInput input) {
        Claims claims = (Claims)request.getAttribute("claims");
        IdsModel model = new IdsModel();
        model.setList(input.getList());
        model.setUpdate_by(claims.getSubject());
        model.setUpdate_time(new Date());
        return _postService.delete(model);
    }

    @ApiOperation(value = "获取邮费详情",notes = "邮费管理")
    @ApiImplicitParams({@ApiImplicitParam(name = "authorization", value = "Bearer {token}", required = true, dataType = "String",paramType = "header")})
    @RequestMapping(value = "/get", method = RequestMethod.GET)
    public ActionResult get(@RequestParam Long id) throws ServletException {
        return _postService.GetDetail(id);
    }

    @ApiOperation(value = "获取产品列表",notes = "获取产品列表")
    @ApiImplicitParams({@ApiImplicitParam(name = "authorization", value = "Bearer {token}", required = true, dataType = "String",paramType = "header")})
    @RequestMapping(value = "/list", method = RequestMethod.POST)
    public PageModel list(@RequestBody SearchModel model) {
        return _postService.getAll(model);
    }

}
