package com.ciicsh.gto.afsupportcenter.socialsecurity.business.impl;

import com.ciicsh.gto.afsupportcenter.socialsecurity.business.ISsComMaterialBusiness;
import com.ciicsh.gto.afsupportcenter.socialsecurity.dao.SsComMaterialMapper;
import com.ciicsh.gto.afsupportcenter.socialsecurity.dao.model.SsComMaterial;
import com.ciicsh.gto.afsupportcenter.util.business.impl.Business;
import org.springframework.stereotype.Service;

@Service
public class SsComMaterialBusiness extends Business<SsComMaterial, SsComMaterialMapper>
    implements ISsComMaterialBusiness {

}
