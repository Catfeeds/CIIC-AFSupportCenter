package com.ciicsh.gto.afsupportcenter.employmanagement.employservice.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.ciicsh.gto.afsupportcenter.employmanagement.employservice.api.dto.TerminateDTO;
import com.ciicsh.gto.afsupportcenter.employmanagement.employservice.bo.AmResignBO;
import com.ciicsh.gto.afsupportcenter.employmanagement.employservice.entity.AmResign;
import com.ciicsh.gto.afsupportcenter.employmanagement.employservice.custom.resignSearchExportOpt;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 用工表 Mapper 接口
 * </p>
 */
public interface AmResignMapper extends BaseMapper<AmResign> {
    List<AmResignBO> queryAmResign(AmResignBO amResignBO);
    List<AmResignBO> taskCount(AmResignBO amResignBO);
    List<AmResignBO> queryAmResignDetail(Map<String, Object> param);
    List<resignSearchExportOpt>  queryAmResignList(AmResignBO amResignBO);
    List<AmResignBO> queryResignIds(AmResignBO amResignBO);
    List<AmResignBO>  jobCount(AmResignBO amEmpTaskBO);
    List<TerminateDTO> getResignByEmpCompanyId(String empCompanyId);
}
