package com.ciicsh.gto.afsupportcenter.housefund.fundservice.dao;

import com.ciicsh.gto.afsupportcenter.housefund.fundservice.bo.HfArchiveBasePeriodBo;
import com.ciicsh.gto.afsupportcenter.housefund.fundservice.bo.HfComAccountBo;
import com.ciicsh.gto.afsupportcenter.housefund.fundservice.bo.HfEmpArchiveBo;
import com.ciicsh.gto.afsupportcenter.housefund.fundservice.entity.HfEmpArchive;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.ciicsh.gto.afsupportcenter.housefund.fundservice.entity.HfEmpTask;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 雇员本地公积金档案主表,
由中智代缴过社保的雇员在此表必有一条记录，如果雇员跳槽到另外一家客户，就会在此表产 Mapper 接口
 * </p>
 */
public interface HfEmpArchiveMapper extends BaseMapper<HfEmpArchive> {
    List<HfEmpArchiveBo> queryEmpArchive(HfEmpArchiveBo dto);
    HfEmpArchiveBo viewEmpArchive(@Param("empArchiveId")String empArchiveId);
    HfComAccountBo viewComAccount(@Param("companyId")String companyId);
    HfArchiveBasePeriodBo viewEmpPeriod(@Param("empArchiveId")String empArchiveId, @Param("hfType")String hfType);
    List<HfEmpTask> listEmpTaskPeriod(@Param("empArchiveId")String empArchiveId,@Param("hfType") String hfType);
    List<HfEmpTask> listEmpTransfer (@Param("empArchiveId")String empArchiveId);
    Map selectEmpByCardIdAndName(@Param("empName")String empName, @Param("idNum")String idNum);
    int deleteHfEmpArchiveByEmpTaskIds(List<Long> empTaskIdList);
    Map queryHfEmpArchiveByEmpTaskIds(List<Long> empTaskIdList);
}
