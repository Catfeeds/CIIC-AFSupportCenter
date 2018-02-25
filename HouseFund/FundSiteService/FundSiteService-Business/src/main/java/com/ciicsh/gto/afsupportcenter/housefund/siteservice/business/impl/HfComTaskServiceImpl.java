package com.ciicsh.gto.afsupportcenter.housefund.siteservice.business.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;

import com.ciicsh.gto.afsupportcenter.housefund.siteservice.bo.HfComTaskBo;
import com.ciicsh.gto.afsupportcenter.housefund.siteservice.business.HfComTaskService;
import com.ciicsh.gto.afsupportcenter.housefund.siteservice.dao.HfAccountComRelationMapper;
import com.ciicsh.gto.afsupportcenter.housefund.siteservice.dao.HfComAccountClassMapper;
import com.ciicsh.gto.afsupportcenter.housefund.siteservice.dao.HfComAccountMapper;
import com.ciicsh.gto.afsupportcenter.housefund.siteservice.dao.HfComTaskMapper;
import com.ciicsh.gto.afsupportcenter.housefund.siteservice.entity.HfAccountComRelation;
import com.ciicsh.gto.afsupportcenter.housefund.siteservice.entity.HfComAccount;
import com.ciicsh.gto.afsupportcenter.housefund.siteservice.entity.HfComAccountClass;
import com.ciicsh.gto.afsupportcenter.housefund.siteservice.entity.HfComTask;
import com.ciicsh.gto.afsupportcenter.util.page.PageInfo;
import com.ciicsh.gto.afsupportcenter.util.page.PageKit;
import com.ciicsh.gto.afsupportcenter.util.page.PageRows;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Map;

/**
 * <p>
 * 企业任务单服务实现类：有关于企业任务单的相关操作
Com：公司简写 服务实现类
 * </p>
 *
 * @author 沈健
 * @since 2018-02-07
 */
@Service
public class HfComTaskServiceImpl extends ServiceImpl<HfComTaskMapper, HfComTask> implements HfComTaskService {

    //公积金独立账户类型 - 独立户，公积金账户默认类型
    private static final Integer HF_COM_ACCOUNT_TYPE_INDEPEDENT = 3;
    //公积金账户默认状态 - 初始
    private static final Integer HF_COM_ACCOUNT_STATE_INIT = 0;
    //公积金账户客户关系是否帐户主客户 - 是
    private static final Integer HF_ACCOUNT_COM_RELATION_MAJOR_COM_TRUE = 1;
    //公积金账户客户关系是否帐户主客户 - 否
    private static final Integer HF_ACCOUNT_COM_RELATION_MAJOR_COM_FALSE = 0;

    @Autowired
    private HfComTaskMapper hfComTaskMapper;
    @Autowired
    private HfComAccountMapper hfComAccountMapper;
    @Autowired
    private HfComAccountClassMapper hfComAccountClassMapper;
    @Autowired
    private HfAccountComRelationMapper hfAccountComRelationMapper;

    /**
     * 获得企业任务单 未处理
     * @param pageInfo
     * @return
     */
    @Override
    public PageRows<HfComTaskBo> queryNoProgressCompanyTask(PageInfo pageInfo) {
        //将PageInfo对象转DTO对象
        HfComTaskBo hfComTaskBo = pageInfo.toJavaObject(HfComTaskBo.class);
        return PageKit.doSelectPage(pageInfo, () -> hfComTaskMapper.queryNoProcessCompanyTask(hfComTaskBo));
    }

    /**
     * 获得企业任务单 处理中
     * @param pageInfo
     * @return
     */
    @Override
    public PageRows<HfComTaskBo> queryProgressingCompanyTask(PageInfo pageInfo) {
        return null;
    }

    /**
     * 获得企业任务单 已完成
     * @param pageInfo
     * @return
     */
    @Override
    public PageRows<HfComTaskBo> queryFinishedCompanyTask(PageInfo pageInfo) {
        return null;
    }

    /**
     * 获得企业任务单 批退
     * @param pageInfo
     * @return
     */
    @Override
    public PageRows<HfComTaskBo> queryRejectedCompanyTask(PageInfo pageInfo) {
        return null;
    }

