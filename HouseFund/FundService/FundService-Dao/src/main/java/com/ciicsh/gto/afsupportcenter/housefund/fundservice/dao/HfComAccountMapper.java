package com.ciicsh.gto.afsupportcenter.housefund.fundservice.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.ciicsh.gto.afsupportcenter.housefund.fundservice.bo.customer.ComAccountExtBo;
import com.ciicsh.gto.afsupportcenter.housefund.fundservice.bo.customer.ComAccountParamExtBo;
import com.ciicsh.gto.afsupportcenter.housefund.fundservice.bo.customer.ComAccountTransBo;
import com.ciicsh.gto.afsupportcenter.housefund.fundservice.entity.HfComAccount;

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
     * @param extBo
     * @return
     */
    List<ComAccountExtBo> getHfComAccountList(ComAccountParamExtBo extBo) ;

    /**
     * 查询企业公积金账户
     * @param comAccountTransBo
     * @return
     */
    List<ComAccountTransBo> queryComAccountTransBoList(ComAccountTransBo comAccountTransBo);
}
