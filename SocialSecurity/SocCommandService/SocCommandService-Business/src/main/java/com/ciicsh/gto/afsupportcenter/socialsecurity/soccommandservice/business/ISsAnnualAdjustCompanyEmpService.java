package com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.business;

import com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.dto.SsAnnualAdjustCompanyEmpDTO;
import com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.dto.SsAnnualAdjustCompanyEmpTempDTO;
import com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.entity.SsAnnualAdjustCompanyEmp;
import com.baomidou.mybatisplus.service.IService;
import com.ciicsh.gto.afsupportcenter.util.page.PageInfo;
import com.ciicsh.gto.afsupportcenter.util.page.PageRows;

import java.util.List;

/**
 * <p>
 *  年调客户雇员信息管理服务类
 * </p>
 */
public interface ISsAnnualAdjustCompanyEmpService extends IService<SsAnnualAdjustCompanyEmp> {

    /**
     * 年调客户雇员信息分页查询
     * @param pageInfo
     * @return
     */
    PageRows<SsAnnualAdjustCompanyEmp> queryAnnualAdjustCompanyEmpInPage(PageInfo pageInfo);

    /**
     * 年调客户雇员信息查询
     * @param ssAnnualAdjustCompanyEmpDTO
     * @return
     */
    List<SsAnnualAdjustCompanyEmp> queryAnnualAdjustCompanyEmp(SsAnnualAdjustCompanyEmpDTO ssAnnualAdjustCompanyEmpDTO);

    /**
     * 将年调客户雇员临时表中没有错误信息的数据插入正式表
     * @param ssAnnualAdjustCompanyEmpTempDTO
     */
    void insertDataWithoutErrorMsg(SsAnnualAdjustCompanyEmpTempDTO ssAnnualAdjustCompanyEmpTempDTO);
}
