package com.ciicsh.gto.afsupportcenter.housefund.fundservice.business;

import com.baomidou.mybatisplus.service.IService;
import com.ciicsh.gto.afsupportcenter.housefund.fundservice.entity.HfComTask;

public interface HfComTaskService extends IService<HfComTask> {

    /**
     * 保存企业任务单
     * @param hfComTask
     * @return
     */
    boolean insertComTask(HfComTask hfComTask);

    /**
     * 判断企业任务单是否存在
     *
     * @param hfComTask
     * @return
     */
    public int countComTaskByCond(HfComTask hfComTask);

}
