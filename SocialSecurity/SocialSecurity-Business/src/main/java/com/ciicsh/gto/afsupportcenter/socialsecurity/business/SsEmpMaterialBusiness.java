package com.ciicsh.gto.afsupportcenter.socialsecurity.business;

import com.ciicsh.gto.afsupportcenter.socialsecurity.dao.SsEmpMaterialMapper;
import com.ciicsh.gto.afsupportcenter.socialsecurity.entity.SsEmpMaterial;
import com.ciicsh.gto.afsupportcenter.util.business.Business;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class SsEmpMaterialBusiness extends Business<SsEmpMaterial, SsEmpMaterialMapper>{

}
