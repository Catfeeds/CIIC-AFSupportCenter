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
import com.ciicsh.gto.afsupportcenter.util.interceptor.authenticate.UserContext;
import com.ciicsh.gto.afsupportcenter.util.page.PageInfo;
import com.ciicsh.gto.afsupportcenter.util.page.PageKit;
import com.ciicsh.gto.afsupportcenter.util.page.PageRows;
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
            if ("1".equals(operatorType) || "2".equals(operatorType) || "12".equals(operatorType) || "13".equals(operatorType)) {
                SsEmpTask ssEmpTask = (SsEmpTask) ssEmpTaskService.selectById(empTaskId);
                //查询证件号码
                SsEmpTaskBO ssEmpTaskBO = ssEmpTaskService.selectIdNumByEmployeeId(ssEmpTask.getEmployeeId());
                //调用外部接口 查询雇员信息
                EmployeeInfoDTO employeeInfoDTO = TaskCommonUtils.getEmployeeInfo(commonApiUtils, ssEmpTaskBO.getIdNum(), ssEmpTaskBO.getIdCardType(), 1);
                //获取雇员信息
                if (null != employeeInfoDTO) {
                    setEmlpoyeeInfo(ssEmpArchiveBO, employeeInfoDTO);
                }
                ssEmpArchiveBO.setInDate(ssEmpTask.getInDate());
                ssEmpArchiveBO.setEmployeeId(ssEmpTask.getEmployeeId());
                ssEmpArchiveBO.setSsEmpTask(ssEmpTask);
                ssEmpArchiveBO.setOldEmpBase(ssEmpTask.getEmpBase());
                return ssEmpArchiveBO;
            } else {
                //先调用外部接口查询雇员信息

                //再查询本地数据库中已存在的数据
                ssEmpArchiveBO = baseMapper.queryByEmpTaskId(empTaskId);
                //查询旧基数
                if (null != ssEmpArchiveBO.getEmpArchiveId()) {
                    EntityWrapper<SsEmpBasePeriod> ew = new EntityWrapper<>();
                    ew.where("emp_archive_id={0}", ssEmpArchiveBO.getEmpArchiveId()).and("is_active=1").orderBy("created_time", false).last("LIMIT 1");
                    SsEmpBasePeriod ssEmpBasePeriod = ssEmpBasePeriodService.selectOne(ew);
                    ssEmpArchiveBO.setOldEmpBase(ssEmpBasePeriod.getBaseAmount());
                }
                return ssEmpArchiveBO;
            }
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
    public SsEmpArchiveBO queryEmployeeDetailInfo(String empArchiveId) {

        return baseMapper.queryEmployeeDetailInfo(empArchiveId);
    }

    public String saveEmpSerial(Map<String, String> map) {
        SsEmpArchive ssEmpArchive = new SsEmpArchive();
        int checkRet = baseMapper.checkSerialDuplicate(map);
        if (checkRet > 0) {
            return "雇员社保序号重复！";
        }
        String ssEmpSerial = Optional.of(map.get("ssSerial")).orElse(" ");
        ssEmpArchive.setSsSerial(ssEmpSerial);
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
            List<SsEmpInfoDetailBO> detailBOList = baseMapper.getSsEmpInfo(paramBO.getEmployeeId(), paramBO.getCompanyId(), paramBO.getSsMonthBelong());
            BigDecimal empAmountTotal = BigDecimal.ZERO;
            for (SsEmpInfoDetailBO detailBO : detailBOList) {
                empAmountTotal = empAmountTotal.add(detailBO.getEmpAmount());
            }
            SsEmpInfoBO resultBO = new SsEmpInfoBO();
            resultBO.setEmployeeId(paramBO.getEmployeeId());
            resultBO.setCompanyId(paramBO.getCompanyId());
            resultBO.setSsMonthBelong(paramBO.getSsMonthBelong());
            resultBO.setSsMonth(detailBOList.get(0).getSsMonth());
            resultBO.setEmpAmountTotal(empAmountTotal);
            resultBO.setSsEmpInfoDetailBOList(detailBOList);
            resultBoList.add(resultBO);
        }
        return resultBoList;
    }
}
