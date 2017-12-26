package com.ciicsh.gto.afsupportcenter.util.page;

import java.io.Serializable;
import java.util.List;

/**
 * 分页结果的封装
 */
public class PageRows<T> implements Serializable {

  // 分页数据
  private List<T> rows;

  // 总数
  private long total;

  public PageRows() {}

  public PageRows(long total) {
    this.total = total;
  }

  public PageRows(List<T> rows, long total) {
    this.rows = rows;
    this.total = total;
  }

  public long getTotal() {
    return total;
  }

  public void setTotal(long total) {
    this.total = total;
  }

  public List<T> getRows() {
    return rows;
  }

  public void setRows(List<T> rows) {
    this.rows = rows;
  }

}
