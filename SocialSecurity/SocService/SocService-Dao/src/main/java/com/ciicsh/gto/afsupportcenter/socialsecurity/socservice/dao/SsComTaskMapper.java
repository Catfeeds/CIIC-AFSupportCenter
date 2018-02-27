package com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.bo.SsComTaskBO;
import com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.entity.SsComTask;

import java.util.List;

/**
 * <p>
 * 独立库客户任务单 Mapper 接口
 * </p>
 *
 * @author HuangXing
 * @since 2017-12-01
 */
public interface SsComTaskMapper extends BaseMapper<SsComTask> {
    List<SsComTaskBO> queryNoProgressCompanyTask(SsComTaskBO ssComTaskBO);
    List<SsComTaskBO> queryProgressingCompanyTask(SsComTaskBO ssComTaskBO);
    List<SsComTaskBO> queryFinshedCompanyTask(SsComTaskBO ssComTaskBO);
    List<SsComTaskBO> queryRefusedCompanyTask(SsComTaskBO ssComTaskBO);
    boolean updatePatchRefuseTask(List<SsComTask> ssComTaskList);
    SsComTaskBO queryComInfoAndMaterial(SsComTaskBO ssComTaskBO);
    SsComTaskBO queryComInfoAndPayWay(SsComTaskBO ssComTaskBO);
    SsComTaskBO queryAccountInfoAndMaterial(SsComTaskBO ssComTaskBO);
    int updateTaskStatusForRevoke(SsComTask ssComTask);

    //判断企业任务单是否存在
    int countComTaskByCond(SsComTaskBO ssComTask);

    boolean insertComTask(SsComTask ssComTask);
}
