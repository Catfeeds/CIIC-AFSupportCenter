package com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.business;


import com.baomidou.mybatisplus.service.IService;
import com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.dto.AmEmpTaskDTO;
import com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.entity.AmEmpTask;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用工退工任务单 服务类
 * </p>
 *
 * @author xsj
 * @since 2018-02-25
 */

public interface AmEmpTaskOfSsService {
    /**
     * 雇员新增查询用退工信息
     * @param empTaskId
     * @return
     */
    public AmEmpTaskDTO queryReworkInfo(Long empTaskId);

}
