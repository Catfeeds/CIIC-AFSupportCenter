package com.ciicsh.gto.afsupportcenter.employmanagement.employservice.business.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.ciicsh.common.entity.JsonResult;
import com.ciicsh.gto.afsupportcenter.employmanagement.employservice.bo.*;
import com.ciicsh.gto.afsupportcenter.employmanagement.employservice.business.AmEmpEmployeeService;
import com.ciicsh.gto.afsupportcenter.employmanagement.employservice.business.IAmEmploymentService;
import com.ciicsh.gto.afsupportcenter.employmanagement.employservice.business.IAmRemarkService;
import com.ciicsh.gto.afsupportcenter.employmanagement.employservice.business.utils.ReasonUtil;
import com.ciicsh.gto.afsupportcenter.employmanagement.employservice.custom.archiveSearchExportOpt;
import com.ciicsh.gto.afsupportcenter.employmanagement.employservice.dao.AmArchiveMapper;
import com.ciicsh.gto.afsupportcenter.employmanagement.employservice.dao.AmEmpTaskMapper;
import com.ciicsh.gto.afsupportcenter.employmanagement.employservice.dao.AmEmploymentMapper;
import com.ciicsh.gto.afsupportcenter.employmanagement.employservice.dto.*;
import com.ciicsh.gto.afsupportcenter.employmanagement.employservice.entity.AmArchive;
import com.ciicsh.gto.afsupportcenter.employmanagement.employservice.entity.AmEmpEmployee;
import com.ciicsh.gto.afsupportcenter.employmanagement.employservice.entity.AmEmployment;
import com.ciicsh.gto.afsupportcenter.employmanagement.employservice.entity.AmRemark;
import com.ciicsh.gto.afsupportcenter.housefund.fundservice.api.FundApiProxy;
import com.ciicsh.gto.afsupportcenter.housefund.fundservice.api.dto.HfEmpInfoDTO;
import com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.api.SocApiProxy;
import com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.api.dto.SsComAccountDTO;
import com.ciicsh.gto.afsupportcenter.util.DateUtil;
import com.ciicsh.gto.afsupportcenter.util.StringUtil;
import com.ciicsh.gto.afsupportcenter.util.interceptor.authenticate.UserContext;
import com.ciicsh.gto.afsupportcenter.util.page.PageInfo;
import com.ciicsh.gto.afsupportcenter.util.page.PageKit;
import com.ciicsh.gto.afsupportcenter.util.page.PageRows;
import com.ciicsh.gto.afsupportcenter.util.web.response.JsonResultKit;
import com.ciicsh.gto.employeecenter.apiservice.api.dto.ResidentInfoDTO;
import com.ciicsh.gto.employeecenter.apiservice.api.proxy.EmployeeInfoProxy;
import com.ciicsh.gto.salecenter.apiservice.api.dto.company.AfCompanyDetailResponseDTO;
import com.ciicsh.gto.salecenter.apiservice.api.proxy.CompanyProxy;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 用工主表 服务实现类
 * </p>
 */
@Service
public class AmEmploymentServiceImpl extends ServiceImpl<AmEmploymentMapper, AmEmployment> implements IAmEmploymentService {

    @Autowired
    private EmployeeInfoProxy employeeInfoProxy;

    @Autowired
    private FundApiProxy fundApiProxy;

    @Autowired
    private AmEmpEmployeeService amEmpEmployeeService;

    @Autowired
    private AmEmploymentMapper amEmploymentMapper;

    @Autowired
    private AmArchiveMapper amArchiveMapper;

    @Autowired
    private IAmRemarkService amRemarkService;

    @Autowired
    private AmEmpTaskMapper amEmpTaskMapper;

    @Autowired
    private SocApiProxy socApiProxy;

    @Autowired
    private CompanyProxy companyProxy;

    @Override
    public List<AmEmploymentBO> queryAmEmployment(Map<String, Object> param) {
        return baseMapper.queryAmEmployment(param);
    }

    @Override
    public PageRows<AmEmploymentBO> queryAmArchive(PageInfo pageInfo) {
        AmEmploymentBO amEmploymentBO = pageInfo.toJavaObject(AmEmploymentBO.class);

        List<String> param = new ArrayList<String>();
        List<String> orderParam = new ArrayList<String>();
        if(!StringUtil.isEmpty(amEmploymentBO.getParams()))
        {
            String arr[] = amEmploymentBO.getParams().split(",");
            for(int i=0;i<arr.length;i++) {
                if(!StringUtil.isEmpty(arr[i]))
                {
                    if(arr[i].indexOf("desc")>0||arr[i].indexOf("asc")>0){
                        orderParam.add(arr[i]);
                    }else {
                        param.add(arr[i]);
                    }
                }

            }
        }

        amEmploymentBO.setParam(param);
        amEmploymentBO.setOrderParam(orderParam);

        if (null != amEmploymentBO.getTaskStatus() && amEmploymentBO.getTaskStatus() == 0)
        {
            amEmploymentBO.setTaskStatus(null);
        }

        if(amEmploymentBO.getTaskStatus()!=null&&amEmploymentBO.getTaskStatus()==6)
        {
            amEmploymentBO.setTaskStatusOther(0);
        }

        if(null!=amEmploymentBO.getTaskResignStatus()&&amEmploymentBO.getTaskResignStatus()==0)
        {
            amEmploymentBO.setTaskResignStatus(null);
        }

        if(null!=amEmploymentBO.getTaskResignStatus()&&amEmploymentBO.getTaskResignStatus()==6)
        {
            amEmploymentBO.setTaskResignStatusOther(0);
        }

        if("Y".equals(amEmploymentBO.getRJob()))
        {
            amEmploymentBO.setIsRJob(1);
        }else if("N".equals(amEmploymentBO.getRJob())){
            amEmploymentBO.setIsRJob(0);
        }

        return PageKit.doSelectPage(pageInfo,() -> baseMapper.queryAmArchive(amEmploymentBO));

    }

