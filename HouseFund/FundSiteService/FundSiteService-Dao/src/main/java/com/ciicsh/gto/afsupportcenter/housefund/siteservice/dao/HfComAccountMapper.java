package com.ciicsh.gto.afsupportcenter.housefund.siteservice.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.ciicsh.gto.afsupportcenter.housefund.siteservice.api.dto.HfComAccountDTO;
import com.ciicsh.gto.afsupportcenter.housefund.siteservice.api.dto.HfComAccountParamDto;
import com.ciicsh.gto.afsupportcenter.housefund.siteservice.entity.HfComAccount;

import java.util.List;

/**
 * <p>
 * 企业公积金账户：存储中智大库、中智外包、独立户企业的账号，含基本公积金和补充公积金
HF：House Fun Mapper 接口
 * </p>
 */
public interface HfComAccountMapper extends BaseMapper<HfComAccount> {

    /**
     * 查询企业社保账户信息表
     *
     * @param dto
     * @return
     */
    List<HfComAccountDTO> getHfComAccountList(HfComAccountParamDto dto) ;
}
