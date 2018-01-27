package com.ciicsh.gto.afsupportcenter.housefund.fundcommandservice.business.impl;

import com.ciicsh.gto.afsupportcenter.housefund.fundcommandservice.entity.HfComTask;
import com.ciicsh.gto.afsupportcenter.housefund.fundcommandservice.dao.HfComTaskMapper;
import com.ciicsh.gto.afsupportcenter.housefund.fundcommandservice.business.IHfComTaskService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 企业任务单总表：有关于企业所有任务单的表单字段都集中记录当前表
Com：公司简写 服务实现类
 * </p>
 */
@Service
public class HfComTaskServiceImpl extends ServiceImpl<HfComTaskMapper, HfComTask> implements IHfComTaskService {

}
