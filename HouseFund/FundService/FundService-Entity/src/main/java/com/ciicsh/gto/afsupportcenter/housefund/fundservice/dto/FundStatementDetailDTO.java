package com.ciicsh.gto.afsupportcenter.housefund.fundservice.dto;

import java.util.ArrayList;
import java.util.List;

/**
 * 对账单详情
 */
public class FundStatementDetailDTO {
    /**
     * 公积金月份
     */
    private String hfMonth;

    /**
     * 公积金企业账户名称
     */
    private String comAccountName;

    /**
     * 导入记录总数
     */
    private int impRecordCount;

    /**
     * 差异总数
     */
    private int diffCount;

    /**
     * 对账单详情上的项目
     */
    private List<FundStatementItemDTO> items = new ArrayList<>();

    public FundStatementDetailDTO(){}

    public FundStatementDetailDTO(String hfMonth, String comAccountName, int impRecordCount, int diffCount) {
        this.hfMonth = hfMonth;
        this.comAccountName = comAccountName;
        this.impRecordCount = impRecordCount;
        this.diffCount = diffCount;
    }

    public String getHfMonth() {
        return hfMonth;
    }

    public void setHfMonth(String hfMonth) {
        this.hfMonth = hfMonth;
    }

    public String getComAccountName() {
        return comAccountName;
    }

    public void setComAccountName(String comAccountName) {
        this.comAccountName = comAccountName;
    }

    public int getImpRecordCount() {
        return impRecordCount;
    }

    public void setImpRecordCount(int impRecordCount) {
        this.impRecordCount = impRecordCount;
    }

    public int getDiffCount() {
        return diffCount;
    }

    public void setDiffCount(int diffCount) {
        this.diffCount = diffCount;
    }

    public List<FundStatementItemDTO> getItems() {
        return items;
    }

    public void setItems(List<FundStatementItemDTO> items) {
        this.items = items;
    }
}