    @Override
    public List<AmEmploymentBO> taskCountEmployee(PageInfo pageInfo) {
        AmEmploymentBO amEmploymentBO = pageInfo.toJavaObject(AmEmploymentBO.class);

        List<String> param = new ArrayList<String>();

        if(!StringUtil.isEmpty(amEmploymentBO.getParams()))
        {
            String arr[] = amEmploymentBO.getParams().split(",");
            for(int i=0;i<arr.length;i++) {
                param.add(arr[i]);
            }
        }

        amEmploymentBO.setParam(param);
        return baseMapper.taskCountEmployee(amEmploymentBO);
    }

    @Override
    public List<AmEmploymentBO> taskCountResign(PageInfo pageInfo) {
        AmEmploymentBO amEmploymentBO = pageInfo.toJavaObject(AmEmploymentBO.class);

        List<String> param = new ArrayList<String>();

        if(!StringUtil.isEmpty(amEmploymentBO.getParams()))
        {
            String arr[] = amEmploymentBO.getParams().split(",");
            for(int i=0;i<arr.length;i++) {
                param.add(arr[i]);
            }
        }

        amEmploymentBO.setParam(param);
        if("Y".equals(amEmploymentBO.getRJob()))
        {
            amEmploymentBO.setIsRJob(1);
        }
        return baseMapper.taskCountResign(amEmploymentBO);
    }

    @Override
    public List<archiveSearchExportOpt> queryAmArchiveList(AmEmploymentBO amEmploymentBO) {
        return baseMapper.queryAmArchiveList(amEmploymentBO);
    }

    @Override
    public List<AmEmploymentBO> queryAmEmploymentResign(Map<String, Object> param) {
        return baseMapper.queryAmEmploymentResign(param);
    }

