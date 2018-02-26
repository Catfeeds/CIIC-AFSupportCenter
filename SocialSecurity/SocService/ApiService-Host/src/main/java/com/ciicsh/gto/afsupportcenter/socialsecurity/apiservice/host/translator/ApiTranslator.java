package com.ciicsh.gto.afsupportcenter.socialsecurity.apiservice.host.translator;

import com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.api.dto.SsComAccountDTO;
import com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.entity.custom.ComAccountExtPO;
import org.springframework.beans.BeanUtils;

/**
 * Created by houwanhua on 2018/2/26.
 */
public class ApiTranslator {
    public static SsComAccountDTO toComAccountDTO(ComAccountExtPO accountExtPO){
        SsComAccountDTO accountDTO = new SsComAccountDTO();
        BeanUtils.copyProperties(accountDTO,accountExtPO);
        return accountDTO;
    }
}
