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
    public List<SsComTaskBO> queryNoProgressCompanyTask(SsComTaskBO ssComTaskBO);
    public List<SsComTaskBO> queryProgressingCompanyTask(SsComTaskBO ssComTaskBO);
    public List<SsComTaskBO> queryFinshedCompanyTask(SsComTaskBO ssComTaskBO);
    public List<SsComTaskBO> queryRefusedCompanyTask(SsComTaskBO ssComTaskBO);
    public boolean updatePatchRefuseTask(List<SsComTask> ssComTaskList);
    public SsComTaskBO queryComInfoAndMaterial(SsComTaskBO ssComTaskBO);
    public SsComTaskBO queryComInfoAndPayWay(SsComTaskBO ssComTaskBO);
    public SsComTaskBO queryAccountInfoAndMaterial(SsComTaskBO ssComTaskBO);
    public int updateTaskStatusForRevoke(SsComTask ssComTask);

    //判断企业任务单是否存在
    public int countComTaskByCond(SsComTaskBO ssComTask);

    boolean insertComTask(SsComTask ssComTask);
}
