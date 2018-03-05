package com.ciicsh.gto.afsupportcenter.housefund.fundservice.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;

import com.ciicsh.gto.afsupportcenter.housefund.fundservice.bo.HfComTaskEndTypeBo;
import com.ciicsh.gto.afsupportcenter.housefund.fundservice.entity.HfComTaskEndType;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
  * 企业公积金任务单终止类型数据表 Mapper 接口
 * </p>
 *
 * @author 沈健
 * @since 2018-03-01
 */
@Repository
public interface HfComTaskEndTypeMapper extends BaseMapper<HfComTaskEndType> {
    List<HfComTaskEndTypeBo> selectAllComTaskEndTypeData();
}