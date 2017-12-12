package com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.host.controller;

import com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.business.ISsAccountRatioService;
import com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.business.ISsComAccountService;
import com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.business.ISsComTaskService;
import com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.dto.SsComTaskDTO;
import com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.entity.SsAccountRatio;
import com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.entity.SsComAccount;
import com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.entity.SsComTask;
import com.ciicsh.gto.afsupportcenter.util.aspect.log.Log;
import com.ciicsh.gto.afsupportcenter.util.page.PageInfo;
import com.ciicsh.gto.afsupportcenter.util.page.PageRows;
import com.ciicsh.gto.afsupportcenter.util.web.controller.BasicController;
import com.ciicsh.gto.afsupportcenter.util.web.response.JsonResult;
import com.ciicsh.gto.afsupportcenter.util.web.response.JsonResultKit;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 独立库客户任务单 前端控制器
 * </p>
 *
 * @author HuangXing
 * @since 2017-12-01
 */
@RestController
@RequestMapping("/api/soccommandservice/ssComTask")
public class SsComTaskController extends BasicController<ISsComTaskService> {
    @Autowired
    private ISsAccountRatioService iSsAccountRatioService;
    @Autowired
    private ISsComAccountService iSsComAccountService;
    @Log("查询未处理企业任务单")
    @RequestMapping(value = "getNoProgressTask")
    public JsonResult<List<SsComTaskDTO>> getNoProgressCompanyTask(PageInfo pageInfo) {
        //mybatis 分页插件
        PageRows<SsComTaskDTO> pageRows = business.queryNoProgressCompanyTask(pageInfo);
        return JsonResultKit.ofPage(pageRows);
    }

    @Log("查询处理中企业任务单")
    @RequestMapping(value = "getProgressingTask")
    public JsonResult<List<SsComTaskDTO>> getNoProgressingCompanyTask(PageInfo pageInfo) {
        //mybatis 分页插件
        PageRows<SsComTaskDTO> pageRows = business.queryProgressingCompanyTask(pageInfo);
        return JsonResultKit.ofPage(pageRows);
    }

    @Log("查询已完成企业任务单")
    @RequestMapping(value = "getFinshedTask")
    public JsonResult<List<SsComTaskDTO>> getFinshedCompanyTask(PageInfo pageInfo) {
        //mybatis 分页插件
        PageRows<SsComTaskDTO> pageRows = business.queryFinshedCompanyTask(pageInfo);
        return JsonResultKit.ofPage(pageRows);
    }

    @Log("查询批退企业任务单")
    @RequestMapping(value = "getRefusedTask")
    public JsonResult<List<SsComTaskDTO>> getRefusedCompanyTask(PageInfo pageInfo) {
        //mybatis 分页插件
        PageRows<SsComTaskDTO> pageRows = business.queryRefusedCompanyTask(pageInfo);
        return JsonResultKit.ofPage(pageRows);
    }

    @Log("批退任务单")
    @RequestMapping(value = "refusingTask")
    public JsonResult<Boolean> refusingTask(@RequestParam(value = "taskIdStr", required = true) String taskIdStr,
                                            @RequestParam(value = "refuseReason", required = true) String refuseReason) {
        //mybatis 使用mybatis 的自带的批量修改
        List<SsComTask> dataList = new ArrayList<SsComTask>();
        //将前台传过来的参数解析成 任务单对象的数组
        if (StringUtils.isNotBlank(taskIdStr)) {
            String[] taskIdArr = taskIdStr.split(",");
            for (int i = 0; i < taskIdArr.length; i++) {
                SsComTask ssComTask = new SsComTask();
                //批退的任务单ID
                ssComTask.setCompanyTaskId(Long.parseLong(taskIdArr[i]));
                //批退状态 4
                ssComTask.setTaskStatus(4);
                //批退原因
                if (StringUtils.isNotBlank(refuseReason))
                    ssComTask.setRejectionRemark(refuseReason);
                dataList.add(ssComTask);
            }
        }
        boolean result = business.updateBatchById(dataList);
        JsonResult<Boolean> returnResult = JsonResultKit.of(result);
        return returnResult;
    }

