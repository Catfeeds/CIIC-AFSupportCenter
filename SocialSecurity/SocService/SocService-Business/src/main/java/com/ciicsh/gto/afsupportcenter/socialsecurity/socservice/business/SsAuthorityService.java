package com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.business;

import com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.dto.dataauth.SsDepartmentDTO;
import com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.dto.dataauth.SsCompanyManagementListDTO;
import com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.dto.dataauth.SsDataauthCompanyDTO;
import com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.dto.dataauth.SsUserInfoDTO;

import java.util.List;

/**
 * <p>
 * 社保限权管理服务类
 * </p>
 *
 * @author LiYueLong
 * @since 2018-4-04
 */
public interface SsAuthorityService {


    List<SsUserInfoDTO> queryUsersByDepartmentId();

    List<SsDepartmentDTO> querySubDepartmentsOfLevel();

    SsCompanyManagementListDTO queryAfCompanyByUidAndServiceCenterId(String userId,Long serviceCenterId);

    boolean saveSsDataauth(SsDataauthCompanyDTO dto);
}
