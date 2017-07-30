package com.yrkj.controller;

import com.yrkj.model.UserProduct.UserProductSearch;
import com.yrkj.model.core.PageModel;
import com.yrkj.model.core.SearchModel;
import com.yrkj.service.UserProductService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Created by xuenianxiang on 2017/7/29.
 */
@RestController
@RequestMapping("/wx/product")
@EnableSwagger2
public class UserProductController {

    @Autowired
    private UserProductService userProductService;

    @ApiOperation(value = "获取精选列表",notes = "获取精选列表")
    @RequestMapping(value = "/perfectList", method = RequestMethod.POST)
    public PageModel perfectList(@RequestBody SearchModel model) {
        return userProductService.getPerfectList(model);
    }


    @ApiOperation(value = "获取商品列表",notes = "获取商品列表")
    @RequestMapping(value = "/productList", method = RequestMethod.POST)
    public PageModel productList(@RequestBody UserProductSearch model) {
        return userProductService.getProductList(model);
    }
}
