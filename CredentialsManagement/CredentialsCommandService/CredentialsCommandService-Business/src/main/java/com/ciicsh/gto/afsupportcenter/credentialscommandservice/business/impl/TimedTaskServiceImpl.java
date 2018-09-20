package com.ciicsh.gto.afsupportcenter.credentialscommandservice.business.impl;


import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.ciicsh.gto.afsupportcenter.credentialscommandservice.business.TimedTaskService;
import com.ciicsh.gto.afsupportcenter.credentialscommandservice.dao.TimedTaskMapper;
import com.ciicsh.gto.afsupportcenter.credentialscommandservice.entity.po.TimedTask;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 定时任务表 服务实现类
 * </p>
 *
 * @author guwei
 * @since 2018-09-18
 */
@Service
public class TimedTaskServiceImpl extends ServiceImpl<TimedTaskMapper, TimedTask> implements TimedTaskService {
	
}
