package com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.bo.SsEmpArchiveBO;
import com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.entity.SsEmpArchive;
import com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.entity.custom.empSSSearchExportOpt;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 雇员本地社保档案主表,
 * 由中智代缴过社保的雇员在此表必有一条记录，如果雇员跳槽到另外一家客户，就会在此表产生 Mapper 接口
 * </p>
 *
 * @author HuangXing
 * @since 2017-12-01
 */
public interface SsEmpArchiveMapper extends BaseMapper<SsEmpArchive> {

    /**
     * 根据雇员任务 ID 查询 雇员本地社保档案信息
     *
     * @param empTaskId
     * @return
     */
    SsEmpArchiveBO queryByEmpTaskId(@Param("empTaskId") String empTaskId);

    /**
     * 雇员查询
     * @param ssEmpArchiveBO
     * @return
     */
    List<SsEmpArchiveBO> queryEmployee (SsEmpArchiveBO ssEmpArchiveBO);
    List<empSSSearchExportOpt> empSSSearchExport (SsEmpArchiveBO ssEmpArchiveBO);
    /**
     * 雇员详细信息查询
     * @param empArchiveId
     * @return
     */
    public SsEmpArchiveBO queryEmployeeDetailInfo(@Param("empArchiveId") String empArchiveId);
}
