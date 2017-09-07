package com.yrkj.controller;

import com.yrkj.model.core.ActionResult;
import com.yrkj.model.order.OrderFilter;
import com.yrkj.model.product.ProductCodeInput;
import com.yrkj.service.OrderService;
import com.yrkj.service.ProductService;
import com.yrkj.utils.excel.CSVUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
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
    public
    void exportExcel(HttpServletRequest request, HttpServletResponse response) throws IOException{
        File csvFile = createCSVFile(request);

        BufferedInputStream bis = null;
        BufferedOutputStream bos = null;

        response.setHeader("Content-disposition", "attachment; filename=" + URLEncoder.encode(csvFile.getName(), "UTF-8"));

        response.setHeader("Content-Length", String.valueOf(csvFile.length()));

        bis = new BufferedInputStream(new FileInputStream(csvFile));
        bos = new BufferedOutputStream(response.getOutputStream());
        byte[] buff = new byte[2048];
        while (true) {
            int bytesRead;
            if (-1 == (bytesRead = bis.read(buff, 0, buff.length))) break;
            bos.write(buff, 0, bytesRead);
        }
        bis.close();
        bos.close();
    }
    public File createCSVFile(HttpServletRequest request){
        // 设置表格头
        Object[] head = {"a" };
        List<Object> headList = Arrays.asList(head);
       List list =_orderService.downOrdersByRecords(new OrderFilter());
        // 设置数据
        List<List<Object>> dataList = new ArrayList<List<Object>>();
        List<Object> rowList = null;
        for (int i = 0; i < list.size(); i++) {
            rowList = new ArrayList<Object>();
            Map<String,Object> temp = (Map<String,Object>)list.get(i);
            String value=String.valueOf(temp.get("productName")) ;
            rowList.add(value);
            dataList.add(rowList);
        }

        // 导出文件路径
        String downloadFilePath = "C:" + File.separator + "cap4j" + File.separator + "download" + File.separator;
        String fileName = "客户列表_" + "aaaa";
        // 导出CSV文件
        File csvFile = CSVUtils.createCSVFile(headList, dataList, downloadFilePath, fileName);
        return csvFile;
    }
}
