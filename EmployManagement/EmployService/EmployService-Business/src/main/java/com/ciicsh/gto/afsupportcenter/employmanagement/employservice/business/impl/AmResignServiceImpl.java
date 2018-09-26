package com.ciicsh.gto.afsupportcenter.employmanagement.employservice.business.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.ciicsh.gto.afsupportcenter.employmanagement.employservice.api.dto.TerminateDTO;
import com.ciicsh.gto.afsupportcenter.employmanagement.employservice.bo.*;
import com.ciicsh.gto.afsupportcenter.employmanagement.employservice.business.*;
import com.ciicsh.gto.afsupportcenter.employmanagement.employservice.business.utils.CommonApiUtils;
import com.ciicsh.gto.afsupportcenter.employmanagement.employservice.business.utils.ReasonUtil;
import com.ciicsh.gto.afsupportcenter.employmanagement.employservice.business.utils.TaskCommonUtils;
import com.ciicsh.gto.afsupportcenter.employmanagement.employservice.custom.resignSearchExportOpt;
import com.ciicsh.gto.afsupportcenter.employmanagement.employservice.dao.AmArchiveMapper;
import com.ciicsh.gto.afsupportcenter.employmanagement.employservice.dao.AmEmpTaskMapper;
import com.ciicsh.gto.afsupportcenter.employmanagement.employservice.dao.AmResignMapper;
import com.ciicsh.gto.afsupportcenter.employmanagement.employservice.dto.AmEmpDispatchExportDTO;
import com.ciicsh.gto.afsupportcenter.employmanagement.employservice.dto.AmEmpDispatchExportPageDTO;
import com.ciicsh.gto.afsupportcenter.employmanagement.employservice.dto.AmEmpExplainExportDTO;
import com.ciicsh.gto.afsupportcenter.employmanagement.employservice.dto.AmEmpExplainExportPageDTO;
import com.ciicsh.gto.afsupportcenter.employmanagement.employservice.entity.AmArchive;
import com.ciicsh.gto.afsupportcenter.employmanagement.employservice.entity.AmEmpTask;
import com.ciicsh.gto.afsupportcenter.employmanagement.employservice.entity.AmResign;
import com.ciicsh.gto.afsupportcenter.employmanagement.employservice.entity.AmResignLink;
import com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.api.SocApiProxy;
import com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.api.dto.SsComAccountDTO;
import com.ciicsh.gto.afsupportcenter.util.DateUtil;
import com.ciicsh.gto.afsupportcenter.util.StringUtil;
import com.ciicsh.gto.afsupportcenter.util.interceptor.authenticate.UserContext;
import com.ciicsh.gto.afsupportcenter.util.logService.LogApiUtil;
import com.ciicsh.gto.afsupportcenter.util.logService.LogMessage;
import com.ciicsh.gto.afsupportcenter.util.page.PageInfo;
import com.ciicsh.gto.afsupportcenter.util.page.PageKit;
import com.ciicsh.gto.afsupportcenter.util.page.PageRows;
import com.ciicsh.gto.salecenter.apiservice.api.dto.company.AfCompanyDetailResponseDTO;
import com.ciicsh.gto.salecenter.apiservice.api.proxy.CompanyProxy;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 用工表 服务实现类
 * </p>
 */
@Service
public class AmResignServiceImpl extends ServiceImpl<AmResignMapper, AmResign> implements IAmResignService {

    @Autowired
    private IAmEmpTaskService taskService;

    @Autowired
    private AmResignLinkService amResignLinkService;

    @Autowired
    private CommonApiUtils employeeInfoProxy;

    @Autowired
    private IAmEmploymentService amEmploymentService;

    @Autowired
    private LogApiUtil logApiUtil;

    @Autowired
    private AmArchiveMapper amArchiveMapper;

    @Autowired
    private AmEmpTaskMapper amEmpTaskMapper;

    @Autowired
    private SocApiProxy socApiProxy;

    @Autowired
    private CompanyProxy companyProxy;

    @Autowired
    private IAmRemarkService amRemarkService;

