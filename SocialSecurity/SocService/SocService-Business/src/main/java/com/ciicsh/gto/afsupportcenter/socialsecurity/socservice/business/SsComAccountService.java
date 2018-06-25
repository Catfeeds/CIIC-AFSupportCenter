package com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.business;

import com.baomidou.mybatisplus.service.IService;
import com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.bo.SsComAccountBO;
import com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.bo.SsEmpInfoBO;
import com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.bo.customer.ComAccountParamBO;
import com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.entity.SsComAccount;
import com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.entity.custom.ComAccountExtPO;
import com.ciicsh.gto.afsupportcenter.util.page.PageInfo;
import com.ciicsh.gto.afsupportcenter.util.page.PageRows;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

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
     * 获取企业社保账户信息导出信息
     */
    List<SsComAccountBO> getAccounts(SsComAccountBO accountBo);


    /**
     * 查询企业社保管理详细信息
     * @param comAccountId
     */
    SsComAccountBO querySocialSecurityManageInfo(String comAccountId);


    /**
     * 根据企业社保账户ID查询企业社保信息
     * @param comAccountId
     * @return
     */
    SsComAccount getAccountById(Long comAccountId);


    /**
     * 查询企业社保账户信息 接口用
     * @param paramBO
     * @return
     */
    List<ComAccountExtPO> getSsComAccountList(ComAccountParamBO paramBO);

    /**
     * 调用银行接口保存社保企业开户转入的银行信息
     *
     * @param map
     * @return
     */
    boolean addBankAccount(@RequestBody Map<String, Object> map) throws Exception;

    boolean isExistAccountInfo(String companyId);


    /**
     * 社保序号增1
     *
     * @param comAccountId
     * @return
     */
    void addSerial(Long comAccountId);

    /**
     * 根据社保账号获得社保序号
     *
     * @param comAccountId
     * @return
     */
    Long getSerialByComAccountId(Long comAccountId);

    ComAccountExtPO getSsComAccountByComId(String companyId);

    SsEmpInfoBO getSsEmpInfoById(String companyId, String employeeId);

}
