package com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.business.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.business.ISsComTaskService;
import com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.dao.SsComTaskMapper;
import com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.dta.SsComTaskDTO;
import com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.entity.SsComTask;
import com.ciicsh.gto.afsupportcenter.util.page.PageInfo;
import com.ciicsh.gto.afsupportcenter.util.page.PageKit;
import com.ciicsh.gto.afsupportcenter.util.page.PageRows;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 独立库客户任务单 服务实现类
 * </p>
 *
 * @author HuangXing
 * @since 2017-12-01
 */
@Service
public class SsComTaskServiceImpl extends ServiceImpl<SsComTaskMapper, SsComTask> implements ISsComTaskService {

    /**
     * 获得企业任务单
     * xsj
     * @return
     */
    @Override
    public PageRows<SsComTaskDTO> queryCompanyTask(PageInfo pageInfo){
        return PageKit.doSelectPage(pageInfo,()-> baseMapper.queryCompanyTask(pageInfo.toJavaObject(SsComTaskDTO.class)));
    }
}
