package com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.business.impl;

import com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.bo.SsAnnualAdjustAccountEmpBO;
import com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.business.ISsAnnualAdjustAccountEmpService;
import com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.dto.SsAnnualAdjustAccountEmpDTO;
import com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.dto.SsAnnualAdjustAccountEmpTempDTO;
import com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.entity.SsAnnualAdjustAccountEmp;
import com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.dao.SsAnnualAdjustAccountEmpMapper;
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
 *  服务实现类
 * </p>
 */
@Service
public class SsAnnualAdjustAccountEmpServiceImpl extends ServiceImpl<SsAnnualAdjustAccountEmpMapper, SsAnnualAdjustAccountEmp> implements ISsAnnualAdjustAccountEmpService {

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void insertDataWithoutErrorMsg(SsAnnualAdjustAccountEmpTempDTO ssAnnualAdjustAccountEmpTempDTO) {
        Map<String, Object> deleteMap = new HashMap<>();
        deleteMap.put("annual_adjust_account_id", ssAnnualAdjustAccountEmpTempDTO.getAnnualAdjustAccountId());
        // 先将之前的记录全部清除，再重新插入导入的记录
        baseMapper.deleteByMap(deleteMap);
        baseMapper.insertDataWithoutErrorMsg(ssAnnualAdjustAccountEmpTempDTO);
    }

    @Override
    public List<SsAnnualAdjustAccountEmpBO> queryAnnualAdjustAccountEmp(SsAnnualAdjustAccountEmpDTO ssAnnualAdjustAccountEmpDTO) {
        return baseMapper.queryAnnualAdjustAccountEmp(ssAnnualAdjustAccountEmpDTO);
    }

    @Override
    public PageRows<SsAnnualAdjustAccountEmpBO> queryAnnualAdjustAccountEmpInPage(PageInfo pageInfo) {
        SsAnnualAdjustAccountEmpDTO ssAnnualAdjustAccountEmpDTO = pageInfo.toJavaObject(SsAnnualAdjustAccountEmpDTO.class);
        return PageKit.doSelectPage(pageInfo, () -> baseMapper.queryAnnualAdjustAccountEmp(ssAnnualAdjustAccountEmpDTO));
    }
}
