package com.ciicsh.gto.afsupportcenter.healthmedical.business.impl;

import com.ciicsh.gto.afsupportcenter.healthmedical.business.AfTpaTaskService;
import com.ciicsh.gto.afsupportcenter.healthmedical.entity.po.ZyTpaTaskPO;
import com.ciicsh.gto.afsupportcenter.healthmedical.entity.po.AfTpaTaskPO;
import com.ciicsh.gto.afsupportcenter.healthmedical.dao.ZyTpaTaskMapper;
import com.ciicsh.gto.afsupportcenter.healthmedical.business.ZyTpaTaskService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
public class ZyTpaTaskServiceImpl extends ServiceImpl<ZyTpaTaskMapper, ZyTpaTaskPO> implements ZyTpaTaskService {

    @Autowired
    private AfTpaTaskService afTpaTaskService;
   @Override
   public List<ZyTpaTaskPO> getZyTpaTaskListBymonth(String month,String zyInsurance_Policy_ID)
   {



       //<editor-fold desc="依据投保时间段读取前道过来的投保任务单">
       List<AfTpaTaskPO> poafTaskList =  afTpaTaskService.getAfTpaTaskByInsureDate("","","4");
       //</editor-fold>

       List<ZyTpaTaskPO> poList=null;
       //<editor-fold desc="依据保单把前道任务单转换成中盈投保格式任务单">
       


       //</editor-fold>

       return poList;
   }
}
