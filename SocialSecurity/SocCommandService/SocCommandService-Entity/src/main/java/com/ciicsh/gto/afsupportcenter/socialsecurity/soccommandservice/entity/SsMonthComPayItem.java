package com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.entity;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 雇员月度费用明细项目
 * </p>
 *
 * @author HuangXing
 * @since 2017-12-15
 */
@TableName("ss_month_com_pay_item")
public class SsMonthComPayItem implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 记录Id  UUID_SHORT()
     */
    @TableId("month_com_pay_item_id")
	private Long monthComPayItemId;
    /**
     * 外键, 关联到雇员月度费用明细表
     */
	@TableField("month_com_pay_detail_id")
	private Long monthComPayDetailId;
    /**
     * 险种类型, 取自全局数据字典表gtobasicdb.DicItem
     */
	@TableField("ss_type")
	private Integer ssType;
    /**
     * 社保险种名称
     */
	@TableField("ss_name")
	private String ssName;
    /**
     * 企业金额
     */
	@TableField("com_amount")
	private BigDecimal comAmount;
    /**
     * 雇员金额
     */
	@TableField("emp_amount")
	private BigDecimal empAmount;
    /**
     * 合计金额
     */
	@TableField("sub_total_amount")
	private BigDecimal subTotalAmount;


	public Long getMonthComPayItemId() {
		return monthComPayItemId;
	}

	public void setMonthComPayItemId(Long monthComPayItemId) {
		this.monthComPayItemId = monthComPayItemId;
	}

	public Long getMonthComPayDetailId() {
		return monthComPayDetailId;
	}

	public void setMonthComPayDetailId(Long monthComPayDetailId) {
		this.monthComPayDetailId = monthComPayDetailId;
	}

	public Integer getSsType() {
		return ssType;
	}

	public void setSsType(Integer ssType) {
		this.ssType = ssType;
	}

	public String getSsName() {
		return ssName;
	}

	public void setSsName(String ssName) {
		this.ssName = ssName;
	}

	public BigDecimal getComAmount() {
		return comAmount;
	}

	public void setComAmount(BigDecimal comAmount) {
		this.comAmount = comAmount;
	}

	public BigDecimal getEmpAmount() {
		return empAmount;
	}

	public void setEmpAmount(BigDecimal empAmount) {
		this.empAmount = empAmount;
	}

	public BigDecimal getSubTotalAmount() {
		return subTotalAmount;
	}

	public void setSubTotalAmount(BigDecimal subTotalAmount) {
		this.subTotalAmount = subTotalAmount;
	}

	@Override
	public String toString() {
		return "SsMonthComPayItem{" +
			", monthComPayItemId=" + monthComPayItemId +
			", monthComPayDetailId=" + monthComPayDetailId +
			", ssType=" + ssType +
			", ssName=" + ssName +
			", comAmount=" + comAmount +
			", empAmount=" + empAmount +
			", subTotalAmount=" + subTotalAmount +
			"}";
	}
}
