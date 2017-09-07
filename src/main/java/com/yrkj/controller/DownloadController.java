package com.yrkj.controller;

import com.yrkj.model.excel.ExcelOrder;
import com.yrkj.model.core.ActionResult;
import com.yrkj.model.excel.TotalOrder;
import com.yrkj.model.excel.TravelInfo;
import com.yrkj.model.order.OrderFilter;
import com.yrkj.model.product.ProductCodeInput;
import com.yrkj.service.OrderService;
import com.yrkj.service.ProductService;
import com.yrkj.service.TravelsService;
import org.apache.poi.ss.usermodel.Workbook;
import org.jeecgframework.poi.excel.ExcelExportUtil;
import org.jeecgframework.poi.excel.entity.ExportParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.*;
import java.util.List;

/**
 * Created by xuenianxiang on 2017/8/28.
 */
@Controller
@RequestMapping("/down")
public class DownloadController {
    @Autowired
    private ProductService productService;
    @Autowired
    private HttpServletRequest request;
    @Autowired
    private OrderService _orderService;
    @Autowired
    private TravelsService travelsService;

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
    public void Orders( HttpServletResponse response, Date start, Date end) throws Exception {
        OrderFilter of = new OrderFilter();
        if (start != null) of.start = start;
        if (end != null) of.end = end;
        List<ExcelOrder> list = _orderService.downOrdersByRecords(of);
        // 告诉浏览器用什么软件可以打开此文件
        response.setHeader("content-Type", "application/vnd.ms-excel");
        // 下载文件的默认名称
        response.setHeader("Content-Disposition", "attachment;filename=product.xls");
        Workbook workbook = ExcelExportUtil.exportExcel(new ExportParams(), ExcelOrder.class, list);
        workbook.write(response.getOutputStream());
    }
    // 下载execl文档
    @RequestMapping(value = "/total",method = RequestMethod.GET)
    public void Totals( HttpServletResponse response, Date start, Date end) throws Exception {
        OrderFilter of = new OrderFilter();
        if (start != null) of.start = start;
        if (end != null) of.end = end;
        List<TotalOrder> list = _orderService.downOrdersByTotal(of);
        // 告诉浏览器用什么软件可以打开此文件
        response.setHeader("content-Type", "application/vnd.ms-excel");
        // 下载文件的默认名称
        response.setHeader("Content-Disposition", "attachment;filename=product.xls");
        Workbook workbook = ExcelExportUtil.exportExcel(new ExportParams(), TotalOrder.class, list);
        workbook.write(response.getOutputStream());
    }
    // 下载execl文档
    @RequestMapping(value = "/travel",method = RequestMethod.GET)
    public void Travels( HttpServletResponse response, Date start, Date end) throws Exception {
        OrderFilter of = new OrderFilter();
        if (start != null) of.start = start;
        if (end != null) of.end = end;
        List<TravelInfo> list = travelsService.downTravelsByFilter(of);
        // 告诉浏览器用什么软件可以打开此文件
        response.setHeader("content-Type", "application/vnd.ms-excel");
        // 下载文件的默认名称
        response.setHeader("Content-Disposition", "attachment;filename=product.xls");
        Workbook workbook = ExcelExportUtil.exportExcel(new ExportParams(), TravelInfo.class, list);
        workbook.write(response.getOutputStream());
    }
}
