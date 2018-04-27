package com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.bo;
import cn.afterturn.easypoi.excel.annotation.Excel;
import cn.afterturn.easypoi.excel.annotation.ExcelTarget;
import com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.entity.SsAccountRatio;
import com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.entity.SsComTask;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;


@Data
@EqualsAndHashCode(callSuper = false)
@ExcelTarget("SsComAccountBO")
public class SsComAccountBO {

    private Long comAccountId;

    private String customerId;

    private String supplierId;

    @Excel(name = "社保账户类型", replace = {"中智大库_1","中智外包_2","独立户_3"},orderNum = "2")
    private Integer ssAccountType;
    @Excel(name = "企业社保账号",orderNum = "4")
    private String ssAccount;

    private Long bankAccountId;

    private String bankAccount;

    @Excel(name = "养老金用公司名称", orderNum = "1")
    private String comAccountName;


    private String settlementArea;

    private String paymentBank;

    private Integer paymentWay;

    private Integer billReceiver;

    private Integer expireDate;

    private String ssUsername;

    private String ssPwd;

    private BigDecimal initialBalance;

    private BigDecimal initialDebt;

    private Integer originPlace;

    private String originPlaceRemark;
    private String queryAccount;

    private String deliverWay;

    private String deliverWayRemark;

    private LocalDate provideCertificateTime;

    private String legalPerson;

    private String contactAddress;

    private LocalDateTime changeTime;

    private LocalDate receiveDate;

    @Excel(name = "开户/转入日期",format = "yyyy-MM-dd",orderNum = "5")
    private LocalDate intoDate;
    @Excel(name = "终止日期",format = "yyyy-MM-dd",orderNum = "6")
    private LocalDate endDate;

    private String dispatchMaterial;

    @Excel(name = "备注说明",orderNum = "7")
    private String remark;

    @Excel(name = "状态", replace = {"初始_0","有效_1","终止_2","封存_3"},orderNum = "3")
    private Integer state;

    private Boolean isActive;

    private LocalDateTime createdTime;

    private LocalDateTime modifiedTime;

    private String createdBy;

    private String modifiedBy;

    //企业工伤比例变更(新增)
    private SsAccountRatio ssAccountRatio;

    // 来源表 sal_company
    // 客户编号
    private String companyId;

    private String companyName;

    // 客户名称
    private String title;
    //历史企业任务单
    private List<SsComTask> ssComTaskList;

    //账户关联公司
    private List<SsAccountComRelationBO> ssAccountComRelationBOList;
    //企业工伤比例变更(查询) 1对多
    private List<SsAccountRatio> ssAccountRatioList;
    //办理备注
    private String handleRemark;
    //批退备注
    private String rejectionRemark;
    private String leaderShipName;
    private String leaderShipName1;
}