    public PageRows<AmResignBO> queryAmResign(PageInfo pageInfo){

        AmResignBO  amResignBO = pageInfo.toJavaObject(AmResignBO.class);

        List<String> param = new ArrayList<String>();
        List<String> orderParam = new ArrayList<String>();
        if(!StringUtil.isEmpty(amResignBO.getParams()))
        {
            String arr[] = amResignBO.getParams().split(",");
            for(int i=0;i<arr.length;i++) {
                if(arr[i].indexOf("desc")>0||arr[i].indexOf("asc")>0){
                    orderParam.add(arr[i]);
                }else {
                    param.add(arr[i]);
                }
            }
        }
        amResignBO.setParam(param);
        amResignBO.setOrderParam(orderParam);
        if(null!=amResignBO.getTaskStatus()&&amResignBO.getTaskStatus()==0){
            amResignBO.setTaskStatus(null);
        }

        if(null!=amResignBO.getTaskStatus()&&amResignBO.getTaskStatus()==6)
        {
            amResignBO.setTaskStatusOther(0);
        }
        return PageKit.doSelectPage(pageInfo,() -> baseMapper.queryAmResign(amResignBO));

    }

    @Override
    public List<AmResignBO> taskCount(PageInfo pageInfo) {
        AmResignBO amResignBO = pageInfo.toJavaObject(AmResignBO.class);
        List<String> param = new ArrayList<String>();

        if(!StringUtil.isEmpty(amResignBO.getParams()))
        {
            String arr[] = amResignBO.getParams().split(",");
            for(int i=0;i<arr.length;i++) {
                param.add(arr[i]);
            }
        }
        amResignBO.setParam(param);
        if(null!=amResignBO.getTaskStatus()&&amResignBO.getTaskStatus()==0){
            amResignBO.setTaskStatus(null);
        }
        if(null!=amResignBO.getTaskStatus()&&amResignBO.getTaskStatus()==6)
        {
            amResignBO.setTaskStatusOther(0);
        }

        return baseMapper.taskCount(amResignBO);
    }

    @Override
    public List<AmResignBO> queryAmResignDetail(Map<String,Object> param) {
        return  baseMapper.queryAmResignDetail(param);
    }

