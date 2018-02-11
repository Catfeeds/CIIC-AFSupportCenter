package com.ciicsh.gto.afsupportcenter.housefund.siteservice.business.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.ciicsh.gto.afsupportcenter.housefund.siteservice.bo.HfEmpTaskBo;
import com.ciicsh.gto.afsupportcenter.housefund.siteservice.bo.HfEmpTaskHandleBo;
import com.ciicsh.gto.afsupportcenter.housefund.siteservice.business.HfEmpTaskService;
import com.ciicsh.gto.afsupportcenter.housefund.siteservice.dao.HfEmpTaskMapper;
import com.ciicsh.gto.afsupportcenter.housefund.siteservice.dto.HfEmpTaskDTO;
import com.ciicsh.gto.afsupportcenter.housefund.siteservice.dto.HfEmpTaskHandleDTO;
import com.ciicsh.gto.afsupportcenter.housefund.siteservice.entity.HfEmpTask;
import com.ciicsh.gto.afsupportcenter.util.page.PageInfo;
import com.ciicsh.gto.afsupportcenter.util.page.PageKit;
import com.ciicsh.gto.afsupportcenter.util.page.PageRows;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HfEmpTaskServiceImpl extends ServiceImpl<HfEmpTaskMapper, HfEmpTask> implements HfEmpTaskService {

    @Override
    public PageRows<HfEmpTaskBo> queryHfEmpTaskInPage(PageInfo pageInfo) {
        HfEmpTaskDTO hfEmpTaskDTO = pageInfo.toJavaObject(HfEmpTaskDTO.class);
        return PageKit.doSelectPage(pageInfo, () -> baseMapper.queryHfEmpTask(hfEmpTaskDTO));
    }

    @Override
    public List<HfEmpTaskHandleBo> getEmpTaskHandleData(HfEmpTaskHandleDTO hfEmpTaskHandleDTO) {
        return baseMapper.getEmpTaskHandleData(hfEmpTaskHandleDTO);
    }
}
