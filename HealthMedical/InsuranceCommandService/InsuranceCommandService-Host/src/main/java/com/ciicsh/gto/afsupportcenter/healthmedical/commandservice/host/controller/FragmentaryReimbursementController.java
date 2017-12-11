package com.ciicsh.gto.afsupportcenter.healthmedical.commandservice.host.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;
import com.ciicsh.gto.afsupportcenter.healthmedical.commandservice.api.dto.FragmentaryReimbursementDTO;
import com.ciicsh.gto.afsupportcenter.healthmedical.commandservice.api.FragmentaryReimbursementProxy;
import com.ciicsh.gto.afsupportcenter.healthmedical.commandservice.api.dto.JsonResult;
import com.ciicsh.gto.afsupportcenter.healthmedical.commandservice.business.FragmentaryReimbursementCommandService;
import com.ciicsh.gto.afsupportcenter.healthmedical.commandservice.entity.po.FragmentaryReimbursementPO;
import com.ciicsh.gto.afsupportcenter.util.aspect.log.Log;
import java.util.Map;

/**
 * <p>
 * 零星报销表 前端控制器
 * </p>
 *
 * @author zhaogang
 * @since 2017-12-02
 */
@RestController
@RequestMapping("/api/afsupportcenter/healthmedical/commandservice/FragmentaryReimbursement")
   public class FragmentaryReimbursementController {
    @Autowired
    private FragmentaryReimbursementCommandService fragmentaryReimbursementCommandService;

    @Log("新增")
    @RequestMapping(value = "/save", method = { RequestMethod.POST})
    public void save(FragmentaryReimbursementPO po)
    {
        fragmentaryReimbursementCommandService.save(po);

    }

    @Log("更新")
    @RequestMapping(value = "/edit", method = { RequestMethod.POST})
    public void edit(FragmentaryReimbursementPO po)
    {
        fragmentaryReimbursementCommandService.edit(po);
    }
}
