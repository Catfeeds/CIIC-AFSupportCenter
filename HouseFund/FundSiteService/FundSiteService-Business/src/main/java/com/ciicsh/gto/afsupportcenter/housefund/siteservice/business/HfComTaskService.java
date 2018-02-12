package com.ciicsh.gto.afsupportcenter.housefund.siteservice.business;

import com.baomidou.mybatisplus.service.IService;
import com.ciicsh.gto.afsupportcenter.housefund.siteservice.bo.HfComTaskBo;
import com.ciicsh.gto.afsupportcenter.housefund.siteservice.entity.HfComTask;
import com.ciicsh.gto.afsupportcenter.util.page.PageInfo;
import com.ciicsh.gto.afsupportcenter.util.page.PageRows;


/**
 * <p>
 * 企业任务单服务类：存放所有公积金企业任务单相关的服务
Com：公司简写 服务类
 * </p>
 *
 * @author 沈健
 * @since 2018-02-07
 */
public interface HfComTaskService extends IService<HfComTask> {
    /**
     * 获得企业任务单 未处理
     * @param pageInfo
     * @return
     */
    public PageRows<HfComTaskBo> queryNoProgressCompanyTask(PageInfo pageInfo);

    /**
     * 获得企业任务单 处理中
     * @param pageInfo
     * @return
     */
    public PageRows<HfComTaskBo> queryProgressingCompanyTask(PageInfo pageInfo);

    /**
     * 获得企业任务单 已完成
     * @param pageInfo
     * @return
     */
    public PageRows<HfComTaskBo> queryFinishedCompanyTask(PageInfo pageInfo);

    /**
     * 获得企业任务单 批退
     * @param pageInfo
     * @return
     */
    public PageRows<HfComTaskBo> queryRejectedCompanyTask(PageInfo pageInfo);
}
