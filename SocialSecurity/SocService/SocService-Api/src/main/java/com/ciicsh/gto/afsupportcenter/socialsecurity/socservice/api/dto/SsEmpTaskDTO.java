package com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.api.dto;/**
 * Created by zhengj on 2018/3/25
 */

import java.util.List;

/**
 * @author: zhengj
 * @date: 2018/3/25 14:34
 **/
public class SsEmpTaskDTO {

    /**
     * 办理方式1：网上申报2：柜面办理
     */
    private Integer handleWay;
    /**
     * 办理月份
     */
    private String handleMonth;
    /**
     * 办理类型1：新进2：转入（对应页面上面的变更类型）
     */
    private Integer taskCategory;
    /**
     * 社保序号
     */
    private String empSsSerial;
    /**
     * 起缴月份
     */
    private String startMonth;
    /**
     * 截止月份
     */
    private String endMonth;

    //费用段
    private List<SsEmpBasePeriodDTO> ssEmpBasePeriodDTOList;

    public Integer getHandleWay() {
        return handleWay;
    }

    public void setHandleWay(Integer handleWay) {
        this.handleWay = handleWay;
    }

    public String getHandleMonth() {
        return handleMonth;
    }

    public void setHandleMonth(String handleMonth) {
        this.handleMonth = handleMonth;
    }

    public Integer getTaskCategory() {
        return taskCategory;
    }

    public void setTaskCategory(Integer taskCategory) {
        this.taskCategory = taskCategory;
    }

    public String getEmpSsSerial() {
        return empSsSerial;
    }

    public void setEmpSsSerial(String empSsSerial) {
        this.empSsSerial = empSsSerial;
    }

    public String getStartMonth() {
        return startMonth;
    }

    public void setStartMonth(String startMonth) {
        this.startMonth = startMonth;
    }

    public String getEndMonth() {
        return endMonth;
    }

    public void setEndMonth(String endMonth) {
        this.endMonth = endMonth;
    }

    public List<SsEmpBasePeriodDTO> getSsEmpBasePeriodDTOList() {
        return ssEmpBasePeriodDTOList;
    }

    public void setSsEmpBasePeriodDTOList(List<SsEmpBasePeriodDTO> ssEmpBasePeriodDTOList) {
        this.ssEmpBasePeriodDTOList = ssEmpBasePeriodDTOList;
    }
}
