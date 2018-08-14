package com.ciicsh.gto.afsupportcenter.housefund.fundservice.business.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.ciicsh.gto.afsupportcenter.housefund.fundservice.bo.customer.AccountInfoBO;
import com.ciicsh.gto.afsupportcenter.housefund.fundservice.bo.customer.ComAccountExtBo;
import com.ciicsh.gto.afsupportcenter.housefund.fundservice.bo.customer.ComAccountParamExtBo;
import com.ciicsh.gto.afsupportcenter.housefund.fundservice.bo.customer.ComAccountTransBo;
import com.ciicsh.gto.afsupportcenter.housefund.fundservice.business.HfComAccountService;
import com.ciicsh.gto.afsupportcenter.housefund.fundservice.dao.HfComAccountClassMapper;
import com.ciicsh.gto.afsupportcenter.housefund.fundservice.dao.HfComAccountMapper;
import com.ciicsh.gto.afsupportcenter.housefund.fundservice.dao.HfComTaskMapper;
import com.ciicsh.gto.afsupportcenter.housefund.fundservice.dto.ComFundAccountDetailDTO;
import com.ciicsh.gto.afsupportcenter.housefund.fundservice.dto.GetComFundAccountListRequestDTO;
import com.ciicsh.gto.afsupportcenter.housefund.fundservice.entity.*;
import com.ciicsh.gto.afsupportcenter.util.constant.HouseFundConst;
import com.ciicsh.gto.afsupportcenter.util.interceptor.authenticate.UserContext;
import com.ciicsh.gto.afsupportcenter.util.web.response.JsonResult;
import com.ciicsh.gto.afsupportcenter.util.web.response.JsonResultKit;
import io.swagger.models.auth.In;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * <p>
 * 企业公积金账户：存储中智大库、中智外包、独立户企业的账号，含基本公积金和补充公积金
 * HF：House Fun 服务实现类
 * </p>
 */
@Service
public class HfComAccountServiceImpl extends ServiceImpl<HfComAccountMapper, HfComAccount> implements HfComAccountService {

    @Autowired
    private HfComTaskMapper comTaskMapper;
    @Autowired
    private HfComAccountClassMapper hfComAccountClassMapper;
    /**
     * 查询企业社保账户信息表
     *
     * @param extBo
     * @return
     */
    @Override
    public List<ComAccountExtBo> getHfComAccountList(ComAccountParamExtBo extBo) {
        return baseMapper.getHfComAccountList(extBo);
    }

    /**
     * 根据查询条件获取企业公积金账户信息，Site用
     *
     * @param request
     * @return
     */
    @Override
    public List<ComFundAccountPO> getComFundAccountList(GetComFundAccountListRequestDTO request) {
        return baseMapper.getComFundAccountList(request.getCompanyId(), request.getCompanyName(), request.getHfType(),
                request.getComHfMonth(), request.getAccountNumber());

    }

    /**
     * 根据筛选条件查询企业公积金账户名称信息
     *
     * @param comAccountName
     * @param hfComAccount
     * @return
     */
    @Override
    public List<ComFundAccountClassNamePO> getComFundAccountClassNameList(String comAccountName, String hfComAccount) {
        return baseMapper.getComFundAccountClassNameList(comAccountName, hfComAccount);

    }

    /**
     * 根据企业账户名称和账户类型筛选公积金账户
     *
     * @param comAccountName
     * @param hfType 1 基本公积金、2 补充公积金
     * @param hfAccountType  1 大库 2 外包 3 独立户
     * @return
     */
    @Override
    public List<ComFundAccountNamePO> getComFundAccountNameList(String comAccountName, Byte hfType,Byte hfAccountType) {
        return baseMapper.getComFundAccountNameList(comAccountName,hfType,hfAccountType);
    }

    /**
     * 获取企业公积金账户详情信息,Site用
     *
     * @param comAccountId 账户主记录id
     * @param hfType       1-基本公积金 2-补充公积金
     * @return
     */
    @Override
    public ComFundAccountDetailPO getComFundAccountDetail(int comAccountId, Byte hfType) {
        ComFundAccountDetailPO po = baseMapper.getComFundAccountDetail(comAccountId, hfType);

        return po;
    }

    /**
     * 获取企业公积金账户绑定的公司列表
     *
     * @param comAccountId
     * @return
     */
    @Override
    public List<ComFundAccountCompanyPO> getComFundAccountCompanyList(int comAccountId) {
        return baseMapper.getComFundAccountCompanyList(comAccountId);
    }

