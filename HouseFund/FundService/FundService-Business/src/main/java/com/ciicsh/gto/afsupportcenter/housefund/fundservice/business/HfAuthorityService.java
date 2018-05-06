package com.ciicsh.gto.afsupportcenter.housefund.fundservice.business;


import com.ciicsh.gto.afsupportcenter.housefund.fundservice.dto.dataauth.*;

import java.util.List;

/**
 * <p>
 * 公积金限权管理服务类
 * </p>
 *
 * @author LiYueLong
 * @since 2018-4-11
 */
public interface HfAuthorityService {


    List<HfUserInfoDTO> queryUsersByDepartmentId();

    List<HfDepartmentDTO> querySubDepartmentsOfLevel();

    HfCompanyManagementListDTO queryAfCompanyByUidAndServiceCenterId(String userId, Long serviceCenterId);

    boolean saveSsDataauth(HfDataauthCompanyDTO dto);

    boolean saveHfDataauthWelfareUnit(HfDataauthWelfareUnitDTO dto);

    boolean saveHfDataauthTaskCategory(HfDataauthWelfareUnitDTO dto);

    HfDataauthWelfareUnitDTO queryHfDataauthWelfareUnit(String userId);

    HfDataauthWelfareUnitDTO queryAuthorityTaskCategory(String userId);

    List<HfDepartmentDTO> querySubDepartmentsOfLevel(Integer[] levels);
}
