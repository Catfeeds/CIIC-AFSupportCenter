package com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.dto;

import com.alibaba.fastjson.annotation.JSONField;
import com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.entity.SsComMaterial;
import com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.entity.SsComTask;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public class SsComTaskDTO extends SsComTask{
    //客户名称
    private String companyName;

    //任务发起时间段的 首段
    @JSONField(format="yyyy-MM-dd")
    private Date submitTimeStart;

    //任务发起时间的 尾段
    @JSONField(format="yyyy-MM-dd")
    private Date submitTimeEnd;

    //账户类型
    private String accountType;

    //结算区县
    private String regionValue;

    //客户经理
    private String customerManager;

    //材料清单
     private List<SsComMaterial> materialList;

    //账户
    private SsComAccountDTO ssComAccountDTO;

    //操作类型  1 开户 2 转移 3 变更 4 终止
    private String operatorType;

    //终止操作时 的终止日期
    private LocalDate endDate;

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public Date getSubmitTimeStart() {
        return submitTimeStart;
    }

    public Date getSubmitTimeEnd() {
        return submitTimeEnd;
    }

    public void setSubmitTimeStart(Date submitTimeStart) {
        this.submitTimeStart = submitTimeStart;
    }

    public void setSubmitTimeEnd(Date submitTimeEnd) {
        this.submitTimeEnd = submitTimeEnd;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public String getRegionValue() {
        return regionValue;
    }

    public void setRegionValue(String regionValue) {
        this.regionValue = regionValue;
    }

    public String getCustomerManager() {
        return customerManager;
    }

    public void setCustomerManager(String customerManager) {
        this.customerManager = customerManager;
    }

    public List<SsComMaterial> getMaterialList() {
        return materialList;
    }

    public void setMaterialList(List<SsComMaterial> materialList) {
        this.materialList = materialList;
    }

    public SsComAccountDTO getSsComAccountDTO() {
        return ssComAccountDTO;
    }

    public void setSsComAccountDTO(SsComAccountDTO ssComAccountDTO) {
        this.ssComAccountDTO = ssComAccountDTO;
    }

    public String getOperatorType() {
        return operatorType;
    }

    public void setOperatorType(String operatorType) {
        this.operatorType = operatorType;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }
}
