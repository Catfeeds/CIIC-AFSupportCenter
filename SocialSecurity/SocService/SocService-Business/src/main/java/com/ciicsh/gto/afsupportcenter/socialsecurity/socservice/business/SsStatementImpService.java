package com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.business;

import com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.entity.custom.GsymxOpt;
import com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.entity.custom.YysmxOpt;

import java.util.List;

/**
 * <p>
 * 对账导入雇员明细 服务类
 * </p>
 *
 * @author HuangXing
 * @since 2017-12-01
 */
public interface SsStatementImpService {

    /**
     * 养老、医疗和失业对账导入
     */
    Boolean yysImport(List<YysmxOpt> opts,String ssMonth,String fileType,Long comAccountId,String fileName);

    /**
     * 生育和工伤对账导入
     */
    Boolean gsyImport(List<GsymxOpt> opts, String ssMonth, String fileType, Long comAccountId,String fileName);

}
