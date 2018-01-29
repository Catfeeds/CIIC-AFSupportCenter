package com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.business;

import com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.dto.EmployeeInfoDTO;
import com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.entity.SsEmpTaskFront;
import com.baomidou.mybatisplus.service.IService;
import com.ciicsh.gto.sheetservice.api.dto.TaskCreateMsgDTO;

/**
 * <p>
 * 雇员任务单前道传递信息,创建任务单的同时，就要把前道的传递信息复制到这表，当前表复制前道cmy_af_emp_socia 服务类
 * </p>
 */
public interface SsEmpTaskFrontService extends IService<SsEmpTaskFront> {

    /**
     * <p>Description: 保存数据到雇员任务单表</p>
     *
     * @return
     * @author zhangxj
     * @date 2017-12-28
     */
    boolean insertTaskTb(TaskCreateMsgDTO taskMsgDTO, Integer taskCategory, EmployeeInfoDTO dto);
}
