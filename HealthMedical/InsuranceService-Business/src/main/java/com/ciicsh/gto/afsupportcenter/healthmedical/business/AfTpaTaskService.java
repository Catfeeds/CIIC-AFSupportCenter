package com.ciicsh.gto.afsupportcenter.healthmedical.business;

import com.ciicsh.gto.afsupportcenter.healthmedical.entity.po.AfTpaTaskPO;
import com.baomidou.mybatisplus.service.IService;

import java.util.List;

/**
 * <p>
 * TPA任务单记录表 服务类
 * </p>
 *
 * @author zhaogang
 * @since 2018-02-11
 */
public interface AfTpaTaskService extends IService<AfTpaTaskPO> {
      List<AfTpaTaskPO> getAfTpaTaskByInsureDate(String startInsureDate,String endInsureDate,String zyInsurance_Policy_ID);
}
