package com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.business.impl;


import com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.business.AmEmpTaskOfSsService;
import com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.business.SsEmpTaskService;
import com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.dao.AmEmpTaskOfSsMapper;
import com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.dto.AmEmpTaskDTO;
import com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.entity.SsEmpTask;
import com.ciicsh.gto.afsupportcenter.util.exception.BusinessException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用工退工任务单 服务实现类
 * </p>
 *
 * @author xsj
 * @since 2018-02-25
 */
@Service
public class AmEmpTaskOfSsServiceImpl implements AmEmpTaskOfSsService {
    @Autowired
    private SsEmpTaskService ssEmpTaskService;
    @Autowired
    private AmEmpTaskOfSsMapper amEmpTaskOfSsMapper;
    /**
     * 雇员新增查询用退工信息
     * @param empTaskId
     * @return
     */
    @Override
    public AmEmpTaskDTO queryReworkInfo(Long empTaskId) {
        SsEmpTask ssEmpTask = ssEmpTaskService.selectById(empTaskId);
        if(StringUtils.isBlank(ssEmpTask.getEmployeeId()) || StringUtils.isBlank(ssEmpTask.getCompanyId()))
            throw new BusinessException("任务单信息不完整");
         AmEmpTaskDTO amEmpTaskDTO = amEmpTaskOfSsMapper.queryReworkInfo(ssEmpTask.getEmployeeId(),ssEmpTask.getCompanyId());
         return amEmpTaskDTO;
    }
}
