package com.yrkj.controller;

import com.yrkj.model.core.ActionResult;
import com.yrkj.model.product.ProductCodeInput;
import com.yrkj.service.ProductService;
import com.yrkj.utils.TxtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by xuenianxiang on 2017/8/28.
 */
@Controller
public class DownloadController {

    @Autowired
    private ProductService productService;

    @RequestMapping(value = "/product/createCode", method = RequestMethod.GET)
    public void testDownload(HttpServletRequest request, HttpServletResponse response) throws IOException {


        Long product_id = Long.parseLong(request.getParameter("product_id"));
        Integer numbers = Integer.parseInt(request.getParameter("numbers"));

        ActionResult result = productService.createProductCode(product_id,numbers);

        ProductCodeInput input = (ProductCodeInput)result.getResult();

        String content = "";
        for (int i=0;i<input.getCodeList().size();i++){
            content = content + "product_id="+input.getProduct_id()+"&code="+input.getCodeList().get(i)+"\r\n";
        }

        byte[] resultStr = content.getBytes();


        OutputStream os = response.getOutputStream();

        try {

            response.reset();

            response.setHeader("Content-Disposition", "attachment; filename=" + new Date().getTime() + ".txt");

            response.setContentType("text/plain;charset=utf-8");

            os.write(resultStr);

            os.flush();

        }

        finally {

            if (os != null) {

                os.close();

            }

        }
    }
}