    @Override
    public List<ComAccountTransBo> queryComAccountTransBoList(ComAccountTransBo comAccountTransBo) {
        return baseMapper.queryComAccountTransBoList(comAccountTransBo);
    }
    @Override
    public List<ComAccountTransBo> queryComAccountByCompanyIdTransBoList(ComAccountTransBo comAccountTransBo) {
        return baseMapper.queryComAccountByCompanyIdTransBoList(comAccountTransBo);
    }
    @Override
    public Integer isExistAccount(String companyId, Integer hfType) {
        return baseMapper.isExistAccount(companyId, hfType);
    }

    @Override
    public List<AccountInfoBO> getAccountByCompany(String companyId) {
        List<AccountInfoBO> infos = new ArrayList<>();
        AccountInfoBO basicAccount = this.getAccountInfo(companyId,1);
        if(null != basicAccount){
            infos.add(basicAccount);
        }
        AccountInfoBO addAccount = this.getAccountInfo(companyId,2);
        if(null != addAccount){
            infos.add(addAccount);
        }
        return infos;
    }


    private AccountInfoBO getAccountInfo(String companyId, Integer hfType){
        Integer result = baseMapper.isExistAccount(companyId,hfType);
        AccountInfoBO accountInfoBO = result > 0 ? baseMapper.getAccountsByCompany(companyId,hfType) : comTaskMapper.getAccountsByCompany(companyId,hfType);
        return accountInfoBO;
    }

    @Override
    public List<ComAccountExtBo> queryHfComAccountList(ComAccountParamExtBo extBo) {
        return baseMapper.queryHfComAccountList(extBo);
    }


    @Override
    public JsonResult submitCompanyFundAccount(ComFundAccountDetailDTO comFundAccountDetailDTO) {
        try{
            HfComAccountClass hfComAccountClass=new HfComAccountClass();
            hfComAccountClass.setModifiedBy(UserContext.getUser().getDisplayName());
            hfComAccountClass.setModifiedTime(new Date());
            String comAccount=comFundAccountDetailDTO.getHfType()==1?comFundAccountDetailDTO.getBasicComAccount():comFundAccountDetailDTO.getCompensativeComAccount();
            hfComAccountClass.setHfComAccount(comAccount);
            hfComAccountClass.setComStartMonth(comFundAccountDetailDTO.getComStartMonthValue());
            String[] tempstore=comFundAccountDetailDTO.getTmpStore();
            boolean tmpStore=comFundAccountDetailDTO.getHfType()==1?
                Arrays.stream(tempstore).filter(i->"基本暂保管".equals(i)).findFirst().isPresent():
                Arrays.stream(tempstore).filter(i->"补充暂保管".equals(i)).findFirst().isPresent();

            hfComAccountClass.setAccountTempStore(tmpStore?1:0);
            hfComAccountClass.setComAccountClassId(comFundAccountDetailDTO.getComAccountClassId());
            //String origiComAccount= hfComAccountClassMapper.selectById(hfComAccountClass).getHfComAccount();
            //if(!origiComAccount.equals(comAccount)){
               hfComAccountClassMapper.updateById(hfComAccountClass);
            //}
            HfComAccount hfComAccount=new HfComAccount();
            BeanUtils.copyProperties(comFundAccountDetailDTO,hfComAccount);
            hfComAccount.setPaymentBank(comFundAccountDetailDTO.getPaymentBank());
            hfComAccount.setRemark(comFundAccountDetailDTO.getRemark());

            baseMapper.updateById(hfComAccount);
        }catch (Exception e){
            e.printStackTrace();
            return JsonResultKit.ofError("保存数据异常！");
        }
        return JsonResultKit.of();
    }

    @Override
    public ComAccountExtBo getHfComAccountByComId(String companyId) {
        ComAccountExtBo comAccountExtBo = new ComAccountExtBo();
        AccountInfoBO basicAccount = this.getAccountInfo(companyId,Integer.valueOf(HouseFundConst.HF_TYPE_BASE));
        if(basicAccount!=null){
            comAccountExtBo.setHfComAccount(basicAccount.getHfComAccount());
            comAccountExtBo.setPaymentBank(basicAccount.getPaymentBank());
            comAccountExtBo.setPaymentBankName(HouseFundConst.BANK_MAP.get(Optional.ofNullable(basicAccount.getPaymentBank()).orElse(-1).toString()));
        }else {
            return null;
        }
        basicAccount = this.getAccountInfo(companyId,Integer.valueOf(HouseFundConst.HF_TYPE_ADD));
        if(basicAccount!=null){
            comAccountExtBo.setHfComAccountBC(basicAccount.getHfComAccount());
        }
        return comAccountExtBo;
    }
}
