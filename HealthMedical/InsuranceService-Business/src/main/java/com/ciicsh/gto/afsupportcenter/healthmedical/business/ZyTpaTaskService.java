package com.ciicsh.gto.afsupportcenter.healthmedical.business;

import com.ciicsh.gto.afsupportcenter.healthmedical.entity.po.ZyTpaTaskPO;

import java.util.List;

/**
 * <p>
 * TPA任务单记录表 服务类
 * </p>
 *
 * @author zhaogang
 * @since 2018-02-11
 */
public interface ZyTpaTaskService {
    List<ZyTpaTaskPO> getZyTpaTaskListBymonth(String month,String zyProductID);
}