package com.ciicsh.gto.afsupportcenter.employmanagement.employcommandservice.business.impl;

import com.ciicsh.gto.afsupportcenter.employmanagement.employcommandservice.api.dto.CompanyDTO;
import com.ciicsh.gto.afsupportcenter.employmanagement.employcommandservice.api.dto.CompanyParamDTO;
import com.ciicsh.gto.afsupportcenter.employmanagement.employcommandservice.bo.AmCompanySetBO;
import com.ciicsh.gto.afsupportcenter.employmanagement.employcommandservice.business.utils.ReasonUtil;
import com.ciicsh.gto.afsupportcenter.employmanagement.employcommandservice.entity.AmCompanySet;
import com.ciicsh.gto.afsupportcenter.employmanagement.employcommandservice.dao.AmCompanySetMapper;
import com.ciicsh.gto.afsupportcenter.employmanagement.employcommandservice.business.IAmCompanySetService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.ciicsh.gto.afsupportcenter.util.StringUtil;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>
 * 公司特殊情况设置表 服务实现类
 * </p>
 *
 * @author xsj
 * @since 2018-03-21
 */

@Service
public class AmCompanySetServiceImpl extends ServiceImpl<AmCompanySetMapper, AmCompanySet> implements IAmCompanySetService {

    @Override
    public AmCompanySetBO queryAmCompanySet(AmCompanySetBO amCompanySetBO)
    {
        List<AmCompanySetBO> list = baseMapper.queryAmCompanySet(amCompanySetBO);
        if(null!=list&&list.size()>0){
            return  list.get(0);
        }
        return null;
    }

    @Override
    public CompanyDTO queryCompanyDTO(CompanyParamDTO companyParamDTO) {
        List<CompanyDTO> list = baseMapper.queryCompanyDTO(companyParamDTO);
        if(null!=list&&list.size()>0){
            return  list.get(0);
        }
        return  null;
    }

    @Override
    public boolean saveCompanyDTO(CompanyDTO companyDTO) {
        AmCompanySetBO amCompanySetBO = new AmCompanySetBO();
        amCompanySetBO.setCompanyId(companyDTO.getCompanyId());
        List<AmCompanySetBO> list = baseMapper.queryAmCompanySet(amCompanySetBO);
        AmCompanySet amCompanySet = new AmCompanySet();
        if(null!=list&&list.size()>0){
            amCompanySetBO = list.get(0);
            org.springframework.beans.BeanUtils.copyProperties(amCompanySetBO,amCompanySet);
        }

        amCompanySet.setCompanyId(companyDTO.getCompanyId());
        amCompanySet.setPhone(companyDTO.getPhone());
        amCompanySet.setMailContinue(companyDTO.getMailContinue());
        amCompanySet.setMailAdress(companyDTO.getMailAdress());
        amCompanySet.setPostCode(companyDTO.getPostCode());
        amCompanySet.setRecipient(companyDTO.getRecipient());

        LocalDateTime now = LocalDateTime.now();
        amCompanySet.setCreatedTime(now);
        amCompanySet.setModifiedTime(now);
        amCompanySet.setCreatedBy(ReasonUtil.getUserId());
        amCompanySet.setModifiedBy(ReasonUtil.getUserId());
        amCompanySet.setActive(true);

        return this.insertOrUpdateAllColumn(amCompanySet);

    }
}
