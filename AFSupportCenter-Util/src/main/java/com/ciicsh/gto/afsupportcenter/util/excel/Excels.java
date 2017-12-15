package com.ciicsh.gto.afsupportcenter.util.excel;

import com.ciicsh.gto.afsupportcenter.util.kit.ReflectKit;
import org.springframework.util.ConcurrentReferenceHashMap;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Excels {

    // 换成 转换器
    private static final Map<Class<?>, Excels> declaredExcelsCache = new ConcurrentReferenceHashMap<>(256);

    public static Excels of(Class<?> excelClass) {
        Excels excels = declaredExcelsCache.get(excelClass);
        if (excels == null) {
            excels = (Excels) ReflectKit.newInstance(excelClass);
            excels.excelClass = excelClass;
            excels.parse();
            declaredExcelsCache.put(excelClass, excels);
        }
        return excels;
    }

    // excel clsaa
    private Class<?> excelClass;

    // 工作表名称
    private String sheetName;
    // 默认查询 field
    private Boolean field;
    // 扩展名 xls 2003 ，xlsx 2007
    private String ext;

    // 单元格列表 row
    private List<Cell> cells = new ArrayList<>();

    public Class<?> getExcelClass() {
        return excelClass;
    }

    public void setExcelClass(Class<?> excelClass) {
        this.excelClass = excelClass;
    }

    public String getSheetName() {
        return sheetName;
    }

    public void setSheetName(String sheetName) {
        this.sheetName = sheetName;
    }

    public Boolean getField() {
        return field;
    }

    public void setField(Boolean field) {
        this.field = field;
    }

    public String getExt() {
        return ext;
    }

    public void setExt(String ext) {
        this.ext = ext;
    }

    public List<Cell> getCells() {
        return cells;
    }

    public void setCells(List<Cell> cells) {
        this.cells = cells;
    }

    /**
     * 解析
     */
    public void parse() {
        handleSheet();
        handleCell();
        sort();
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

    /**
     * 处理 field
     */
    private void handleField() {
        Field[] fields = excelClass.getDeclaredFields();
        for (Field f : fields) {
            if (Modifier.isTransient(f.getModifiers()) || Modifier.isStatic(f.getModifiers())) {
                continue;
            }
            this.cells.add(createCell(f));
        }
    }

    /**
     * 处理 method
     */
    private void handleMethod() {
        try {
            BeanInfo info = Introspector.getBeanInfo(excelClass);
            PropertyDescriptor[] pds = info.getPropertyDescriptors();
            for (PropertyDescriptor pd : pds) {
                this.cells.add(createCell(pd));
            }
        } catch (IntrospectionException e) {
            e.printStackTrace();
        }
    }

    /**
     * 创建 cell
     *
     * @param f field or method
     * @return
     */
    private Cell createCell(PropertyDescriptor f) {
        ExcelCell excelCell = f.getReadMethod().getAnnotation(ExcelCell.class);
        Cell cell = new Cell();
        cell.setOrder(excelCell.order());
        cell.setTarget(f);
        cell.setTitle(excelCell.value());
        cell.setExcelConvert(ExcelConvert.of(excelCell.converter()));
        return cell;
    }


    /**
     * 创建 cell
     *
     * @param f field or method
     * @return
     */
    private Cell createCell(Field f) {
        ExcelCell excelCell = f.getAnnotation(ExcelCell.class);
        Cell cell = new Cell();
        cell.setOrder(excelCell.order());
        cell.setTarget(f);
        cell.setTitle(excelCell.value());
        cell.setExcelConvert(ExcelConvert.of(excelCell.converter()));
        return cell;
    }

    /**
     * 排序，升序
     */
    private void sort() {
        cells.sort((a, b) -> {
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
