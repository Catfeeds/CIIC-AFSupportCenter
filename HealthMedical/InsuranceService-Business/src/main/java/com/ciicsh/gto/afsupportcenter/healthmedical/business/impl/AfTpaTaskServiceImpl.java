package com.ciicsh.gto.afsupportcenter.healthmedical.business.impl;

import com.ciicsh.gto.afsupportcenter.healthmedical.dao.AfTpaTaskMapper;
import com.ciicsh.gto.afsupportcenter.healthmedical.entity.po.AfTpaTaskPO;
import com.ciicsh.gto.afsupportcenter.healthmedical.dao.AfTpaTaskMapper;
import com.ciicsh.gto.afsupportcenter.healthmedical.business.ZyTpaTaskService;
import com.ciicsh.gto.afsupportcenter.healthmedical.business.AfTpaTaskService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * <p>
 * TPA任务单记录表 服务实现类
 * </p>
 *
 * @author zhaogang
 * @since 2018-02-11
 */
@Service
@Transactional
public class AfTpaTaskServiceImpl extends ServiceImpl<AfTpaTaskMapper, AfTpaTaskPO> implements AfTpaTaskService {
    @Autowired
    private AfTpaTaskMapper afTpaTaskMapper;

    @Autowired
    private ZyTpaTaskService zyTpaTaskService;

    @Override
    public List<AfTpaTaskPO> getAfTpaTaskByInsureDate(String startInsureDate, String endInsureDate, String zyInsurance_Policy_ID) {
            List<AfTpaTaskPO> poList = afTpaTaskMapper.getAfTpaTaskByInsureDate(startInsureDate,endInsureDate,zyInsurance_Policy_ID);
           return  poList;
    }

}
