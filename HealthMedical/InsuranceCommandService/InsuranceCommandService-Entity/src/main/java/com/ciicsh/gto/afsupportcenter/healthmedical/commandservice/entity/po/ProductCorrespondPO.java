package com.ciicsh.gto.afsupportcenter.healthmedical.commandservice.entity.po;

import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.annotations.TableId;
import java.time.LocalTime;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 中智中盈产品对应表
包含中盈和中智的自己承包的产品
 * </p>
 *
 * @author zhaogang
 * @since 2017-12-29
 */
@TableName("hm_product_correspond")
public class ProductCorrespondPO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 保险产品编号
     */
	@TableId(value="insurance_product_id", type= IdType.AUTO)
	private Integer insuranceProductId;
    /**
     * 保险产品名称
     */
	@TableField("insurance_product_name")
	private String insuranceProductName;
    /**
     * 单项产品ID,11位编码规则(CPD+BusinessCategory+6位数字)
     */
	@TableField("product_id")
	private String productId;
    /**
     * 选择的服务项目ID，空格分隔
     */
	@TableField("service_items")
	private String serviceItems;
    /**
     * 保单编号
     */
	@TableField("insurance_policy_id")
	private Integer insurancePolicyId;
    /**
     * 类型
（1-主险 2-附件险 3-理赔规则）
     */
	private String type;
    /**
     * 是否可用
     */
	@TableField("is_active")
	private Boolean isActive;
    /**
     * 创建时间
     */
	@TableField("created_time")
	private LocalTime createdTime;
    /**
     * 最后更新时间
     */
	@TableField("modified_time")
	private LocalTime modifiedTime;
    /**
     * 创建者登录名
     */
	@TableField("created_by")
	private String createdBy;
    /**
     * 修改者登录名
     */
	@TableField("modified_by")
	private String modifiedBy;


	public Integer getInsuranceProductId() {
		return insuranceProductId;
	}

	public void setInsuranceProductId(Integer insuranceProductId) {
		this.insuranceProductId = insuranceProductId;
	}

	public String getInsuranceProductName() {
		return insuranceProductName;
	}

	public void setInsuranceProductName(String insuranceProductName) {
		this.insuranceProductName = insuranceProductName;
	}

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public String getServiceItems() {
		return serviceItems;
	}

	public void setServiceItems(String serviceItems) {
		this.serviceItems = serviceItems;
	}

	public Integer getInsurancePolicyId() {
		return insurancePolicyId;
	}

	public void setInsurancePolicyId(Integer insurancePolicyId) {
		this.insurancePolicyId = insurancePolicyId;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Boolean getActive() {
		return isActive;
	}

	public void setActive(Boolean isActive) {
		this.isActive = isActive;
	}

	public LocalTime getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(LocalTime createdTime) {
		this.createdTime = createdTime;
	}

	public LocalTime getModifiedTime() {
		return modifiedTime;
	}

	public void setModifiedTime(LocalTime modifiedTime) {
		this.modifiedTime = modifiedTime;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public String getModifiedBy() {
		return modifiedBy;
	}

	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	@Override
	public String toString() {
		return "ProductCorrespond{" +
			", insuranceProductId=" + insuranceProductId +
			", insuranceProductName=" + insuranceProductName +
			", productId=" + productId +
			", serviceItems=" + serviceItems +
			", insurancePolicyId=" + insurancePolicyId +
			", type=" + type +
			", isActive=" + isActive +
			", createdTime=" + createdTime +
			", modifiedTime=" + modifiedTime +
			", createdBy=" + createdBy +
			", modifiedBy=" + modifiedBy +
			"}";
	}
}
