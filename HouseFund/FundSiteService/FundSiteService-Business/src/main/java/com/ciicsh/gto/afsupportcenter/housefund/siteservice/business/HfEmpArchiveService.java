package com.ciicsh.gto.afsupportcenter.housefund.siteservice.business;

import com.baomidou.mybatisplus.service.IService;
import com.ciicsh.gto.afsupportcenter.housefund.siteservice.bo.HfEmpArchiveBo;
import com.ciicsh.gto.afsupportcenter.housefund.siteservice.dto.EmpAccountImpXsl;
import com.ciicsh.gto.afsupportcenter.housefund.siteservice.entity.HfEmpArchive;
import com.ciicsh.gto.afsupportcenter.util.page.PageInfo;
import com.ciicsh.gto.afsupportcenter.util.page.PageRows;
import com.ciicsh.gto.afsupportcenter.util.web.response.JsonResult;

import java.util.List;
import java.util.Map;

public interface HfEmpArchiveService extends IService<HfEmpArchive> {
    PageRows<HfEmpArchiveBo> queryEmpArchive(PageInfo pageInfo);
    Map<String, Object> viewEmpArchiveInfo(String empArchiveId , String companyId);
    boolean saveComAccount(Map<String,String> updateDto);
    String xlsImportEmpAccount(List<EmpAccountImpXsl> opts,  String fileName);
}
