package com.ciicsh.gto.afsupportcenter.socialsecurity.business.impl;

import com.ciicsh.gto.afsupportcenter.socialsecurity.business.ISsEmployeeTaskBusiness;
import com.ciicsh.gto.afsupportcenter.socialsecurity.dao.SsEmployeeTaskMapper;
import com.ciicsh.gto.afsupportcenter.socialsecurity.dao.model.SsEmployeeTask;
import com.ciicsh.gto.afsupportcenter.util.business.impl.Business;
import org.springframework.stereotype.Service;

@Service
public class SsEmployeeTaskBusiness extends Business<SsEmployeeTask, SsEmployeeTaskMapper>
    implements ISsEmployeeTaskBusiness {

}
