package com.ciicsh.gto.afsupportcenter.housefund.apiservice.host.translator;

import com.ciicsh.gto.afsupportcenter.housefund.fundservice.api.dto.HfComAccountDTO;
import com.ciicsh.gto.afsupportcenter.housefund.fundservice.bo.customer.ComAccountExtBo;
import org.springframework.beans.BeanUtils;

/**
 * Created by houwanhua on 2018/2/26.
 */
public class ApiTranslator {
    public static HfComAccountDTO toComAccountDTO(ComAccountExtBo comAccountExtBo){
        HfComAccountDTO accountDTO = new HfComAccountDTO();
        BeanUtils.copyProperties(accountDTO,comAccountExtBo);
        return accountDTO;
    }
}
