package com.ciicsh.gto.afsupportcenter.employmanagement.employservice.business;

import com.baomidou.mybatisplus.service.IService;
import com.ciicsh.gto.afsupportcenter.employmanagement.employservice.bo.AmArchiveAdvanceBO;
import com.ciicsh.gto.afsupportcenter.employmanagement.employservice.bo.AmEmploymentBO;
import com.ciicsh.gto.afsupportcenter.employmanagement.employservice.custom.advanceSearchExportOpt;
import com.ciicsh.gto.afsupportcenter.employmanagement.employservice.entity.AmArchiveAdvance;
import com.ciicsh.gto.afsupportcenter.util.page.PageInfo;
import com.ciicsh.gto.afsupportcenter.util.page.PageRows;

import java.util.List;

/**
 * <p>
 * 档案预增表 服务类
 * </p>
 */
public interface IAmArchiveAdvanceService extends IService<AmArchiveAdvance> {

    PageRows<AmArchiveAdvanceBO> queryAmArchiveAdvanceList(PageInfo pageInfo);

    boolean deleteAmArchiveAdvance(AmArchiveAdvanceBO amArchiveAdvanceBO);

    boolean saveAmArchiveAdvance(AmArchiveAdvanceBO amArchiveAdvanceBO);

    AmArchiveAdvanceBO queryAmArchiveAdvanceByNameIdcard(String name,String idCard);

    List<advanceSearchExportOpt> queryAdvanceSearchExportOpt(AmArchiveAdvanceBO amArchiveAdvanceBO);

    AmEmploymentBO queryAmArchiveByEmployeeNameIdCard(String employeeName,String idCard);
}
