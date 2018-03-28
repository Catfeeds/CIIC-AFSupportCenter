package com.ciicsh.gto.afsupportcenter.employmanagement.employcommandservice.business.impl;

import com.ciicsh.gto.afsupportcenter.employmanagement.employcommandservice.bo.AmCustomBO;
import com.ciicsh.gto.afsupportcenter.employmanagement.employcommandservice.entity.AmEmpCustom;
import com.ciicsh.gto.afsupportcenter.employmanagement.employcommandservice.dao.AmEmpCustomMapper;
import com.ciicsh.gto.afsupportcenter.employmanagement.employcommandservice.business.IAmEmpCustomService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 客户信息表 服务实现类
 * </p>
 *
 * @author xsj
 * @since 2018-03-27
 */

@Service
public class AmEmpCustomServiceImpl extends ServiceImpl<AmEmpCustomMapper, AmEmpCustom> implements IAmEmpCustomService {

    @Override
    public AmCustomBO getCustom(AmCustomBO amCustomBO) {
         List<AmCustomBO> list = baseMapper.getCustom(amCustomBO);
         if(null!=list&&list.size()>0){
             return  baseMapper.getCustom(amCustomBO).get(0);
         }
         return  null;
    }
}
