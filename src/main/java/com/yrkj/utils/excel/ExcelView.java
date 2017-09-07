package com.yrkj.utils.excel;

import com.yrkj.model.excel.ExcelOrder;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.*;
import org.springframework.web.servlet.view.document.AbstractXlsView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.URLEncoder;
import java.util.List;
import java.util.Map;

/**
 * @Author Kent.Wang
 * @Date 2017/6/26
 */
public abstract class ExcelView extends AbstractXlsView {
    public CellStyle cellStyle;

    /**
     * 设置样式
     *
     * @param workbook
     */
    protected abstract void setStyle(Workbook workbook);

    /**
     * 设置Row，由子类实现
     *
     * @param sheet
     * @param map
     */
    protected abstract void setRow(Sheet sheet, Map<String, Object> map);

    @Override
    protected void buildExcelDocument(Map<String, Object> map,
                                      Workbook workbook,
                                      HttpServletRequest request,
                                      HttpServletResponse response) throws Exception {
        response.reset();
        String excelName = map.get("name").toString() + ".xls";
        String Agent = request.getHeader("User-Agent");
        if (null != Agent) {
            Agent = Agent.toLowerCase();
            if (Agent.indexOf("firefox") != -1) {
                response.setHeader("content-disposition", String.format("attachment;filename*=utf-8'zh_cn'%s", URLEncoder.encode(excelName, "utf-8")));

            } else {
                response.setHeader("content-disposition", "attachment;filename=" + URLEncoder.encode(excelName, "utf-8"));
            }
        }
        //下面是对中文文件名的处理
        response.setCharacterEncoding("UTF-8");//设置相应内容的编码格式
      String  fname = java.net.URLEncoder.encode(map.get("name").toString(),"UTF-8");
        response.setHeader("Content-Disposition","attachment;filename="+new String(fname.getBytes("UTF-8"),"GBK")+".xls");
        response.setContentType("application/msexcel");//定义输出类型
     //   SimpleExcelWrite sw = new SimpleExcelWrite();
     //   sw.createExcel(os);
      //  response.setContentType("application/ms-excel; charset=UTF-8");
        Sheet sheet = workbook.createSheet("订单信息");
        sheet.setDefaultColumnWidth(30);
        this.setStyle(workbook);
        setRow(sheet, map);
    }

}
