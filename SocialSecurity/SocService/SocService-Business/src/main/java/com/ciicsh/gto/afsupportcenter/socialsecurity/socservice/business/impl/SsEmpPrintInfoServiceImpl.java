package com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.business.impl;

import com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.bo.SsEmpPrintInfoBO;
import com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.business.SsEmpPrintInfoService;
import com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.business.utils.CommonApiUtils;
import com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.dao.SsEmpArchiveMapper;
import com.ciicsh.gto.afsupportcenter.util.StringUtil;
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
    @Autowired
    private CommonApiUtils commonApiUtils;

    @Override
    public List<Map> ssExpEmpRegisterFormPrint(SsEmpPrintInfoBO ssEmpPrintInfoBO) {
        List<Map> empList =ssEmpArchiveMapper.ssExpEmpRegisterFormPrint(ssEmpPrintInfoBO);
        empList.forEach(map -> {
            if(!StringUtil.isEmpty(map.get("province_code"))){
                map.put("householdProvince",commonApiUtils.getProvinceName(map.get("province_code").toString()));
            }
            if(!StringUtil.isEmpty(map.get("city_code"))){
                map.put("householdCity",commonApiUtils.getCityName(map.get("city_code").toString()));
            }
        });
        return empList;

    }

    @Override
    public List<Map> ssExpChangeItemDeclarationFormPrint(SsEmpPrintInfoBO ssEmpPrintInfoBO) {

        return ssEmpArchiveMapper.ssExpChangeItemDeclarationFormPrint(ssEmpPrintInfoBO);
    }
}
