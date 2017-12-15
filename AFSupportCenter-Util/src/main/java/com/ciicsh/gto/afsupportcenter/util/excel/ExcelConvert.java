package com.ciicsh.gto.afsupportcenter.util.excel;

/**
 * excel 转换器
 *
 * @param <T>
 */
public class ExcelConvert<T> {

    // 处理之后的值
    private T value;

    public Object getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }

    public String getAsText() {
        return (this.value != null)
            ? this.value.toString()
            : null;
    }

    public void setAsText(String text) {
        if (value instanceof String) {
            setValue((T) text);
            return;
        }
        throw new java.lang.IllegalArgumentException(text);
    }
}
