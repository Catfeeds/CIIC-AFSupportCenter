package com.ciicsh.gto.afsupportcenter.housefund.fundservice.business.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.ciicsh.gto.afsupportcenter.housefund.fundservice.business.HfEmpArchiveService;
import com.ciicsh.gto.afsupportcenter.housefund.fundservice.business.HfEmpPreInputService;
import com.ciicsh.gto.afsupportcenter.housefund.fundservice.constant.HfEmpTaskConstant;
import com.ciicsh.gto.afsupportcenter.housefund.fundservice.dao.HfEmpPreInputMapper;
import com.ciicsh.gto.afsupportcenter.housefund.fundservice.entity.HfEmpPreInput;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HfEmpPreInputServiceImpl extends ServiceImpl<HfEmpPreInputMapper, HfEmpPreInput> implements HfEmpPreInputService {
    @Autowired
    HfEmpArchiveService hfEmpArchiveService;

    @Override
    public boolean isEmpAccountNotExists(String empAccount, int hfType, String employeeId) {
        EntityWrapper<HfEmpPreInput> wrapper = new EntityWrapper<>();
        wrapper.where("is_active = 1");

        if (hfType == HfEmpTaskConstant.HF_TYPE_BASIC) {
            wrapper.and("hf_emp_bas_account = {0}", empAccount);
        } else {
            wrapper.and("hf_emp_add_account = {0}", empAccount);
        }
        HfEmpPreInput exist = selectOne(wrapper);

        if (exist != null) {
            return false;
        } else {
            return hfEmpArchiveService.isEmpAccountNotExists(empAccount, hfType, employeeId, false);
        }
    }
}
