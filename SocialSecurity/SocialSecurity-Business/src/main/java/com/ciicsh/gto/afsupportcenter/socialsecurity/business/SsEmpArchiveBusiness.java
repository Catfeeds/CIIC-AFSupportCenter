package com.ciicsh.gto.afsupportcenter.socialsecurity.business;

import com.ciicsh.gto.afsupportcenter.socialsecurity.dao.SsEmpArchiveMapper;
import com.ciicsh.gto.afsupportcenter.socialsecurity.entity.SsEmpArchive;
import com.ciicsh.gto.afsupportcenter.util.business.Business;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 《雇员本地社保档案》业务
 */
@Service
@Transactional
public class SsEmpArchiveBusiness extends Business<SsEmpArchive, SsEmpArchiveMapper> {
}
