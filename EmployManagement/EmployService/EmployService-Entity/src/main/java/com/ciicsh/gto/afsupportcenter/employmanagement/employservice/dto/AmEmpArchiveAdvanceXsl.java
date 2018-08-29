package com.ciicsh.gto.afsupportcenter.employmanagement.employservice.dto;

import cn.afterturn.easypoi.excel.annotation.Excel;
import cn.afterturn.easypoi.excel.annotation.ExcelTarget;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

@Data
@EqualsAndHashCode(callSuper = false)
@ExcelTarget("AmEmpArchiveAdvanceXsl")
public class AmEmpArchiveAdvanceXsl {

        @Excel(name = "用工序号", orderNum = "1")
        private Long matchEmployIndex;

        @Excel(name = "雇员姓名",orderNum = "2")
        private String employmentName;

        @Excel(name = "身份证号码",orderNum = "3")
        private String idNum;

        @Excel(name = "雇员编号", orderNum = "4")
        private String employeeId;

        @Excel(name = "档案类别",orderNum = "5")
        private String docType;

        @Excel(name = "档案编号",orderNum = "6")
        private String docNum;

        @Excel(name = "档案来源",orderNum = "7")
        private String docFrom;

        @Excel(name = "存档地",orderNum = "8")
        private String archivePlace;

        @Excel(name = "入库日期",orderNum = "9")
        private Date createdDate;

        @Excel(name = "档案备注",orderNum = "10")
        private String docRemark;

        @Excel(name = "录用处理结束",orderNum = "11")
        private Boolean employHandleEnd;

    }

