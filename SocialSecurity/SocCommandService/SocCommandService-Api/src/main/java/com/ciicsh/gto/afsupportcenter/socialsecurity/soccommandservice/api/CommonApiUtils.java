package com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.api;

import com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.api.dto.TaskSheetRequestDTO;
import com.ciicsh.gto.commonservice.util.dto.Result;
import com.ciicsh.gto.sheetservice.api.SheetServiceProxy;
import com.ciicsh.gto.sheetservice.api.dto.request.TaskRequestDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;

@Component
public class CommonApiUtils {
    @Autowired
    SheetServiceProxy sheetServiceProxy;

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

}
