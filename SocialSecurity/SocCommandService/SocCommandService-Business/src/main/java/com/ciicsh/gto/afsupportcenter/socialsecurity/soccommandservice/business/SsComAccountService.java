package com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.business;

import com.baomidou.mybatisplus.service.IService;
import com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.api.dto.SsComAccountParamDto;
import com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.bo.SsComAccountBO;
import com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.entity.SsComAccount;
import com.ciicsh.gto.afsupportcenter.util.page.PageInfo;
import com.ciicsh.gto.afsupportcenter.util.page.PageRows;
import com.ciicsh.gto.settlementcenter.invoicecommandservice.api.dto.JsonResult;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 企业社保账户信息表
 * 企业社保账户分类 : 大库（中智大库、外包库）、独立户 服务类
 * </p>
 *
 * @author HuangXing
 * @since 2017-12-01
 */
public interface SsComAccountService extends IService<SsComAccount> {

    /**
     * 根据雇员任务 ID 查询 企业社保账户信息
     * @param empTaskId
     * @param type
     * @return
     */
    SsComAccountBO queryByEmpTaskId(String empTaskId,String type);
    /**
     * 查询企业社保账户信息
     *
     * @param pageInfo
     * @return
     */
    PageRows<SsComAccountBO> accountQuery(PageInfo pageInfo);

    /**
     * 查询企业社保管理详细信息
     * @param comAccountId
     */
    SsComAccountBO querySocialSecurityManageInfo(String comAccountId);

    /**
     * 查询企业社保账户信息 接口用
     *
     * @param dto
     * @return
     */
    List<com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.api.dto.SsComAccountDTO> getSsComAccountList(SsComAccountParamDto dto);

    /**
     * 调用银行接口保存社保企业开户转入的银行信息
     *
     * @param map
     * @return
     */
    JsonResult addBankAccount(@RequestBody Map<String, Object> map) throws Exception;
}
