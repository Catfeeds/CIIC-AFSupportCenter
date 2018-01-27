package com.ciicsh.gto.afsupportcenter.housefund.fundcommandservice.business.impl;

import com.ciicsh.gto.afsupportcenter.housefund.fundcommandservice.dto.HfEmpArchiveDto;
import com.ciicsh.gto.afsupportcenter.housefund.fundcommandservice.entity.HfEmpArchive;
import com.ciicsh.gto.afsupportcenter.housefund.fundcommandservice.dao.HfEmpArchiveMapper;
import com.ciicsh.gto.afsupportcenter.housefund.fundcommandservice.business.IHfEmpArchiveService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.ciicsh.gto.afsupportcenter.util.page.PageInfo;
import com.ciicsh.gto.afsupportcenter.util.page.PageKit;
import com.ciicsh.gto.afsupportcenter.util.page.PageRows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 雇员本地公积金档案主表,
由中智代缴过社保的雇员在此表必有一条记录，如果雇员跳槽到另外一家客户，就会在此表产 服务实现类
 * </p>
 */
@Service
public class HfEmpArchiveServiceImpl extends ServiceImpl<HfEmpArchiveMapper, HfEmpArchive> implements IHfEmpArchiveService {

    @Autowired
    HfEmpArchiveMapper hfEmpArchiveMapper;

   public PageRows<HfEmpArchiveDto> queryEmpArchive(PageInfo pageInfo){
       HfEmpArchiveDto dto = pageInfo.toJavaObject(HfEmpArchiveDto.class);
       return  PageKit.doSelectPage(pageInfo, () ->  hfEmpArchiveMapper.queryEmpArchive(dto));
    }
}
