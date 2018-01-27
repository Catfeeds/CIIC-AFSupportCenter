package com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.business;

import com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.bo.SsAnnualAdjustAccountEmpBO;
import com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.dto.SsAnnualAdjustAccountEmpDTO;
import com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.dto.SsAnnualAdjustAccountEmpTempDTO;
import com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.entity.SsAnnualAdjustAccountEmp;
import com.baomidou.mybatisplus.service.IService;
import com.ciicsh.gto.afsupportcenter.util.page.PageInfo;
import com.ciicsh.gto.afsupportcenter.util.page.PageRows;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 */
public interface ISsAnnualAdjustAccountEmpService extends IService<SsAnnualAdjustAccountEmp> {

    /**
     * 将年调社保账户雇员临时表中没有错误信息的数据插入正式表
     * @param ssAnnualAdjustAccountEmpTempDTO
     */
    void insertDataWithoutErrorMsg(SsAnnualAdjustAccountEmpTempDTO ssAnnualAdjustAccountEmpTempDTO);

    /**
     * 查询年调社保账户雇员信息
     * @param ssAnnualAdjustAccountEmpDTO
     * @return
     */
    List<SsAnnualAdjustAccountEmpBO> queryAnnualAdjustAccountEmp(SsAnnualAdjustAccountEmpDTO ssAnnualAdjustAccountEmpDTO);

    /**
     * 分页查询年调社保账户雇员信息
     * @param pageInfo
     * @return
     */
    PageRows<SsAnnualAdjustAccountEmpBO> queryAnnualAdjustAccountEmpInPage(PageInfo pageInfo);

}
