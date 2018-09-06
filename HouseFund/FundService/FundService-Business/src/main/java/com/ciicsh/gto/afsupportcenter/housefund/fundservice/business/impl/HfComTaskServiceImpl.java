package com.ciicsh.gto.afsupportcenter.housefund.fundservice.business.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.ciicsh.gto.afsupportcenter.housefund.fundservice.bo.HfComAccountPaymentWayBo;
import com.ciicsh.gto.afsupportcenter.housefund.fundservice.bo.HfComTaskBo;
import com.ciicsh.gto.afsupportcenter.housefund.fundservice.bo.HfComTaskEndTypeBo;
import com.ciicsh.gto.afsupportcenter.housefund.fundservice.bo.HfComTaskTaskStatusBo;
import com.ciicsh.gto.afsupportcenter.housefund.fundservice.business.HfComTaskService;
import com.ciicsh.gto.afsupportcenter.housefund.fundservice.dao.*;
import com.ciicsh.gto.afsupportcenter.housefund.fundservice.entity.HfAccountComRelation;
import com.ciicsh.gto.afsupportcenter.housefund.fundservice.entity.HfComAccount;
import com.ciicsh.gto.afsupportcenter.housefund.fundservice.entity.HfComAccountClass;
import com.ciicsh.gto.afsupportcenter.housefund.fundservice.entity.HfComTask;
import com.ciicsh.gto.afsupportcenter.util.interceptor.authenticate.UserContext;
import com.ciicsh.gto.afsupportcenter.util.logService.LogApiUtil;
import com.ciicsh.gto.afsupportcenter.util.logService.LogMessage;
import com.ciicsh.gto.afsupportcenter.util.page.PageInfo;
import com.ciicsh.gto.afsupportcenter.util.page.PageKit;
import com.ciicsh.gto.afsupportcenter.util.page.PageRows;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * <p>
 * 企业任务单总表：有关于企业所有任务单的表单字段都集中记录当前表
 * Com：公司简写 服务实现类
 * </p>
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

    //企业任务单状态 批退
    final Integer HF_COM_TASK_TASK_STATUS_4 = 4;
    final Integer HF_COM_TASK_TASK_STATUS_3 = 3;
    final Integer HF_COM_ACCOUNT_STATE_1 = 1;
    final Integer HF_COM_ACCOUNT_STATE_0 = 0;
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
    @Autowired
    private HfComTaskEndTypeMapper hfComTaskEndTypeMapper;
    @Autowired
    private LogApiUtil logApiUtil;
    /**
     * 保存企业任务单
     *
     * @param hfComTask
     * @return
     */
    @Override
    public Integer addComTask(HfComTask hfComTask) {
        //return baseMapper.insertComTask(hfComTask);
        return baseMapper.insert(hfComTask);
    }

    /**
     * 判断企业任务单是否存在
     *
     * @return
     */
    @Override
    public Integer isExistComTask(String companyId, Integer hfType, Integer taskCategory) {
        return baseMapper.isExistComTask(companyId, hfType, taskCategory);
    }

    /**
     * 获得企业任务单 未处理
     *
     * @param pageInfo
     * @return
     */
    @Override
    public PageRows<HfComTaskBo> queryCompanyTasks(PageInfo pageInfo) {
        //将PageInfo对象转DTO对象
        HfComTaskBo hfComTaskBo = pageInfo.toJavaObject(HfComTaskBo.class);
        if ("0".equals(hfComTaskBo.getTaskStatusString()) || "4".equals(hfComTaskBo.getTaskStatusString())) { //任务单状态：未处理 、批退
            return PageKit.doSelectPage(pageInfo, () -> hfComTaskMapper.queryCompanyTask(hfComTaskBo));
        } else { //否则就是：处理中 完成 批退
            return PageKit.doSelectPage(pageInfo, () -> hfComTaskMapper.queryCompanyTaskProcessing(hfComTaskBo));
        }

    }

    /**
     * 获得企业任务单列表
     *
     * @param hfComTaskBo
     * @return
     */
    @Override
    public List<HfComTaskBo> getCompanyTasks(HfComTaskBo hfComTaskBo) {
        return hfComTaskMapper.queryCompanyTask(hfComTaskBo);
    }

    /**
     * 获得企业任务单支付方式数据
     *
     * @return
     */
    @Override
    public List<HfComAccountPaymentWayBo> queryComTaskPaymentWayData() {
        return hfComAccountPaymentWayMapper.selectAllComTaskPaymentWayData();
    }

    /**
     * 获得企业任务单任务状态数据
     *
     * @return
     */
    @Override
    public List<HfComTaskTaskStatusBo> queryComTaskTaskStatusData() {
        return hfComTaskTaskStatusMapper.selectAllComTaskTaskStatusData();
    }

    /**
     * 获得企业任务单终止类型数据
     *
     * @return
     */
    @Override
    public List<HfComTaskEndTypeBo> queryComTaskEndTypeData() {
        return hfComTaskEndTypeMapper.selectAllComTaskEndTypeData();
    }

    /**
     * 添加/更新企业任务单及相关表单
     *
     * @param map
     * @return
     */
    @Override
    public boolean upsertCompanyTaskRelated(Map<String, String> map) {
        if (!StringUtils.isNotBlank(map.get("comTaskId"))) {
            return false;
        }
        if (!StringUtils.isNotBlank(map.get("companyId"))) {
            return false;
        }
        //取得企业任务单
        HfComTask hfComTask = hfComTaskMapper.selectById(map.get("comTaskId"));
        hfComTask.setTaskStatus(Integer.parseInt(map.get("taskStatus")));

        //获取公司是否已绑定企业账号
        Map<String, Object> mapCom = new HashMap();
        mapCom.put("company_id", map.get("companyId"));
        mapCom.put("major_com", "1");
        List<HfAccountComRelation> listRelation = hfAccountComRelationMapper.selectByMap(mapCom);
        if (listRelation.size() > 0) {
            map.put("comAccountId", String.valueOf(listRelation.get(0).getComAccountId()));
            //获取企业账户Class子表的ID
            Map<String, Object> mapClass = new HashMap();
            mapClass.put("com_account_id",map.get("comAccountId"));
            mapClass.put("hf_type",hfComTask.getTaskStatus());
            mapClass.put("is_active","1");
            List<HfComAccountClass> listAcccountClass = hfComAccountClassMapper.selectByMap(mapClass);
            if(listAcccountClass.size() > 0){
                map.put("comAccountClassId",listAcccountClass.get(0).getComAccountClassId().toString());
            }
        }

        Integer ifHasDealAccount = hfAccountComRelationMapper.queryOperAccountByComId(map.get("companyId"));
        if (ifHasDealAccount > 0) {
            map.put("ifHasDealAccount", "1");//已开户标记 1表示已开户
        }


        //设置企业公积金账号主表
        HfComAccount hfComAccount = new HfComAccount();
        this.updateComAccount(map, hfComAccount, hfComTask);

        //设置企业公积金账号从表
        HfComAccountClass hfComAccountClass = new HfComAccountClass();
        this.updateComAccountClass(map, hfComAccount, hfComAccountClass, hfComTask);

        //设置企业公积金账户客户关系表
        HfAccountComRelation hfAccountComRelation = new HfAccountComRelation();
        this.updateComAccountRelation(map, hfComAccount, hfAccountComRelation, hfComTask);


        //更新ComTask表
        hfComTask.setComAccountId(hfComAccount.getComAccountId());
        hfComTask.setComAccountClassId(hfComAccountClass.getComAccountClassId());
        if (StringUtils.isNotBlank(map.get("taskStatus"))) {
            hfComTask.setTaskStatus(Integer.parseInt(map.get("taskStatus")));
        }
        if (StringUtils.isNotBlank(map.get("paymentWay"))) {
            hfComTask.setPaymentWay(Integer.parseInt(map.get("paymentWay")));
        }
        if (StringUtils.isNotBlank(map.get("acceptDate"))) {
            try {
                hfComTask.setStrartHandleDate(new SimpleDateFormat(DATA_FORMAT_STRING).parse(map.get("acceptDate")));
            } catch (Exception e) {
                logApiUtil.error(LogMessage.create().setTitle("HfComTaskServiceImpl#upsertCompanyTaskRelated").setContent(e.getMessage()));
            }
        }else {
            hfComTask.setStrartHandleDate(null);
        }
        if (StringUtils.isNotBlank(map.get("approvalDate"))) {
            try {
                hfComTask.setSendCheckDate(new SimpleDateFormat(DATA_FORMAT_STRING).parse(map.get("approvalDate")));
            } catch (Exception e) {
                logApiUtil.error(LogMessage.create().setTitle("HfComTaskServiceImpl#upsertCompanyTaskRelated").setContent(e.getMessage()));
            }
        }else {
            hfComTask.setSendCheckDate(null);
        }
        if (StringUtils.isNotBlank(map.get("finishDate"))) {
            try {
                hfComTask.setFinishDate(new SimpleDateFormat(DATA_FORMAT_STRING).parse(map.get("finishDate")));
            } catch (Exception e) {
                logApiUtil.error(LogMessage.create().setTitle("HfComTaskServiceImpl#upsertCompanyTaskRelated").setContent(e.getMessage()));
            }
        }else{
            hfComTask.setFinishDate(null);
        }
        hfComTaskMapper.updateAllColumnById(hfComTask);
        return true;

    }

    /**
     * 变更企业任务单
     *
     * @param map
     * @return
     */
    @Override
    public boolean upsertCompanyTask(Map<String, String> map) {
        if (!StringUtils.isNotBlank(map.get("comTaskId"))) {
            return false;
        }
        //取得企业任务单
        HfComTask hfComTask = hfComTaskMapper.selectById(map.get("comTaskId"));
        HfComAccount hfComAccount = new HfComAccount();
        if (hfComTask.getTaskStatus() == HF_COM_TASK_TASK_STATUS_3) { //已完成
            hfComAccount.setComAccountId(Long.valueOf(map.get("comAccountId")));
            if (StringUtils.isNotBlank(map.get("comAccountName"))) {
                hfComAccount.setComAccountName(map.get("comAccountName"));
            }
            if (StringUtils.isNotBlank(map.get("paymentType"))) {
                hfComAccount.setPaymentWay(Integer.parseInt(map.get("paymentType")));
            }
            hfComAccountMapper.updateById(hfComAccount);
            HfComAccountClass hfComAccountClass = new HfComAccountClass();
            this.updateComAccountClass(map, hfComAccount, hfComAccountClass, hfComTask);
        }
        hfComTask.setComAccountName(map.get("comAccountName"));
        if (StringUtils.isNotBlank(map.get("paymentType"))) {
            hfComTask.setPaymentWay(Integer.parseInt(map.get("paymentType")));
        }
        if (StringUtils.isNotBlank(map.get("taskStatus"))) {
            hfComTask.setTaskStatus(Integer.parseInt(map.get("taskStatus")));
        }
        if (StringUtils.isNotBlank(map.get("endMonth"))) {
            hfComTask.setEndMonth(map.get("endMonth"));
        }
        if (StringUtils.isNotBlank(map.get("endType"))) {
            hfComTask.setEndType(Integer.parseInt(map.get("endType")));
        }
        if (StringUtils.isNotBlank(map.get("acceptDate"))) {
            try {
                hfComTask.setStrartHandleDate(new SimpleDateFormat(DATA_FORMAT_STRING).parse(map.get("acceptDate")));
            } catch (Exception e) {
                logApiUtil.error(LogMessage.create().setTitle("HfComTaskServiceImpl#upsertCompanyTask").setContent(e.getMessage()));
//                e.printStackTrace();
            }
        }else {
            hfComTask.setStrartHandleDate(null);
        }
        if (StringUtils.isNotBlank(map.get("approvalDate"))) {
            try {
                hfComTask.setSendCheckDate(new SimpleDateFormat(DATA_FORMAT_STRING).parse(map.get("approvalDate")));
            } catch (Exception e) {
                logApiUtil.error(LogMessage.create().setTitle("HfComTaskServiceImpl#upsertCompanyTask").setContent(e.getMessage()));
//                e.printStackTrace();
            }
        }else{
            hfComTask.setSendCheckDate(null);
        }
        if (StringUtils.isNotBlank(map.get("finishDate"))) {
            try {
                hfComTask.setFinishDate(new SimpleDateFormat(DATA_FORMAT_STRING).parse(map.get("finishDate")));
            } catch (Exception e) {
                logApiUtil.error(LogMessage.create().setTitle("HfComTaskServiceImpl#upsertCompanyTask").setContent(e.getMessage()));
//                e.printStackTrace();
            }
        }else {
            hfComTask.setFinishDate(null);
        }

        hfComTask.setRemark(map.get("remark"));
        hfComTask.setActive(true);
        hfComTaskMapper.updateAllColumnById(hfComTask);
        return true;

    }

    /**
     * 终止企业任务单
     *
     * @param map
     * @return
     */
    @Override
    public boolean stopCompAccountTask(Map<String, String> map) {
        if (!StringUtils.isNotBlank(map.get("comTaskId"))) {
            return false;
        }
        //取得企业任务单
        HfComTask hfComTask = hfComTaskMapper.selectById(map.get("comTaskId"));
        HfComAccount hfComAccount = new HfComAccount();
        if (hfComTask.getTaskStatus() == HF_COM_TASK_TASK_STATUS_3) { //已完成
            if (hfComTask.getHfType() == 1) {//只有基本公积金 才可以设置成无效
                hfComAccount.setState(HF_COM_ACCOUNT_STATE_0);  //设置初始无效
                hfComAccount.setComAccountId(Long.valueOf(map.get("comAccountId")));
                hfComAccountMapper.updateById(hfComAccount);
            }
            HfComAccountClass hfComAccountClass = new HfComAccountClass();
            this.updateComAccountClass(map, hfComAccount, hfComAccountClass, hfComTask);
        }
        //更新企业任务单
        if (StringUtils.isNotBlank(map.get("taskStatus"))) {
            hfComTask.setTaskStatus(Integer.parseInt(map.get("taskStatus")));
        }
        hfComTask.setEndMonth(map.get("endMonth"));
        if (StringUtils.isNotBlank(map.get("endType"))) {
            hfComTask.setEndType(Integer.parseInt(map.get("endType")));
        }
        if (StringUtils.isNotBlank(map.get("acceptDate"))) {
            try {
                hfComTask.setStrartHandleDate(new SimpleDateFormat(DATA_FORMAT_STRING).parse(map.get("acceptDate")));
            } catch (Exception e) {
                logApiUtil.error(LogMessage.create().setTitle("HfComTaskServiceImpl#stopCompAccountTask").setContent(e.getMessage()));
//                e.printStackTrace();
            }
        }else {
            hfComTask.setStrartHandleDate(null);
        }
        if (StringUtils.isNotBlank(map.get("approvalDate"))) {
            try {
                hfComTask.setSendCheckDate(new SimpleDateFormat(DATA_FORMAT_STRING).parse(map.get("approvalDate")));
            } catch (Exception e) {
                logApiUtil.error(LogMessage.create().setTitle("HfComTaskServiceImpl#stopCompAccountTask").setContent(e.getMessage()));
//                e.printStackTrace();
            }
        }else {
            hfComTask.setSendCheckDate(null);
        }
        if (StringUtils.isNotBlank(map.get("finishDate"))) {
            try {
                hfComTask.setFinishDate(new SimpleDateFormat(DATA_FORMAT_STRING).parse(map.get("finishDate")));
            } catch (Exception e) {
                logApiUtil.error(LogMessage.create().setTitle("HfComTaskServiceImpl#stopCompAccountTask").setContent(e.getMessage()));
//                e.printStackTrace();
            }
        }else{
            hfComTask.setFinishDate(null);
        }
        hfComTask.setRemark(map.get("remark"));
        hfComTaskMapper.updateAllColumnById(hfComTask);
        return true;
    }

    /**
     * 批退企业任务
     *
     * @param map
     * @return
     */
    @Override
    public boolean rejection(Map<String, String> map) {
        HfComTask hfComTask = new HfComTask();
        hfComTask.setComTaskId(Long.valueOf(map.get("comTaskId")));
        hfComTask.setRemark(map.get("remark"));
        hfComTask.setTaskStatus(HF_COM_TASK_TASK_STATUS_4);
        hfComTaskMapper.updateById(hfComTask);
        return false;
    }

    private void updateComAccountClass(Map<String, String> map, HfComAccount hfComAccount, HfComAccountClass hfComAccountClass, HfComTask hfComTask) {

        if (Optional.ofNullable(map.get("comAccountClassId")).isPresent()) {
            HfComAccountClass hfComAccountClass1=hfComAccountClassMapper.selectById(map.get("comAccountClassId"));
            BeanUtils.copyProperties(hfComAccountClass1,hfComAccountClass);
        }
        hfComAccountClass.setComAccountId(hfComAccount.getComAccountId());
        hfComAccountClass.setHfType(hfComTask.getHfType());
        hfComAccountClass.setHfComAccount(map.get("comAccountNum"));
        if (StringUtils.isNotBlank(map.get("comStartMonth"))) {
            String yearMonthString = map.get("comStartMonth");
            hfComAccountClass.setComStartMonth(yearMonthString);
            hfComAccountClass.setComHfMonth(yearMonthString);
        }else {
            hfComAccountClass.setComStartMonth(null);
            hfComAccountClass.setComHfMonth(null);
        }
        hfComAccountClass.setEndMonth(hfComTask.getEndMonth());
        if (StringUtils.isNotBlank(map.get("operateStartMonth"))) {
            String yearMonthString = map.get("operateStartMonth");
            hfComAccountClass.setOperateStartMonth(yearMonthString);
        }else{
            hfComAccountClass.setOperateStartMonth(null);
        }
        if (StringUtils.isNotBlank(map.get("accountTempStore"))) {
            hfComAccountClass.setAccountTempStore(Integer.parseInt(map.get("accountTempStore")));
        }else{
            hfComAccountClass.setAccountTempStore(null);
        }
        if (StringUtils.isNotBlank(map.get("endType"))) {
            hfComAccountClass.setEndType(Integer.parseInt(map.get("endType")));
        }else{
            hfComAccountClass.setEndType(null);
        }
        if (HF_COM_TASK_TASK_STATUS_3.equals(hfComTask.getTaskStatus())) { //已完成
            hfComAccountClass.setActive(true);  //设置有效
        } else {
            hfComAccountClass.setActive(false);  //设置初始无效
        }
        hfComAccountClass.setActive(true);
        hfComAccountClass.setCreatedTime(new Date());
        hfComAccountClass.setCreatedBy(UserContext.getUser().getDisplayName());
        hfComAccountClass.setModifiedBy(UserContext.getUser().getDisplayName());
        hfComAccountClass.setModifiedTime(new Date());
        if (Optional.ofNullable(map.get("comAccountClassId")).isPresent()) {
            hfComAccountClass.setComAccountClassId(Long.valueOf(map.get("comAccountClassId")));
            hfComAccountClassMapper.updateAllColumnById(hfComAccountClass);
        } else {
            hfComAccountClassMapper.insert(hfComAccountClass);
        }
    }

    private void updateComAccount(Map<String, String> map, HfComAccount hfComAccount, HfComTask hfComTask) {
        if (Optional.ofNullable(map.get("comAccountId")).isPresent()) {
            HfComAccount  hfComAccount1 = hfComAccountMapper.selectById(map.get("comAccountId"));
            BeanUtils.copyProperties(hfComAccount1,hfComAccount);
        }

        hfComAccount.setComAccountName(map.get("comAccountName"));
        if (StringUtils.isNotBlank(map.get("paymentWay"))) {
            hfComAccount.setPaymentWay(Integer.parseInt(map.get("paymentWay")));
        }else{
            hfComAccount.setPaymentWay(null);
        }
        hfComAccount.setHfAccountType(HF_COM_ACCOUNT_TYPE_INDEPEDENT);//独立户
        if (StringUtils.isNotBlank(map.get("closeDay"))) {
            hfComAccount.setCloseDay(Integer.parseInt(map.get("closeDay")));
        }else{
            hfComAccount.setCloseDay(null);
        }
        if (StringUtils.isNotBlank(map.get("uKeyStore"))) {
            hfComAccount.setUkeyStore(Integer.parseInt(map.get("uKeyStore")));
        }else{
            hfComAccount.setUkeyStore(null);
        }
        if (StringUtils.isNotBlank(map.get("paymentBank"))) {
            hfComAccount.setPaymentBank(Integer.parseInt(map.get("paymentBank")));
        }else{
            hfComAccount.setPaymentBank(null);
        }
        if (StringUtils.isNotBlank(map.get("comAccountRemark"))) {
            hfComAccount.setRemark(map.get("comAccountRemark").toString());
        }
        hfComAccount.setActive(true);
        hfComAccount.setCreatedTime(new Date());
        hfComAccount.setCreatedDisplayName(UserContext.getUser().getDisplayName());
        hfComAccount.setModifiedDisplayName(UserContext.getUser().getDisplayName());
        hfComAccount.setCreatedBy(UserContext.getUserId());
        hfComAccount.setModifiedBy(UserContext.getUserId());
        hfComAccount.setModifiedTime(new Date());

        String ifHasDealAccount = Optional.ofNullable(map.get("ifHasDealAccount")).orElse("0");
        if ("0".equals(ifHasDealAccount)) {
            if (hfComTask.getTaskStatus() == HF_COM_TASK_TASK_STATUS_3) { //已完成
                hfComAccount.setState(HF_COM_ACCOUNT_STATE_1);  //设置有效
            } else {
                hfComAccount.setState(HF_COM_ACCOUNT_STATE_0);  //设置初始无效
            }
        }

        if (Optional.ofNullable(map.get("comAccountId")).isPresent()) {
            hfComAccount.setComAccountId(Long.valueOf(map.get("comAccountId")));
            hfComAccountMapper.updateAllColumnById(hfComAccount);
        } else {
            hfComAccountMapper.insert(hfComAccount);
        }
    }

    private void updateComAccountRelation(Map<String, String> map, HfComAccount hfComAccount, HfAccountComRelation hfAccountComRelation, HfComTask hfComTask) {
        hfAccountComRelation.setComAccountId(hfComAccount.getComAccountId());
        hfAccountComRelation.setCompanyId(hfComTask.getCompanyId());
        hfAccountComRelation.setMajorCom(1);
        int ifComAccountIdExists = hfAccountComRelationMapper.queryIfComAccountIdExists(hfComAccount.getComAccountId());
        if (ifComAccountIdExists == 0) {
            hfAccountComRelation.setCreatedTime(new Date());
            hfAccountComRelation.setCreatedBy(UserContext.getUser().getDisplayName());
            hfAccountComRelation.setModifiedBy(UserContext.getUser().getDisplayName());
            hfAccountComRelation.setModifiedTime(new Date());
            hfAccountComRelationMapper.insert(hfAccountComRelation);
        } else {
            hfAccountComRelation.setModifiedBy(UserContext.getUser().getDisplayName());
            hfAccountComRelation.setModifiedTime(new Date());
            hfAccountComRelationMapper.updateById(hfAccountComRelation);
        }
    }

}