    @Override
    @Transactional(
        rollbackFor = {Exception.class}
    )
    public com.ciicsh.gto.afsupportcenter.util.web.response.JsonResult xlsImportAmEmpAdvance(List<AmEmpArchiveAdvanceXsl> opts, String fileName) {

        StringBuffer retStr = new StringBuffer();
        if(opts==null||opts.size()==0){
            retStr.append("导入失败，没有录入数据.");
        }

        for (int i=0;i< opts.size();i++){
            AmEmpArchiveAdvanceXsl xsl = opts.get(i);
            if(xsl.getIdNum()==null||xsl.getEmploymentName()==null||xsl.getMatchEmployIndex()==null||xsl.getEmployeeId()==null){
                retStr.append("第 " + (i+2) + " 行这个雇员信息不完整无法匹配！ ");
                continue;
            }
            Wrapper<AmEmpEmployee> wrapper = new EntityWrapper<>();
            wrapper.eq("employee_name",xsl.getEmploymentName());
            wrapper.eq("id_num",xsl.getIdNum());
            wrapper.eq("employee_id", xsl.getEmployeeId());
            // 是否有雇员
            List<AmEmpEmployee> empEmployeeList = amEmpEmployeeService.selectList(wrapper);
            if(empEmployeeList == null || empEmployeeList.size() == 0) {
                retStr.append("第 " + (i+2) +  " 行这个雇员在系统中不存在！ ");
                continue;
            }
            AmEmpEmployee employee = empEmployeeList.get(0);
            Wrapper<AmEmployment> wrapper2 = new EntityWrapper<>();
            wrapper2.eq("employment_id",xsl.getMatchEmployIndex());
            wrapper2.eq("employee_id",employee.getEmployeeId());
            List<AmEmployment> empList = amEmploymentMapper.selectList(wrapper2);
            if(empList == null || empList.size() == 0) {
                retStr.append("第 " + (i+2) +  " 行这个雇员对应的用工序号 " + xsl.getMatchEmployIndex() + " 不匹配系统的用工序号！ ");
                continue;
            }
            AmEmployment amEmployment = empList.get(0);
            Wrapper<AmArchive> wrapper3 = new EntityWrapper<>();
            wrapper3.eq("employee_id",employee.getEmployeeId());
            wrapper3.eq("company_id",employee.getCompanyId());
            wrapper3.eq("employment_id",amEmployment.getEmploymentId());
            List<AmArchive> archives = amArchiveMapper.selectList(wrapper3);
            if(archives!=null && archives.size()>0){
                AmArchive amArchive = archives.get(0);
                amArchive.setDocType(xsl.getDocType()==null?"":xsl.getDocType().trim());
                amArchive.setDocNum(xsl.getDocNum()==null?"":xsl.getDocNum().trim());
                amArchive.setDocFrom(xsl.getDocFrom()==null?"":xsl.getDocFrom().trim());
                amArchive.setArchivePlace(xsl.getArchivePlace()==null?"":xsl.getArchivePlace().trim());
                amArchive.setStorageDate(DateUtil.dateToLocaleDate(xsl.getCreatedDate()));
                if(xsl.getEmployHandleEnd() == null){
                    amArchive.setLuyongHandleEnd(false);
                }else{
                    if(xsl.getEmployHandleEnd().toLowerCase().indexOf("true")!=-1){
                        amArchive.setLuyongHandleEnd(true);
                    }
                }
                xsl.getEmployHandleEnd().trim().indexOf("true");

                amArchive.setModifiedBy(UserContext.getUser().getDisplayName());
                amArchive.setModifiedTime(LocalDateTime.now());
                amArchiveMapper.updateById(amArchive);
            }else{
                AmArchive amArchive = new AmArchive();
                amArchive.setCompanyId(employee.getCompanyId());
                amArchive.setEmployeeId(xsl.getEmployeeId());
                amArchive.setEmploymentId(Long.parseLong(xsl.getMatchEmployIndex()));
                amArchive.setDocType(xsl.getDocType()==null?"":xsl.getDocType().trim());
                amArchive.setDocNum(xsl.getDocNum()==null?"":xsl.getDocNum().trim());
                amArchive.setDocFrom(xsl.getDocFrom()==null?"":xsl.getDocFrom().trim());
                amArchive.setArchivePlace(xsl.getArchivePlace()==null?"":xsl.getArchivePlace().trim());
                amArchive.setStorageDate(DateUtil.dateToLocaleDate(xsl.getCreatedDate()));
                if(xsl.getEmployHandleEnd() == null){
                    amArchive.setLuyongHandleEnd(false);
                }else{
                    if(xsl.getEmployHandleEnd().toLowerCase().indexOf("true")!=-1){
                        amArchive.setLuyongHandleEnd(true);
                    }
                }
                amArchive.setCreatedBy(UserContext.getUser().getDisplayName());
                amArchive.setModifiedBy(UserContext.getUser().getDisplayName());
                amArchive.setCreatedTime(LocalDateTime.now());
                amArchive.setModifiedTime(LocalDateTime.now());
                amArchiveMapper.insert(amArchive);
            }
            AmRemark amRemark = new AmRemark();
            amRemark.setCreatedBy(UserContext.getUserId());
            amRemark.setModifiedBy(UserContext.getUserId());
            amRemark.setCreatedTime(LocalDateTime.now());
            amRemark.setModifiedTime(LocalDateTime.now());
            amRemark.setRemarkContent(xsl.getDocRemark());
            amRemark.setRemarkMan(UserContext.getUser().getDisplayName());
            amRemark.setRemarkDate(LocalDate.now());
            amRemark.setEmpTaskId(employee.getEmpTaskId());
            amRemark.setRemarkType(2);
            amRemark.setActive(true);
            amRemarkService.insert(amRemark);
        }
        if (retStr.toString().equals("")) {
            return JsonResultKit.of(0, "导入成功！");
        } else {
            return JsonResultKit.of(1, "部分导入失败!原因： \n" + retStr.toString());
        }
    }

    @Override
    public List<AmEmploymentBO> queryAmEmploymentCount(EmployeeBatchBO employeeBatchBO) {
        return baseMapper.queryAmEmploymentCount(employeeBatchBO);
    }

    @Override
    public List<AmEmploymentBO> queryAmEmploymentBatch(List<Long> empTaskIds) {
        EmployeeBatchBO employeeBatchBO = new  EmployeeBatchBO();
        employeeBatchBO.setEmpTaskIds(empTaskIds);
        return baseMapper.queryAmEmploymentBatch(employeeBatchBO);
    }

    @Override
    public AmEmpTaskCollection queryArchiveTaskCount(AmEmploymentBO amEmploymentBO) {
        List<String> param = new ArrayList<String>();

        if(!StringUtil.isEmpty(amEmploymentBO.getParams()))
        {
            String arr[] = amEmploymentBO.getParams().split(",");
            for(int i=0;i<arr.length;i++) {
                param.add(arr[i]);
            }
        }

        amEmploymentBO.setParam(param);
        AmEmpTaskCollection amEmpTaskCollection = new AmEmpTaskCollection();
        List<AmEmploymentBO> list = baseMapper.queryTaskCountResign(amEmploymentBO);
        AmTaskStatusResignBO bo = new AmTaskStatusResignBO();
        Integer rJOb = 0;
        Integer rNoJob = 0;
        if(null!=list&&list.size()>0)
        {
            for(AmEmploymentBO amEmploymentBO1:list){
                if("N".equals(amEmploymentBO1.getJob())){
                    rNoJob = rNoJob + amEmploymentBO1.getCount();
                }else{
                    rJOb = rJOb + amEmploymentBO1.getCount();
                }
            }
        }
        bo.setJob(rJOb);
        bo.setNoJob(rNoJob);
        amEmpTaskCollection.setAmTaskStatusResignBO(bo);
        AmTaskStatusBO amTaskStatusBO = new AmTaskStatusBO();
        List<AmEmploymentBO> secondList = baseMapper.queryTaskCount(amEmploymentBO);
        Integer job = 0;
        Integer noJob = 0;
        if(null!=secondList&&secondList.size()>0){
            for(AmEmploymentBO temp:secondList){
                if("Y".equals(temp.getJob())){
                    job = job + temp.getCount();
                }else{
                    noJob = noJob + temp.getCount();
                }
            }
        }
        amTaskStatusBO.setNoJob(noJob);
        amTaskStatusBO.setJob(job);
        amEmpTaskCollection.setAmTaskStatusBO(amTaskStatusBO);
        return amEmpTaskCollection;
    }

