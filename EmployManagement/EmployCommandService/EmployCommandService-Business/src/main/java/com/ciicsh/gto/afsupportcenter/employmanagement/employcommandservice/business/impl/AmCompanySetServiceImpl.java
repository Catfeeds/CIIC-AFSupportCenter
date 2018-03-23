package com.ciicsh.gto.afsupportcenter.employmanagement.employcommandservice.business.impl;

import com.ciicsh.gto.afsupportcenter.employmanagement.employcommandservice.bo.AmCompanySetBO;
import com.ciicsh.gto.afsupportcenter.employmanagement.employcommandservice.entity.AmCompanySet;
import com.ciicsh.gto.afsupportcenter.employmanagement.employcommandservice.dao.AmCompanySetMapper;
import com.ciicsh.gto.afsupportcenter.employmanagement.employcommandservice.business.IAmCompanySetService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 公司特殊情况设置表 服务实现类
 * </p>
 *
 * @author xsj
 * @since 2018-03-21
 */

@Service
public class AmCompanySetServiceImpl extends ServiceImpl<AmCompanySetMapper, AmCompanySet> implements IAmCompanySetService {

    @Override
    public AmCompanySetBO queryAmCompanySet(AmCompanySetBO amCompanySetBO)
    {
        List<AmCompanySetBO> list = baseMapper.queryAmCompanySet(amCompanySetBO);
        if(null!=list&&list.size()>0){
            return  list.get(0);
        }
        return null;
    }
}
