package com.ciicsh.gto.afsupportcenter.util.page;

import java.util.List;

/**
 * 分页函数接口
 */
@FunctionalInterface
public interface SelectPage<T> {
  List<T> doSelect();
}