    @Override
    public List<AmArchiveReturnPrintDTO> queryAmArchiveForeignerPritDate(PageInfo pageInfo) {
        PageRows<AmEmploymentBO> boResult = queryAmArchive(pageInfo);
        List<AmArchiveReturnPrintDTO> list = new ArrayList<>();
        List<AmEmploymentBO> data = boResult.getRows();
        for (AmEmploymentBO bo:data) {
            AmArchiveReturnPrintDTO dto = new AmArchiveReturnPrintDTO();
            dto.setDocType(bo.getDocType());
            dto.setDocNum(bo.getDocNum());
            dto.setEmployeeId(bo.getEmployeeId());// 雇员ID
            dto.setEmployeeName(bo.getEmployeeName());// 雇员姓名
            dto.setGender(bo.getGender());// 姓别
            dto.setStartDate(DateUtil.localDateToDate(bo.getEmployDate()));// 实际录用日期
            dto.setEmployStyle(bo.getEmployStyle());
            dto.setEndDate(DateUtil.localDateToDate(bo.getOutDate()));// 合同终止时间  退工成功日期
            dto.setEndType(bo.getEndType());// 合同终止类型
            dto.setIdNum(bo.getIdNum()==null?bo.getIdNum():bo.getIdNum()+"                  ");// 身份证号
            dto.setOutDate(bo.getDocHalfwayOutDate());// 档案转出时间
            dto.setIfLaborManualReturnStr(bo.getIfLaborManualReturn());// 是否被交退人员
            // 户口地址
            com.ciicsh.gto.employeecenter.apiservice.api.dto.JsonResult<ResidentInfoDTO> residen
                = employeeInfoProxy.getEmpResidentDetailInfo(bo.getEmployeeId());
            if(residen.getData()!=null){
                BeanUtils.copyProperties(residen.getData(),dto);
            }
            JsonResult<HfEmpInfoDTO> hfResult = fundApiProxy.getHfEmpInfoById(bo.getCompanyId(),bo.getEmployeeId());
            if(hfResult.getData()!=null){
                dto.setHfAccount(hfResult.getData().getHfEmpAccount());// 公积金
                dto.setHfAccountBC(hfResult.getData().getHfEmpAccountBC());// 补充公积金
            }
            // 组织机构代码
            com.ciicsh.gto.salecenter.apiservice.api.dto.core.JsonResult<AfCompanyDetailResponseDTO> companyDto = companyProxy.afDetail(bo.getCompanyId());
            if(companyDto.getObject()!=null){
                dto.setOrganizationCode(companyDto.getObject().getOrganizationCode()==null?null:companyDto.getObject().getOrganizationCode().replace("-","")+"         ");
            }
            dto.setOperationName(UserContext.getUser().getDisplayName());
            dto.setMobile("54594545");
            list.add(dto);
        }

        return list;
    }

