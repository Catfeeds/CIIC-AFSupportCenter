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
     *
     * @param employeeId
     * @param companyId
     * @param amEmpTaskTaskCategory
     * @return
     */
    public AmEmpTaskDTO queryReworkInfo(String employeeId, String companyId, Integer amEmpTaskTaskCategory);

    String queryEmployFeedback(String employeeId, String companyId);

    String queryResignFeedback(String employeeId, String companyId);
}
