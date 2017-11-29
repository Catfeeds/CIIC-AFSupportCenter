package com.ciicsh.gto.afsupportcenter.socialsecurity.business;

import com.ciicsh.gto.afsupportcenter.socialsecurity.dao.SsComMaterialMapper;
import com.ciicsh.gto.afsupportcenter.socialsecurity.entity.SsComMaterial;
import com.ciicsh.gto.afsupportcenter.util.business.Business;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 《企业材料收缴》业务
 */
@Service
@Transactional
public class SsComMaterialBusiness extends Business<SsComMaterial, SsComMaterialMapper>{

}
