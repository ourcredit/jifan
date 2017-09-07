package com.yrkj.utils.excel;

import com.yrkj.model.excel.ExcelOrder;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.*;

import java.util.List;
import java.util.Map;

public  class  OrderExcelView extends ExcelView{

    @Override
    protected void setStyle(Workbook workbook) {
        DefaultCellStyle defaultCellStyle = new DefaultCellStyleImpl();
        super.cellStyle = defaultCellStyle.setCellStyle(workbook);
    }

    @Override
    protected void setRow(Sheet sheet, Map<String, Object> map) {
        // create header row
        Row header = sheet.createRow(0);
        header.createCell(0).setCellValue("商品名称");
        header.getCell(0).setCellStyle(super.cellStyle);


        @SuppressWarnings("unchecked")
        List<ExcelOrder> list = (List<ExcelOrder>) map.get("members");
        int rowCount = 1;
        for (ExcelOrder user : list) {
            Row userRow = sheet.createRow(rowCount++);
            userRow.createCell(0).setCellValue(user.getProductName());

        }
    }
    public interface DefaultCellStyle {
        CellStyle setCellStyle(Workbook workbook);
    }
    public class DefaultCellStyleImpl implements DefaultCellStyle {
        @Override
        public CellStyle setCellStyle(Workbook workbook) {
            // create style for header cells
            CellStyle cellStyle = workbook.createCellStyle();
            Font font = workbook.createFont();
            font.setFontName("Arial");
            cellStyle.setFillForegroundColor(HSSFColor.BLUE.index);
            cellStyle.setFillPattern((short) 1);
            font.setColor(HSSFColor.WHITE.index);
            cellStyle.setFont(font);
            return cellStyle;
        }
    }
}