    @Log("获得企业信息和材料收缴信息")
    @RequestMapping(value = "getCompanyInfoAndMaterial")
    public JsonResult<SsComTaskDTO> getCompanyInfoAndMaterial(SsComTaskDTO ssComTaskDTO) {
        if (null != ssComTaskDTO.getCompanyTaskId()) {
            if(StringUtils.isNotBlank(ssComTaskDTO.getOperatorType())){
                //1 开户 2 转移 3 变更 4 终止
                if("1".equals(ssComTaskDTO.getOperatorType())){
                    //开户时 需要查询企业信息(企业信息和材料)
                    SsComTaskDTO ssComTaskDTORes = business.queryComInfoAndMaterial(ssComTaskDTO);
                    return  JsonResultKit.of(ssComTaskDTORes);
                }else{
                    //变更 转移 终止时 需要查询账户信息(账户信息和材料)
                    SsComTaskDTO ssComTaskDTORes = business.queryAccountInfoAndMaterial(ssComTaskDTO);
                    return  JsonResultKit.of(ssComTaskDTORes);
                }
            }else return JsonResultKit.ofError("操作类型为空");
            //前台传ID 为空 返回空
        } else return JsonResultKit.ofError("任务ID为空");
    }

    @Log("查询企业信息和前道传过来的JSON（包含社保截止和付款方式）")
    @RequestMapping(value = "getComInfoAndPayWay")
    public JsonResult<SsComTaskDTO> queryComInfoAndPayWay(SsComTask ssComTask) {
        SsComTaskDTO ssComTaskDTO = business.queryComInfoAndPayWay(ssComTask);
        return  JsonResultKit.of(ssComTaskDTO);
    }

    @Log("添加或者修改企业任务单")
    @RequestMapping(value = "addOrUpdateCompanyTask")
    public JsonResult<Boolean> addOrUpdateCompanyTask(@RequestParam Map<String,String> map) {
        //获得社保账户信息
        SsComAccount ssComAccount = getSsComAccount(map);
        //获得企业任务单信息
        SsComTask ssComTask =  getSsComTask(map);
        //获得工伤变更信息
        SsAccountRatio ssAccountRatio= getSsAccountRatio(map);
        if(3==ssComTask.getTaskStatus()){
            //任务单为已完成状态 账户设置为可用
            ssComAccount.setState(new Integer(1));
        }else{
            //任务单 为初始，受理， 送审  账户为初始状态
            ssComAccount.setState(new Integer(0));
        }
        boolean result = business.addOrUpdateCompanyTask(ssComTask,ssComAccount,ssAccountRatio);
        return  JsonResultKit.of(result);
    }



