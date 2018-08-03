package com.ciicsh.gto.afsupportcenter.employmanagement.employservice.bo;

import com.ciicsh.gto.afsupportcenter.employmanagement.employservice.entity.AmEmpTask;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class AmEmpTaskBO extends AmEmpTask {

    private String employStyle;
    /**
     * 办理类型
     */
    private String handleType;
    /**
     * 预留档案类别
     */
    private String yuliuDocType;
    /**
     * 预留档案编号
     */
    private String yuliuDocNum;
    /**
     * 身份证号码
     */
    private String idNum;
    /**
     * 档案类别
     */
    private String docType;
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
    private String diaodangFeedback;
    /**
     * 调档反馈操作日期
     */
    private LocalDate diaodangFeedbackOptDate;
    /**
     * 退工状态数量
     */
    private Integer count;
    /**
     * 服务中心
     */
    private String serviceCenter;
    /**
     * 客服经理
     */
    private String leaderShipName;
    /**
     * 退工日期
     */
    private LocalDate resignDate;
    /**
     * 实际录用日期
     */
    private LocalDate employDate;

    private String params;

    private String remarkType;
    /**
     * 证件类型
     */
    private Integer idCardType;

    private String sex;

    private String mobile;
    /**
     * 户籍地址
     */
    private String residenceAddress;

    private String ssPwd;

    private String ukey;

    private LocalDate accoutModified;
    /**
     * 公司社保登记码
     */
    private String ssAccount;
    /**
     * 缴费区县
     */
    private String settlementArea;
    /**
     * 首次进中智日期
     */
    private String firstInDate;
    /**
     * 首月合同生效日期
     */
    private String firstInCompanyDate;
    /**
     * 合同起始日期
     */
    private String laborStartDate;
    /**
     * 合同截至日期
     */
    private String laborEndDate;
    /**
     * 岗位
     */
    private String position;
    /**
     * 组织机构代码
     */
    private String organizationCode;

    /**
     * 客服专员
     */
    private String customServiceOperator;
    /**
     * 雇员服务专员
     */
    private String employeeCenterOperator;
    /**
     * 客户名称
     */
    private String companyName;
    /**
     * 空、Ukey、集体转入,用工自办、翻牌、无材料用工、网办无材料、转人员性质、新进转人员性质、送外区办、修改信息、外来新进、外来转入
     */
    private String employWay;

    private String openAfDate;
    /**
     * 客服用工属性
     */
    private String templateType;

    private String archivePlace;

    private Long employmentId;
    /**
     * 社保账户名称
     */
    private String comAccountName;

    private LocalDate accountRepairDate;
    /**
     * 用工公司特殊情况
     */
    private String employSpecial;

    /**
     * Ukey类别
     */
    private String keyType;
    /**
     * Ukey编码
     */
    private String keyCode;
    /**
     * Ukey密码
     */
    private String keyPwd;
    /**
     * Ukey状态
     */
    private String keyStatus;
    /**
     * 是否无期限合同
     */
    private String isUnlimitedContract;
    /**
     * 派遣年限
     */
    private String sendCondemnationYears;

    /**
     * 单位性质
     */
    private String companyType;

    private String phone;
    /**
     * 档案费
     */
    private String fileFee;

    private String cici;

    private String material;

    private List<String> orderParam;

    private List<String> param;

}
