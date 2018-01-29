package com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.business.impl;

import com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.dto.SsAnnualAdjustCompanyEmpDTO;
import com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.dto.SsAnnualAdjustCompanyEmpTempDTO;
import com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.entity.SsAnnualAdjustCompanyEmp;
import com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.dao.SsAnnualAdjustCompanyEmpMapper;
import com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.business.SsAnnualAdjustCompanyEmpService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.ciicsh.gto.afsupportcenter.util.page.PageInfo;
import com.ciicsh.gto.afsupportcenter.util.page.PageKit;
import com.ciicsh.gto.afsupportcenter.util.page.PageRows;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  年调客户雇员管理服务实现类
 * </p>
 */
@Service
public class SsAnnualAdjustCompanyEmpServiceImpl extends ServiceImpl<SsAnnualAdjustCompanyEmpMapper, SsAnnualAdjustCompanyEmp> implements SsAnnualAdjustCompanyEmpService {

    /**
     * 分页查询年调客户雇员信息
     * @param pageInfo
     * @return
     */
    @Override
    public PageRows<SsAnnualAdjustCompanyEmp> queryAnnualAdjustCompanyEmpInPage(PageInfo pageInfo) {
        SsAnnualAdjustCompanyEmpDTO ssAnnualAdjustCompanyEmpDTO = pageInfo.toJavaObject(SsAnnualAdjustCompanyEmpDTO.class);
        return PageKit.doSelectPage(pageInfo, () -> baseMapper.queryAnnualAdjustCompanyEmp(ssAnnualAdjustCompanyEmpDTO));
    }

    @Override
    public List<SsAnnualAdjustCompanyEmp> queryAnnualAdjustCompanyEmp(SsAnnualAdjustCompanyEmpDTO ssAnnualAdjustCompanyEmpDTO) {
        return baseMapper.queryAnnualAdjustCompanyEmp(ssAnnualAdjustCompanyEmpDTO);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void insertDataWithoutErrorMsg(SsAnnualAdjustCompanyEmpTempDTO ssAnnualAdjustCompanyEmpTempDTO) {
        Map<String, Object> deleteMap = new HashMap<>();
        deleteMap.put("annual_adjust_company_id", ssAnnualAdjustCompanyEmpTempDTO.getAnnualAdjustCompanyId());
        // 先将之前的记录全部清除，再重新插入导入的记录
        baseMapper.deleteByMap(deleteMap);
        baseMapper.insertDataWithoutErrorMsg(ssAnnualAdjustCompanyEmpTempDTO);
    }
}
