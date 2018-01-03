package com.ciicsh.gto.afsupportcenter.util;

import com.baomidou.mybatisplus.plugins.Page;

import java.util.List;

public class PageUtil {
    public static <T, E> Page<T> changeWapper(Page<E> page, Class<T> t) {
        Page<T> pageT = new Page<>(page.getCurrent(), page.getSize());
        pageT.setTotal(page.getTotal());
        List<T> list = CommonTransform.convertToDTOs(page.getRecords(), t);
        pageT.setRecords(list);
        return pageT;
    }
}
