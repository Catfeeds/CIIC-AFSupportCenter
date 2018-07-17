package com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.business.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.bo.*;
import com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.business.SsComAccountService;
import com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.business.SsEmpArchiveService;
import com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.business.SsEmpBasePeriodService;
import com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.business.SsEmpTaskService;
import com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.business.utils.CommonApiUtils;
import com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.business.utils.TaskCommonUtils;
import com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.dao.SsEmpArchiveMapper;
import com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.entity.SsComAccount;
import com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.entity.SsEmpArchive;
import com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.entity.SsEmpBasePeriod;
import com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.entity.SsEmpTask;
import com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.entity.custom.empSSSearchExportOpt;
import com.ciicsh.gto.afsupportcenter.util.constant.SocialSecurityConst;
import com.ciicsh.gto.afsupportcenter.util.interceptor.authenticate.UserContext;
import com.ciicsh.gto.afsupportcenter.util.page.PageInfo;
import com.ciicsh.gto.afsupportcenter.util.page.PageKit;
import com.ciicsh.gto.afsupportcenter.util.page.PageRows;
import com.ciicsh.gto.employeecenter.apiservice.api.dto.AfEmployeeDTO;
import com.ciicsh.gto.employeecenter.apiservice.api.dto.EmployeeInfoDTO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * <p>
 * 雇员本地社保档案主表,
 * 由中智代缴过社保的雇员在此表必有一条记录，如果雇员跳槽到另外一家客户，就会在此表产生 服务实现类
 * </p>
 *
 * @author HuangXing
 * @since 2017-12-01
 */
@Service
public class SsEmpArchiveServiceImpl extends ServiceImpl<SsEmpArchiveMapper, SsEmpArchive> implements SsEmpArchiveService {
    @Autowired
    SsEmpTaskService ssEmpTaskService;
    @Autowired
    CommonApiUtils commonApiUtils;
    @Autowired
    SsEmpBasePeriodService ssEmpBasePeriodService;
    @Autowired
    SsComAccountService ssComAccountService;

