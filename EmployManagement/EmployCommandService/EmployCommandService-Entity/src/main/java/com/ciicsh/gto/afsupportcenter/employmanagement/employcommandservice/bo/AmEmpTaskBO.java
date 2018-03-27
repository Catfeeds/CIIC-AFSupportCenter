package com.ciicsh.gto.afsupportcenter.employmanagement.employcommandservice.bo;

import com.ciicsh.gto.afsupportcenter.employmanagement.employcommandservice.entity.AmEmpTask;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public class AmEmpTaskBO extends AmEmpTask {

    private String employStyle;
    /**
     * 办理类型
     */
    private String handleType;
    /**
     * 预留档案编号
     */
    private String yuliuDocNum;
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
     *  调档反馈操作日期
     */
    private String diaodangFeedback;
    /**
     * 调档反馈操作日期
     */
    private LocalDate diaodangFeedbackOptDate;
    /**
     * 退工状态数量
     */
    private  Integer count;
    /**
     * 公司编码
     */
    private  String licenseCode;
    /**
     * 服务中心
     */
    private  String serviceCenter;
    /**
     * 客服
     */
    private String custom;
    /**
     * 退工日期
     */
    private LocalDate resignDate;
    /**
     * 实际录用日期
     */
    private LocalDate employDate;

    private String params;

    private  String remarkType;
    /**
     *  证件类型
     */
    private Integer idCardType;

    private  String sex;

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
    private  String settlementArea;
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
    private String  laborStartDate;
    /**
     * 合同截至日期
     */
    private String  laborEndDate;
    /**
     * 岗位
     */
    private String position;

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

    private  String openAfDate;
    /**
     * 客服用工属性
     */
    private  String templateType;

    private  String archivePlace;

    private Long employmentId;
    /**
     * 社保账户名称
     */
    private  String comAccountName;

    private  LocalDate accountRepairDate;
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


    public String getSendCondemnationYears() {
        return sendCondemnationYears;
    }

    public void setSendCondemnationYears(String sendCondemnationYears) {
        this.sendCondemnationYears = sendCondemnationYears;
    }

    public String getIsUnlimitedContract() {
        return isUnlimitedContract;
    }

    public void setIsUnlimitedContract(String isUnlimitedContract) {
        this.isUnlimitedContract = isUnlimitedContract;
    }

    public String getKeyCode() {
        return keyCode;
    }

    public void setKeyCode(String keyCode) {
        this.keyCode = keyCode;
    }

    public String getKeyPwd() {
        return keyPwd;
    }

    public void setKeyPwd(String keyPwd) {
        this.keyPwd = keyPwd;
    }

    public String getKeyStatus() {
        return keyStatus;
    }

    public void setKeyStatus(String keyStatus) {
        this.keyStatus = keyStatus;
    }

    public String getKeyType() {
        return keyType;
    }

    public void setKeyType(String keyType) {
        this.keyType = keyType;
    }

    public String getEmploySpecial() {
        return employSpecial;
    }

    public void setEmploySpecial(String employSpecial) {
        this.employSpecial = employSpecial;
    }

    public LocalDate getAccountRepairDate() {
        return accountRepairDate;
    }

    public void setAccountRepairDate(LocalDate accountRepairDate) {
        this.accountRepairDate = accountRepairDate;
    }

    public String getComAccountName() {
        return comAccountName;
    }

    public void setComAccountName(String comAccountName) {
        this.comAccountName = comAccountName;
    }

    public Long getEmploymentId() {
        return employmentId;
    }

    public void setEmploymentId(Long employmentId) {
        this.employmentId = employmentId;
    }

    public String getArchivePlace() {
        return archivePlace;
    }

    public void setArchivePlace(String archivePlace) {
        this.archivePlace = archivePlace;
    }

    public String getTemplateType() {
        return templateType;
    }

    public void setTemplateType(String templateType) {
        this.templateType = templateType;
    }

    public String getOpenAfDate() {
        return openAfDate;
    }

    public void setOpenAfDate(String openAfDate) {
        this.openAfDate = openAfDate;
    }

    public String getLaborStartDate() {
        return laborStartDate;
    }

    public void setLaborStartDate(String laborStartDate) {
        this.laborStartDate = laborStartDate;
    }

    public String getLaborEndDate() {
        return laborEndDate;
    }

    public void setLaborEndDate(String laborEndDate) {
        this.laborEndDate = laborEndDate;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getCustomServiceOperator() {
        return customServiceOperator;
    }

    public void setCustomServiceOperator(String customServiceOperator) {
        this.customServiceOperator = customServiceOperator;
    }

    public String getEmployeeCenterOperator() {
        return employeeCenterOperator;
    }

    public void setEmployeeCenterOperator(String employeeCenterOperator) {
        this.employeeCenterOperator = employeeCenterOperator;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getFirstInDate() {
        return firstInDate;
    }

    public void setFirstInDate(String firstInDate) {
        this.firstInDate = firstInDate;
    }

    public String getFirstInCompanyDate() {
        return firstInCompanyDate;
    }

    public void setFirstInCompanyDate(String firstInCompanyDate) {
        this.firstInCompanyDate = firstInCompanyDate;
    }

    public String getOrganizationCode() {
        return organizationCode;
    }

    public void setOrganizationCode(String organizationCode) {
        this.organizationCode = organizationCode;
    }

    public String getUkey() {
        return ukey;
    }

    public void setUkey(String ukey) {
        this.ukey = ukey;
    }

    public LocalDate getAccoutModified() {
        return accoutModified;
    }

    public void setAccoutModified(LocalDate accoutModified) {
        this.accoutModified = accoutModified;
    }

    public String getSsAccount() {
        return ssAccount;
    }

    public void setSsAccount(String ssAccount) {
        this.ssAccount = ssAccount;
    }

    public String getSettlementArea() {
        return settlementArea;
    }

    public void setSettlementArea(String settlementArea) {
        this.settlementArea = settlementArea;
    }

    public String getSsPwd() {
        return ssPwd;
    }

    public void setSsPwd(String ssPwd) {
        this.ssPwd = ssPwd;
    }

    public String getResidenceAddress() {
        return residenceAddress;
    }

    public void setResidenceAddress(String residenceAddress) {
        this.residenceAddress = residenceAddress;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    private List<String> param;

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public Integer getIdCardType() {
        return idCardType;
    }

    public void setIdCardType(Integer idCardType) {
        this.idCardType = idCardType;
    }

    public String getRemarkType() {
        return remarkType;
    }

    public void setRemarkType(String remarkType) {
        this.remarkType = remarkType;
    }

    public LocalDate getResignDate() {
        return resignDate;
    }

    public void setResignDate(LocalDate resignDate) {
        this.resignDate = resignDate;
    }
    
    public LocalDate getEmployDate() {
        return employDate;
    }

    public void setEmployDate(LocalDate employDate) {
        this.employDate = employDate;
    }

    public String getServiceCenter() {
        return serviceCenter;
    }

    public void setServiceCenter(String serviceCenter) {
        this.serviceCenter = serviceCenter;
    }

    public String getCustom() {
        return custom;
    }

    public void setCustom(String custom) {
        this.custom = custom;
    }

    public String getLicenseCode() {
        return licenseCode;
    }

    public void setLicenseCode(String licenseCode) {
        this.licenseCode = licenseCode;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public LocalDate getEmployFeedbackOptDate() {
        return employFeedbackOptDate;
    }

    public void setEmployFeedbackOptDate(LocalDate employFeedbackOptDate) {
        this.employFeedbackOptDate = employFeedbackOptDate;
    }

    public String getDiaodangFeedback() {
        return diaodangFeedback;
    }

    public void setDiaodangFeedback(String diaodangFeedback) {
        this.diaodangFeedback = diaodangFeedback;
    }

    public LocalDate getDiaodangFeedbackOptDate() {
        return diaodangFeedbackOptDate;
    }

    public void setDiaodangFeedbackOptDate(LocalDate diaodangFeedbackOptDate) {
        this.diaodangFeedbackOptDate = diaodangFeedbackOptDate;
    }

    public List<String> getParam() {
        return param;
    }

    public void setParam(List<String> param) {
        this.param = param;
    }

    public String getParams() {
        return params;
    }

    public void setParams(String params) {
        this.params = params;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public String getIdNum() {
        return idNum;
    }

    public void setIdNum(String idNum) {
        this.idNum = idNum;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public String getYuliuDocNum() {
        return yuliuDocNum;
    }

    public void setYuliuDocNum(String yuliuDocNum) {
        this.yuliuDocNum = yuliuDocNum;
    }

    public String getDocNum() {
        return docNum;
    }

    public void setDocNum(String docNum) {
        this.docNum = docNum;
    }

    public String getEmployWay() {
        return employWay;
    }

    public void setEmployWay(String employWay) {
        this.employWay = employWay;
    }

    public String getHandleType() {
        return handleType;
    }

    public void setHandleType(String handleType) {
        this.handleType = handleType;
    }

    public String getEmployStyle() {
        return employStyle;
    }

    public void setEmployStyle(String employStyle) {
        this.employStyle = employStyle;
    }
}
