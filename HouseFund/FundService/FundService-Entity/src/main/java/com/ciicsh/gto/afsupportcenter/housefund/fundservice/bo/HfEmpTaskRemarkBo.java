package com.ciicsh.gto.afsupportcenter.housefund.fundservice.bo;

import com.ciicsh.gto.afsupportcenter.housefund.fundservice.constant.HfEmpTaskConstant;
import com.ciicsh.gto.afsupportcenter.util.constant.DictUtil;
import com.ciicsh.gto.afsupportcenter.util.constant.SocialSecurityConst;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
public class HfEmpTaskRemarkBo implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long empTaskId;
    private Integer hfType;
    private String hfTypeName;
    private Integer processCategory;
    private Integer taskCategory;
    private String taskCategoryName;
    private Integer taskStatus;
    private String taskStatusName;
    private String modifiedBy;
    private String modifiedDisplayName;
    private LocalDateTime modifiedTime;
    private String handleRemark;
    private String rejectionRemark;
    private String remark;

    public String getHfTypeName() {
        return DictUtil.getInstance().getTextByItemValueAndTypeValue(String.valueOf(this.hfType), SocialSecurityConst.FUND_TYPE_KEY, false);
    }

    public String getTaskCategoryName() {
        return DictUtil.getInstance().getTextByItemValueAndTypeValue(String.valueOf(this.taskCategory), DictUtil.TYPE_VALUE_HF_LOCAL_TASK_CATEGORY, true);
    }

    public String getTaskStatusName() {
        return DictUtil.getInstance().getTextByItemValueAndTypeValue(String.valueOf(this.taskStatus), DictUtil.TYPE_VALUE_TASK_PROCESS_STATUS, false);
    }

    public String getRemark() {
        if (taskStatus != null && taskStatus == HfEmpTaskConstant.TASK_STATUS_REJECTED) {
            return this.rejectionRemark;
        } else {
            return this.handleRemark;
        }
    }
}