    public SsComAccount  getSsComAccount(Map<String,String> map){

        SsComAccount ssComAccount = new SsComAccount();
        //设置账户ID
        ssComAccount.setComAccountId(isNotNull(map.get("comAccountId"))?Long.parseLong(map.get("comAccountId")):null);
        //参保户登记码
        ssComAccount.setSsAccount(isNotNull(map.get("ssAccount"))?map.get("ssAccount"):null);
        //卡号
        ssComAccount.setBankAccount(isNotNull(map.get("bankAccount"))?map.get("bankAccount"):null);
        //养老金账户公司名称
        ssComAccount.setComAccountName(isNotNull(map.get("comAccountName"))?map.get("comAccountName"):null);
        //结算区县
        ssComAccount.setSettlementArea(isNotNull(map.get("settlementArea"))?map.get("settlementArea"):null);
        //付款银行
        ssComAccount.setPaymentBank(isNotNull(map.get("paymentBank"))?map.get("paymentBank"):null);
        //付款方式
        ssComAccount.setPaymentWay(isNotNull(map.get("paymentWay"))?Integer.valueOf(map.get("paymentWay")):null);
        //用户名
        ssComAccount.setSsUsername(isNotNull(map.get("ssUsername"))?map.get("ssUsername"):null);
        //密码
        ssComAccount.setSsPwd(isNotNull(map.get("ssPwd"))?map.get("ssPwd"):null);
        //初始余额
        ssComAccount.setInitialBalance(isNotNull(map.get("initialBalance"))?new BigDecimal(map.get("initialBalance")):null);
        //初期欠费
        ssComAccount.setInitialDebt(isNotNull(map.get("initialDebt"))?new BigDecimal(map.get("initialDebt")):null);
        //来源地
        ssComAccount.setOriginPlace(isNotNull(map.get("originPlace"))?Integer.valueOf(map.get("originPlace")):null);
        //来源地备注
        ssComAccount.setOriginPlaceRemark(isNotNull(map.get("originPlaceRemark"))?map.get("originPlaceRemark"):null);
        //查询账户
        ssComAccount.setQueryAccount(isNotNull(map.get("queryAccount"))?map.get("queryAccount"):null);
        //交予方式
        ssComAccount.setDeliverWay(isNotNull(map.get("deliverWay"))?map.get("deliverWay"):null);
        //交予方式备注
        ssComAccount.setDeliverWayRemark(isNotNull(map.get("deliverWayRemark"))?map.get("deliverWayRemark"):null);
        //字符串转LocalDate 格式
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        //给凭证时间
        ssComAccount.setProvideCertificateTime(isNotNull(map.get("provideCertificateTime"))? LocalDate.parse(map.get("provideCertificateTime"), dateFormatter):null);
        //变更时间
        ssComAccount.setChangeTime(isNotNull(map.get("changeTime"))? LocalDateTime.parse(map.get("changeTime"),timeFormatter):null);
        //收到日期
        ssComAccount.setReceiveDate(isNotNull(map.get("receiveDate"))? LocalDate.parse(map.get("receiveDate"),dateFormatter):null);
        //转入日期
        ssComAccount.setIntoDate(isNotNull(map.get("intoDate"))? LocalDate.parse(map.get("intoDate"),dateFormatter):null);
        //发出材料
        ssComAccount.setDispatchMaterial(isNotNull(map.get("dispatchMaterial"))?map.get("dispatchMaterial"):null);
        //设置每个月截止时间
        ssComAccount.setExpireDate(isNotNull(map.get("expireDate"))?Integer.valueOf(map.get("expireDate")):null);
        //默认独立户
        ssComAccount.setSsAccountType(new Integer(3));
        ssComAccount.setModifiedBy("xsj");
        ssComAccount.setModifiedTime(LocalDateTime.now());

        return ssComAccount;
    }
    //获得企业任务单对象
    public SsComTask  getSsComTask(Map<String,String> map){
        SsComTask ssComTask = new SsComTask();
        //企业任务单id
        ssComTask.setCompanyTaskId(isNotNull(map.get("companyTaskId"))?Long.parseLong(map.get("companyTaskId")):null);
        //任务单类型
        ssComTask.setTaskCategory(isNotNull(map.get("taskCategory"))?map.get("taskCategory"):null);
        //前道传过来的截止日期和支付方式
        ssComTask.setTaskFormContent(isNotNull(map.get("taskFormContent"))?map.get("taskFormContent"):null);
        //任务单状态
        ssComTask.setTaskStatus(isNotNull(map.get("taskStatus"))?Integer.valueOf(map.get("taskStatus")):null);
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        //任务受理时间
        ssComTask.setStartHandleDate(isNotNull(map.get("startHandleDate"))?LocalDate.parse(map.get("startHandleDate"),dateFormatter):null);
        //任务送审时间
        ssComTask.setSendCheckDate(isNotNull(map.get("sendCheckDate"))?LocalDate.parse(map.get("sendCheckDate"),dateFormatter):null);
        //任务完成时间
        ssComTask.setSendCheckDate(isNotNull(map.get("finishDate"))?LocalDate.parse(map.get("finishDate"),dateFormatter):null);
        //办理备注
        ssComTask.setHandleRemark(isNotNull(map.get("handleRemark"))?map.get("handleRemark"):null);
        ssComTask.setModifiedBy("xsj");
        ssComTask.setModifiedTime(LocalDateTime.now());
        return ssComTask;
    }

    //获得工伤比例信息
    private SsAccountRatio getSsAccountRatio(Map<String,String> map){

        SsAccountRatio ssAccountRatio = new SsAccountRatio();
        ssAccountRatio.setSsAccountRatioId(isNotNull(map.get("ssAccountRatioId"))?Long.valueOf(map.get("ssAccountRatioId")):null);
        //行业类别
        ssAccountRatio.setIndustryCategory(isNotNull(map.get("industryCategory"))?map.get("industryCategory"):null);
        //企业工伤比例
        ssAccountRatio.setComRatio(isNotNull(map.get("comRatio"))?new BigDecimal(map.get("comRatio")).setScale(4, BigDecimal.ROUND_HALF_UP):null);
        //开始月份
        ssAccountRatio.setStartMonth(isNotNull(map.get("startMonth"))?map.get("startMonth"):null);


        ssAccountRatio.setModifiedBy("xsj");
        ssAccountRatio.setModifiedTime(LocalDateTime.now());
        return ssAccountRatio;
    }

    //是否不为空
    public boolean isNotNull(String charecter){
        return StringUtils.isNotBlank(charecter);
    }
}


