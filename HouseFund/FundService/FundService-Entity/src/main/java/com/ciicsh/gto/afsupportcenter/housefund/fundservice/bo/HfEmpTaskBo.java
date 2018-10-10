package com.ciicsh.gto.afsupportcenter.housefund.fundservice.bo;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@Data
public class HfEmpTaskBo implements Serializable {
    private static final long serialVersionUID = 1L;

    private String companyId;
    private String employeeId;
    private String employeeName;
    private String idNum;
    private Integer hfAccountType;
    private Integer hfType;
    private Integer paymentBank;
//    private Integer dictTaskCategory;
    private Integer processCategory;
    private Integer taskCategory;
    private Integer urgent;
    private Integer isChange;
    private Integer processStatus;
    private Integer taskStatus;
//    private String[] submitTime;
    private String submitTimeStart;
    private String submitTimeEnd;
    private String exceptTaskCategories;
    private String includeTaskCategories;
    private String hfComAccount;
    private String userId;
    private List<String> param;
    private String params;
    private List<String> orderParam;
    private Integer preInput;
    private Long[] selectedRecord;
    private String empTaskIds;
    private List<String> empTaskIdsList;
//    public void setSubmitTime(String[] submitTime) {
//        this.submitTime = submitTime;
//        if (submitTime != null && submitTime.length == 2) {
//            setSubmitTimeStart(submitTime[0]);
//            setSubmitTimeEnd(submitTime[1]);
//        }
//    }

//    public void setDictTaskCategory(Integer dictTaskCategory) {
//        this.dictTaskCategory = dictTaskCategory;
//        EmpTaskCategoryConverter.convertCategoriesFromDict(this);
//    }
}
