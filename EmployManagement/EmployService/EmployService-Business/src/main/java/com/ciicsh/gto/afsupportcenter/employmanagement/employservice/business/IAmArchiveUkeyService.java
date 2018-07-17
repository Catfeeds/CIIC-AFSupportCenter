package com.ciicsh.gto.afsupportcenter.employmanagement.employservice.business;

import com.baomidou.mybatisplus.service.IService;
import com.ciicsh.gto.afsupportcenter.employmanagement.employservice.bo.AmArchiveUkeyBO;
import com.ciicsh.gto.afsupportcenter.employmanagement.employservice.bo.AmArchiveUkeyRenewBO;
import com.ciicsh.gto.afsupportcenter.employmanagement.employservice.custom.ukeySearchExportOpt;
import com.ciicsh.gto.afsupportcenter.employmanagement.employservice.entity.AmArchiveUkey;
import com.ciicsh.gto.afsupportcenter.util.page.PageInfo;
import com.ciicsh.gto.afsupportcenter.util.page.PageRows;

import java.util.List;

/**
 * <p>
 * 档案Ukey表 服务类
 * </p>
 */
public interface IAmArchiveUkeyService extends IService<AmArchiveUkey> {

    PageRows<AmArchiveUkeyBO> queryAmArchiveUkeyList(PageInfo pageInfo);

    AmArchiveUkeyBO queryAmArchiveUkey(Long id);

    AmArchiveUkeyBO queryOrganizationCodeByCid(String companyId,String companyName);

    List<AmArchiveUkeyRenewBO> queryAmArchiveUkeyRenew(Long id);

    AmArchiveUkeyBO queryAmArchiveUkey(String organizationCode);

    boolean deleteAmArchiveUkey(Long id);

    boolean saveAmArchiveUkey(AmArchiveUkeyBO amArchiveUkeyBO);

    boolean amArchiveUkeyRenew(AmArchiveUkeyBO amArchiveUkeyBO);

    List<ukeySearchExportOpt> queryAdvanceSearchExportOpt(AmArchiveUkeyBO amArchiveUkeyBO);
}
