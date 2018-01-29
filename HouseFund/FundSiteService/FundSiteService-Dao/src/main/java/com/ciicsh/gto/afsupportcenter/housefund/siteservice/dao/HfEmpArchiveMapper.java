package com.ciicsh.gto.afsupportcenter.housefund.siteservice.dao;

import com.ciicsh.gto.afsupportcenter.housefund.siteservice.bo.HfEmpArchiveBo;
import com.ciicsh.gto.afsupportcenter.housefund.siteservice.entity.HfEmpArchive;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.ciicsh.gto.afsupportcenter.housefund.siteservice.entity.HfEmpTask;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 雇员本地公积金档案主表,
由中智代缴过社保的雇员在此表必有一条记录，如果雇员跳槽到另外一家客户，就会在此表产 Mapper 接口
 * </p>
 */
public interface HfEmpArchiveMapper extends BaseMapper<HfEmpArchive> {
    List<HfEmpArchiveBo> queryEmpArchive(HfEmpArchiveBo dto);
    HfEmpArchiveBo viewEmpArchive(@Param("empArchiveId")String empArchiveId);
    HfEmpArchiveBo viewComAccount(@Param("companyId")String companyId);
    HfEmpArchiveBo viewEmpPeriod(@Param("empArchiveId")String empArchiveId, @Param("hfType")String hfType);
    List<HfEmpTask> listEmpTaskPeriod(@Param("empArchiveId")String empArchiveId,@Param("hfType") String hfType);
    List<HfEmpTask> listEmpTransfer (@Param("empArchiveId")String empArchiveId);
}
