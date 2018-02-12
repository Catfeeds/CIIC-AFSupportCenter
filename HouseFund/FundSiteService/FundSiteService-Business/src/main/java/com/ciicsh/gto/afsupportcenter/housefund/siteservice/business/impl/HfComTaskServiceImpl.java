package com.ciicsh.gto.afsupportcenter.housefund.siteservice.business.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;

import com.ciicsh.gto.afsupportcenter.housefund.siteservice.bo.HfComTaskBo;
import com.ciicsh.gto.afsupportcenter.housefund.siteservice.business.HfComTaskService;
import com.ciicsh.gto.afsupportcenter.housefund.siteservice.dao.HfComTaskMapper;
import com.ciicsh.gto.afsupportcenter.housefund.siteservice.entity.HfComTask;
import com.ciicsh.gto.afsupportcenter.util.page.PageInfo;
import com.ciicsh.gto.afsupportcenter.util.page.PageKit;
import com.ciicsh.gto.afsupportcenter.util.page.PageRows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 企业任务单服务实现类：有关于企业任务单的相关操作
Com：公司简写 服务实现类
 * </p>
 *
 * @author 沈健
 * @since 2018-02-07
 */
@Service
public class HfComTaskServiceImpl extends ServiceImpl<HfComTaskMapper, HfComTask> implements HfComTaskService {

    @Autowired
    private HfComTaskMapper hfComTaskMapper;

    /**
     * 获得企业任务单 未处理
     * @param pageInfo
     * @return
     */
    @Override
    public PageRows<HfComTaskBo> queryNoProgressCompanyTask(PageInfo pageInfo) {
        //将PageInfo对象转DTO对象
        HfComTaskBo hfComTaskBo = pageInfo.toJavaObject(HfComTaskBo.class);
        return PageKit.doSelectPage(pageInfo, () -> hfComTaskMapper.queryNoProcessCompanyTask(hfComTaskBo));
    }

    /**
     * 获得企业任务单 处理中
     * @param pageInfo
     * @return
     */
    @Override
    public PageRows<HfComTaskBo> queryProgressingCompanyTask(PageInfo pageInfo) {
        return null;
    }

    /**
     * 获得企业任务单 已完成
     * @param pageInfo
     * @return
     */
    @Override
    public PageRows<HfComTaskBo> queryFinishedCompanyTask(PageInfo pageInfo) {
        return null;
    }

    /**
     * 获得企业任务单 批退
     * @param pageInfo
     * @return
     */
    @Override
    public PageRows<HfComTaskBo> queryRejectedCompanyTask(PageInfo pageInfo) {
        return null;
    }

}
