package com.ciicsh.gto.afsupportcenter.employmanagement.employservice.bo;

import com.ciicsh.gto.afsupportcenter.employmanagement.employservice.entity.AmResign;
import lombok.Data;
import java.time.LocalDate;
import java.util.List;

@Data
public class AmResignBO extends AmResign {

    private String employStyle;
    /**
     * 办理类型
     */
    private String handleType;
    /**
     * 用工属性
     */
    private String employProperty;
    /**
     * 预留档案编号
     */
    private String yuliuDocNum;
    /**
     * 存档地
     */
    private String archivePlace;

    /**
     * 身份证号码
     */
    private String idNum;
    /**
     * 档案编号

     */
    private String docNum;
    /**
     * 公司名称
     */
    private String title;
    /**
     * 雇员姓名
     */
    private String employeeName;
    /**
     * 用工反馈操作日期
     */
    private LocalDate employFeedbackOptDate;

    /**
     * 调档反馈操作日期
     */
    private LocalDate diaodangFeedbackOptDate;
    /**
     * 出库日期
     */
    private  LocalDate storageOutDate;
    /**
     * 退工状态数量
     */
    private  Integer count;

    /**
     * 服务中心
     */
    private  String serviceCenter;
    /**
     * 客服
     */
    private String custom;
    /**
     * 实际录用日期
     */
    private LocalDate employDate;

    private String oldResignFeedback;

    /**
     * 入库日期
     */
    private LocalDate storageDate;

    private  String diaodangFeedback;

    private String docCode;

    private  String  archivePlaceAdditional;

    private String archiveCardState;

    private Integer taskStatus;

    private Integer taskStatusOther;

    private String remarkType;

    private Integer idCardType;

    private Long empTaskId;
    /**
     * 用工反馈
     */
    private  String employFeedback;

    private String matchEmployIndex;
    /**
     * 首次进中智日期
     */
    private String firstInDate;

    private String  outReason;

    private String outDate;

    private String params;

    private Boolean luyongHandleEnd;

    private String  luyongHandleEndStr;

    private  String ifLaborManualReturnStr;
    /**
     * 客服经理
     */
    private String leaderShipName;

    /**
     * 用工属性编码
     */
    private Integer employCode;

    private String cici;

    private  String refuseSpecial;

    private String archiveDirection;

    private String changeCompany;

    private String docType;

    private String yuliuDocType;

    private Long[] empTaskIds;

    private String remark;

    private String job;

    private List<String> param;

    private List<String> orderParam;

    private  Integer  post;

    private  Integer isReturn;

    private String postSaver;

    private Boolean checked=false;

}
