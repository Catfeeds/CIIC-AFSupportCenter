package com.ciicsh.gto.afsupportcenter.util.kit;

import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletResponse;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.List;


/**
 * 导出Excel
 *
 * @author Guang
 */
public class ExcelKit {

    private static final Logger logger = LoggerFactory.getLogger(ExcelKit.class);

    /**
     * 导出Excel
     *
     * @param list 要导出的数据集合
     * @return
     */
    public static <T> void export(List<T> list, Class<T> excelClass) {
        String sheetname = null;

        try {
            //创建一个WorkBook,对应一个Excel文件
            HSSFWorkbook wb = new HSSFWorkbook();
            //在Workbook中，创建一个sheet，对应Excel中的工作薄（sheet）
            HSSFSheet sheet = wb.createSheet(sheetname);
            //创建单元格，并设置值表头 设置表头居中
            HSSFCellStyle style = wb.createCellStyle();
            //创建一个居中格式
            style.setAlignment(HorizontalAlignment.CENTER);
            // 填充工作表
            sheet(sheet, list, excelClass);

        } catch (Exception e) {
            logger.info("导出Excel失败！");
            logger.error(e.getMessage());
        }
    }

    /**
     * 向工作表中填充数据
     *
     * @param sheet excel的工作表名称
     * @param list  数据源
     */
    public static <T> void sheet(HSSFSheet sheet, List<T> list, Class<?> excelClass) {
        handles(sheet, excelClass);
        rows(sheet, list, excelClass);
    }

    /**
     * 处理标题
     *
     * @param sheet
     * @param excelClass
     */
    public static void handles(HSSFSheet sheet, Class<?> excelClass) {
        List<String> handles = null;
        HSSFRow row = sheet.createRow(0);
        // 填充表头
        for (int i = 0; i < handles.size(); i++) {
            HSSFCell cell = row.createCell(i);
            cell.setCellValue(handles.get(i));
            sheet.autoSizeColumn(i);
        }
    }

    /**
     * 处理行数据
     *
     * @param sheet
     * @param list
     * @param excelClass
     * @param <T>
     */
    public static <T> void rows(HSSFSheet sheet, List<T> list, Class<?> excelClass) {
        // 填充内容
        for (int rowIndex = 0; rowIndex < list.size(); rowIndex++) {
            // 从第二行开始，第一行是标题
            HSSFRow row = sheet.createRow(rowIndex + 1);
            List<String> cellValues = getCellValues(list.get(rowIndex), excelClass);

            for (int cellIndex = 0; cellIndex < cellValues.size(); cellIndex++) {
                String value = null;
                if (value == null) {
                    row.createCell(cellIndex).setCellValue("");
                } else {
                    row.createCell(cellIndex).setCellValue(value);
                }
            }
        }
    }

    public static <T> List<String> getCellValues(T data, Class<?> excelClass) {
        return null;
    }

}