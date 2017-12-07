package com.ciicsh.gto.afsupportcenter.healthmedical.commandservice.host.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;
import com.ciicsh.gto.afsupportcenter.healthmedical.commandservice.api.dto.FragmentaryReimbursementDTO;
import com.ciicsh.gto.afsupportcenter.healthmedical.commandservice.api.FragmentaryReimbursementProxy;
import com.ciicsh.gto.afsupportcenter.healthmedical.commandservice.api.dto.JsonResult;
import com.ciicsh.gto.afsupportcenter.util.JSONConverter;
import com.ciicsh.gto.afsupportcenter.healthmedical.commandservice.business.FragmentaryReimbursementCommandService;
import com.ciicsh.gto.afsupportcenter.healthmedical.commandservice.entity.po.FragmentaryReimbursementPO;

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
@RequestMapping("/CommandService/FragmentaryReimbursement")
public class FragmentaryReimbursementController implements FragmentaryReimbursementProxy{

    @Autowired
    private FragmentaryReimbursementCommandService fragmentaryReimbursementCommandService;

    @Override
    @RequestMapping(value = "/saveFragmentaryReimbursement", method = { RequestMethod.POST})
    public JsonResult saveFragmentaryReimbursement(@RequestBody Map<String, Object> param) {
        JsonResult jr = new JsonResult();

        Object fragmentaryReimbursementObj = param.get("fragmentaryReimbursement");
     //   FragmentaryReimbursementPO fragmentaryReimbursementPO = JSONConverter


        return jr;
    }
}
