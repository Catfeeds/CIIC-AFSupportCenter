package com.ciicsh.gto.afsupportcenter.housefund.fundcommandservice.business;

import com.ciicsh.gto.afsupportcenter.housefund.fundcommandservice.dto.HfEmpArchiveDto;
import com.ciicsh.gto.afsupportcenter.housefund.fundcommandservice.entity.HfEmpArchive;
import com.baomidou.mybatisplus.service.IService;
import com.ciicsh.gto.afsupportcenter.util.page.PageInfo;
import com.ciicsh.gto.afsupportcenter.util.page.PageRows;

import java.util.List;

/**
 * <p>
 * 雇员本地公积金档案主表,
由中智代缴过社保的雇员在此表必有一条记录，如果雇员跳槽到另外一家客户，就会在此表产 服务类
 * </p>
 */
public interface IHfEmpArchiveService extends IService<HfEmpArchive> {

    PageRows<HfEmpArchiveDto> queryEmpArchive(PageInfo pageInfo);

}