    @Override
    public SsEmpArchiveBO queryByEmpTaskId(String empTaskId, String operatorType) {
        SsEmpArchiveBO ssEmpArchiveBO = new SsEmpArchiveBO();
        try {
            //再查询本地数据库中已存在的数据
            ssEmpArchiveBO = baseMapper.queryByEmpTaskId(empTaskId);

            if ("1".equals(operatorType) || "2".equals(operatorType) || "12".equals(operatorType) || "13".equals(operatorType)) {
                SsEmpTask ssEmpTask = ssEmpTaskService.selectById(empTaskId);
                //查询证件号码
                SsEmpTaskBO ssEmpTaskBO = ssEmpTaskService.selectIdNumByEmployeeId(ssEmpTask.getEmployeeId());
                //调用外部接口 查询雇员信息
                EmployeeInfoDTO employeeInfoDTO = TaskCommonUtils.getEmployeeInfo(commonApiUtils, ssEmpTaskBO.getIdNum(), ssEmpTaskBO.getIdCardType(), ssEmpTask.getAfBpoFc());
                //获取雇员信息
                if (null != employeeInfoDTO) {
                    setEmlpoyeeInfo(ssEmpArchiveBO, employeeInfoDTO);

                    if (ssEmpTask.getAfBpoFc() == SocialSecurityConst.SS_BUSINESS_TYPE_AF) {
                        AfEmployeeDTO afEmployeeDTO = TaskCommonUtils.getAfEmployeeInfo(commonApiUtils, ssEmpTaskBO.getIdNum(), ssEmpTaskBO.getIdCardType());

                        if (afEmployeeDTO != null) {
                            ssEmpArchiveBO.setZipCode(afEmployeeDTO.getZipCode());
                        }
                    }
                }
                if (null == ssEmpArchiveBO.getEmpArchiveId()) {
                    ssEmpArchiveBO.setInDate(ssEmpTask.getInDate());
                    ssEmpArchiveBO.setEmployeeId(ssEmpTask.getEmployeeId());
                }
                ssEmpArchiveBO.setOldEmpBase(ssEmpTask.getEmpBase());
                ssEmpArchiveBO.setSsEmpTask(ssEmpTask);
            } else {
                //先调用外部接口查询雇员信息

                if (null == ssEmpArchiveBO.getEmpArchiveId()) {
                    EntityWrapper<SsEmpArchive> entityWrapper = new EntityWrapper<>();
                    entityWrapper.where("employee_id={0}", ssEmpArchiveBO.getEmployeeId())
                        .and("company_id={0}", ssEmpArchiveBO.getCompanyId())
                        .and("is_active=1").orderBy("created_time", false).last("LIMIT 1");
                    SsEmpArchive ssEmpArchive = this.selectOne(entityWrapper);
                    if (ssEmpArchive != null) {
                        ssEmpArchiveBO.setEmpArchiveId(ssEmpArchive.getEmpArchiveId());
                    }
                }

                //查询旧基数
                if (null != ssEmpArchiveBO.getEmpArchiveId()) {
                    EntityWrapper<SsEmpBasePeriod> ew = new EntityWrapper<>();
                    ew.where("emp_archive_id={0}", ssEmpArchiveBO.getEmpArchiveId()).and("is_active=1").orderBy("start_month", false).last("LIMIT 1");
                    SsEmpBasePeriod ssEmpBasePeriod = ssEmpBasePeriodService.selectOne(ew);
                    ssEmpArchiveBO.setOldEmpBase(ssEmpBasePeriod.getBaseAmount());
                }
            }

            return ssEmpArchiveBO;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ssEmpArchiveBO;
    }

    /**
     * 雇员查询
     *
     * @param pageInfo
     * @return
     */
    @Override
    public PageRows<SsEmpArchiveBO> queryEmployee(PageInfo pageInfo) {
        //将json对象转 BO对象
        SsEmpArchiveBO ssEmpArchiveBO = pageInfo.toJavaObject(SsEmpArchiveBO.class);

        return PageKit.doSelectPage(pageInfo, () -> baseMapper.queryEmployee(ssEmpArchiveBO));
    }

    /**
     * 雇员查询导出
     *
     * @param ssEmpArchiveBO
     * @return
     */
    @Override
    public List<empSSSearchExportOpt> empSSSearchExport(SsEmpArchiveBO ssEmpArchiveBO) {
        //将json对象转 BO对象
        // SsEmpArchiveBO ssEmpArchiveBO = pageInfo.toJavaObject(SsEmpArchiveBO.class);

        return baseMapper.empSSSearchExport(ssEmpArchiveBO);
    }

    @Override
    public SsEmpArchiveBO queryEmployeeDetailInfo(String empArchiveId,String companyId,String employeeId) {
        SsEmpArchiveBO ssEmpArchiveBO = new SsEmpArchiveBO();
        if (empArchiveId == null){
            ssEmpArchiveBO = baseMapper.queryEmployeeDetailInfoByComEmp(companyId,employeeId);
        }else {
            ssEmpArchiveBO = baseMapper.queryEmployeeDetailInfo(empArchiveId);
        }
        if(ssEmpArchiveBO.getAfempStatus()!= 3){ // 3 = 离职
            ssEmpArchiveBO.setOutOperateDate(null);
        }
        return ssEmpArchiveBO;

    }

    public String saveEmpSerial(Map<String, String> map) {
        SsEmpArchive ssEmpArchive = new SsEmpArchive();
        int checkRet = baseMapper.checkSerialDuplicate(map);
        if (checkRet > 0) {
            return "雇员社保序号重复！";
        }
        String ssEmpSerial = Optional.of(map.get("ssSerial")).orElse(" ");
        ssEmpArchive.setSsSerial(ssEmpSerial);
        ssEmpArchive.setEmpClassify( Integer.parseInt(Optional.ofNullable(map.get("empClassify")).orElse("0")));
        ssEmpArchive.setEmpArchiveId(Long.valueOf(map.get("empArchiveId")));
        baseMapper.updateById(ssEmpArchive);

        ssEmpSerial = Optional.of(map.get("ssSerial")).orElse("");

        // 手动填写的社保序号若大于当前种子，则更新为最新的种子
        if (map.get("comAccountId") != null && StringUtils.isNotEmpty(ssEmpSerial)) {
            Long existsSsSerial = ssComAccountService.getSerialByComAccountId(Long.valueOf(map.get("comAccountId")));
            long ssSerial = Long.parseLong(ssEmpSerial);
            if (existsSsSerial == null || ssSerial > existsSsSerial) {
                SsComAccount ssComAccount = new SsComAccount();
                ssComAccount.setSsSerial(ssSerial);
                ssComAccount.setComAccountId(Long.valueOf(map.get("comAccountId")));
                ssComAccount.setModifiedTime(LocalDateTime.now());
                ssComAccount.setModifiedBy(UserContext.getUserId());
                ssComAccountService.updateById(ssComAccount);
            }
        }
        return "SUCC";
    }

    private void setEmlpoyeeInfo(SsEmpArchiveBO ssEmpArchiveBO, EmployeeInfoDTO employeeInfoDTO) {
        ssEmpArchiveBO.setEmployeeName(null == employeeInfoDTO.getEmployeeName() ? null : employeeInfoDTO.getEmployeeName());
        ssEmpArchiveBO.setIdNum(null == employeeInfoDTO.getIdNum() ? null : employeeInfoDTO.getIdNum());
        ssEmpArchiveBO.setResidenceAddress(null == employeeInfoDTO.getResidenceAddress() ? null : employeeInfoDTO.getResidenceAddress());
        ssEmpArchiveBO.setResidenceAttribute(null == employeeInfoDTO.getResidentType() ? null : String.valueOf(employeeInfoDTO.getResidentType()));
        ssEmpArchiveBO.setContactAddress(null == employeeInfoDTO.getAddress() ? null : employeeInfoDTO.getAddress());
    }

    /**
     * 批量办理时 查询 新进任务 雇员是否已经有过新进
     *
     * @param empTaskId
     * @return
     */
    @Override
    public SsEmpArchiveBO queryEmployeeIsnewOrChangeInto(String empTaskId) {
        return baseMapper.queryByEmpTaskId(empTaskId);
    }

    @Override
    public boolean checkSerial(Long comAccountId, String employeeId, String empSsSerial) {
        SsEmpArchive ssEmpArchive = new SsEmpArchive();
        ssEmpArchive.setComAccountId(comAccountId);
        ssEmpArchive.setEmployeeId(employeeId);
        ssEmpArchive.setSsSerial(empSsSerial);
        int count = baseMapper.checkSerial(ssEmpArchive);
        if (count == 0) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public List<SsEmpInfoBO> getSsEmpArchiveInfo(List<SsEmpInfoParamBO> paramBoList) {
        List<SsEmpInfoBO> resultBoList = new ArrayList<>();
        for (SsEmpInfoParamBO paramBO : paramBoList) {
            // 如果detailBOList查无数据，MapperMethod.class返回的是一个new ArrayList<>()
            List<SsEmpInfoDetailBO> detailBOList = baseMapper.getSsEmpInfo(paramBO.getEmployeeId(), paramBO.getCompanyId(), paramBO.getSsMonthBelong());
            BigDecimal empAmountTotal = BigDecimal.ZERO;
            // detailBOList不会出现null，如果查无数据，detailBOList.size()==0,返回查询入参
            for (SsEmpInfoDetailBO detailBO : detailBOList) {
                empAmountTotal = empAmountTotal.add(detailBO.getEmpAmount());
            }
            SsEmpInfoBO resultBO = new SsEmpInfoBO();
            resultBO.setEmployeeId(parseValue(paramBO.getEmployeeId()));
            resultBO.setCompanyId(parseValue(paramBO.getCompanyId()));
            resultBO.setSsMonthBelong(parseValue(paramBO.getSsMonthBelong()));
            resultBO.setSsMonth(detailBOList != null && detailBOList.size() > 0 ? detailBOList.get(0).getSsMonth() : "");
            resultBO.setEmpAmountTotal(empAmountTotal);
            resultBO.setSsEmpInfoDetailBOList(detailBOList);
            resultBoList.add(resultBO);
        }
        return resultBoList;
    }

    @Override
    public SsEmpArchiveBO getSsEmployee(String companyId, String employeeId) {
        return baseMapper.getSsEmployee(companyId, employeeId);
    }

    private String parseValue(String value) {
        return value == null ? "" : value;
    }
}
