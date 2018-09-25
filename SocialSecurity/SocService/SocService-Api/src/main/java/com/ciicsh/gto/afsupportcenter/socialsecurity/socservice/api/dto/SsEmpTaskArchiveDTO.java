package com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.api.dto;

import lombok.Data;

@Data
public class SsEmpTaskArchiveDTO {
    //雇员社保基数
    private String empBase;
    //任务单起缴月份
    private String startMonth;
    //任务单办理月份
    private String handleMonth;
    //任务单办理备注
    private String remark;
    //任务单类型 ID
    private Integer taskCategory;
    //任务单类型名称
    private String taskCategoryName;
    //雇员档案编号
    private Long emp_archive_id;
    //客户编号
    private String companyId;
    //雇员编号
    private String employeeId;
    //雇员姓名
    private String employeeName;
    //身份证号
    private String idNum;
    //社保序号
    private String ssSerial;
    //人员属性
    private Integer empClassify;
    //人员属性
    private String empClassifyName;
    //社保档案状态
    private String archiveStatus;
    //新开\转入办理年月
    private String ssMonth;
    //缴纳截至年月
    private String endMonth;
    //结算区县
    private String settlementArea;
}