    /**
     * 添加/删除企业任务单
     * @param map
     * @return
     */
    @Override
    public boolean upsertCompanyTaskRelated(Map<String, String> map){
        if (StringUtils.isNotBlank(map.get("comTaskId"))) {

            //取得企业任务单
            HfComTask hfComTask = hfComTaskMapper.selectById(map.get("comTaskId"));

            //设置企业公积金账号主表
            HfComAccount hfComAccount = new HfComAccount();
            hfComAccount.setComAccountName(hfComTask.getComAccountName());
            hfComAccount.setPaymentWay(hfComTask.getPaymentWay());
            hfComAccount.setHfAccountType(HF_COM_ACCOUNT_TYPE_INDEPEDENT);
            hfComAccount.setCloseDay(hfComTask.getCloseDay());
            if (StringUtils.isNotBlank(map.get("uKeyStore"))) {
                hfComAccount.setUkeyStore(Integer.parseInt(map.get("uKeyStore")));
            }
            if (StringUtils.isNotBlank(map.get("paymentBank"))) {
                hfComAccount.setPaymentBank(Integer.parseInt(map.get("paymentBank")));
            }
            if (StringUtils.isNotBlank(map.get("initiatorNotes"))) {
                hfComAccount.setRemark(map.get("initiatorNotes").toString());
            }
            hfComAccount.setState(HF_COM_ACCOUNT_STATE_INIT);
            hfComAccount.setCreatedTime(new Date());
            hfComAccount.setCreatedBy("sj");
            hfComAccount.setModifiedBy("sj");
            hfComAccount.setModifiedTime(new Date());
            hfComAccountMapper.insert(hfComAccount);

            //设置企业公积金账号从表
            HfComAccountClass hfComAccountClass = new HfComAccountClass();
            hfComAccountClass.setComAccountId(hfComAccount.getComAccountId());
            hfComAccountClass.setHfType(hfComTask.getHfType());
            hfComAccountClass.setHfComAccount(hfComTask.getHfComAccount());
            hfComAccountClass.setComStartMonth(hfComTask.getComStartMonth());
            hfComAccountClass.setEndMonth(hfComTask.getEndMonth());
            if (StringUtils.isNotBlank(map.get("operationStartMonth"))) {
                hfComAccountClass.setOperateStartMonth(map.get("operationStartMonth"));
            }
            //accountTempStore，页面
            hfComAccountClass.setComHfMonth(hfComTask.getComStartMonth());
            if (StringUtils.isNotBlank(map.get("endType"))) {
                hfComAccountClass.setEndType(Integer.parseInt(map.get("endType")));
            }
            hfComAccountClass.setCreatedTime(new Date());
            hfComAccountClass.setCreatedBy("sj");
            hfComAccountClass.setModifiedBy("sj");
            hfComAccountClass.setModifiedTime(new Date());
            hfComAccountClassMapper.insert(hfComAccountClass);

            //设置企业公积金账户客户关系表
            HfAccountComRelation hfAccountComRelation = new HfAccountComRelation();
            hfAccountComRelation.setComAccountId(hfComAccount.getComAccountId());
            hfAccountComRelation.setCompanyId(hfComTask.getCompanyId());
            hfAccountComRelation.setCreatedTime(new Date());
            hfAccountComRelation.setCreatedBy("sj");
            hfAccountComRelation.setModifiedBy("sj");
            hfAccountComRelation.setModifiedTime(new Date());
            if(hfAccountComRelationMapper.queryIfComAccountIdExists(hfComAccount.getComAccountId()) == 0) {
                hfAccountComRelation.setMajorCom(HF_ACCOUNT_COM_RELATION_MAJOR_COM_TRUE);
            } else {
                hfAccountComRelation.setMajorCom(HF_ACCOUNT_COM_RELATION_MAJOR_COM_FALSE);
            }
            hfAccountComRelationMapper.insert(hfAccountComRelation);

            //将账户主从表ID更新回任务单表中
            hfComTask.setComAccountId(hfComAccount.getComAccountId());
            hfComTask.setComAccountClassId(hfComAccountClass.getComAccountClassId());
            hfComTaskMapper.updateById(hfComTask);

            return true;
        } else {
            return false;
        }

    }

}
