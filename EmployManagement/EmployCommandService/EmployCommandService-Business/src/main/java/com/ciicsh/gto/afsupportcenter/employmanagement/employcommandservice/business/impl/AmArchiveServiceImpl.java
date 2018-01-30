package com.ciicsh.gto.afsupportcenter.employmanagement.employcommandservice.business.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.ciicsh.gto.afsupportcenter.employmanagement.employcommandservice.business.IAmArchiveService;
import com.ciicsh.gto.afsupportcenter.employmanagement.employcommandservice.dao.AmArchiveMapper;
import com.ciicsh.gto.afsupportcenter.employmanagement.employcommandservice.entity.AmArchive;
import org.springframework.stereotype.Service;

/**
 * Created by zhangzhiwen on 2018/1/29.
 */

@Service
public class AmArchiveServiceImpl  extends ServiceImpl<AmArchiveMapper, AmArchive> implements IAmArchiveService {
}
