package com.ciicsh.gto.afsupportcenter.util.tips;

/**
 * 分页 Tip
 */
public class PageTip<T> extends ListTip<T> {

  // 总数
  private long total;

  public long getTotal() {
    return total;
  }

  public void setTotal(long total) {
    this.total = total;
  }
}
