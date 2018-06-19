package com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.bo;

import cn.afterturn.easypoi.excel.annotation.Excel;
import cn.afterturn.easypoi.excel.annotation.ExcelTarget;
import com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.dto.AmEmpTaskDTO;
import com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.entity.SsEmpBasePeriod;
import com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.entity.SsEmpTask;
import com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.entity.SsEmpTaskPeriod;
import com.ciicsh.gto.afsupportcenter.util.DateUtil;
import com.ciicsh.gto.afsupportcenter.util.constant.DictUtil;
import com.ciicsh.gto.afsupportcenter.util.constant.SocialSecurityConst;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

/**
 * SsEmpTask DTO
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ExcelTarget("SsEmpTaskBO")
public class SsEmpTaskBO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long empTaskId;
    private String customerId;
    private Long empArchiveId;
    private Integer isChange;
    private Integer taskCategory;
    @Excel(name = "变更内容", orderNum = "8")
    private String taskCategoryName;
    private Integer taskCategorySpecial;
    private Integer urgent;
    private String submitterId;
    private String submitterName;
    private String submitterDeptId;
    private String submitterDeptName;
    private LocalDateTime submitTime;
    private LocalDate expireDate;
    private String submitterRemark;
    private String handleUserId;
    private String handleUserName;
    @Excel(name = "序号", orderNum = "1", width = 15)
    private String empSsSerial;
    private Integer handleWay;
    private String handleMonth;
    private String handleRemark;
    private String handleRemarkMan;
    private LocalDate handleRemarkDate;
    private String rejectionRemark;
    private String rejectionRemarkMan;
    private LocalDate rejectionRemarkDate;
    private Integer taskStatus;
    private Integer handleStatus;
    private String chatHistory;
    private LocalDate startHandleDate;
    private LocalDate sendCheckDate;
    private LocalDate finishDate;
    private String taskFormContent;
    private BigDecimal salary;
    private Integer empClassify;
    @Excel(name = "个人属性", orderNum = "5", width = 15)
    private String empClassifyName;
    @Excel(name = "社保基数", orderNum = "9", width = 15)
    private BigDecimal empBase;
    @Excel(name = "截止月份", orderNum = "11")
    private String endMonth;
    private LocalDate outDate;
    private String businessInterfaceId;
    private String oldAgreementId;
    private String taskId;
    private String policyDetailId;
    private Boolean isActive;
    private LocalDateTime createdTime;
    private LocalDateTime modifiedTime;
    private String createdBy;
    private String modifiedBy;
    private String createdDisplayName;
    private String modifiedDisplayName;
    private String leaderShipId;
    @Excel(name = "客户经理", orderNum = "15")
    private String leaderShipName;
    private Integer serviceCenterId;
    @Excel(name = "服务中心", orderNum = "14", width = 20)
    private String serviceCenter;
    private String userId;

    //费用段
    private List<SsEmpBasePeriod> listEmpBasePeriod;
    // ohter
    // 操作类型，用于区分操作
    // operatorType 操作类型，1 常规操作、2 特殊操作，默认常规操作

    private Integer operatorType;
    // 任务类型
    private Integer[] taskCategories;
    // 任务发起开始时间

    private LocalDateTime beginSubmitTime;
    // 任务发起结束时间
    private LocalDateTime endSubmitTime;
    // 任务单费用段
    private List<SsEmpTaskPeriod> empTaskPeriods;
    // 来源表 emp_employee
    // 雇员姓名
    @Excel(name = "雇员姓名", orderNum = "3")
    private String employeeName;
    // 雇员编号
    @Excel(name = "雇员编号", orderNum = "2", width = 20)
    private String employeeId;
    // 雇员证件号
    @Excel(name = "雇员证件号", orderNum = "4", width = 30)
    private String idNum;
    // 来源表 sal_company
    // 客户编号
    @Excel(name = "客户编号", orderNum = "12", width = 20)
    private String companyId;
    // 客户名称
    @Excel(name = "公司名称", orderNum = "13", width = 20)
    private String title;
    // 来源表 ss_com_account
    // 企业社保账户Id
    private Long comAccountId;
    // 养老金独立开户密码（使用U盾登陆的密码）
    @Excel(name = "密码", orderNum = "17", width = 20)
    private String ssPwd;
    // 结算区县(社保局所在上海地区)
    private String settlementArea;
    // 账户类型：1:中智大库 2中智外包 3独立户
    private Integer ssAccountType;
    // 参保户登记码（账号）
    private String ssAccount;
    // 供应商Id
    private String supplierId;
    // 社保起缴月份
    @Excel(name = "起缴月份", orderNum = "10")
    private String startMonth;
    //入职日期
    private LocalDate inDate;
    //退账金额
    private BigDecimal refundAmount;
    //批量查询的id
    private List<Long> empTaskIdList;
    //存在相同已办任务单
    private List theSameTask;
    //顺调 和  倒调(1 顺调 0 倒调)
    private Integer adustType;

    //企业部分实缴金额（回调时使用）
    private BigDecimal companyConfirmAmount;

    //雇员部分实缴金额（回调使用）
    private BigDecimal personalConfirmAmount;

    //用退工信息
    private AmEmpTaskDTO amEmpTaskDTO;
    /**
     * 福利办理方
     */
    private Integer welfareUnit;
    //证件类型
    private Integer idCardType;

    //进位方式
    private Map<String, Map<String, Integer>> roundTypeMap;

    private SsEmpTask oldSsEmpTask;
    private String oldCityCode;
    private String newCityCode;
    private Boolean socialStartAndStop;

    private List<String> param;
    private  String params;
    private List<String> orderParam;

    private Integer afBpoFc;
    @Excel(name = "用工成功", replace = {"是_1", "否_0"}, orderNum = "15")
    private Integer employmentSuccess;
    private LocalDate openAfDate;
    @Excel(name = "开AF单日期", orderNum = "14", width = 15)
    private String openAfDateName;
    private Integer outReason;
    @Excel(name = "中止原因", orderNum = "16", width = 25)
    private String outReasonName;
    @Excel(name = "联系地址", orderNum = "7", width = 35)
    private String address;
    @Excel(name = "户口所在地", orderNum = "6", width = 25)
    private String residenceAddress;

    public String getTaskCategoryName() {
        return DictUtil.getInstance().getTextByItemValueAndTypeValue(String.valueOf(this.taskCategory), DictUtil.TYPE_VALUE_SOC_LOCAL_TASK_CATEGORY, false);
    }

    public void setTaskCategoryName(String taskCategoryName) {
        this.taskCategoryName = taskCategoryName;
    }

    public String getEmpClassifyName() {
        if (this.empClassify != null) {
            return DictUtil.getInstance().getTextByItemValueAndTypeValue(String.valueOf(this.empClassify), DictUtil.TYPE_VALUE_SOCIAL_SECURITY_EMPLOYEE_CLASSIFY, false);
        }
        return empClassifyName;
    }

    public void setEmpClassifyName(String empClassifyName) {
        this.empClassifyName = empClassifyName;
    }

    public String getOpenAfDateName() {
        if (openAfDateName != null) {
            return DateUtil.yyyyMMddHyphen(openAfDate);
        }
        return openAfDateName;
    }

    public void setOpenAfDateName(String openAfDateName) {
        this.openAfDateName = openAfDateName;
    }

    public String getOutReasonName() {
        if (this.outReason != null) {
            if (this.afBpoFc == 1) {
                return DictUtil.getInstance().getTextByItemValueAndTypeValue(String.valueOf(this.outReason), SocialSecurityConst.AF_EMP_OUT_REASON_KEY, false);
            } else if (this.afBpoFc == 2) {
                return DictUtil.getInstance().getTextByItemValueAndTypeValue(String.valueOf(this.outReason), SocialSecurityConst.BPO_EMP_OUT_REASON_KEY,false);
            }
        }
        return outReasonName;
    }

    public void setOutReasonName(String outReasonName) {
        this.outReasonName = outReasonName;
    }
}
