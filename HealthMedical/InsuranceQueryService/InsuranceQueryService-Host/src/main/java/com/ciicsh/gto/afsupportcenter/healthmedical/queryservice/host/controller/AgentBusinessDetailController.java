package com.ciicsh.gto.afsupportcenter.healthmedical.queryservice.host.controller;



import com.baomidou.mybatisplus.plugins.Page;
import com.ciicsh.gto.afsupportcenter.healthmedical.queryservice.api.AgentBusinessQueryProxy;
import com.ciicsh.gto.afsupportcenter.util.core.PageParam;
import com.ciicsh.gto.afsupportcenter.util.core.Result;
import com.ciicsh.gto.afsupportcenter.util.core.ResultGenerator;
import com.ciicsh.gto.afsupportcenter.healthmedical.queryservice.api.dto.AgentBusinessDetailDTO;
import com.ciicsh.gto.afsupportcenter.healthmedical.queryservice.business.AgentBusinessDetailQueryService;
import com.ciicsh.gto.afsupportcenter.healthmedical.queryservice.entity.bo.AgentBusinessDetailBO;
import com.ciicsh.gto.afsupportcenter.healthmedical.queryservice.entity.po.AgentBusinessDetailPO;
import com.ciicsh.gto.afsupportcenter.util.ConvertUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 代收代付发放明细表 前端控制器
 * </p>
 *
 * @author zhaogang
 * @since 2017-12-13
 */
@RestController
@RequestMapping("/queryservice/agentBusinessDetail")
public class AgentBusinessDetailController {
    @Autowired
    private AgentBusinessDetailQueryService agentBusinessDetailQueryService;

    @RequestMapping(value = "/getAgentBusinessDetailListById", method = {RequestMethod.GET, RequestMethod.POST})
    public Result getAgentBusinessDetailListById(String agentbusinessipid,String month) {
        try {
            List<AgentBusinessDetailPO> bolist = agentBusinessDetailQueryService.getAgentBusinessDetailListByIpId(agentbusinessipid,month);
            List<AgentBusinessDetailDTO> agentBusinessIpDTOList = ConvertUtil.listConvert(bolist, AgentBusinessDetailDTO.class);
            return ResultGenerator.genSuccessResult(agentBusinessIpDTOList);
        } catch (Exception e) {
            return ResultGenerator.genServerFailResult();
        }
    }
}
