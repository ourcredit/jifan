package com.yrkj.controller;

import com.yrkj.model.clientele.ClienteleSearch;
import com.yrkj.model.core.PageModel;
import com.yrkj.model.product.ProductSearch;
import com.yrkj.service.ClienteleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Created by xuenianxiang on 2017/8/13.
 */
@RestController
@RequestMapping("/api/clientele")
@EnableSwagger2
@Api(description = "客户管理接口")
public class ClienteleController {

    @Autowired
    private ClienteleService clienteleService;

    @ApiOperation(value = "获取客户列表",notes = "获取客户列表")
    @ApiImplicitParams({@ApiImplicitParam(name = "authorization", value = "Bearer {token}", required = true, dataType = "String",paramType = "header")})
    @RequestMapping(value = "/list", method = RequestMethod.POST)
    public PageModel list(@RequestBody ClienteleSearch model) {
        return clienteleService.getAll(model);
    }

}
