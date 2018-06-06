package com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.bo.SsEmpArchiveBO;
import com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.bo.SsEmpInfoDetailBO;
import com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.entity.SsEmpArchive;
import com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.entity.custom.empSSSearchExportOpt;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

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
    public SsEmpArchiveBO queryEmployeeDetailInfoByComEmp(@Param("companyId") String companyId,@Param("employeeId") String employeeId);
    /**
     *
     * */
    public int checkSerialDuplicate(Map map);


    /**
     * 检查社保序号重复
     * @param ssEmpArchive :comAccountId employeeId empSsSerial
     */
    int checkSerial(SsEmpArchive ssEmpArchive);

    List<SsEmpInfoDetailBO> getSsEmpInfo(@Param("employeeId")String employeeId, @Param("companyId")String companyId, @Param("ssMonthBelong")String ssMonthBelong);
}
