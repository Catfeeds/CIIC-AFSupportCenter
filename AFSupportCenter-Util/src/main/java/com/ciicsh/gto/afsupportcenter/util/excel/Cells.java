package com.ciicsh.gto.afsupportcenter.util.excel;

import java.lang.reflect.AccessibleObject;

public class Cells {
    // 标题
    private String titile;
    // 转换器
    private ExcelConvert excelConvert;
    // 序号
    private int order;

    // field or method
    private AccessibleObject accessibleObject;

    public String getTitile() {
        return titile;
    }

    public void setTitile(String titile) {
        this.titile = titile;
    }

    public ExcelConvert getExcelConvert() {
        return excelConvert;
    }

    public void setExcelConvert(ExcelConvert excelConvert) {
        this.excelConvert = excelConvert;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }
}
