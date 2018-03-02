package com.ciicsh.gto.afsupportcenter.housefund.fundservice.business;

import com.baomidou.mybatisplus.service.IService;
import com.ciicsh.gto.afsupportcenter.housefund.fundservice.bo.customer.ComAccountExtBo;
import com.ciicsh.gto.afsupportcenter.housefund.fundservice.bo.customer.ComAccountParamExtBo;
import com.ciicsh.gto.afsupportcenter.housefund.fundservice.bo.customer.ComAccountTransBo;
import com.ciicsh.gto.afsupportcenter.housefund.fundservice.entity.HfComAccount;

import java.util.List;

public interface HfComAccountService extends IService<HfComAccount> {

    /**
     * 查询企业社保账户信息 接口用
     *
     * @param extBo
     * @return
     */
    List<ComAccountExtBo> getHfComAccountList(ComAccountParamExtBo extBo);

    /**
     * 查询企业公积金账户
     * @param comAccountTransBo
     * @return
     */
    List<ComAccountTransBo> queryComAccountTransBoList(ComAccountTransBo comAccountTransBo);
}
