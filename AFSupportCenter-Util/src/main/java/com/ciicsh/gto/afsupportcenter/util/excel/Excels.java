package com.ciicsh.gto.afsupportcenter.util.excel;

import java.lang.reflect.Field;
import java.util.List;

public class Excels {
    // excel clsaa
    private final Class<?> excelClass;

    // 工作表名称
    private String sheetName;
    // 默认查询 field
    private Boolean field;
    // 扩展名 xls 2003 ，xlsx 2007
    private String ext;

    // 转换器列表
    private List<ExcelConvert> converts;

    public Excels(Class<?> excelClass) {
        this.excelClass = excelClass;
    }

    /**
     * 解析
     */
    public void parse() {
        handleSheet();
        handleCell();
    }

    /**
     * 处理 sheet
     */
    private void handleSheet() {
        ExcelSheet sheet = excelClass.getAnnotation(ExcelSheet.class);
        this.sheetName = sheet.value();
        this.ext = sheet.ext();
        this.field = sheet.field();
    }

    /**
     * 处理 cell
     */
    private void handleCell() {
        if (field == null) {
            handleSheet();
        }
        if (Boolean.TRUE.equals(field)) {
            handleMethod();
        } else {
            handleField();
        }
    }

    private void handleField() {
        Field[] fields = excelClass.getDeclaredFields();
    }

    private void handleMethod() {

    }

    // 升序
    private void sort(List<Cells> list) {
        list.sort((a, b) -> {
            if (a.getOrder() == b.getOrder()) {
                return 0;
            } else if (a.getOrder() > b.getOrder()) {
                return 1;
            } else {
                return -1;
            }
        });
    }
}
