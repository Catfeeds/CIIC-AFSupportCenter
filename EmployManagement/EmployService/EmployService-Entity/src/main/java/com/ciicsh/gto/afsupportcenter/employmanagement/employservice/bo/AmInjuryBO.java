package com.ciicsh.gto.afsupportcenter.employmanagement.employservice.bo;

import com.ciicsh.gto.afsupportcenter.employmanagement.employservice.entity.AmInjury;
import lombok.Data;

/**
 * Created by zhangzhiwen on 2018/2/7.
 */
@Data
public class AmInjuryBO extends AmInjury {

    private  String  ifCompleteLabel;
    private  String  ifGiveupEvaluationLabel;
}
