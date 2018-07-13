package com.ciicsh.gto.afsupportcenter.housefund.fundservice.business;

import com.baomidou.mybatisplus.service.IService;
import com.ciicsh.gto.afsupportcenter.housefund.fundservice.bo.HfEmpArchiveBo;
import com.ciicsh.gto.afsupportcenter.housefund.fundservice.bo.HfEmpInfoBO;
import com.ciicsh.gto.afsupportcenter.housefund.fundservice.bo.HfEmpInfoParamBO;
import com.ciicsh.gto.afsupportcenter.housefund.fundservice.dto.EmpAccountImpXsl;
import com.ciicsh.gto.afsupportcenter.housefund.fundservice.entity.HfEmpArchive;
import com.ciicsh.gto.afsupportcenter.util.page.PageInfo;
import com.ciicsh.gto.afsupportcenter.util.page.PageRows;
import com.ciicsh.gto.afsupportcenter.util.web.response.JsonResult;

import java.util.List;
import java.util.Map;

public interface HfEmpArchiveService extends IService<HfEmpArchive> {
    PageRows<HfEmpArchiveBo> queryEmpArchive(PageInfo pageInfo);
    Map<String, Object> viewEmpArchiveInfo(String empArchiveId , String companyId ,String employeeId);
    JsonResult saveComAccount(Map<String,String> updateDto);
    JsonResult xlsImportEmpAccount(List<EmpAccountImpXsl> opts, String fileName);
    int deleteHfEmpArchiveByEmpTaskIds(List<Long> empTaskIdList);
    Map queryHfEmpArchiveByEmpTaskIds(List<Long> empTaskIdList);
    String getEmpAccountByEmployeeId(String employeeId, Integer hfType);
    List<HfEmpInfoBO> getHfEmpArchiveInfo(List<HfEmpInfoParamBO> paramBoList);
    HfEmpInfoBO getHfEmpInfoById(String companyId ,String employeeId);

    boolean isEmpAccountNotExists(String empAccount, int hfType, String employeeId, boolean isIncludeClosed);
}
