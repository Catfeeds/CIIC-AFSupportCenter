package com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.business;

import com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.dto.SsMonthEmpChangeDTO;
import com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.entity.SsMonthEmpChange;
import com.baomidou.mybatisplus.service.IService;

/**
 * <p>
 * 雇员月度变更主表 服务类
 * </p>
 *
 * @author HuangXing
 * @since 2017-12-07
 */
public interface ISsMonthEmpChangeService extends IService<SsMonthEmpChange> {

    /**
     * <p>Description: 根据对账单ID查询社保汇总数据主表</p>
     *
     * @author wengxk
     * @date 2017-12-13
     * @param ssMonthEmpChangeDTO 检索条件
     * @return  SsMonthEmpChangeDTO
     */
    SsMonthEmpChangeDTO serachMonthEmpChangeByStatementId(SsMonthEmpChangeDTO ssMonthEmpChangeDTO);
}
