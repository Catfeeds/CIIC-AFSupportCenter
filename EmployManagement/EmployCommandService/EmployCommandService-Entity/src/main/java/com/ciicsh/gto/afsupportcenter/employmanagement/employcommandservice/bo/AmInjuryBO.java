package com.ciicsh.gto.afsupportcenter.employmanagement.employcommandservice.bo;

import com.ciicsh.gto.afsupportcenter.employmanagement.employcommandservice.entity.AmInjury;

/**
 * Created by zhangzhiwen on 2018/2/7.
 */
public class AmInjuryBO extends AmInjury {

    private  String  ifCompleteLabel;
    private  String  ifGiveupEvaluationLabel;

    public String getIfCompleteLabel() {
        return ifCompleteLabel;
    }

    public void setIfCompleteLabel(String ifCompleteLabel) {
        this.ifCompleteLabel = ifCompleteLabel;
    }

    public String getIfGiveupEvaluationLabel() {
        return ifGiveupEvaluationLabel;
    }

    public void setIfGiveupEvaluationLabel(String ifGiveupEvaluationLabel) {
        this.ifGiveupEvaluationLabel = ifGiveupEvaluationLabel;
    }
}
