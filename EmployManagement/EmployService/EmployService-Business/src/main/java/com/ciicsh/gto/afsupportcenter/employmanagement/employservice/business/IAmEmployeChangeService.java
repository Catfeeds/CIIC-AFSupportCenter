package com.ciicsh.gto.afsupportcenter.employmanagement.employservice.business;

import com.ciicsh.gto.afsupportcenter.employmanagement.employservice.entity.AmEmployeChange;
import com.baomidou.mybatisplus.service.IService;
import com.ciicsh.gto.sheetservice.api.dto.TaskCreateMsgDTO;

/**
 * <p>
 * 雇员信息变更表 服务类
 * </p>
 */
public interface IAmEmployeChangeService extends IService<AmEmployeChange> {

    AmEmployeChange getEmployeeChange(Long empTaskId);

    boolean taskHireUpdate(TaskCreateMsgDTO taskMsgDTO) throws Exception;

    boolean taskFireUpdate(TaskCreateMsgDTO taskMsgDTO) throws Exception;

}
