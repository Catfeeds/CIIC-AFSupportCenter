package com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.business.impl;


import com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.business.AmEmpTaskOfSsService;
import com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.dao.AmEmpTaskOfSsMapper;
import com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.dto.AmEmpTaskDTO;
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
    private AmEmpTaskOfSsMapper amEmpTaskOfSsMapper;

    @Override
    public AmEmpTaskDTO queryReworkInfo(String employeeId, String companyId, Integer amEmpTaskTaskCategory) {
        AmEmpTaskDTO amEmpTaskDTO = amEmpTaskOfSsMapper.queryReworkInfo(employeeId, companyId, amEmpTaskTaskCategory);
        return amEmpTaskDTO;
    }

    @Override
    public String queryEmployFeedback(String employeeId, String companyId) {
        return amEmpTaskOfSsMapper.queryEmployFeedback(employeeId, companyId);
    }

    @Override
    public String queryResignFeedback(String employeeId, String companyId) {
        return amEmpTaskOfSsMapper.queryResignFeedback(employeeId, companyId);
    }
}
