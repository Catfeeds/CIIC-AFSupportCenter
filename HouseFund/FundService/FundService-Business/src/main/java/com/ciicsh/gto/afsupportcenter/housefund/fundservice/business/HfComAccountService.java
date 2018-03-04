package com.ciicsh.gto.afsupportcenter.housefund.fundservice.business;

import com.baomidou.mybatisplus.service.IService;
import com.ciicsh.gto.afsupportcenter.housefund.fundservice.bo.customer.ComAccountExtBo;
import com.ciicsh.gto.afsupportcenter.housefund.fundservice.bo.customer.ComAccountParamExtBo;
import com.ciicsh.gto.afsupportcenter.housefund.fundservice.dto.ComFundAccountDTO;
import com.ciicsh.gto.afsupportcenter.housefund.fundservice.dto.GetComFundAccountListRequestDTO;
import com.ciicsh.gto.afsupportcenter.housefund.fundservice.entity.ComFundAccountPO;
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
     * 根据查询条件获取企业公积金账户信息，Site用
     * @param request
     * @return
     */
    List<ComFundAccountPO> getComFundAccountList(GetComFundAccountListRequestDTO request);

}
