package com.ciicsh.gto.afsupportcenter.util.model;

import javax.persistence.Column;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 基础 model
 */
public class BasicModel implements Serializable {

  private static final long serialVersionUID = 1L;

  /**
   * 是否有效
   */
  @Column(name = "IsActive")
  private Boolean isActive;

  /**
   * 创建时间
   */
  @Column(name = "DataChange_CreateTime")
  private LocalDateTime dataChangeCreateTime;

  /**
   * 修改时间
   */
  @Column(name = "DataChange_LastTime")
  private LocalDateTime dataChangeLastTime;

  /**
   * 创建者登录名
   */
  @Column(name = "CreatedBy")
  private String createdBy;

  /**
   * 修改者登录名
   */
  @Column(name = "ModifiedBy")
  private String modifiedBy;


  /**
   * 获取是否有效
   *
   * @return IsActive - 是否有效
   */
  public Boolean getIsActive() {
    return isActive;
  }

  /**
   * 设置是否有效
   *
   * @param isActive 是否有效
   */
  public void setIsActive(Boolean isActive) {
    this.isActive = isActive;
  }

  /**
   * 获取创建时间
   *
   * @return DataChange_CreateTime - 创建时间
   */
  public LocalDateTime getDataChangeCreateTime() {
    return dataChangeCreateTime;
  }

  /**
   * 设置创建时间
   *
   * @param dataChangeCreateTime 创建时间
   */
  public void setDataChangeCreateTime(LocalDateTime dataChangeCreateTime) {
    this.dataChangeCreateTime = dataChangeCreateTime;
  }

  /**
   * 获取修改时间
   *
   * @return DataChange_LastTime - 修改时间
   */
  public LocalDateTime getDataChangeLastTime() {
    return dataChangeLastTime;
  }

  /**
   * 设置修改时间
   *
   * @param dataChangeLastTime 修改时间
   */
  public void setDataChangeLastTime(LocalDateTime dataChangeLastTime) {
    this.dataChangeLastTime = dataChangeLastTime;
  }

  /**
   * 获取创建者登录名
   *
   * @return CreatedBy - 创建者登录名
   */
  public String getCreatedBy() {
    return createdBy;
  }

  /**
   * 设置创建者登录名
   *
   * @param createdBy 创建者登录名
   */
  public void setCreatedBy(String createdBy) {
    this.createdBy = createdBy == null ? null : createdBy.trim();
  }

  /**
   * 获取修改者登录名
   *
   * @return ModifiedBy - 修改者登录名
   */
  public String getModifiedBy() {
    return modifiedBy;
  }

  /**
   * 设置修改者登录名
   *
   * @param modifiedBy 修改者登录名
   */
  public void setModifiedBy(String modifiedBy) {
    this.modifiedBy = modifiedBy == null ? null : modifiedBy.trim();
  }
}
