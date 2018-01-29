package com.ciicsh.gto.afsupportcenter.housefund.siteservice.business;

import com.baomidou.mybatisplus.service.IService;
import com.ciicsh.gto.afsupportcenter.housefund.siteservice.bo.HfEmpArchiveBo;
import com.ciicsh.gto.afsupportcenter.housefund.siteservice.entity.HfEmpArchive;
import com.ciicsh.gto.afsupportcenter.util.page.PageInfo;
import com.ciicsh.gto.afsupportcenter.util.page.PageRows;

import java.util.Map;

public interface HfEmpArchiveService  extends IService<HfEmpArchive> {
    PageRows<HfEmpArchiveBo> queryEmpArchive(PageInfo pageInfo);
    Map<String, Object> viewEmpArchiveInfo(String empArchiveId, String companyId);
}
