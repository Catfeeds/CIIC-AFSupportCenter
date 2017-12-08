package com.ciicsh.gto.afsupportcenter.healthmedical.queryservice.host.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;

import com.ciicsh.gto.afsupportcenter.healthmedical.queryservice.business.FragmentaryReimbursementQueryService;
import com.ciicsh.gto.afsupportcenter.healthmedical.queryservice.entity.po.FragmentaryReimbursementPO;

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
@RequestMapping("/QueryService/FragmentaryReimbursement")
//public class FragmentaryReimbursementController implements FragmentaryReimbursementProxy{
   public class FragmentaryReimbursementController {
    @Autowired
    private FragmentaryReimbursementQueryService fragmentaryReimbursementQueryService;


    @RequestMapping(value = "/saveFragmentaryReimbursement", method = { RequestMethod.POST})
 //   public void saveFragmentaryReimbursement(@RequestBody Map<String, Object> param)
    public void saveFragmentaryReimbursement()
    {


    }

    @RequestMapping(value = "/editFragmentaryReimbursement", method = { RequestMethod.POST})
    public void editFragmentaryReimbursement()
    {

    }
}
