package com.yrkj.controller;

import com.yrkj.model.UserProduct.UserCart;
import com.yrkj.model.UserProduct.UserProductSearch;
import com.yrkj.model.core.ActionResult;
import com.yrkj.model.core.PageModel;
import com.yrkj.model.core.SearchModel;
import com.yrkj.service.CategoryService;
import com.yrkj.service.UserProductService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Date;

/**
 * Created by xuenianxiang on 2017/7/29.
 */
@RestController
@RequestMapping("/wx/product")
@EnableSwagger2
public class UserProductController {

    @Autowired
    private UserProductService userProductService;

    @Autowired
    private CategoryService categoryService;

    @ApiOperation(value = "获取精选列表",notes = "获取精选列表")
    @RequestMapping(value = "/perfectList", method = RequestMethod.POST)
    public PageModel perfectList(@RequestBody SearchModel model) {
        return userProductService.getPerfectList(model);
    }

    @ApiOperation(value = "根据pid获取分类列表",notes = "根据pid获取分类列表")
    @RequestMapping(value = "/getCategoryByPid", method = RequestMethod.POST)
    public ActionResult getCategoryByPid(@RequestParam Long pid) {
        return categoryService.getAllByPid(pid);
    }

    @ApiOperation(value = "获取商品列表",notes = "获取商品列表")
    @RequestMapping(value = "/productList", method = RequestMethod.POST)
    public PageModel productList(@RequestBody UserProductSearch model) {
        return userProductService.getProductList(model);
    }

    @ApiOperation(value = "添加购物车",notes = "添加购物车")
    @RequestMapping(value = "/addCart", method = RequestMethod.POST)
    public ActionResult addCart(@RequestBody UserCart userCart){
        userCart.setCreate_time(new Date());
        return userProductService.addCart(userCart);
    }

    @ApiOperation(value = "移除购物车",notes = "移除购物车")
    @RequestMapping(value = "/removeCart", method = RequestMethod.POST)
    public ActionResult removeCart(@RequestBody UserCart userCart){
        return userProductService.removeCart(userCart);
    }
}
