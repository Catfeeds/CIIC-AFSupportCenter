package com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.business;

import com.baomidou.mybatisplus.service.IService;
import com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.bo.SsEmpArchiveBO;
import com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.bo.SsEmpInfoBO;
import com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.bo.SsEmpInfoParamBO;
import com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.entity.SsEmpArchive;
import com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.entity.custom.empSSSearchExportOpt;
import com.ciicsh.gto.afsupportcenter.util.page.PageInfo;
import com.ciicsh.gto.afsupportcenter.util.page.PageRows;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 雇员本地社保档案主表,
 由中智代缴过社保的雇员在此表必有一条记录，如果雇员跳槽到另外一家客户，就会在此表产生 服务类
 * </p>
 *
 * @author HuangXing
 * @since 2017-12-01
 */
public interface SsEmpArchiveService extends IService<SsEmpArchive> {


    /**
     * 根据雇员任务 ID 查询 雇员本地社保档案信息
     * @param empTaskId
     * @param operatorType
     * @return
     */
    SsEmpArchiveBO queryByEmpTaskId(String empTaskId,String operatorType);

    /**
     *  雇员查询
     * @param pageInfo
     * @return
     */
    PageRows<SsEmpArchiveBO> queryEmployee (PageInfo pageInfo);

    List<empSSSearchExportOpt> empSSSearchExport(SsEmpArchiveBO ssEmpArchiveBO);
    /**
     * 雇员详细信息查询
     * @param empArchiveId
     * @return
     */
    public SsEmpArchiveBO queryEmployeeDetailInfo(String empArchiveId,String companyId,String employeeId);

    public String saveEmpSerial(Map<String,String> map);

    public SsEmpArchiveBO queryEmployeeIsnewOrChangeInto(String empTaskId);

    /**
     * 检查社保序号重复
     * @param comAccountId
     * @param employeeId
     * @param empSsSerial
     * @return true:验证成功
     */
    boolean checkSerial( Long comAccountId,String employeeId,String empSsSerial);

    List<SsEmpInfoBO> getSsEmpArchiveInfo(List<SsEmpInfoParamBO> paramBoList);

    SsEmpArchiveBO getSsEmployee(String companyId, String employeeId);
}
