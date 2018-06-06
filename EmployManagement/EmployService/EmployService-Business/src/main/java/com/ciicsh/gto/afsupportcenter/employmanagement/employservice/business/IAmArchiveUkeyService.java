package com.ciicsh.gto.afsupportcenter.employmanagement.employservice.business;

import com.baomidou.mybatisplus.service.IService;
import com.ciicsh.gto.afsupportcenter.employmanagement.employservice.bo.AmArchiveAdvanceBO;
import com.ciicsh.gto.afsupportcenter.employmanagement.employservice.bo.AmArchiveUkeyBO;
import com.ciicsh.gto.afsupportcenter.employmanagement.employservice.custom.advanceSearchExportOpt;
import com.ciicsh.gto.afsupportcenter.employmanagement.employservice.entity.AmArchiveAdvance;
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

    boolean deleteAmArchiveUkey(Long id);

    boolean saveAmArchiveUkey(AmArchiveUkeyBO amArchiveUkeyBO);

    boolean amArchiveUkeyRenew(Long keyId,String keyFee,String keyRenewFee);
}
