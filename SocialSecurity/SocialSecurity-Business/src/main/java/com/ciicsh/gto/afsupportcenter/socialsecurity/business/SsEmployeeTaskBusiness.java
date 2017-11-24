package com.ciicsh.gto.afsupportcenter.socialsecurity.business;

import com.ciicsh.gto.afsupportcenter.socialsecurity.dao.SsEmployeeTaskMapper;
import com.ciicsh.gto.afsupportcenter.socialsecurity.entity.SsEmployeeTask;
import com.ciicsh.gto.afsupportcenter.util.business.Business;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class SsEmployeeTaskBusiness extends Business<SsEmployeeTask, SsEmployeeTaskMapper>{

}
