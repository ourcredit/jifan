package com.yrkj.controller;

import com.yrkj.model.core.ActionResult;
import com.yrkj.model.order.OrderFilter;
import com.yrkj.model.product.ProductCodeInput;
import com.yrkj.service.OrderService;
import com.yrkj.service.ProductService;
import com.yrkj.utils.excel.ExcelUtil;
import com.yrkj.utils.excel.ExportExcel;
import org.apache.poi.hssf.usermodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
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

    @RequestMapping(value = "/orders",method = RequestMethod.GET)
    public void exportExcel(HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        List list=_orderService.downOrdersByRecords(new OrderFilter());
        String[] excelHeader = { "商品名称"};

        ExcelUtil.excelExport(response,"订单信息",excelHeader,list);
    }
    @RequestMapping(value = "/test",method = RequestMethod.GET)
    public void Test(HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        List list=_orderService.downOrdersByRecords(new OrderFilter());
        String[] excelHeader = { "商品名称"};
//定义表的内容
        List<Object[]> dataList = new ArrayList<Object[]>();
        Object[] objs = null;
        for (int i = 0; i < list.size(); i++) {
            Map<String,Object> per =( Map<String,Object> ) list.get(i);
            objs = new Object[excelHeader.length];
            objs[0] = per.get("productName").toString();

            dataList.add(objs);
        }

        // 创建ExportExcel对象
        ExportExcel ex = new ExportExcel("订单详情", excelHeader, dataList);

        // 输出Excel文件
        try {
            OutputStream output = response.getOutputStream();
          /*  response.reset();
            response.setHeader("Content-disposition",
                    "attachment; filename="+encodeChineseDownloadFileName(request,"订单详情")+".xls");
            response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
*/
            response.setHeader("Expires", "0");
            response.setHeader("Cache-Control", "must-revalidate, post-check=0, pre-check=0");
            response.setHeader("Content-Disposition", "attachment; filename=1");
                    response.setHeader("Pragma", "public");
            response.setContentType("application/vnd.ms-excel;charset=gb2312");
            ex.export(output);
            output.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    public static String encodeChineseDownloadFileName(HttpServletRequest request, String pFileName) throws UnsupportedEncodingException {

        String filename = null;
        String agent = request.getHeader("USER-AGENT");
        if (null != agent){
            if (-1 != agent.indexOf("Firefox")) {//Firefox
                filename = "=?UTF-8?B?" + (new String(org.apache.commons.codec.binary.Base64.encodeBase64(pFileName.getBytes("UTF-8"))))+ "?=";
            }else if (-1 != agent.indexOf("Chrome")) {//Chrome
                filename = new String(pFileName.getBytes(), "ISO8859-1");
            } else {//IE7+
                filename = java.net.URLEncoder.encode(pFileName, "UTF-8");
            }
        } else {
            filename = pFileName;
        }
        return filename;
    }
}
