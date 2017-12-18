package com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.dto;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.entity.SsStatementResult;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * <p>
 * 对账差异结果表
 * </p>
 *
 * @author HuangXing
 * @since 2017-12-07
 */
@TableName("ss_statement_result")
public class SsStatementResultDTO extends SsStatementResult {

    private static final long serialVersionUID = 1L;


    /**
     * 雇员名
     */
    private String employeeName;


    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }
}
