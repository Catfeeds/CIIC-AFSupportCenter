package com.ciicsh.gto.afsupportcenter.util.excel;

import com.ciicsh.gto.afsupportcenter.util.kit.ReflectKit;
import org.springframework.util.ConcurrentReferenceHashMap;

import java.util.Map;

/**
 * excel 转换器
 *
 * @param <T>
 */
public class ExcelConvert<T> {

    // 换成 转换器
    private static final Map<Class<?>, ExcelConvert> declaredExcelConvertCache = new ConcurrentReferenceHashMap<>(256);

    public static <T extends ExcelConvert> ExcelConvert of(Class<T> clazz) {
        ExcelConvert convert = declaredExcelConvertCache.get(clazz);
        if (convert == null) {
            convert = ReflectKit.newInstance(clazz);
            declaredExcelConvertCache.put(clazz, convert);
        }
        return convert;
    }

    public T value(String text) {
        return (T) text;
    }

    public String text(T value) {
        return (value != null)
            ? value.toString()
            : null;
    }
}
