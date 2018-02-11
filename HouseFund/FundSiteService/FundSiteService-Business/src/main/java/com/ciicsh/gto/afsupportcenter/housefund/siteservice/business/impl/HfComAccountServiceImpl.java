package com.ciicsh.gto.afsupportcenter.housefund.siteservice.business.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.ciicsh.gto.afsupportcenter.housefund.siteservice.api.dto.HfComAccountDTO;
import com.ciicsh.gto.afsupportcenter.housefund.siteservice.api.dto.HfComAccountParamDto;
import com.ciicsh.gto.afsupportcenter.housefund.siteservice.business.HfComAccountService;
import com.ciicsh.gto.afsupportcenter.housefund.siteservice.dao.HfComAccountMapper;
import com.ciicsh.gto.afsupportcenter.housefund.siteservice.entity.HfComAccount;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 企业公积金账户：存储中智大库、中智外包、独立户企业的账号，含基本公积金和补充公积金
HF：House Fun 服务实现类
 * </p>
 */
@Service
public class HfComAccountServiceImpl extends ServiceImpl<HfComAccountMapper, HfComAccount> implements HfComAccountService {

    /**
     * 查询企业社保账户信息表
     *
     * @param dto
     * @return
     */
    @Override
    public List<HfComAccountDTO> getHfComAccountList(HfComAccountParamDto dto) {
        return baseMapper.getHfComAccountList(dto);
    }
}
