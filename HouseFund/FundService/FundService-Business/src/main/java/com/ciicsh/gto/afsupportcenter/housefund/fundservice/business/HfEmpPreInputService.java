package com.ciicsh.gto.afsupportcenter.housefund.fundservice.business;

import com.baomidou.mybatisplus.service.IService;
import com.ciicsh.gto.afsupportcenter.housefund.fundservice.entity.HfEmpPreInput;

public interface HfEmpPreInputService extends IService<HfEmpPreInput> {

    boolean isEmpAccountNotExists(String empAccount, int hfType, String employeeId);
}
