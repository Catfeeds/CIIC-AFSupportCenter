package com.ciicsh.gto.afsupportcenter.util.page;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
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
      Page<Object> page = PageHelper.startPage(pageInfo.getPageNum(), pageInfo.getPageSize());
      if (StringUtils.isNotBlank(pageInfo.getOrderBy())) {
        page.setOrderBy(pageInfo.getOrderBy());
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
      pageRows.setRows(selectPage.doSelect());
      pageRows.setTotal(pageRows.getRows().size());
    } else {
      startPage(pageInfo);
      com.github.pagehelper.PageInfo pi = new com.github.pagehelper.PageInfo<>(selectPage.doSelect());
      pageRows.setRows(pi.getList());
      pageRows.setTotal(pi.getTotal());
    }
    return pageRows;
  }

  /**
   * 转换到 PageRows
   *
   * @param selectPage
   * @param <T>
   * @return
   */
  public static <T> PageRows<T> doSelectPage(SelectPage<T> selectPage) {
    return doSelectPage(null, selectPage);
  }
}
