package com.ciicsh.gto.afsupportcenter.housefund.fundservice.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;

import com.ciicsh.gto.afsupportcenter.housefund.fundservice.bo.HfComTaskTaskStatusBo;
import com.ciicsh.gto.afsupportcenter.housefund.fundservice.entity.HfComTaskTaskStatus;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
  * 企业公积金任务单状态数据表 Mapper 接口
 * </p>
 *
 * @author 沈健
 * @since 2018-02-28
 */
@Repository
public interface HfComTaskTaskStatusMapper extends BaseMapper<HfComTaskTaskStatus> {
    List<HfComTaskTaskStatusBo> selectAllComTaskTaskStatusData();
}