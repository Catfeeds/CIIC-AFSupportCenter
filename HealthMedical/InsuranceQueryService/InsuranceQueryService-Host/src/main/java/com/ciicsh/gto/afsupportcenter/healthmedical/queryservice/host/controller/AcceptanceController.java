package com.ciicsh.gto.afsupportcenter.healthmedical.queryservice.host.controller;

import com.ciicsh.gto.afsupportcenter.healthmedical.queryservice.business.AcceptanceQueryService;
import com.ciicsh.gto.afsupportcenter.healthmedical.queryservice.entity.po.AcceptancePO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.remoting.RemoteInvocationFailureException;
import org.springframework.web.bind.annotation.*;

import javax.validation.OverridesAttribute;
import java.util.List;
import java.util.Map;
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
   private AcceptanceQueryService acceptanceQueryService;


   @RequestMapping(value = "/getID", method = {RequestMethod.GET, RequestMethod.POST})
   public int getID()
   {
       int  count = acceptanceQueryService.countAll();
       return count;
   }

}
