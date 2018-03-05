package com.ciicsh.gto.afsupportcenter.employmanagement.employcommandservice.business;

import com.ciicsh.gto.afsupportcenter.employmanagement.employcommandservice.bo.AmResignBO;
import com.ciicsh.gto.afsupportcenter.employmanagement.employcommandservice.entity.AmResign;
import com.baomidou.mybatisplus.service.IService;
import com.ciicsh.gto.afsupportcenter.employmanagement.employcommandservice.entity.custom.resignSearchExportOpt;
import com.ciicsh.gto.afsupportcenter.util.page.PageInfo;
import com.ciicsh.gto.afsupportcenter.util.page.PageRows;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 用工表 服务类
 * </p>
 */

@Service
public interface IAmResignService extends IService<AmResign> {

    PageRows<AmResignBO> queryAmResign(PageInfo pageInfo);

    List<AmResignBO> taskCount(PageInfo pageInfo);

    List<AmResignBO>  queryAmResignDetail(Map<String,Object> param);

    List<resignSearchExportOpt> queryAmResignList(AmResignBO amResignBO);
}
