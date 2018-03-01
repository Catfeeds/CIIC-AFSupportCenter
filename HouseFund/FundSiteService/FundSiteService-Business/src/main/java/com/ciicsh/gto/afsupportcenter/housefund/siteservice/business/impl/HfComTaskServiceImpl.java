package com.ciicsh.gto.afsupportcenter.housefund.siteservice.business.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;

import com.ciicsh.gto.afsupportcenter.housefund.siteservice.bo.HfComAccountPaymentWayBo;
import com.ciicsh.gto.afsupportcenter.housefund.siteservice.bo.HfComTaskBo;
import com.ciicsh.gto.afsupportcenter.housefund.siteservice.bo.HfComTaskTaskStatusBo;
import com.ciicsh.gto.afsupportcenter.housefund.siteservice.business.HfComTaskService;
import com.ciicsh.gto.afsupportcenter.housefund.siteservice.dao.HfAccountComRelationMapper;
import com.ciicsh.gto.afsupportcenter.housefund.siteservice.dao.HfComAccountClassMapper;
import com.ciicsh.gto.afsupportcenter.housefund.siteservice.dao.HfComAccountMapper;
import com.ciicsh.gto.afsupportcenter.housefund.siteservice.dao.HfComAccountPaymentWayMapper;
import com.ciicsh.gto.afsupportcenter.housefund.siteservice.dao.HfComTaskMapper;
import com.ciicsh.gto.afsupportcenter.housefund.siteservice.dao.HfComTaskTaskStatusMapper;
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

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
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
    //前端日期时间格式
    private static final String DATA_FORMAT_STRING = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX";

    @Autowired
    private HfComTaskMapper hfComTaskMapper;
    @Autowired
    private HfComAccountMapper hfComAccountMapper;
    @Autowired
    private HfComAccountClassMapper hfComAccountClassMapper;
    @Autowired
    private HfAccountComRelationMapper hfAccountComRelationMapper;
    @Autowired
    private HfComAccountPaymentWayMapper hfComAccountPaymentWayMapper;
    @Autowired
    private HfComTaskTaskStatusMapper hfComTaskTaskStatusMapper;

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
     * 获得企业任务单支付方式数据
     * @return
     */
    @Override
    public List<HfComAccountPaymentWayBo> queryComTaskPaymentWayData(){
        return hfComAccountPaymentWayMapper.selectAllComTaskPaymentWayData();
    }

    /**
     * 获得企业任务单任务状态数据
     * @return
     */
    @Override
    public List<HfComTaskTaskStatusBo> queryComTaskTaskStatusData(){
        return hfComTaskTaskStatusMapper.selectAllComTaskTaskStatusData();
    }

    /**
     * 添加/更新企业任务单
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
            if (StringUtils.isNotBlank(map.get("comAccountName"))) {
                hfComAccount.setComAccountName(map.get("comAccountName"));
            }
            if (StringUtils.isNotBlank(map.get("paymentWay"))) {
                hfComAccount.setPaymentWay(Integer.parseInt(map.get("paymentWay")));
            }
            hfComAccount.setHfAccountType(HF_COM_ACCOUNT_TYPE_INDEPEDENT);
            if (StringUtils.isNotBlank(map.get("closeDay"))) {
                hfComAccount.setCloseDay(Integer.parseInt(map.get("closeDay")));
            }
            if (StringUtils.isNotBlank(map.get("uKeyStore"))) {
                hfComAccount.setUkeyStore(Integer.parseInt(map.get("uKeyStore")));
            }
            if (StringUtils.isNotBlank(map.get("paymentBank"))) {
                hfComAccount.setPaymentBank(Integer.parseInt(map.get("paymentBank")));
            }
            if (StringUtils.isNotBlank(map.get("comAccountRemark"))) {
                hfComAccount.setRemark(map.get("comAccountRemark").toString());
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
            if (StringUtils.isNotBlank(map.get("comAccountNum"))) {
                hfComAccountClass.setHfComAccount(map.get("comAccountNum"));
            }
            if (StringUtils.isNotBlank(map.get("comStartMonth"))) {
                hfComAccountClass.setComStartMonth(map.get("comStartMonth"));
                hfComAccountClass.setComHfMonth(map.get("comStartMonth"));
            }
            hfComAccountClass.setEndMonth(hfComTask.getEndMonth());
            if (StringUtils.isNotBlank(map.get("operateStartMonth"))) {
                hfComAccountClass.setOperateStartMonth(map.get("operateStartMonth"));
            }
            if (StringUtils.isNotBlank(map.get("accountTempStore"))) {
                hfComAccountClass.setAccountTempStore(Integer.parseInt(map.get("accountTempStore")));
            }
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
            if(hfAccountComRelationMapper.queryIfComAccountIdExists(hfComAccount.getComAccountId()) == 0) {
                hfAccountComRelation.setMajorCom(HF_ACCOUNT_COM_RELATION_MAJOR_COM_TRUE);
            } else {
                hfAccountComRelation.setMajorCom(HF_ACCOUNT_COM_RELATION_MAJOR_COM_FALSE);
            }
            hfAccountComRelation.setCreatedTime(new Date());
            hfAccountComRelation.setCreatedBy("sj");
            hfAccountComRelation.setModifiedBy("sj");
            hfAccountComRelation.setModifiedTime(new Date());
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

    /**
     * 添加/更新企业任务单（变更）
     * @param map
     * @return
     */
    @Override
    public boolean upsertCompanyTaskChangeInfoRelated(Map<String, String> map){
        if (StringUtils.isNotBlank(map.get("comTaskId"))) {
            //取得企业任务单
            HfComTask hfComTask = hfComTaskMapper.selectById(map.get("comTaskId"));
            if (StringUtils.isNotBlank(map.get("comAccountName"))) {
                hfComTask.setComAccountName(map.get("comAccountName"));
            }
            if (StringUtils.isNotBlank(map.get("paymentType"))) {
                hfComTask.setPaymentWay(Integer.parseInt(map.get("paymentType")));
            }
            if (StringUtils.isNotBlank(map.get("taskStatus"))) {
                hfComTask.setTaskStatus(Integer.parseInt(map.get("taskStatus")));
            }
            if (StringUtils.isNotBlank(map.get("acceptDate"))) {
                try {
                    hfComTask.setStrartHandleDate(new SimpleDateFormat(DATA_FORMAT_STRING).parse(map.get("acceptDate")));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            if (StringUtils.isNotBlank(map.get("approvalDate"))) {
                try {
                    hfComTask.setSendCheckDate(new SimpleDateFormat(DATA_FORMAT_STRING).parse(map.get("approvalDate")));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            if (StringUtils.isNotBlank(map.get("finishDate"))) {
                try {
                    hfComTask.setFinishDate(new SimpleDateFormat(DATA_FORMAT_STRING).parse(map.get("finishDate")));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            if (StringUtils.isNotBlank(map.get("remark"))) {
                hfComTask.setRemark(map.get("remark"));
            }
            hfComTaskMapper.updateById(hfComTask);
            return true;
        } else {
            return false;
        }
    }
}
