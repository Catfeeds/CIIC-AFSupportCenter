package com.ciicsh.gto.afsupportcenter.employmanagement.employcommandservice.dao;

import com.ciicsh.gto.afsupportcenter.employmanagement.employcommandservice.bo.AmResignBO;
import com.ciicsh.gto.afsupportcenter.employmanagement.employcommandservice.entity.AmResign;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.ciicsh.gto.afsupportcenter.employmanagement.employcommandservice.entity.custom.resignSearchExportOpt;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 用工表 Mapper 接口
 * </p>
 */
public interface AmResignMapper extends BaseMapper<AmResign> {
    List<AmResignBO> queryAmResign(AmResignBO amResignBO);
    List<AmResignBO> queryAmResignOther(AmResignBO amResignBO);
    List<AmResignBO> taskCount(AmResignBO amResignBO);
    List<AmResignBO> queryAmResignDetail(Map<String,Object> param);
    List<resignSearchExportOpt>  queryAmResignList(AmResignBO amResignBO);
}
