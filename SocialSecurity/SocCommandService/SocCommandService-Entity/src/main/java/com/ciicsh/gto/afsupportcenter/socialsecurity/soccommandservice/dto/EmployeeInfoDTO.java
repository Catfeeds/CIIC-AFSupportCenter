package com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.dto;

import javax.validation.Valid;
import java.util.List;

/**
 * @Description:
 * @author: wujinglei/吴敬磊
 * @date: 2017/12/4 18:00
 */
public class EmployeeInfoDTO extends BaseEntity {

    private static final long serialVersionUID = 1956978912500925712L;

    private String activeType;

    @Valid
    private AFEmployeeCompanyDTO employeeCompany;

    private List<AFEmpSocialDTO> empSocial;

    public String getActiveType() {
        return activeType;
    }

    public void setActiveType(String activeType) {
        this.activeType = activeType;
    }

    public AFEmployeeCompanyDTO getEmployeeCompany() {
        return employeeCompany;
    }

    public void setEmployeeCompany(AFEmployeeCompanyDTO employeeCompany) {
        this.employeeCompany = employeeCompany;
    }

    public List<AFEmpSocialDTO> getEmpSocial() {
        return empSocial;
    }

    public void setEmpSocial(List<AFEmpSocialDTO> empSocial) {
        this.empSocial = empSocial;
    }
}
