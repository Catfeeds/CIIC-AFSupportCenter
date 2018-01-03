package com.ciicsh.gto.afsupportcenter.util.kit;

import com.ciicsh.gto.afsupportcenter.util.exception.BusinessException;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * 反射工具类
 */
public class ReflectKit {

    public static <T> T newInstance(Class<T> clazz) {
        try {
            return clazz.newInstance();
        } catch (Exception e) {
            throw BusinessException.unchecked(e);
        }
    }

    /**
     * 获取 class 泛型实际类型
     *
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T> Class<T> getClassActualTypeArguments(Class<?> clazz) {
        return getClassActualTypeArguments(clazz, 0);
    }

    /**
     * 获取 class 泛型实际类型
     *
     * @param clazz
     * @param index
     * @return Class
     */
    public static <T> Class<T> getClassActualTypeArguments(Class<?> clazz, int index) {
        BusinessException.notNull(clazz, "class 不能为空");
        String name = clazz.getName();
        BusinessException.isTrue(!clazz.isInterface(), "class 类型不能是 interface：" + name);
        Type[] arguments = ((ParameterizedType) clazz.getGenericSuperclass()).getActualTypeArguments();
        BusinessException.isTrue(index >= 0 && index < arguments.length, "泛型参数索引不能越界：" + name);
        return (Class<T>) arguments[index];
    }
}


