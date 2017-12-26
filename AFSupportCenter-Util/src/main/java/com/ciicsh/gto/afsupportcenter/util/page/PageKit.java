package com.ciicsh.gto.afsupportcenter.util.page;

import com.baomidou.mybatisplus.plugins.pagination.PageHelper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import org.apache.commons.lang3.StringUtils;

/**
 * 分页工具类
 */
public class PageKit {

    /**
     * 启动分页
     *
     * @param pageInfo
     */
    public static void startPage(PageInfo pageInfo) {
        if (pageInfo != null) {
            PageHelper.startPage(pageInfo.getPageNum(), pageInfo.getPageSize());
            String orderBy = pageInfo.getOrderBy();
            if (StringUtils.isNotBlank(orderBy)) {
                Pagination pagination = PageHelper.getPagination();
                handleOrderBy(pagination, orderBy.trim());
            }
        }
    }

    private static void handleOrderBy(Pagination pagination, String orderBy) {
        int idx = orderBy.lastIndexOf(" ");
        if (idx > 0) {
            pagination.setAsc("asc".equalsIgnoreCase(orderBy.substring(idx + 1)));
            pagination.setOrderByField(orderBy.substring(0, idx));
        } else {// 没有排序关键字
            pagination.setAsc(true);
            pagination.setOrderByField(orderBy);
        }
    }

    /**
     * 转换到 PageRows
     *
     * @param pageInfo
     * @param selectPage
     * @param <T>
     * @return
     */
    public static <T> PageRows<T> doSelectPage(PageInfo pageInfo, SelectPage<T> selectPage) {
        PageRows<T> pageRows = new PageRows<>();
        // 没有分页条件直接处理
        if (pageInfo == null || (pageInfo.getPageNum() == null && pageInfo.getPageSize() == null)) {
            pageRows.setRows(selectPage.doSelect());
            pageRows.setTotal(pageRows.getRows().size());
        } else {
            try {
                startPage(pageInfo);
                pageRows.setRows(selectPage.doSelect());
                pageRows.setTotal(PageHelper.getTotal());
            } finally {
                // 释放
                PageHelper.remove();
            }
        }
        return pageRows;
    }
}
