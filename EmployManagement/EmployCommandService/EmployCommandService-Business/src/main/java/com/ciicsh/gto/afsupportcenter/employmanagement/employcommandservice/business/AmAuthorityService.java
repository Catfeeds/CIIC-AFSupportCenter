package com.ciicsh.gto.afsupportcenter.employmanagement.employcommandservice.business;

import com.ciicsh.gto.afsupportcenter.employmanagement.employcommandservice.dto.dataauth.AmCompanyManagementListDTO;
import com.ciicsh.gto.afsupportcenter.employmanagement.employcommandservice.dto.dataauth.AmDataauthCompanyDTO;
import com.ciicsh.gto.afsupportcenter.employmanagement.employcommandservice.dto.dataauth.AmDepartmentDTO;
import com.ciicsh.gto.afsupportcenter.employmanagement.employcommandservice.dto.dataauth.AmUserInfoDTO;

import java.util.List;

/**
 * <p>
 * 雇佣数据限权管理服务类
 * </p>
 *
 * @author LiYueLong
 * @since 2018-4-18
 */
public interface AmAuthorityService {


    List<AmUserInfoDTO> queryUsersByDepartmentId();

    List<AmDepartmentDTO> querySubDepartmentsOfLevel();

    AmCompanyManagementListDTO queryAfCompanyByUidAndServiceCenterId(String userId, Long serviceCenterId);

    boolean saveSsDataauth(AmDataauthCompanyDTO dto);
}
