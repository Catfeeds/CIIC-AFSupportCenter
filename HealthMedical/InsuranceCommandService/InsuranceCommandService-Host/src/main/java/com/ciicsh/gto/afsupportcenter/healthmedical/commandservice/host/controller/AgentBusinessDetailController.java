package com.ciicsh.gto.afsupportcenter.healthmedical.commandservice.host.controller;



import com.baomidou.mybatisplus.plugins.Page;
import com.ciicsh.gto.afsupportcenter.util.core.PageParam;
import com.ciicsh.gto.afsupportcenter.util.core.Result;
import com.ciicsh.gto.afsupportcenter.util.core.ResultGenerator;
import com.ciicsh.gto.afsupportcenter.healthmedical.commandservice.business.AgentBusinessDetailCommandService;
import com.ciicsh.gto.afsupportcenter.healthmedical.commandservice.entity.bo.AgentBusinessDetailBO;
import com.ciicsh.gto.afsupportcenter.healthmedical.commandservice.entity.po.AgentBusinessDetailPO;
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
@RequestMapping("/commandservice/agentBusinessDetail")
public class AgentBusinessDetailController {

}