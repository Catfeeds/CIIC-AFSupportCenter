package com.ciicsh.gto.afsupportcenter.employmanagement.employservice.bo;

import com.ciicsh.gto.afsupportcenter.employmanagement.employservice.entity.AmEmployment;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class AmEmploymentBO extends AmEmployment {

    /**
     * 预留档案类型
     */
    private String yuliuDocType;

    /**
     * 预留档案编号
     */
    private String yuliuDocNum;
    /**
     * 档案类型
     */
    private String docType;
    /**
     * 档案编号
     */
    private String docNum;

    /**
     * 退工日期
     */
    private LocalDate resignDate;
    /**
     * 出库日期
     */
    private LocalDate storageOutDate;
    /**
     * 存档地
     */
    private String archivePlace;

    private String resignFeedback1;

    private String resignFeedback2;

    private LocalDate returnDocDate;
    /**
     * 退工送办日期
     */
    private LocalDate resignHandleDate;

    private  String employFeedback;

    private Integer taskCategory;

    private Integer remarkType;

    private Long archiveId;

    private String params;

    private List<String> param;

    private Integer taskStatus;

    private Integer taskResignStatus;

    private  Integer count;

    private  String archiveDirection;

    private String employeeNature;

    private String idNum;

    private Integer idCardType;

    private  String employeeName;

    private String title;

    private LocalDate jobCentreFeedbackDate;

    private  String outReason;

    private LocalDate outDate;

    private  String employNotes;

    private String cici;

    // 姓别
    private Integer gender;

    // 合同终止类型
    private String endType;

    /**
     * 服务中心
     */
    private  String serviceCenter;
    /**
     * 客服经理
     */
    private String leaderShipName;

    private  String archiveSpecial;

    private Long  empTaskResignId;

    private Integer taskStatusOther;

    private Integer taskResignStatusOther;

    private Integer taskCategoryResign;

    private String changeCompany;
    /**
     * 档案预增表主键
     */
    private Long archiveAdvanceId;
    /**
     * 档案来源
     */
    private String docFrom;

    private String job;
    /**
     * 用工属性编码
     */
    private Integer employCode;
    /**
     * 排序集合
     */
    private List<String> orderParam;

    private String refuseSpecial;

    private String employSpecial;

    private Boolean luyongHandleEnd;

}
