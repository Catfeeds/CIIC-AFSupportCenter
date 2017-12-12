package com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.business.impl;

import com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.dto.SsEmpArchiveDTO;
import com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.entity.SsEmpArchive;
import com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.dao.SsEmpArchiveMapper;
import com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.business.ISsEmpArchiveService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 雇员本地社保档案主表,
 * 由中智代缴过社保的雇员在此表必有一条记录，如果雇员跳槽到另外一家客户，就会在此表产生 服务实现类
 * </p>
 *
 * @author HuangXing
 * @since 2017-12-01
 */
@Service
public class SsEmpArchiveServiceImpl extends ServiceImpl<SsEmpArchiveMapper, SsEmpArchive> implements ISsEmpArchiveService {

    @Override
    public SsEmpArchiveDTO queryByEmpTaskId(String empTaskId) {
        return baseMapper.queryByEmpTaskId(empTaskId);
    }
}
