package com.ciicsh.gto.afsupportcenter.util.business;

import java.util.List;

import com.ciicsh.gto.afsupportcenter.util.page.PageInfo;
import com.ciicsh.gto.afsupportcenter.util.page.PageRows;

/**
 * 业务接口
 */
public interface IBusiness<T> {

  /**
   * 分页查询
   * 
   * @param pageInfo
   * @return
   */
  PageRows<T> finds(PageInfo pageInfo);

  /**
   * 查询数据列表
   * 
   * @param entity
   * @return
   */
  List<T> finds(T entity);

  /**
   * 查询数据
   *
   * @param entity
   * @return
   */
  T find(T entity);

  /**
   * 根据 id 查找
   * 
   * @param id
   * @return
   */
  T findById(Object id);

  /**
   * 保存
   * 
   * @param entity
   */
  int save(T entity);

  /**
   * 删除
   * 
   * @param entity
   */
  int delete(T entity);

  /**
   * 删除
   * 
   * @param id （复合主键类型 Map 或当前 bean，非复合主键直接传值）
   */
  int deleteById(Object id);

  /**
   * 更新
   * 
   * @param entity
   */
  int update(T entity);

  /**
   * 保存或更新，根据需求使用
   * 
   * @param entity
   * @return
   */
  int saveOrUpdate(T entity);
}
