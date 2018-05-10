package com.ciicsh.gto.afsupportcenter.employmanagement.employservice.business.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.ciicsh.gto.afsupportcenter.employmanagement.employservice.bo.AmCustomBO;
import com.ciicsh.gto.afsupportcenter.employmanagement.employservice.business.IAmEmpCustomService;
import com.ciicsh.gto.afsupportcenter.employmanagement.employservice.dao.AmEmpCustomMapper;
import com.ciicsh.gto.afsupportcenter.employmanagement.employservice.entity.AmEmpCustom;
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
    public AmCustomBO getCustom(Long empTaskId) {
         List<AmCustomBO> list = baseMapper.getCustom(empTaskId);
         if(null!=list&&list.size()>0){
             return  list.get(0);
         }
         return  null;
    }
}
