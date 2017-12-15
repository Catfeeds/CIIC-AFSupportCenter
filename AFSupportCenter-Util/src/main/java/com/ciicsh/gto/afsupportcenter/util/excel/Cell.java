package com.ciicsh.gto.afsupportcenter.util.excel;

import com.ciicsh.gto.afsupportcenter.util.exception.ExcelException;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;

public class Cell {
    // 标题
    private String title;
    // 转换器
    private ExcelConvert excelConvert;
    // 序号
    private int order;

    // field or PropertyDescriptor
    private Object target;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Object getTarget() {
        return target;
    }

    public void setTarget(Object target) {
        this.target = target;
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

    /**
     * 获取值
     *
     * @return
     */
    public <T> T value(Object object, String text) {
        T value = (T) excelConvert.value(text);
        try {
            if (target instanceof Field) {
                Field f = (Field) target;
                f.setAccessible(true);
                f.set(object, value);
            } else if (target instanceof PropertyDescriptor) {// getter Method
                PropertyDescriptor pd = (PropertyDescriptor) target;
                pd.getWriteMethod().invoke(object, value);
            } else {
                throw new ExcelException("当前 cell 不支持此类型：" + target.getClass());
            }
        } catch (Exception e) {
            throw new ExcelException("获取值错误：" + e.getMessage());
        }
        return value;
    }

    /**
     * 获取文本
     *
     * @return
     */
    public String text(Object object) {
        try {
            if (target instanceof Field) {
                Field f = (Field) target;
                f.setAccessible(true);
                return excelConvert.text(f.get(object));
            } else if (target instanceof PropertyDescriptor) {// PropertyDescriptor
                PropertyDescriptor pd = (PropertyDescriptor) target;
                return excelConvert.text(pd.getReadMethod().invoke(object));
            } else {
                throw new ExcelException("当前 cell 不支持此类型：" + target.getClass());
            }
        } catch (Exception e) {
            throw new ExcelException("获取文本错误：" + e.getMessage());
        }
    }
}