    @Override
    public List<resignSearchExportOpt> queryAmResignList(AmResignBO amResignBO) {
        return baseMapper.queryAmResignList(amResignBO);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public AmResign saveAmResign(AmResignBO bo) {
        AmResign entity = new AmResign();
        if(null!=bo.getIsReturn())
        {
            entity = super.selectById(bo.getResignId());
            entity.setReturnDocDate(bo.getReturnDocDate());
            entity.setReturnDocMan(bo.getReturnDocMan());
        }else{
            BeanUtils.copyProperties(bo,entity);
        }

        String userId = "sys";
        try {
            userId = UserContext.getUserId();
        } catch (Exception e) {

        }

        LocalDateTime now = LocalDateTime.now();
        if(entity.getResignId()==null){
            entity.setCreatedTime(now);
            entity.setModifiedTime(now);
            entity.setCreatedBy(userId);
            entity.setModifiedBy(userId);
            entity.setActive(true);
        }else{
            AmResign entity1 = this.selectById(entity.getResignId());
            entity.setCreatedBy(entity1.getCreatedBy());
            entity.setCreatedTime(entity1.getCreatedTime());
            entity.setActive(true);
            entity.setModifiedTime(now);
            entity.setModifiedBy(userId);
        }
        entity.setResignOperateMan(ReasonUtil.getUserName());

        Integer isFinish = 0;
        AmEmpTask amEmpTask = null;
        if(!StringUtil.isEmpty(bo.getResignFeedback()))
        {
            amEmpTask = taskService.selectById(bo.getEmpTaskId());
            /**
             * 退工任务单签收 但退工成功日期为空
             */
            if("1".equals(bo.getResignFeedback())&&bo.getJobCentreFeedbackDate()==null)
            {
                /**
                 * 13 15 代表无需退工(退工原因)，转其他城市缴纳按照辞职的原因来看待
                 */
                if("13".equals(amEmpTask.getOutReasonCode())||"15".equals(amEmpTask.getOutReasonCode()))
                {
                    amEmpTask.setTaskStatus(1);
                    isFinish = 1;
                }else{
                    amEmpTask.setTaskStatus(98);
                }
            }else if("1".equals(bo.getResignFeedback())&&bo.getJobCentreFeedbackDate()!=null){
                /**
                 * 更加v6文档有退工成功返回日期 并且 退工任务单签收 代表退工成功
                 */
                amEmpTask.setTaskStatus(1);
                isFinish = 1;
            }else {
                amEmpTask.setTaskStatus(Integer.parseInt(bo.getResignFeedback()));
            }

            if(isFinish==0)
            {
                isFinish = this.isResginFinish(bo,amEmpTask);
            }
            if(isFinish==1){
                amEmpTask.setFinish(true);
            }

            taskService.insertOrUpdate(amEmpTask);

            entity.setIsFinish(isFinish);

            AmResignLink amResignLink = new AmResignLink();
            amResignLink.setTaskId(amEmpTask.getTaskId());
            amResignLink.setResignFeedback(ReasonUtil.getTgfk(bo.getResignFeedback()));
            amResignLink.setJobCentreFeedbackDate(bo.getJobCentreFeedbackDate());
            amResignLink.setResignFeedbackDate(bo.getResignFeedbackDate());
            amResignLink.setCreatedTime(now);
            amResignLink.setModifiedTime(now);

            amResignLink.setCreatedBy(userId);
            amResignLink.setModifiedBy(userId);

            amResignLinkService.insert(amResignLink);
        }

        boolean result = super.insertOrUpdateAllColumn(entity);

        if(isFinish==1&&result)
        {
            Map<String,Object> variables = new HashMap<>();
            variables.put("status", true);
            variables.put("remark",ReasonUtil.getTgfk(bo.getResignFeedback()));
            String userName = "system";
            try {
                userName = UserContext.getUser().getDisplayName();
            } catch (Exception e) {

            }
            variables.put("assignee",userName);
            if(null!=amEmpTask&&!"是".equals(amEmpTask.getChangeCompany()))
            {
                variables.put("fire_material",true);
            }
            variables.put("empTaskId",bo.getEmpTaskId());
            try {
                TaskCommonUtils.completeTask(amEmpTask.getTaskId(),employeeInfoProxy,variables);
            } catch (Exception e) {
                LogMessage logMessage = LogMessage.create().setTitle("退工办理").setContent(e.getMessage());
                logApiUtil.info(logMessage);
            }
        }

        return  entity;
    }

    @Override
    public Boolean saveAmSend(AmPostBO amPostBO) {
        AmArchive archive = new AmArchive();
        archive.setPost(amPostBO.getPost());
        archive.setPostSaver(amPostBO.getPostSaver());
        Wrapper<AmArchive> wrapper = new EntityWrapper<>();
        wrapper.eq("employment_id",amPostBO.getEmploymentId());
        return amArchiveMapper.update(archive,wrapper)>0;
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public Map<String, Object> batchSaveResign(AmResignBO bo) {
        Map<String, Object> result = new HashMap<>();
        String userId = "sys";
        try {
            userId = UserContext.getUserId();
        } catch (Exception e) {

        }
        List<AmResignBO> list = baseMapper.queryResignIds(bo);

        Map<String, Object> param = new HashMap<>();
        LocalDateTime now = LocalDateTime.now();
        List<AmResign> amResignList = new ArrayList<>();
        Integer isFinish =0;
        List<AmEmpTask> amEmpTaskList = new ArrayList<>();
        for(AmResignBO temp:list)
        {
            AmResign entity = new AmResign();
            BeanUtils.copyProperties(bo,entity);
            if(temp.getResignId()==null){
                entity.setCreatedTime(now);
                entity.setModifiedTime(now);
                entity.setCreatedBy(userId);
                entity.setModifiedBy(userId);
                entity.setActive(true);
            }else{
                entity.setResignId(temp.getResignId());
            }
            entity.setResignOperateMan(ReasonUtil.getUserName());
            param.put("employeeId",temp.getEmployeeId());
            param.put("companyId",temp.getCompanyId());
            List<AmEmploymentBO> resultEmployList = amEmploymentService.queryAmEmploymentResign(param);
            if(resultEmployList==null||resultEmployList.size()==0)
            {
                result.put("message","用工没有办理");
                return  result;
            }
            //更新状态

            AmEmpTask amEmpTask = null;
            if(!StringUtil.isEmpty(bo.getResignFeedback()))
            {
                amEmpTask = taskService.selectById(temp.getEmpTaskId());
                /**
                 * 退工任务单签收 但退工成功日期为空
                 */
                if("1".equals(bo.getResignFeedback())&&bo.getJobCentreFeedbackDate()==null)
                {
                    /**
                     * 13 15 代表无需退工(退工原因)，转其他城市缴纳按照辞职的原因来看待
                     */
                    if("13".equals(amEmpTask.getOutReasonCode())||"15".equals(amEmpTask.getOutReasonCode()))
                    {
                        amEmpTask.setTaskStatus(1);
                        isFinish = 1;
                    }else{
                        amEmpTask.setTaskStatus(98);
                    }
                }else if("1".equals(bo.getResignFeedback())&&bo.getJobCentreFeedbackDate()!=null){
                    /**
                     * 更加v6文档有退工成功返回日期 并且 退工任务单签收 代表退工成功
                     */
                    amEmpTask.setTaskStatus(1);
                    isFinish = 1;
                }else {
                    amEmpTask.setTaskStatus(Integer.parseInt(bo.getResignFeedback()));
                }
                if(isFinish==0)
                {
                    isFinish = this.isResginFinish(bo,amEmpTask);
                }
                if(isFinish==1){
                    amEmpTask.setFinish(true);
                }
                taskService.insertOrUpdate(amEmpTask);
                amEmpTaskList.add(amEmpTask);
                entity.setIsFinish(isFinish);
                entity.setEmpTaskId(amEmpTask.getEmpTaskId());
            }
            //
            entity.setEmploymentId(resultEmployList.get(0).getEmploymentId());
            entity.setEmployeeId(temp.getEmployeeId());
            entity.setCompanyId(temp.getCompanyId());
            amResignList.add(entity);
            bo.setIsFinish(isFinish);
        }

        Boolean isInsert = super.insertOrUpdateBatch(amResignList);
        Boolean returnResult = (isFinish==1&&isInsert);

        result.put("result",returnResult);
        result.put("taskList",amEmpTaskList);
        return  result;
    }

    @Override
    public List<AmResignBO> queryResignIds(AmResignBO amResignBO) {
        return baseMapper.queryResignIds(amResignBO);
    }

    @Override
    public Map<String, Object> batchCheck(AmResignBO amResignBO) {
        Map<String,Object> resultMap = new HashMap<>();
        List<AmResignBO>  list =  baseMapper.queryResignIds(amResignBO);
        Integer empCount=0;
        Integer resignCount=0;
        for(AmResignBO temp:list)
        {
            if(temp.getIsFinish()==1){
                empCount++;
            }

            if(null!=temp.getResignId()){
                resignCount++;
            }
        }
        if(empCount>0){
            resultMap.put("empTask",empCount);
            return  resultMap;
        }
        if(resignCount>0){
            resultMap.put("resignCount",resignCount);
            return  resultMap;
        }

        return resultMap;
    }

    @Override
    public List<AmResignBO> jobCount(AmResignBO amEmpTaskBO) {
        return baseMapper.jobCount(amEmpTaskBO);
    }

    @Override
    public TerminateDTO getResignByEmpCompanyId(String empCompanyId) {
        List<TerminateDTO> list = baseMapper.getResignByEmpCompanyId(empCompanyId);
        if(null!=list&&list.size()>0)
        {
            return list.get(0);
        }
        return null;
    }

    /**
     *判断是否完成退工详情见v6版退工反馈
     * @param bo
     * @param amEmpTask
     * @return
     */
    Integer  isResginFinish(AmResignBO bo,AmEmpTask amEmpTask){
        String outReasonCode = amEmpTask.getOutReasonCode();
        String resignFeedback = bo.getResignFeedback();
        if(StringUtil.isEmpty(outReasonCode))
        {
            return  0;
        }
        /**
         * 13 15 代表无需退工(退工原因)，转其他城市缴纳按照辞职的原因来看待
         */
        if("6".equals(resignFeedback)||"12".equals(resignFeedback))
        {
            return 1;
        }
        if("13".equals(resignFeedback)||"14".equals(resignFeedback))
        {
            return 1;
        }
        if("15".equals(resignFeedback)||"16".equals(resignFeedback))
        {
            return 1;
        }
        if("17".equals(resignFeedback))
        {
            return 1;
        }

        return 0;
    }

    @Override
    public List<AmEmpExplainExportPageDTO> queryExportOptExplain(AmResignBO amResignBO, Integer employCode) {
        List<AmEmpExplainExportPageDTO> result = new ArrayList<>();
        List<String> param = new ArrayList<String>();
        List<String> orderParam = new ArrayList<String>();
        if(!StringUtil.isEmpty(amResignBO.getParams()))
        {
            String arr[] = amResignBO.getParams().split(",");
            for(int i=0;i<arr.length;i++) {
                if(arr[i].indexOf("desc")>0||arr[i].indexOf("asc")>0){
                    orderParam.add(arr[i]);
                }else {
                    param.add(arr[i]);
                }
            }
        }
        // 中智大库或者外包
        param.add("a.employ_code=" + employCode);
        amResignBO.setParam(param);
        amResignBO.setOrderParam(orderParam);
        if(null!=amResignBO.getTaskStatus()&&amResignBO.getTaskStatus()==0){
            amResignBO.setTaskStatus(null);
        }
        if(null!=amResignBO.getTaskStatus()&&amResignBO.getTaskStatus()==6){
            amResignBO.setTaskStatusOther(0);
        }
        PageInfo pageInfo = new PageInfo();
        pageInfo.setPageNum(1);
        pageInfo.setPageSize(10);
        PageRows<AmResignBO> pageRows = PageKit.doSelectPage(pageInfo,() -> baseMapper.queryAmResign(amResignBO));
        Long pageSize = (pageRows.getTotal()-1)/10 +1;
        for (int currPage = 1;currPage<=pageSize;currPage++){
            pageInfo.setPageNum(currPage);
            AmEmpExplainExportPageDTO dtoList = new AmEmpExplainExportPageDTO();
            List<AmEmpExplainExportDTO> exportList = new ArrayList<>(10);
            pageRows = PageKit.doSelectPage(pageInfo,() -> baseMapper.queryAmResign(amResignBO));
            List<AmResignBO> amList = pageRows.getRows();
            for (AmResignBO b:amList ) {
                AmEmpExplainExportDTO dto =  new AmEmpExplainExportDTO();
                dto.setEmployeeName(b.getEmployeeName());
                dto.setInNumber(b.getIdNum());
                if(b.getOutDate()!=null){// 退工日期
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                    try {
                        dto.setDate(sdf.parse(b.getOutDate()));
                    }catch (Exception e){

                    }
                }
                exportList.add(dto);
            }
            if(exportList.size()!=0){
                // 中智大库和外包公司title 不一样
                dtoList.setCompanyName(employCode==2?"中智上海经济技术合作有限公司":employCode==3?"上海中智项目外包咨询服务有限公司":"");
                dtoList.setRemark("原因");
                dtoList.setSettlementArea("徐汇");
                dtoList.setList(exportList);
                dtoList.setIsEntry(0);// 退工导出为离职
                result.add(dtoList);
            }
        }
        return result;
    }

    @Override
    public List<AmEmpExplainExportPageDTO> queryExportOptExplain(AmResignBO amResignBO) {
        List<AmEmpExplainExportPageDTO> result = new ArrayList<>();
        List<String> param = new ArrayList<String>();
        List<String> orderParam = new ArrayList<String>();
        if(!StringUtil.isEmpty(amResignBO.getParams()))
        {
            String arr[] = amResignBO.getParams().split(",");
            for(int i=0;i<arr.length;i++) {
                if(arr[i].indexOf("desc")>0||arr[i].indexOf("asc")>0){
                    orderParam.add(arr[i]);
                }else {
                    param.add(arr[i]);
                }
            }
        }
        // 固定为独立户
        param.add("a.employ_code=" + 1);
        amResignBO.setParam(param);
        amResignBO.setOrderParam(orderParam);
        if(null!=amResignBO.getTaskStatus()&&amResignBO.getTaskStatus()==0){
            amResignBO.setTaskStatus(null);
        }
        if(null!=amResignBO.getTaskStatus()&&amResignBO.getTaskStatus()==6){
            amResignBO.setTaskStatusOther(0);
        }
        AmEmpTaskBO taskBO = new AmEmpTaskBO();
        taskBO.setParam(param);
        taskBO.setOrderParam(orderParam);
        List<String> companys = amEmpTaskMapper.queryAmEmpTaskCompanys(taskBO);
        for (String companyId:companys) {
            param.add("a.company_id='"+companyId+"'");
            PageInfo pageInfo  = new PageInfo();
            pageInfo.setPageNum(1);
            pageInfo.setPageSize(10);
            PageRows<AmResignBO> pageRows = PageKit.doSelectPage(pageInfo,() -> baseMapper.queryAmResign(amResignBO));
            Long pageSize = (pageRows.getTotal()-1)/10 +1;
            for (int i = 1;i<=pageSize;i++){
                pageInfo.setPageNum(i);
                AmEmpExplainExportPageDTO dtoList = new AmEmpExplainExportPageDTO();
                List<AmEmpExplainExportDTO> exportList = new ArrayList<>(10);
                pageRows = PageKit.doSelectPage(pageInfo,() -> baseMapper.queryAmResign(amResignBO));
                List<AmResignBO> amList = pageRows.getRows();
                for (AmResignBO b:amList ) {
                    AmEmpExplainExportDTO dto =  new AmEmpExplainExportDTO();
                    dto.setEmployeeName(b.getEmployeeName());
                    dto.setInNumber(b.getIdNum());
                    if(b.getOutDate()!=null){
                        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                        try {
                            dto.setDate(sdf.parse(b.getOutDate()));
                        }catch (Exception e){

                        }
                    }
                    exportList.add(dto);
                }
                if(exportList.size()!=0){
                    dtoList.setRemark("原因");
                    com.ciicsh.common.entity.JsonResult<SsComAccountDTO> accountResult = socApiProxy.getSsComAccountByComId(companyId);
                    // 独立户公司title信息
                    if(accountResult.getData()!=null){
                        dtoList.setSettlementArea(accountResult.getData().getSettlementArea());
                    }
                    com.ciicsh.gto.salecenter.apiservice.api.dto.core.JsonResult<AfCompanyDetailResponseDTO> companyDto = companyProxy.afDetail(companyId);
                    dtoList.setCompanyName(companyDto.getObject().getCompanyName());
                    dtoList.setList(exportList);
                    dtoList.setIsEntry(0);//退工导出外来情况说明 都为 离职
                    result.add(dtoList);
                }
            }
            param.remove(param.size()-1);
        }
        return result;
    }

    @Override
    public List<AmEmpDispatchExportPageDTO> queryExportOptReturn(AmResignBO amResignBO, Integer employCode, Integer pageSize) {
        List<String> param = new ArrayList<String>();
        List<String> orderParam = new ArrayList<String>();
        if(!StringUtil.isEmpty(amResignBO.getParams())) {
            String arr[] = amResignBO.getParams().split(",");
            for(int i=0;i<arr.length;i++) {
                if(arr[i].indexOf("desc")>0||arr[i].indexOf("asc")>0){
                    orderParam.add(arr[i]);
                }else {
                    param.add(arr[i]);
                }
            }
        }
        amResignBO.setParam(param);
        amResignBO.setOrderParam(orderParam);
        if(null!=amResignBO.getTaskStatus()&&amResignBO.getTaskStatus()==0){
            amResignBO.setTaskStatus(null);
        }
        if(null!=amResignBO.getTaskStatus()&&amResignBO.getTaskStatus()==6) {
            amResignBO.setTaskStatusOther(0);
        }
        PageInfo pageInfo = new PageInfo();
        pageInfo.setPageNum(1);
        pageInfo.setPageSize(pageSize);
        List<AmEmpDispatchExportPageDTO> result = new ArrayList<>();
        // 中智大库 还是外包
        param.add("a.employ_code=" + employCode);
        amResignBO.setParam(param);
        amResignBO.setOrderParam(orderParam);
        PageRows<AmResignBO> pageRows = PageKit.doSelectPage(pageInfo,() -> baseMapper.queryAmResign(amResignBO));
        long sumPage = (pageRows.getTotal()-1)/pageSize +1;
        for (int i = 1;i<=sumPage;i++){
            pageInfo.setPageNum(i);
            AmEmpDispatchExportPageDTO dtoList = new AmEmpDispatchExportPageDTO();
            List<AmEmpDispatchExportDTO> exportList = new ArrayList<>();
            pageRows = PageKit.doSelectPage(pageInfo, () -> baseMapper.queryAmResign(amResignBO));
            List<AmResignBO> amList = pageRows.getRows();
            for (AmResignBO b:amList ) {
                AmEmpDispatchExportDTO dto =  new AmEmpDispatchExportDTO();
                dto.setEmployeeName(b.getEmployeeName());
                dto.setIdNum(b.getIdNum());
                dto.setEmploymentStartDate(DateUtil.localDateToDate(b.getEmployDate()));// 用工起始日期 实际录用日期
                if(b.getOutDate()!=null){// 退工日期
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                    try {
                        dto.setResignDate(sdf.parse(b.getOutDate()));
                    }catch (Exception e){

                    }
                }
                AmRemarkBO remarkBO = new AmRemarkBO();
                remarkBO.setEmpTaskId(b.getEmpTaskId());
                remarkBO.setRemarkType(3);
                remarkBO.setActive(true);
                List<AmRemarkBO> remarks = amRemarkService.getAmRemakList(remarkBO);
                if(remarks.size()>0){
                    dto.setRemark(remarks.get(0).getRemarkContent());//退工备注
                }
                dto.setEndType(b.getEndType());// 终止类型
                exportList.add(dto);
            }
            if(exportList.size()!=0){
                // 中智大库和外包公司title 不一样
                dtoList.setCompanyName(employCode==2?"中智上海经济技术合作有限公司":employCode==3?"上海中智项目外包咨询服务有限公司":"");
                dtoList.setOrganizationCode(employCode==2?"132224030":employCode==3?"669359349":"");
                dtoList.setLinkman(UserContext.getUser().getDisplayName());
                dtoList.setCreatedBy(UserContext.getUser().getDisplayName());
                dtoList.setLinkPhone("54594545");
                dtoList.setSsAccount(employCode==2?"00048926":employCode==3?"00309096":"");//社保登记码
                dtoList.setList(exportList);
                result.add(dtoList);
            }
        }
        return result;
    }

    @Override
    public List<AmEmpDispatchExportPageDTO> queryExportOptReturn(AmResignBO amResignBO, Integer pageSize) {
        List<String> param = new ArrayList<String>();
        List<String> orderParam = new ArrayList<String>();
        if(!StringUtil.isEmpty(amResignBO.getParams())) {
            String arr[] = amResignBO.getParams().split(",");
            for(int i=0;i<arr.length;i++) {
                if(arr[i].indexOf("desc")>0||arr[i].indexOf("asc")>0){
                    orderParam.add(arr[i]);
                }else {
                    param.add(arr[i]);
                }
            }
        }
        amResignBO.setParam(param);
        amResignBO.setOrderParam(orderParam);
        if(null!=amResignBO.getTaskStatus()&&amResignBO.getTaskStatus()==0){
            amResignBO.setTaskStatus(null);
        }
        if(null!=amResignBO.getTaskStatus()&&amResignBO.getTaskStatus()==6) {
            amResignBO.setTaskStatusOther(0);
        }
        PageInfo pageInfo = new PageInfo();
        pageInfo.setPageNum(1);
        pageInfo.setPageSize(pageSize);
        List<AmEmpDispatchExportPageDTO> result = new ArrayList<>();

        // 固定为独立户
        param.add("a.employ_code=" + 1);
        amResignBO.setParam(param);
        amResignBO.setOrderParam(orderParam);
        AmEmpTaskBO taskBO = new AmEmpTaskBO();
        taskBO.setParam(param);
        taskBO.setOrderParam(orderParam);
        List<String> companys = amEmpTaskMapper.queryAmEmpTaskCompanys(taskBO);
        for (String companyId:companys) {
            param.add("a.company_id='"+companyId+"'");
            pageInfo.setPageSize(pageSize);
            pageInfo.setPageNum(1);
            PageRows<AmResignBO> pageRows = PageKit.doSelectPage(pageInfo, () -> baseMapper.queryAmResign(amResignBO));
            long count = pageRows.getTotal();
            long pageCount = (count-1)/pageSize +1;
            for (int i = 1;i<=pageCount;i++){
                pageInfo.setPageNum(i);
                AmEmpDispatchExportPageDTO dtoList = new AmEmpDispatchExportPageDTO();
                List<AmEmpDispatchExportDTO> exportList = new ArrayList<>();
                pageRows = PageKit.doSelectPage(pageInfo, () -> baseMapper.queryAmResign(amResignBO));
                List<AmResignBO> amList = pageRows.getRows();
                for (AmResignBO b:amList ) {
                    AmEmpDispatchExportDTO dto =  new AmEmpDispatchExportDTO();
                    dto.setEmployeeName(b.getEmployeeName());
                    dto.setIdNum(b.getIdNum());
                    dto.setEmploymentStartDate(DateUtil.localDateToDate(b.getEmployDate()));// 用工起始日期 实际录用日期
                    if(b.getOutDate()!=null){// 退工日期
                        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                        try {
                            dto.setResignDate(sdf.parse(b.getOutDate()));
                        }catch (Exception e){

                        }
                    }
                    dto.setEndType(b.getEndType());// 终止类型
                    AmRemarkBO remarkBO = new AmRemarkBO();
                    remarkBO.setEmpTaskId(b.getEmpTaskId());
                    remarkBO.setRemarkType(3);
                    remarkBO.setActive(true);
                    List<AmRemarkBO> remarks = amRemarkService.getAmRemakList(remarkBO);
                    if(remarks.size()>0){
                        dto.setRemark(remarks.get(0).getRemarkContent());//退工备注
                    }
                    exportList.add(dto);
                }
                if(exportList.size()!=0){
                    // 独立户公司title信息
                    com.ciicsh.gto.salecenter.apiservice.api.dto.core.JsonResult<AfCompanyDetailResponseDTO> companyDto = companyProxy.afDetail(companyId);
                    if(companyDto.getObject()!=null){
                        dtoList.setCompanyName(companyDto.getObject().getCompanyName());
                        // 组织机构代码
                        dtoList.setOrganizationCode(companyDto.getObject().getOrganizationCode()==null?null:companyDto.getObject().getOrganizationCode().replace("-","")+"         ");
                    }
                    dtoList.setLinkman(UserContext.getUser().getDisplayName());
                    dtoList.setLinkPhone("54594545");
                    dtoList.setCreatedBy(UserContext.getUser().getDisplayName());
                    com.ciicsh.common.entity.JsonResult<SsComAccountDTO> accountResult = socApiProxy.getSsComAccountByComId(companyId);
                    if(accountResult.getData()!=null){
                        String account = accountResult.getData().getSsAccount();
                        dtoList.setSsAccount(account==null||account.length()<8?"        ":account);//社保登记码
                    }
                    dtoList.setList(exportList);
                    result.add(dtoList);
                }
            }
            param.remove(param.size()-1);
        }
        return result;
    }

    @Override
    public AmResign saveAmReturn(AmResignBO bo) {
        AmResign amResign = new AmResign();
        amResign.setReturnDocDate(bo.getReturnDocDate());
        amResign.setReturnDocMan(ReasonUtil.getUserName());

        Wrapper<AmResign> wrapper = new EntityWrapper<>();
        wrapper.eq("resign_id",bo.getResignId());
        baseMapper.update(amResign,wrapper);
        return  amResign;
    }
}
