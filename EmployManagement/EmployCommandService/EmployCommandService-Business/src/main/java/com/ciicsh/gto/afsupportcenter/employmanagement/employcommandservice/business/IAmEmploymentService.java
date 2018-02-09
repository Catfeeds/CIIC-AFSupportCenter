package com.ciicsh.gto.afsupportcenter.employmanagement.employcommandservice.business;

import com.ciicsh.gto.afsupportcenter.employmanagement.employcommandservice.bo.AmEmploymentBO;
import com.ciicsh.gto.afsupportcenter.employmanagement.employcommandservice.entity.AmEmployment;
import com.baomidou.mybatisplus.service.IService;
import com.ciicsh.gto.afsupportcenter.util.page.PageInfo;
import com.ciicsh.gto.afsupportcenter.util.page.PageRows;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 用工主表 服务类
 * </p>
 */
public interface IAmEmploymentService extends IService<AmEmployment> {
    PageRows<AmEmploymentBO> queryAmEmployment(PageInfo pageInfo);

    AmEmploymentBO queryAmEmployment(@Param("AmEmploymentId") String AmEmploymentId);

    PageRows<AmEmploymentBO> queryAmArchive(PageInfo pageInfo);

    List<AmEmploymentBO> taskCountEmployee(PageInfo pageInfo);

    List<AmEmploymentBO> taskCountResign(PageInfo pageInfo);

}
