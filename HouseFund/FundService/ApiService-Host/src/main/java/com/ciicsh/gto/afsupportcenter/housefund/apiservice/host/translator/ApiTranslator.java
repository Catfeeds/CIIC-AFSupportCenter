package com.ciicsh.gto.afsupportcenter.housefund.apiservice.host.translator;

import com.ciicsh.gto.afsupportcenter.housefund.fundservice.api.dto.FundInfoDTO;
import com.ciicsh.gto.afsupportcenter.housefund.fundservice.api.dto.HfComAccountDTO;
import com.ciicsh.gto.afsupportcenter.housefund.fundservice.bo.customer.AccountInfoBO;
import com.ciicsh.gto.afsupportcenter.housefund.fundservice.bo.customer.ComAccountExtBo;
import org.springframework.beans.BeanUtils;

/**
 * Created by houwanhua on 2018/2/26.
 */
public class ApiTranslator {
    public static HfComAccountDTO toComAccountDTO(ComAccountExtBo comAccountExtBo){
        HfComAccountDTO accountDTO = new HfComAccountDTO();
        BeanUtils.copyProperties(comAccountExtBo,accountDTO);
        return accountDTO;
    }

    public static FundInfoDTO toFundInfoDTO(AccountInfoBO infoBO){
        FundInfoDTO fundInfoDTO = new FundInfoDTO();
        BeanUtils.copyProperties(infoBO,fundInfoDTO);
        return fundInfoDTO;
    }
}
