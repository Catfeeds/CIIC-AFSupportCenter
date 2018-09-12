package com.ciicsh.gto.afsupportcenter.housefund.fundservice.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.ciicsh.gto.afsupportcenter.housefund.fundservice.bo.customer.AccountInfoBO;
import com.ciicsh.gto.afsupportcenter.housefund.fundservice.bo.customer.ComAccountExtBo;
import com.ciicsh.gto.afsupportcenter.housefund.fundservice.bo.customer.ComAccountParamExtBo;
import com.ciicsh.gto.afsupportcenter.housefund.fundservice.bo.customer.ComAccountTransBo;
import com.ciicsh.gto.afsupportcenter.housefund.fundservice.dto.GetComFundAccountListRequestDTO;
import com.ciicsh.gto.afsupportcenter.housefund.fundservice.entity.ComFundAccountCompanyPO;
import com.ciicsh.gto.afsupportcenter.housefund.fundservice.entity.ComFundAccountDetailPO;
import com.ciicsh.gto.afsupportcenter.housefund.fundservice.entity.ComFundAccountClassNamePO;
import com.ciicsh.gto.afsupportcenter.housefund.fundservice.entity.ComFundAccountNamePO;
import com.ciicsh.gto.afsupportcenter.housefund.fundservice.entity.ComFundAccountPO;
import com.ciicsh.gto.afsupportcenter.housefund.fundservice.entity.HfComAccount;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 * 企业公积金账户：存储中智大库、中智外包、独立户企业的账号，含基本公积金和补充公积金
HF：House Fun Mapper 接口
 * </p>
 */
@Repository
public interface HfComAccountMapper extends BaseMapper<HfComAccount> {

    /**
     * 查询企业公积金账户信息表
     *
     * @param extBo
     * @return
     */
    List<ComAccountExtBo> getHfComAccountList(ComAccountParamExtBo extBo);

    /**
     * 查询企业公积金账户信息表,Site用
     * @return
     */
//    List<ComFundAccountPO> getComFundAccountList(@Param("companyId") String companyId, @Param("companyName") String companyName,
//                                                 @Param("hfType") Byte hfType,
//                                                 @Param("comHfMonth") String comHfMonth,
//                                                 @Param("accountNumber") String accountNumber,
//                                                 @Param("leaderShipName") String leaderShipName,
//                                                 @Param("payBankValue") String payBankValue,
//                                                 @Param("serviceCenterValue") String serviceCenterValue
//                                                 );
    List<ComFundAccountPO> getComFundAccountList(GetComFundAccountListRequestDTO getComFundAccountListRequestDTO);
    /**
     * 根据查询条件获取企业公积金账户名称列表
     * @param comAccountName
     * @param hfComAccount
     * @return
     */
    List<ComFundAccountClassNamePO> getComFundAccountClassNameList(@Param("comAccountName") String comAccountName, @Param("hfComAccount") String hfComAccount);

    /**
     * 根据查询条件获取企业公积金账户名称列表
     * @param comAccountName
     * @param hfAccountType 1 大库 2 外包 3 独立户
     * @return
     */
    List<ComFundAccountNamePO> getComFundAccountNameList(@Param("comAccountName")String comAccountName, @Param("hfType") Byte hfType, @Param("hfAccountType") Byte hfAccountType);

    /**
     * 获取企业公积金账户明细信息,Site用
     * @param comAccountId
     * @param hfType
     * @return
     */
    ComFundAccountDetailPO getComFundAccountDetail(@Param("comAccountId") int comAccountId,@Param("hfType") Byte hfType);


    /**
     * 获取企业公积金账户绑定的客户列表
     * @param comAccountId
     * @return
     */
    List<ComFundAccountCompanyPO> getComFundAccountCompanyList(@Param("comAccountId") int comAccountId);

    /**
     * 查询企业公积金账户信息
     * @param comAccountTransBo
     * @return
     */
    List<ComAccountTransBo> queryComAccountTransBoList(ComAccountTransBo comAccountTransBo);
    List<ComAccountTransBo> queryComAccountByCompanyIdTransBoList(ComAccountTransBo comAccountTransBo);

    Integer isExistAccount(@Param("companyId") String companyId,@Param("hfType") Integer hfType);

    Integer serchExistAccount(@Param("companyId") String companyId);

    AccountInfoBO getAccountsByCompany(@Param("companyId") String companyId,@Param("hfType") Integer hfType);

    /**
     * 查询企业公积金账户信息
     *
     * @param extBo
     * @return
     */
    List<ComAccountExtBo> queryHfComAccountList(ComAccountParamExtBo extBo);
}