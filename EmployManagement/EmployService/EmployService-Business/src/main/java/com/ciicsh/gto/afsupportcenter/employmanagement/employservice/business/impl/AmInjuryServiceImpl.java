package com.ciicsh.gto.afsupportcenter.employmanagement.employservice.business.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.ciicsh.gto.afsupportcenter.employmanagement.employservice.bo.AmInjuryBO;
import com.ciicsh.gto.afsupportcenter.employmanagement.employservice.business.IAmInjuryService;
import com.ciicsh.gto.afsupportcenter.employmanagement.employservice.dao.AmInjuryMapper;
import com.ciicsh.gto.afsupportcenter.employmanagement.employservice.entity.AmInjury;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by zhangzhiwen on 2018/2/7.
 */

@Service
public class AmInjuryServiceImpl extends ServiceImpl<AmInjuryMapper, AmInjury> implements IAmInjuryService {


    @Override
    public List<AmInjuryBO> queryAmInjury(AmInjuryBO amInjuryBO) {
        List<AmInjuryBO> amInjuryBOList = baseMapper.queryAmInjury(amInjuryBO);
        if(amInjuryBOList!=null&&amInjuryBOList.size()>0)
        {
            for(AmInjuryBO bo:amInjuryBOList){
                if(null!=bo.getIfComplete()&&bo.getIfComplete()==1){
                    bo.setIfCompleteLabel("是");
                }

                if(null!=bo.getIfGiveupEvaluation()&&bo.getIfGiveupEvaluation()==1){
                    bo.setIfGiveupEvaluationLabel("是");
                }
            }
        }
        return amInjuryBOList;
    }

}