    @Override
    public List<AmEmpDispatchExportPageDTO> queryExportOptDispatch(AmEmploymentBO bo,Integer employCode,Integer pageSize) {
        PageInfo pageInfo = new PageInfo();
        pageInfo.setPageNum(1);
        pageInfo.setPageSize(pageSize);
        List<AmEmpDispatchExportPageDTO> result = new ArrayList<>();
        List<String> param = new ArrayList<String>();
        List<String> orderParam = new ArrayList<String>();
        if(!StringUtil.isEmpty(bo.getParams()))
        {
            String arr[] = bo.getParams().split(",");
            for(int i=0;i<arr.length;i++) {
                if(!StringUtil.isEmpty(arr[i]))
                {
                    if(arr[i].indexOf("desc")>0||arr[i].indexOf("asc")>0){
                        orderParam.add(arr[i]);
                    }else {
                        param.add(arr[i]);
                    }
                }

            }
        }

        // 中智大库 还是外包
        param.add("a.employ_code=" + employCode);
        bo.setParam(param);
        bo.setOrderParam(orderParam);

        PageRows<AmEmploymentBO> pageRows = PageKit.doSelectPage(pageInfo,() -> baseMapper.queryAmArchive(bo));

        long sumPage = (pageRows.getTotal()-1)/pageSize +1;
        for (int i = 1;i<=sumPage;i++){
            pageInfo.setPageNum(i);
            AmEmpDispatchExportPageDTO dtoList = new AmEmpDispatchExportPageDTO();
            List<AmEmpDispatchExportDTO> exportList = new ArrayList<>();
            pageRows = PageKit.doSelectPage(pageInfo, () -> baseMapper.queryAmArchive(bo));

            List<AmEmploymentBO> amList = pageRows.getRows();
            for (AmEmploymentBO b:amList ) {
                AmEmpDispatchExportDTO dto =  getAmEmpDispatchExportDTO(b);
                if(dtoList.getCreatedBy() == null){// 用工操作员
                    dtoList.setCreatedBy(b.getEmployOperateMan());
                }
                if(dtoList.getCreatedTime() == null){// 开F单日期
                    dtoList.setCreatedTime(b.getOpenAfDate()==null?"":b.getOpenAfDate().toString());
                }
                if(dto.getLaborStartDate()==null||dto.getLaborEndDate()==null){
                    dto.setTimeLimitForDispatch("5年以上");
                }else{
                    dto.setTimeLimitForDispatch(ReasonUtil.getCondemnationYears(dto.getLaborStartDate(),dto.getLaborEndDate()));
                }
                dto.setEndType(b.getEndType());// 终止类型
                dto.setSendOut(b.getCompanyType());
                dto.setResignDate(DateUtil.localDateToDate(b.getOutDate()));// 退工日期
                dto.setEmpTaskId(b.getEmpTaskResignId());
                exportList.add(dto);
            }
            if(exportList.size()!=0){
                // 中智大库和外包公司title 不一样
                dtoList.setSuperiorDepartment("无");
                dtoList.setCompanyName(employCode==2?"中智上海经济技术合作有限公司":employCode==3?"上海中智项目外包咨询服务有限公司":"");
                dtoList.setCompanyType("国有");
                dtoList.setOrganizationCode(employCode==2?"132224030":employCode==3?"669359349":"");
                dtoList.setCompanyAddress(employCode==2?"上海市徐汇区衡山路922号18楼":employCode==3?"上海市徐汇区衡山路922号8楼":"");
                dtoList.setPostalCode("200030");
                dtoList.setIndustryCategory("职业中介");
                dtoList.setMembership(employCode==2?"中央属":employCode==3?"无":"");
                dtoList.setLinkman(UserContext.getUser().getDisplayName());
                dtoList.setCreatedBy(UserContext.getUser().getDisplayName());
                dtoList.setLinkPhone("54594545");
                dtoList.setSsAccount(employCode==2?"00048926":employCode==3?"00309096":"");//社保登记码
                dtoList.setSettlementArea("徐汇");
                dtoList.setList(exportList);
                result.add(dtoList);
            }
        }

        return result;
    }

    @Override
    public List<AmEmpDispatchExportPageDTO> queryExportOptDispatch(AmEmploymentBO bo, Integer pageSize) {
        List<AmEmpDispatchExportPageDTO> result = new ArrayList<>();
        List<String> param = new ArrayList<String>();
        List<String> orderParam = new ArrayList<String>();
        if(!StringUtil.isEmpty(bo.getParams()))
        {
            String arr[] = bo.getParams().split(",");
            for(int i=0;i<arr.length;i++) {
                if(!StringUtil.isEmpty(arr[i]))
                {
                    if(arr[i].indexOf("desc")>0||arr[i].indexOf("asc")>0){
                        orderParam.add(arr[i]);
                    }else {
                        param.add(arr[i]);
                    }
                }

            }
        }

        // 固定为独立户
        param.add("a.employ_code=" + 1);
        bo.setParam(param);
        bo.setOrderParam(orderParam);
        AmEmpTaskBO taskBO = new AmEmpTaskBO();
        taskBO.setParam(param);
        taskBO.setOrderParam(orderParam);
        List<String> companys = amEmpTaskMapper.queryAmEmpTaskCompanys(taskBO);

        for (String companyId:companys) {

            param.add("a.company_id='"+companyId+"'");

            PageInfo pageInfo  = new PageInfo();
            pageInfo.setPageSize(pageSize);
            pageInfo.setPageNum(1);
            PageRows<AmEmploymentBO> pageRows = PageKit.doSelectPage(pageInfo, () -> baseMapper.queryAmArchive(bo));

            long count = pageRows.getTotal();
            long pageCount = (count-1)/pageSize +1;
            for (int i = 1;i<=pageCount;i++){
                pageInfo.setPageNum(i);
                AmEmpDispatchExportPageDTO dtoList = new AmEmpDispatchExportPageDTO();

                List<AmEmpDispatchExportDTO> exportList = new ArrayList<>();

                pageRows = PageKit.doSelectPage(pageInfo, () -> baseMapper.queryAmArchive(bo));

                List<AmEmploymentBO> amList = pageRows.getRows();
                for (AmEmploymentBO b:amList ) {
                    AmEmpDispatchExportDTO dto = getAmEmpDispatchExportDTO(b);
                    if(dtoList.getCreatedBy() == null){// 用工操作员
                        dtoList.setCreatedBy(b.getEmployOperateMan());
                    }
                    if(dtoList.getCreatedTime() == null){// 开F单日期
                        dtoList.setCreatedTime(b.getOpenAfDate()==null?"":b.getOpenAfDate().toString());
                    }
                    if(dto.getLaborStartDate()==null||dto.getLaborEndDate()==null){
                        dto.setTimeLimitForDispatch("5年以上");
                    }else{
                        dto.setTimeLimitForDispatch(ReasonUtil.getCondemnationYears(dto.getLaborStartDate(),dto.getLaborEndDate()));
                    }
                    dto.setSendOut(b.getCompanyType());
                    dto.setEndType(b.getEndType());// 终止类型
                    dto.setResignDate(DateUtil.localDateToDate(b.getOutDate()));// 退工日期
                    dto.setEmpTaskId(b.getEmpTaskResignId());
                    exportList.add(dto);
                }
                if(exportList.size()!=0){
                    // 独立户公司title信息
                    dtoList.setSuperiorDepartment("无");// 上级部门主管
                    com.ciicsh.gto.salecenter.apiservice.api.dto.core.JsonResult<AfCompanyDetailResponseDTO> companyDto = companyProxy.afDetail(companyId);
                    if(companyDto.getObject()!=null){
                        dtoList.setCompanyName(companyDto.getObject().getCompanyName());
                        dtoList.setCompanyType(companyDto.getObject().getCompanyTypeName());// 单位性质
                        dtoList.setOrganizationCode(companyDto.getObject().getOrganizationCode()==null || companyDto.getObject().getOrganizationCode().length()<9?"         "
                            :companyDto.getObject().getOrganizationCode());// 组织机构代码
                        dtoList.setCompanyAddress(companyDto.getObject().getBusinessAddress());// 营业地址
                        dtoList.setPostalCode(companyDto.getObject().getBusinessZipCode()==null || companyDto.getObject().getBusinessZipCode().length()<6?"         "
                            :companyDto.getObject().getBusinessZipCode());// 邮编
                        dtoList.setIndustryCategory(companyDto.getObject().getIndustryCategoryName());// 行业类别
                    }
                    dtoList.setMembership("");
                    dtoList.setLinkman(UserContext.getUser().getDisplayName());
                    dtoList.setLinkPhone("54594545");
                    dtoList.setCreatedBy(UserContext.getUser().getDisplayName());
                    com.ciicsh.common.entity.JsonResult<SsComAccountDTO> accountResult = socApiProxy.getSsComAccountByComId(companyId);
                    if(accountResult.getData()!=null){
                        String account = accountResult.getData().getSsAccount();
                        dtoList.setSsAccount(account==null||account.length()<8?"        ":account);//社保登记码
                        dtoList.setSettlementArea(accountResult.getData().getSettlementArea());
                    }
                    dtoList.setList(exportList);
                    result.add(dtoList);
                }
            }
            param.remove(param.size()-1);
        }
        return result;
    }

