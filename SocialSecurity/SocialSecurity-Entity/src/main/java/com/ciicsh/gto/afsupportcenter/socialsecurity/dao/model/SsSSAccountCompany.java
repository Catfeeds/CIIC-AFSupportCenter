package com.ciicsh.gto.afsupportcenter.socialsecurity.dao.model;

import com.ciicsh.gto.afsupportcenter.util.model.BasicModel;
import java.io.Serializable;
import javax.persistence.*;

/**
 * 企业社保账户与公司关系表，当企业社保账户性质是独立库，例如：欧莱雅有10家子公司，在中智就一个社保账户
 */
@Table(name = "SS_SSAccountCompany")
public class SsSSAccountCompany extends BasicModel implements Serializable {
    /**
     * 记录Id
     */
    @Id
    @Column(name = "SSAccountCompanyId")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long SSAccountCompanyId;

    /**
     * 外键, 客户Id, 来自gtofrontdb.CMY_COMPANY
     */
    @Column(name = "CompanyId")
    private String companyId;

    /**
     * 外键, 企业社保账户Id
     */
    @Column(name = "ComAccountId")
    private String comAccountId;

    private static final long serialVersionUID = 1L;

    /**
     * 获取记录Id
     *
     * @return SSAccountCompanyId - 记录Id
     */
    public Long getSSAccountCompanyId() {
        return SSAccountCompanyId;
    }

    /**
     * 设置记录Id
     *
     * @param SSAccountCompanyId 记录Id
     */
    public void setSSAccountCompanyId(Long SSAccountCompanyId) {
        this.SSAccountCompanyId = SSAccountCompanyId;
    }

    /**
     * 获取外键, 客户Id, 来自gtofrontdb.CMY_COMPANY
     *
     * @return CompanyId - 外键, 客户Id, 来自gtofrontdb.CMY_COMPANY
     */
    public String getCompanyId() {
        return companyId;
    }

    /**
     * 设置外键, 客户Id, 来自gtofrontdb.CMY_COMPANY
     *
     * @param companyId 外键, 客户Id, 来自gtofrontdb.CMY_COMPANY
     */
    public void setCompanyId(String companyId) {
        this.companyId = companyId == null ? null : companyId.trim();
    }

    /**
     * 获取外键, 企业社保账户Id
     *
     * @return ComAccountId - 外键, 企业社保账户Id
     */
    public String getComAccountId() {
        return comAccountId;
    }

    /**
     * 设置外键, 企业社保账户Id
     *
     * @param comAccountId 外键, 企业社保账户Id
     */
    public void setComAccountId(String comAccountId) {
        this.comAccountId = comAccountId == null ? null : comAccountId.trim();
    }
}