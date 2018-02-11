package com.ciicsh.gto.afsupportcenter.housefund.siteservice.business;

import com.baomidou.mybatisplus.service.IService;
import com.ciicsh.gto.afsupportcenter.housefund.siteservice.bo.HfEmpTaskBo;
import com.ciicsh.gto.afsupportcenter.housefund.siteservice.bo.HfEmpTaskHandleBo;
import com.ciicsh.gto.afsupportcenter.housefund.siteservice.dto.HfEmpTaskDTO;
import com.ciicsh.gto.afsupportcenter.housefund.siteservice.dto.HfEmpTaskHandleDTO;
import com.ciicsh.gto.afsupportcenter.housefund.siteservice.entity.HfEmpTask;
import com.ciicsh.gto.afsupportcenter.util.page.PageInfo;
import com.ciicsh.gto.afsupportcenter.util.page.PageRows;

import java.util.List;

public interface HfEmpTaskService extends IService<HfEmpTask> {

    /**
     * 分页查询雇员任务单信息
     * @param pageInfo
     * @return
     */
    PageRows<HfEmpTaskBo> queryHfEmpTaskInPage(PageInfo pageInfo);

    /**
     * 查询雇员任务单办理信息
     * @param hfEmpTaskHandleDTO
     * @return
     */
    List<HfEmpTaskHandleBo> getEmpTaskHandleData(HfEmpTaskHandleDTO hfEmpTaskHandleDTO);
}
