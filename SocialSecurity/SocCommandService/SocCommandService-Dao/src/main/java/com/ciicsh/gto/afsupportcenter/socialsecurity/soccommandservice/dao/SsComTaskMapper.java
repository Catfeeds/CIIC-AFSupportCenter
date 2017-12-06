package com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.dto.SsComTaskDTO;
import com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.entity.SsComTask;

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
    public List<SsComTaskDTO> queryCompanyTask(SsComTaskDTO ssComTaskDTO);
}
