package com.ciicsh.gto.afsupportcenter.housefund.fundservice.business.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.ciicsh.gto.afsupportcenter.housefund.fundservice.business.HfComTaskService;
import com.ciicsh.gto.afsupportcenter.housefund.fundservice.dao.HfComTaskMapper;
import com.ciicsh.gto.afsupportcenter.housefund.fundservice.entity.HfComTask;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 企业任务单总表：有关于企业所有任务单的表单字段都集中记录当前表
 * Com：公司简写 服务实现类
 * </p>
 */
@Service
public class HfComTaskServiceImpl extends ServiceImpl<HfComTaskMapper, HfComTask> implements HfComTaskService {

    /**
     * 保存企业任务单
     *
     * @param hfComTask
     * @return
     */
    public boolean insertComTask(HfComTask hfComTask) {
        return baseMapper.insertComTask(hfComTask);
    }

    /**
     * 判断企业任务单是否存在
     *
     * @param hfComTask
     * @return
     */
    public int countComTaskByCond(HfComTask hfComTask) {
        return baseMapper.countComTaskByCond(hfComTask);
    }

}
