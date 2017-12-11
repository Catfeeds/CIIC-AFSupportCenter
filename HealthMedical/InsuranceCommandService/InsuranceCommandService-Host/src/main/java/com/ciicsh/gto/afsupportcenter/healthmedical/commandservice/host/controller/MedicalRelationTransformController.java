package com.ciicsh.gto.afsupportcenter.healthmedical.commandservice.host.controller;



import com.ciicsh.gto.afsupportcenter.healthmedical.commandservice.business.MedicalRelationTransformCommandService;
import com.ciicsh.gto.afsupportcenter.healthmedical.commandservice.entity.po.MedicalRelationTransformPO;
import com.ciicsh.gto.afsupportcenter.util.aspect.log.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * <p>
 * 医疗关系转移表 前端控制器
 * </p>
 *
 * @author zhaogang
 * @since 2017-12-02
 */
@Controller
@RequestMapping("/api/afsupportcenter/healthmedical/commandservice//MedicalRelationTransform")
public class MedicalRelationTransformController {
    @Autowired
    private MedicalRelationTransformCommandService medicalRelationTransformCommandService;

    @Log("新增")
    @RequestMapping(value = "/save", method = { RequestMethod.POST})
    public void save(MedicalRelationTransformPO po)
    {
        medicalRelationTransformCommandService.save(po);
    }

    @Log("更新")
    @RequestMapping(value = "/edit", method = { RequestMethod.POST})
    public void edit(MedicalRelationTransformPO po)
    {
        medicalRelationTransformCommandService.edit(po);
    }
}
