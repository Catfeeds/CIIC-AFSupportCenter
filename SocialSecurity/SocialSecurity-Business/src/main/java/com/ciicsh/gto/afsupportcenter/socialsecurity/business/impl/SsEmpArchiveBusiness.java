package com.ciicsh.gto.afsupportcenter.socialsecurity.business.impl;

import com.ciicsh.gto.afsupportcenter.socialsecurity.business.ISsEmpArchiveBusiness;
import com.ciicsh.gto.afsupportcenter.socialsecurity.dao.SsEmpArchiveMapper;
import com.ciicsh.gto.afsupportcenter.socialsecurity.dao.model.SsEmpArchive;
import com.ciicsh.gto.afsupportcenter.util.business.impl.Business;
import org.springframework.stereotype.Service;

@Service
public class SsEmpArchiveBusiness extends Business<SsEmpArchive, SsEmpArchiveMapper>
    implements ISsEmpArchiveBusiness {
}
