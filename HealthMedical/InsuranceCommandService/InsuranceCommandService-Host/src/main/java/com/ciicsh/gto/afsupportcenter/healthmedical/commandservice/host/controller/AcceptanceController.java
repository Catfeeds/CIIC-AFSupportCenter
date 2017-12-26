package com.ciicsh.gto.afsupportcenter.healthmedical.commandservice.host.controller;

import com.ciicsh.gto.afsupportcenter.healthmedical.commandservice.business.AcceptanceCommandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
/**
 * <p>
 * 补充医疗理赔表 前端控制器
 * </p>
 *
 * @author zhaogang
 * @since 2017-12-02
 */
@RestController
@RequestMapping("/CommandService/Acceptance")
public class AcceptanceController {

   @Autowired
   private AcceptanceCommandService acceptanceCommandService;


   @RequestMapping(value = "/getID", method = {RequestMethod.GET, RequestMethod.POST})
   public int getID()
   {
       int  count = acceptanceCommandService.countAll();
       return count;
   }

}
