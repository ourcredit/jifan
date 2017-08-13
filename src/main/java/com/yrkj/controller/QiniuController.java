package com.yrkj.controller;

import com.qiniu.util.Auth;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


/**
 * Created by xuenianxiang on 2017/4/12.
 */

@RestController
@RequestMapping("/api/qiniu")
@EnableSwagger2
@Api(description = "七牛接口")
public class QiniuController {

    @ApiImplicitParams({@ApiImplicitParam(name = "authorization", value = "Bearer {token}", required = true, dataType = "String",paramType = "header")})
    @RequestMapping(value = "/token", method = RequestMethod.POST)
    public ModelMap token(){

        Auth auth = Auth.create("C0qui5BTENLB9C9cYuVH7T1wDnY38i7EdSoRwlpV", "Vy23acIg17_2hsEKKIeoE3w4Wux0wVjEORGBgGWL");
        String upToken = auth.uploadToken("g-fun-efan");

        ModelMap result = new ModelMap();

        if(upToken != null){
            result.put("result","1");
            result.put("errorMsg","获取成功");
            result.put("data",upToken);
        }else {
            result.put("result", "0");
            result.put("errorMsg", "获取失败");
        }

        return result;
    }

}
