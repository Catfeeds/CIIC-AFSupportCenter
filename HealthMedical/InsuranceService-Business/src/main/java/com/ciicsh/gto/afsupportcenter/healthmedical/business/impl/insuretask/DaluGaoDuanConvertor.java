package com.ciicsh.gto.afsupportcenter.healthmedical.business.impl.insuretask;

import com.ciicsh.gto.afsupportcenter.healthmedical.entity.po.AfTpaTaskPO;
import com.ciicsh.gto.afsupportcenter.healthmedical.entity.dto.insuretask.DaLuGaoDuanDTO;

/**
 * <p>
 *
 * </p>
 *
 * @author zhaogang
 * @since 2017-12-04
 */

public class DaluGaoDuanConvertor {

    public DaLuGaoDuanDTO getXlsData(AfTpaTaskPO afTpaTaskPO, String insurancePolicyName) {
        DaLuGaoDuanDTO daluDTO=null;
        daluDTO.setInsureType(insurancePolicyName);
        daluDTO.setInsureDate(afTpaTaskPO.getStartConfirmDate().toString());
        daluDTO.setCompanyId(afTpaTaskPO.getCompanyId());
        daluDTO.setEmployeeId(afTpaTaskPO.getEmployeeId());
        daluDTO.setEmployeeName(afTpaTaskPO.getEmployeeName());

        daluDTO.setIDNum(afTpaTaskPO.getIdNum());
        // 性别字符转换还没有做
        daluDTO.setGender(afTpaTaskPO.getGender().toString());
        daluDTO.setAge(afTpaTaskPO.getAge().toString());
        daluDTO.setBrithday(afTpaTaskPO.getBirthDate().toString());
        daluDTO.setRemark(afTpaTaskPO.getRemark());
        return daluDTO;
    }

}
