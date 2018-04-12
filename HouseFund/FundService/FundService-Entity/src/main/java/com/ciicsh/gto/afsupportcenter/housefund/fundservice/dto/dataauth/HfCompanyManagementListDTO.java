package com.ciicsh.gto.afsupportcenter.housefund.fundservice.dto.dataauth;

import java.io.Serializable;
import java.util.List;

public class HfCompanyManagementListDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 调用客服中心返回的客户列表
     */
    private List<HfCompanyManagementDTO> dtoList;
    /**
     * 已配置的客户
     */
    private List<String> companyIds;

    /**
     * 状态码
     */
    private int code;

    /**
     * 信息
     */
    private String message;


    public List<String> getCompanyIds() {
        return companyIds;
    }

    public void setCompanyIds(List<String> companyIds) {
        this.companyIds = companyIds;
    }

    public List<HfCompanyManagementDTO> getDtoList() {
        return dtoList;
    }

    public void setDtoList(List<HfCompanyManagementDTO> dtoList) {
        this.dtoList = dtoList;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
