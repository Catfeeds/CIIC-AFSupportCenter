package com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.api;

import com.ciicsh.gto.afcompanycenter.commandservice.api.dto.employee.AfEmpSocialUpdateDateDTO;
import com.ciicsh.gto.afcompanycenter.commandservice.api.proxy.AfEmployeeSocialProxy;
import com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.api.dto.TaskSheetRequestDTO;
import com.ciicsh.gto.basicdataservice.api.DicItemServiceProxy;
import com.ciicsh.gto.basicdataservice.api.dto.DicItemDTO;
import com.ciicsh.gto.commonservice.util.dto.Result;
import com.ciicsh.gto.sheetservice.api.SheetServiceProxy;
import com.ciicsh.gto.sheetservice.api.dto.request.TaskRequestDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Component
public class CommonApiUtils {
    @Autowired
    SheetServiceProxy sheetServiceProxy;

    @Autowired
    DicItemServiceProxy dicItemServiceProxy;

    @Autowired
    AfEmployeeSocialProxy afEmployeeSocialProxy;

//    @Autowired
//    EmployeeInfoProxy employeeInfoProxy;

    /**
     * 调用客服中心的完成任务接口
     *
     * @param taskSheetRequestDTO
     * @return
     */
    public Result completeTask(@RequestBody TaskSheetRequestDTO taskSheetRequestDTO) throws Exception {
        TaskRequestDTO taskRequestDTO = new TaskRequestDTO();
        taskRequestDTO.setTaskId(taskSheetRequestDTO.getTaskId());
        taskRequestDTO.setAssignee(taskSheetRequestDTO.getAssignee());
        taskRequestDTO.setVariables(taskSheetRequestDTO.getVariable());
        return sheetServiceProxy.completeTask(taskRequestDTO);
    }

    /**
     * 根据ID取得名称
     *
     * @param dicItemId
     * @return
     */
    public DicItemDTO selectByDicItemId(String dicItemId) throws Exception {
        return dicItemServiceProxy.selectByDicItemId(dicItemId);
    }

    /**
     * 根据ID取得名称
     *
     * @param listByDicId
     * @return
     */
    public List<DicItemDTO> listByDicId(String listByDicId) throws Exception {
        return dicItemServiceProxy.listByDicId(listByDicId);
    }

    /**
     * 刷新REDIS中的ID数据
     *
     * @param dicItemId
     * @return
     */
    public void fresh2Redis(String dicItemId) throws Exception {
        dicItemServiceProxy.fresh2Redis(dicItemId);
    }

    /**
     * 雇员任务单实缴金额回调接口（支持中心调用客服中心）
     *
     * @param var1
     * @return int
     */
    public int updateConfirmDate(@RequestBody List<AfEmpSocialUpdateDateDTO> var1) throws Exception {
        return afEmployeeSocialProxy.updateConfirmDate(var1);
    }

    /**
     * 获取雇员信息（支持中心调用雇员中心）
     *
     * @param var1 传入employeeId和业务类型
     * @return
     */
//    public com.ciicsh.gto.employeecenter.util.JsonResult<Page<EmployeeInfoDTO>> searchEmployeeInfo(
//            @RequestBody EmployeeSearchDTO var1) throws Exception {
//        return employeeInfoProxy.searchEmployeeInfo(var1);
//    }
}
