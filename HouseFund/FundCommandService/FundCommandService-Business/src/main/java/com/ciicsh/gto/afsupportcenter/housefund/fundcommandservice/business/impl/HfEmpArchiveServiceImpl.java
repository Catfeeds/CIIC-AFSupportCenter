package com.ciicsh.gto.afsupportcenter.housefund.fundcommandservice.business.impl;

import com.ciicsh.gto.afsupportcenter.housefund.fundcommandservice.entity.HfEmpArchive;
import com.ciicsh.gto.afsupportcenter.housefund.fundcommandservice.dao.HfEmpArchiveMapper;
import com.ciicsh.gto.afsupportcenter.housefund.fundcommandservice.business.IHfEmpArchiveService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 雇员本地公积金档案主表,
由中智代缴过社保的雇员在此表必有一条记录，如果雇员跳槽到另外一家客户，就会在此表产 服务实现类
 * </p>
 */
@Service
public class HfEmpArchiveServiceImpl extends ServiceImpl<HfEmpArchiveMapper, HfEmpArchive> implements IHfEmpArchiveService {

}
