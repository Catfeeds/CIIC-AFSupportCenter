package com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.business;

import com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.bo.SsEmpPrintInfoBO;

import java.util.List;
import java.util.Map;

/**
 * 雇员日常操作-打印信息业务接口
 *
 * @author sunjian
 * @since 2018-9-7
 */
public interface SsEmpPrintInfoService {
    List<Map> ssExpEmpRegisterFormPrint(SsEmpPrintInfoBO ssEmpPrintInfoBO);
    List<Map> ssExpChangeItemDeclarationFormPrint(SsEmpPrintInfoBO ssEmpPrintInfoBO);

}
