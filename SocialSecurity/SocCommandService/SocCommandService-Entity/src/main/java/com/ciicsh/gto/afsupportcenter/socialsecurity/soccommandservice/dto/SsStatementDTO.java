package com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.dto;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.entity.SsStatement;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 本地社保中，中智公司与社保局的对账单（各一条记录）
 * </p>
 *
 * @author wengxk
 * @since 2017-12-08
 */
public class SsStatementDTO extends SsStatement {

    private static final long serialVersionUID = 1L;


    /**
     * 外键, 企业社保账户名
     */
    private String comAccountName;

    /**
     * 最小差异数（按雇员）
     */
    private Integer minDiffSumByEmp;

    /**
     * 最大差异数（按雇员）
     */
    private Integer maxDiffSumByEmp;


    public Integer getMinDiffSumByEmp() {
        return minDiffSumByEmp;
    }

    public void setMinDiffSumByEmp(Integer minDiffSumByEmp) {
        this.minDiffSumByEmp = minDiffSumByEmp;
    }

    public Integer getMaxDiffSumByEmp() {
        return maxDiffSumByEmp;
    }

    public void setMaxDiffSumByEmp(Integer maxDiffSumByEmp) {
        this.maxDiffSumByEmp = maxDiffSumByEmp;
    }

    public String getComAccountName() {
        return comAccountName;
    }

    public void setComAccountName(String comAccountName) {
        this.comAccountName = comAccountName;
    }
}
