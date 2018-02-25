package com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.dao;


import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.dto.AmEmpTaskDTO;
import com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.entity.AmEmpTask;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 用工退工任务单 Mapper 接口
 * </p>
 *
 * @author xsj
 * @since 2018-02-25
 */
public interface AmEmpTaskOfSsMapper{

    public AmEmpTaskDTO queryReworkInfo(@Param("employeeId")String employeeId,  @Param("companyId")String companyId);
}
