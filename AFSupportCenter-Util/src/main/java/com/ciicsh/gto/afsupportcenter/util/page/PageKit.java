package com.ciicsh.gto.afsupportcenter.util.page;

import com.baomidou.mybatisplus.plugins.pagination.PageHelper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import org.apache.commons.lang3.StringUtils;

import java.util.List;

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
                pagination.setAsc(pageInfo.isAsc());
                pagination.setOrderByField(pageInfo.getOrderBy());
            }
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
            List<T> list = selectPage.doSelect();
            pageRows.setRows(list);
            pageRows.setTotal(list.size());
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
