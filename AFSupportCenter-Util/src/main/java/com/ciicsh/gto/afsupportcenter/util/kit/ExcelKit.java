package com.ciicsh.gto.afsupportcenter.util.kit;

import com.ciicsh.gto.afsupportcenter.util.excel.Cell;
import com.ciicsh.gto.afsupportcenter.util.excel.Excels;
import com.ciicsh.gto.afsupportcenter.util.exception.ExcelException;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.HorizontalAlignment;

import java.util.ArrayList;
import java.util.List;


/**
 * 导出Excel
 *
 * @author Guang
 */
public class ExcelKit {

    /**
     * 导出Excel
     *
     * @param list 要导出的数据集合
     * @return
     */
    public static <T> void export(List<T> list, Class<T> excelClass) {
        try {
            Excels excels = Excels.of(excelClass);
            //创建一个WorkBook,对应一个Excel文件
            HSSFWorkbook wb = new HSSFWorkbook();
            //在Workbook中，创建一个sheet，对应Excel中的工作薄（sheet）
            HSSFSheet sheet = wb.createSheet(excels.getSheetName());
            //创建单元格，并设置值表头 设置表头居中
            HSSFCellStyle style = wb.createCellStyle();
            //创建一个居中格式
            style.setAlignment(HorizontalAlignment.CENTER);
            // 填充工作表
            sheet(sheet, list, excels);

        } catch (Exception e) {
            throw new ExcelException("导出 Excel 失败：" + e.getMessage());
        }
    }

    /**
     * 向工作表中填充数据
     *
     * @param sheet excel的工作表名称
     * @param list  数据源
     */
    public static <T> void sheet(HSSFSheet sheet, List<T> list, Excels excels) {
        handles(sheet, excels);
        rows(sheet, list, excels);
    }

    /**
     * 处理标题
     *
     * @param sheet
     * @param excels
     */
    public static void handles(HSSFSheet sheet, Excels excels) {
        HSSFRow row = sheet.createRow(0);

        List<Cell> handles = excels.getCells();
        // 填充表头
        for (int i = 0; i < handles.size(); i++) {
            HSSFCell cell = row.createCell(i);
            cell.setCellValue(handles.get(i).getTitle());
            sheet.autoSizeColumn(i);
        }
    }

    /**
     * 处理行数据
     *
     * @param sheet
     * @param list
     * @param excels
     * @param <T>
     */
    public static <T> void rows(HSSFSheet sheet, List<T> list, Excels excels) {
        // 填充内容
        for (int rowIndex = 0; rowIndex < list.size(); rowIndex++) {
            // 从第二行开始，第一行是标题
            HSSFRow row = sheet.createRow(rowIndex + 1);
            List<String> cellValues = getCellValues(list.get(rowIndex), excels);

            for (int cellIndex = 0; cellIndex < cellValues.size(); cellIndex++) {
                String value = cellValues.get(cellIndex);
                if (value == null) {
                    row.createCell(cellIndex).setCellValue("");
                } else {
                    row.createCell(cellIndex).setCellValue(value);
                }
            }
        }
    }

    public static <T> List<String> getCellValues(T data, Excels excels) {
        List<Cell> cells = excels.getCells();
        List<String> cellValues = new ArrayList<>(cells.size());

        cells.forEach(cell -> cellValues.add(cell.text(data)));

        return cellValues;
    }

}