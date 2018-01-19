package com.ciicsh.gto.afsupportcenter.healthmedical.business.impl;


import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.ciicsh.gto.afsupportcenter.healthmedical.business.UninsuredMedicalService;
import com.ciicsh.gto.afsupportcenter.healthmedical.dao.UninsuredMedicalMapper;
import com.ciicsh.gto.afsupportcenter.healthmedical.entity.po.UninsuredMedical;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 未投保医疗 服务实现类
 * </p>
 *
 * @author xiweizhen
 */
@Service
public class UninsuredMedicalServiceImpl extends ServiceImpl<UninsuredMedicalMapper, UninsuredMedical> implements UninsuredMedicalService {

    @Override
    public Page<UninsuredMedical> queryAcceptanceList(Page<UninsuredMedical> page, UninsuredMedical uninsuredMedical) {
        return null;
    }
}