    public AmEmpDispatchExportDTO getAmEmpDispatchExportDTO(AmEmploymentBO b){
        AmEmpDispatchExportDTO dto = new AmEmpDispatchExportDTO();
        if(b==null){
            return dto;
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        BeanUtils.copyProperties(b,dto);
        try {
            if(b.getLaborStartDate()!=null){
                dto.setLaborStartDate(sdf.parse(b.getLaborStartDate()));
            }
            if(b.getLaborEndDate()!=null){
                dto.setLaborEndDate(sdf.parse(b.getLaborEndDate()));
            }
            dto.setEmploymentStartDate(DateUtil.localDateToDate(b.getEmployDate()));
            // 派遣期限
            if(dto.getLaborEndDate() == null || dto.getLaborStartDate() == null){
                dto.setTimeLimitForDispatch("4");
            }else{
                String s1 = sdf.format(dto.getLaborStartDate());
                String s2 = sdf.format(dto.getLaborEndDate());
                Integer i1 = Integer.parseInt(s1.substring(0,4));
                Integer i2 = Integer.parseInt(s2.substring(0,4));
                switch ((i2-i1)){
                    case 0:
                    case 1:
                        dto.setTimeLimitForDispatch("1");
                        break;
                    case 2:
                        dto.setTimeLimitForDispatch("2");
                        break;
                    case 3:
                    case 4:
                    case 5:
                        dto.setTimeLimitForDispatch("3");
                        break;
                    default:
                        dto.setTimeLimitForDispatch("4");
                        break;
                }
            }
        }catch (Exception e){
        }
        return dto;
    }

    @Override
    public List<AmEmpCollectExportPageDTO> queryExportOptCollect(AmEmploymentBO bo, Integer employCode) {
        List<AmEmpCollectExportPageDTO> result = new ArrayList<>();
        List<String> param = new ArrayList<String>();
        List<String> orderParam = new ArrayList<String>();
        if(!StringUtil.isEmpty(bo.getParams()))
        {
            String arr[] = bo.getParams().split(",");
            for(int i=0;i<arr.length;i++) {
                if(!StringUtil.isEmpty(arr[i]))
                {
                    if(arr[i].indexOf("desc")>0||arr[i].indexOf("asc")>0){
                        orderParam.add(arr[i]);
                    }else {
                        param.add(arr[i]);
                    }
                }

            }
        }
        // 中智大库 还是外包
        param.add("a.employ_code=" + employCode);
        bo.setParam(param);
        bo.setOrderParam(orderParam);

        PageInfo pageInfo  = new PageInfo();
        pageInfo.setPageNum(1);
        pageInfo.setPageSize(30);
        PageRows<AmEmploymentBO> pageRows = PageKit.doSelectPage(pageInfo,() -> baseMapper.queryAmArchive(bo));
        long count = pageRows.getTotal();
        long pageSize = (count-1)/30 +1;
        for (int i = 1;i<=pageSize;i++){
            pageInfo.setPageNum(i);
            pageRows = PageKit.doSelectPage(pageInfo,() -> baseMapper.queryAmArchive(bo));
            AmEmpCollectExportPageDTO pageDTO = new AmEmpCollectExportPageDTO();
            if(employCode == 2){
                pageDTO.setCompanyName("中智上海经济技术合作公司");
                pageDTO.setSsAccount("00048926");
                pageDTO.setSettlementArea("徐汇");
            }
            if(employCode == 3){
                pageDTO.setCompanyName("上海中智项目外包咨询服务有限公司");
                pageDTO.setSsAccount("00309096");
                pageDTO.setSettlementArea("徐汇");
            }
            List<AmEmploymentBO> boList = pageRows.getRows();
            List<AmEmpCollectExportDTO> list1 = new ArrayList<>();
            List<AmEmpCollectExportDTO> list2 = new ArrayList<>();
            List<AmEmpCollectExportDTO> list3 = new ArrayList<>();
            for (int n = 0;n<boList.size();n++){
                AmEmpCollectExportDTO collectDto = new AmEmpCollectExportDTO();
                collectDto.setId((n+1));
                collectDto.setIdNum(boList.get(n).getIdNum());
                collectDto.setEmployeeName(boList.get(n).getEmployeeName());
                if(n<=9){
                    list1.add(collectDto);
                }else if(n>9 && n<=19){
                    list2.add(collectDto);
                }else if(n>19){
                    list3.add(collectDto);
                }
            }
            pageDTO.setList1(list1);
            pageDTO.setList2(list2);
            pageDTO.setList3(list3);
            if(list1.size()>0){
                result.add(pageDTO);
            }
        }
        return result;
    }

    @Override
    public List<AmEmpCollectExportPageDTO> queryExportOptCollect(AmEmploymentBO bo) {
        List<AmEmpCollectExportPageDTO> result = new ArrayList<>();
        List<String> param = new ArrayList<String>();
        List<String> orderParam = new ArrayList<String>();
        if(!StringUtil.isEmpty(bo.getParams()))
        {
            String arr[] = bo.getParams().split(",");
            for(int i=0;i<arr.length;i++) {
                if(!StringUtil.isEmpty(arr[i]))
                {
                    if(arr[i].indexOf("desc")>0||arr[i].indexOf("asc")>0){
                        orderParam.add(arr[i]);
                    }else {
                        param.add(arr[i]);
                    }
                }

            }
        }
        // 独立户
        param.add("a.employ_code=" + 1);
        bo.setParam(param);
        bo.setOrderParam(orderParam);
        AmEmpTaskBO taskBO = new AmEmpTaskBO();
        taskBO.setParam(param);
        taskBO.setOrderParam(orderParam);
        List<String> companys = amEmpTaskMapper.queryAmEmpTaskCompanys(taskBO);
        for (String company:companys) {
            param.add("a.company_id='"+company+"'");
            bo.setParam(param);
            PageInfo pageInfo  = new PageInfo();
            pageInfo.setPageNum(1);
            pageInfo.setPageSize(30);
            PageRows<AmEmploymentBO> pageRows = PageKit.doSelectPage(pageInfo,() -> baseMapper.queryAmArchive(bo));
            long count = pageRows.getTotal();
            long pageSize = (count-1)/30 +1;
            for (int i = 1;i<=pageSize;i++){
                pageInfo.setPageNum(i);
                pageRows = PageKit.doSelectPage(pageInfo,() -> baseMapper.queryAmArchive(bo));
                AmEmpCollectExportPageDTO pageDTO = new AmEmpCollectExportPageDTO();
                // 独立户公司title信息
                com.ciicsh.gto.salecenter.apiservice.api.dto.core.JsonResult<AfCompanyDetailResponseDTO> companyDto = companyProxy.afDetail(company);
                pageDTO.setCompanyName(companyDto.getObject().getCompanyName());
                com.ciicsh.common.entity.JsonResult<SsComAccountDTO> accountResult = socApiProxy.getSsComAccountByComId(company);
                if(accountResult.getData()!=null){
                    String account = accountResult.getData().getSsAccount();
                    pageDTO.setSsAccount(account==null||account.length()<8?"        ":account);//社保登记码
                    pageDTO.setSettlementArea(accountResult.getData().getSettlementArea());
                }
                List<AmEmploymentBO> boList = pageRows.getRows();
                List<AmEmpCollectExportDTO> list1 = new ArrayList<>();
                List<AmEmpCollectExportDTO> list2 = new ArrayList<>();
                List<AmEmpCollectExportDTO> list3 = new ArrayList<>();
                for (int n = 0;n<boList.size();n++){
                    AmEmpCollectExportDTO collectDto = new AmEmpCollectExportDTO();
                    collectDto.setId((n+1));
                    collectDto.setEmployeeName(boList.get(n).getEmployeeName());
                    collectDto.setIdNum(boList.get(n).getIdNum());
                    if(n<=9){
                        list1.add(collectDto);
                    }else if(n>9 && n<=19){
                        list2.add(collectDto);
                    }else if(n>19){
                        list3.add(collectDto);
                    }
                }
                pageDTO.setList1(list1);
                pageDTO.setList2(list2);
                pageDTO.setList3(list3);
                result.add(pageDTO);
            }
            param.remove(param.size()-1);
        }

        return result;
    }

    @Override
    public List<AmEmpExplainExportPageDTO> queryExportOptExplain(AmEmploymentBO bo, Integer employCode,Integer isEntry) {
        List<AmEmpExplainExportPageDTO> result = new ArrayList<>();
        List<String> param = new ArrayList<String>();
        List<String> orderParam = new ArrayList<String>();
        if(!StringUtil.isEmpty(bo.getParams()))
        {
            String arr[] = bo.getParams().split(",");
            for(int i=0;i<arr.length;i++) {
                if(!StringUtil.isEmpty(arr[i]))
                {
                    if(arr[i].indexOf("desc")>0||arr[i].indexOf("asc")>0){
                        orderParam.add(arr[i]);
                    }else {
                        param.add(arr[i]);
                    }
                }

            }
        }
        // 中智大库 还是外包
        param.add("a.employ_code=" + employCode);
        bo.setParam(param);
        bo.setOrderParam(orderParam);
        PageInfo pageInfo = new PageInfo();
        pageInfo.setPageNum(1);
        pageInfo.setPageSize(10);
        PageRows<AmEmploymentBO> pageRows = PageKit.doSelectPage(pageInfo,() -> baseMapper.queryAmArchive(bo));
        Long pageSize = (pageRows.getTotal()-1)/10 +1;
        for (int currPage = 1;currPage<=pageSize;currPage++){
            pageInfo.setPageNum(currPage);
            AmEmpExplainExportPageDTO dtoList = new AmEmpExplainExportPageDTO();
            List<AmEmpExplainExportDTO> exportList = new ArrayList<>(10);
            pageRows = PageKit.doSelectPage(pageInfo, () -> baseMapper.queryAmArchive(bo));
            List<AmEmploymentBO> amList = pageRows.getRows();
            for (AmEmploymentBO b:amList ) {
                AmEmpExplainExportDTO dto =  new AmEmpExplainExportDTO();
                dto.setEmployeeName(b.getEmployeeName());
                dto.setInNumber(b.getIdNum());
                if(isEntry == 1){
                    dto.setDate(DateUtil.localDateToDate(b.getEmployDate()));
                }else if(isEntry == 0){
                    dto.setDate(DateUtil.localDateToDate(b.getOutDate()));
                }
                exportList.add(dto);
            }
            if(exportList.size()!=0){
                // 中智大库和外包公司title 不一样
                dtoList.setCompanyName(employCode==2?"中智上海经济技术合作有限公司":employCode==3?"上海中智项目外包咨询服务有限公司":"");
                dtoList.setRemark("原因");
                dtoList.setSettlementArea("徐汇");
                dtoList.setList(exportList);
                dtoList.setIsEntry(isEntry);// 入职
                result.add(dtoList);
            }
        }
        return result;
    }

    @Override
    public List<AmEmpExplainExportPageDTO> queryExportOptExplain(AmEmploymentBO bo,Integer isEntry) {
        List<AmEmpExplainExportPageDTO> result = new ArrayList<>();
        List<String> param = new ArrayList<String>();
        List<String> orderParam = new ArrayList<String>();
        if(!StringUtil.isEmpty(bo.getParams()))
        {
            String arr[] = bo.getParams().split(",");
            for(int i=0;i<arr.length;i++) {
                if(!StringUtil.isEmpty(arr[i]))
                {
                    if(arr[i].indexOf("desc")>0||arr[i].indexOf("asc")>0){
                        orderParam.add(arr[i]);
                    }else {
                        param.add(arr[i]);
                    }
                }

            }
        }
        // 固定为独立户
        param.add("a.employ_code=" + 1);
        bo.setParam(param);
        bo.setOrderParam(orderParam);
        AmEmpTaskBO taskBO = new AmEmpTaskBO();
        taskBO.setParam(param);
        taskBO.setOrderParam(orderParam);
        List<String> companys = amEmpTaskMapper.queryAmEmpTaskCompanys(taskBO);
        for (String companyId:companys) {
            param.add("a.company_id='"+companyId+"'");
            PageInfo pageInfo  = new PageInfo();
            pageInfo.setPageNum(1);
            pageInfo.setPageSize(10);
            PageRows<AmEmploymentBO> pageRows = PageKit.doSelectPage(pageInfo, () -> baseMapper.queryAmArchive(bo));
            Long pageSize = (pageRows.getTotal()-1)/10 +1;
            for (int i = 1;i<=pageSize;i++){
                pageInfo.setPageNum(i);
                AmEmpExplainExportPageDTO dtoList = new AmEmpExplainExportPageDTO();
                List<AmEmpExplainExportDTO> exportList = new ArrayList<>(10);
                pageRows = PageKit.doSelectPage(pageInfo, () -> baseMapper.queryAmArchive(bo));
                List<AmEmploymentBO> amList = pageRows.getRows();
                for (AmEmploymentBO b:amList ) {
                    AmEmpExplainExportDTO dto =  new AmEmpExplainExportDTO();
                    dto.setEmployeeName(b.getEmployeeName());
                    dto.setInNumber(b.getIdNum());
                    if(isEntry == 1){
                        dto.setDate(DateUtil.localDateToDate(b.getEmployDate()));
                    }else if(isEntry == 0){
                        dto.setDate(DateUtil.localDateToDate(b.getOutDate()));// 退工日期
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
                    dtoList.setIsEntry(isEntry);// 入职
                    result.add(dtoList);
                }
            }
            param.remove(param.size()-1);
        }
        return result;
    }
}
