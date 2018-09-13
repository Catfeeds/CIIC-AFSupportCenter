package com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.business.impl;

import com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.bo.SsEmpPrintInfoBO;
import com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.business.SsEmpPrintInfoService;
import com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.dao.SsEmpArchiveMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 雇员日常操作-打印信息业务接口实现
 *
 * @author sunjian
 * @since 2018-9-7
 */
@Service
public class SsEmpPrintInfoServiceImpl implements SsEmpPrintInfoService {
    @Autowired
    private SsEmpArchiveMapper ssEmpArchiveMapper;


    @Override
    public List<Map> ssExpEmpRegisterFormPrint(SsEmpPrintInfoBO ssEmpPrintInfoBO) {
        return ssEmpArchiveMapper.ssExpEmpRegisterFormPrint(ssEmpPrintInfoBO);

    }

    @Override
    public List<Map> ssExpChangeItemDeclarationFormPrint(SsEmpPrintInfoBO ssEmpPrintInfoBO) {

        return ssEmpArchiveMapper.ssExpChangeItemDeclarationFormPrint(ssEmpPrintInfoBO);
    }
}
