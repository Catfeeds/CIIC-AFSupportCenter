package com.ciicsh.gto.afsupportcenter.housefund.siteservice.business;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.service.IService;
import com.ciicsh.gto.afsupportcenter.housefund.siteservice.bo.HfEmpTaskHandleBo;
import com.ciicsh.gto.afsupportcenter.housefund.siteservice.bo.HfEmpTaskHandlePostBo;
import com.ciicsh.gto.afsupportcenter.housefund.siteservice.entity.HfEmpTask;
import com.ciicsh.gto.afsupportcenter.util.web.response.JsonResult;

import java.util.List;

public interface HfEmpTaskHandleService extends IService<HfEmpTask> {
    /**
     * 查询雇员任务单办理信息
     * @param hfEmpTaskHandleDTO
     * @return
     */
    List<HfEmpTaskHandleBo> getEmpTaskHandleData(HfEmpTaskHandlePostBo hfEmpTaskHandleDTO);

    JsonResult inputDataSave(JSONObject params, boolean isHandle);
}
