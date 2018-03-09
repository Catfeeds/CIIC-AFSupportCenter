package com.ciicsh.gto.afsupportcenter.employmanagement.employcommandservice.business.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.ciicsh.gto.afsupportcenter.employmanagement.employcommandservice.bo.AmInjuryBO;
import com.ciicsh.gto.afsupportcenter.employmanagement.employcommandservice.business.IAmInjuryService;
import com.ciicsh.gto.afsupportcenter.employmanagement.employcommandservice.dao.AmInjuryMapper;
import com.ciicsh.gto.afsupportcenter.employmanagement.employcommandservice.entity.AmInjury;
import com.ciicsh.gto.afsupportcenter.util.page.PageInfo;
import com.ciicsh.gto.afsupportcenter.util.page.PageKit;
import com.ciicsh.gto.afsupportcenter.util.page.PageRows;
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

    @Override
    public boolean deleteAmInjury(Long injuryId) {
        int i =0;
        i = baseMapper.deleteAmInjury(injuryId);
        if(i>0){
            return  true;
        }else {
            return  false;
        }

    }
}
