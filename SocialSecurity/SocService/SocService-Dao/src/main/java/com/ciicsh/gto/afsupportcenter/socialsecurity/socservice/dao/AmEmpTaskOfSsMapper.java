package com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.dao;


import com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.dto.AmEmpTaskDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

/**
 * <p>
 * 用工退工任务单 Mapper 接口
 * </p>
 *
 * @author xsj
 * @since 2018-02-25
 */
@Mapper
@Component
public interface AmEmpTaskOfSsMapper{

    AmEmpTaskDTO queryReworkInfo(@Param("employeeId")String employeeId,  @Param("companyId")String companyId);
}
