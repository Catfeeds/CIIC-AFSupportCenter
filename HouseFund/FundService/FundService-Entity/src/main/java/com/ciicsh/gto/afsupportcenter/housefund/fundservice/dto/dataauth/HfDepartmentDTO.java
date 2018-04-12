package com.ciicsh.gto.afsupportcenter.housefund.fundservice.dto.dataauth;

/**
 * 用户的部门岗DTO
 */
public class HfDepartmentDTO {
    /**
     * 部门id
     */
    private Integer departmentId;

    /**
     * 客户编号
     */
    private String customerId;

    /**
     * 部门名称
     */
    private String departmentName;

    /**
     * 部门层级
     */
    private Integer departmentLevel;

    /**
     * 上一级部门id
     */
    private Integer parentDepartmentId;

    /**
     * 备注
     */
    private String description;

    /**
     * 是否有效
     */
    private Boolean isValid;

    private static final long serialVersionUID = 1L;

    /**
     * 获取部门id
     *
     * @return department_id - 部门id
     */
    public Integer getDepartmentId() {
        return departmentId;
    }

    /**
     * 设置部门id
     *
     * @param departmentId 部门id
     */
    public void setDepartmentId(Integer departmentId) {
        this.departmentId = departmentId;
    }

    /**
     * 获取客户编号
     *
     * @return customer_id - 客户编号
     */
    public String getCustomerId() {
        return customerId;
    }

    /**
     * 设置客户编号
     *
     * @param customerId 客户编号
     */
    public void setCustomerId(String customerId) {
        this.customerId = customerId == null ? null : customerId.trim();
    }

    /**
     * 获取部门名称
     *
     * @return department_name - 部门名称
     */
    public String getDepartmentName() {
        return departmentName;
    }

    /**
     * 设置部门名称
     *
     * @param departmentName 部门名称
     */
    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName == null ? null : departmentName.trim();
    }

    /**
     * 获取部门层级
     *
     * @return department_level - 部门层级
     */
    public Integer getDepartmentLevel() {
        return departmentLevel;
    }

    /**
     * 设置部门层级
     *
     * @param departmentLevel 部门层级
     */
    public void setDepartmentLevel(Integer departmentLevel) {
        this.departmentLevel = departmentLevel;
    }

    /**
     * 获取上一级部门id
     *
     * @return parent_department_id - 上一级部门id
     */
    public Integer getParentDepartmentId() {
        return parentDepartmentId;
    }

    /**
     * 设置上一级部门id
     *
     * @param parentDepartmentId 上一级部门id
     */
    public void setParentDepartmentId(Integer parentDepartmentId) {
        this.parentDepartmentId = parentDepartmentId;
    }

    /**
     * 获取备注
     *
     * @return description - 备注
     */
    public String getDescription() {
        return description;
    }

    /**
     * 设置备注
     *
     * @param description 备注
     */
    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    /**
     * 获取是否有效
     *
     * @return is_valid - 是否有效
     */
    public Boolean getIsValid() {
        return isValid;
    }

    /**
     * 设置是否有效
     *
     * @param isValid 是否有效
     */
    public void setIsValid(Boolean isValid) {
        this.isValid = isValid;
    }
}