package com.yrkj.controller;

import com.yrkj.model.excel.ExcelOrder;
import com.yrkj.model.core.ActionResult;
import com.yrkj.model.order.OrderFilter;
import com.yrkj.model.product.ProductCodeInput;
import com.yrkj.service.OrderService;
import com.yrkj.service.ProductService;
import com.yrkj.utils.excel.ExcelView;
import com.yrkj.utils.excel.OrderExcelView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.*;
import java.util.List;

/**
 * Created by xuenianxiang on 2017/8/28.
 */
@RestController
@RequestMapping("/down")
public class DownloadController {
    @Autowired
    private ProductService productService;
    @Autowired
    private HttpServletRequest request;
    @Autowired
    private OrderService _orderService;

    @RequestMapping(value = "/product/createCode", method = RequestMethod.GET)
    public void testDownload(HttpServletRequest request, HttpServletResponse response) throws Exception {


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

    // 下载execl文档
    @RequestMapping(value = "/orders",method = RequestMethod.GET)
    public ModelAndView product( Date start, Date end, String name ) throws Exception {
        OrderFilter of = new OrderFilter();
        if (start != null) of.start = start;
        if (end != null) of.end = end;
        if (name != null) of.name = name;
        List<ExcelOrder> list = _orderService.downOrdersByRecords(of);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("members", list);
        map.put("name", "订单信息");
        ExcelView excelView = new OrderExcelView();
        return new ModelAndView(excelView, map);
    }
}
