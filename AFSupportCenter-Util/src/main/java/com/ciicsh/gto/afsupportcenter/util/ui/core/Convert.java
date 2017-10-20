package com.ciicsh.gto.afsupportcenter.util.ui.core;

/**
 * 数据转换函数接口
 */
@FunctionalInterface
public interface Convert<S,T> {
  S handle(T t);
}
