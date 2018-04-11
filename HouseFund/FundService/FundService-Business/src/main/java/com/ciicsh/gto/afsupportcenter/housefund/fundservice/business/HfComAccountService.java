package com.ciicsh.gto.afsupportcenter.housefund.fundservice.business;

import com.baomidou.mybatisplus.service.IService;
import com.ciicsh.gto.afsupportcenter.housefund.fundservice.bo.customer.AccountInfoBO;
import com.ciicsh.gto.afsupportcenter.housefund.fundservice.bo.customer.ComAccountExtBo;
import com.ciicsh.gto.afsupportcenter.housefund.fundservice.bo.customer.ComAccountParamExtBo;
import com.ciicsh.gto.afsupportcenter.housefund.fundservice.bo.customer.ComAccountTransBo;
import com.ciicsh.gto.afsupportcenter.housefund.fundservice.dto.ComFundAccountDetailDTO;
import com.ciicsh.gto.afsupportcenter.housefund.fundservice.dto.GetComFundAccountListRequestDTO;
import com.ciicsh.gto.afsupportcenter.housefund.fundservice.entity.ComFundAccountCompanyPO;
import com.ciicsh.gto.afsupportcenter.housefund.fundservice.entity.ComFundAccountDetailPO;
import com.ciicsh.gto.afsupportcenter.housefund.fundservice.entity.ComFundAccountClassNamePO;
import com.ciicsh.gto.afsupportcenter.housefund.fundservice.entity.ComFundAccountNamePO;
import com.ciicsh.gto.afsupportcenter.housefund.fundservice.entity.ComFundAccountPO;
import com.ciicsh.gto.afsupportcenter.housefund.fundservice.entity.HfComAccount;
import com.ciicsh.gto.afsupportcenter.util.web.response.JsonResult;

import java.util.List;

public interface HfComAccountService extends IService<HfComAccount> {

    /**
     * 查询企业社保账户信息 接口用
     *
     * @param extBo
     * @return
     */
    List<ComAccountExtBo> getHfComAccountList(ComAccountParamExtBo extBo);


    /**
     * 根据查询条件获取企业公积金账户信息，Site用
     * @param request
     * @return
     */
    List<ComFundAccountPO> getComFundAccountList(GetComFundAccountListRequestDTO request);

    /**
     * 根据企业账户名称和账号筛选公积金账户和账号
     * @param comAccountName
     * @param hfComAccount
     * @return
     */
    List<ComFundAccountClassNamePO> getComFundAccountClassNameList(String comAccountName, String hfComAccount);

    /**
     * 根据企业账户名称和账户类型筛选公积金账户
     * @param comAccountName
     * @param hfAccountType  1 大库 2 外包 3 独立户
     * @return
     */
    List<ComFundAccountNamePO> getComFundAccountNameList(String comAccountName,Byte hfType,Byte hfAccountType);

    /**
     * 获取企业公积金账户详情信息
     * @param comAccountId
     * @param hfType
     * @return
     */
    ComFundAccountDetailPO getComFundAccountDetail(int comAccountId, Byte hfType);


    /**
     * 获取企业公积金账户绑定的公司列表
     * @param comAccountId
     * @return
     */
    List<ComFundAccountCompanyPO> getComFundAccountCompanyList(int comAccountId);

    /**
     * 查询企业公积金账户信息
     * @param comAccountTransBo
     * @return
     */
    List<ComAccountTransBo> queryComAccountTransBoList(ComAccountTransBo comAccountTransBo);

    /**
     * 根据公司和公积金类型判断是否存在账户
     * @param companyId 公司ID
     * @param hfType 公积金类型（基本公积金/补充公积金）
     * @return
     */
    Integer isExistAccount(String companyId, Integer hfType);

    /**
     * 根据公司信息查询账户信息
     * @param companyId 公司ID
     * @return
     */
    List<AccountInfoBO> getAccountByCompany(String companyId);

    /**
     * 查询企业公积金账户信息
     *
     * @param extBo
     * @return
     */
    List<ComAccountExtBo> queryHfComAccountList(ComAccountParamExtBo extBo);

    JsonResult submitCompanyFundAccount(ComFundAccountDetailDTO comFundAccountDetailDTO);
}
