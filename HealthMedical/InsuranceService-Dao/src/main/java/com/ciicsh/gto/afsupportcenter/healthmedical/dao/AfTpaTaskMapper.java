package com.ciicsh.gto.afsupportcenter.healthmedical.dao;

import com.ciicsh.gto.afsupportcenter.healthmedical.entity.po.AfTpaTaskPO;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
  * TPA任务单记录表 Mapper 接口
 * </p>
 *
 * @author zhaogang
 * @since 2018-02-11
 */
@Repository
public interface AfTpaTaskMapper extends BaseMapper<AfTpaTaskPO> {
    List<AfTpaTaskPO> getAfTpaTaskByInsureDate(@Param("startInsureDate") String startInsureDate,@Param("endInsureDate") String endInsureDate,@Param("zyInsurance_Policy_ID") String zyInsurance_Policy_ID);
}