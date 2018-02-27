package com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.business.impl;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.bo.SsEmpArchiveBO;
import com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.business.SsEmpArchiveService;
import com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.business.SsEmpTaskService;
import com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.business.utils.CommonApiUtils;
import com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.business.utils.TaskCommonUtils;
import com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.dao.SsEmpArchiveMapper;
import com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.entity.SsEmpArchive;
import com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.entity.SsEmpTask;
import com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.entity.custom.empSSSearchExportOpt;
import com.ciicsh.gto.afsupportcenter.util.StringUtil;
import com.ciicsh.gto.afsupportcenter.util.page.PageInfo;
import com.ciicsh.gto.afsupportcenter.util.page.PageKit;
import com.ciicsh.gto.afsupportcenter.util.page.PageRows;
import com.ciicsh.gto.employeecenter.apiservice.api.dto.EmployeeInfoDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
    @Override
    public SsEmpArchiveBO  queryByEmpTaskId(String empTaskId,String operatorType) {
        SsEmpArchiveBO ssEmpArchiveBO =new SsEmpArchiveBO();
        try{
            if("1".equals(operatorType) || "2".equals(operatorType)){
                SsEmpTask ssEmpTask = (SsEmpTask) ssEmpTaskService.selectById(empTaskId);
                //调用外部接口 查询雇员信息
                EmployeeInfoDTO employeeInfoDTO =TaskCommonUtils.getEmployeeInfo(commonApiUtils,ssEmpTask.getEmployeeId());
                //获取雇员信息
                if(null!=employeeInfoDTO){
                    setEmlpoyeeInfo(ssEmpArchiveBO,employeeInfoDTO);
                }
                ssEmpArchiveBO.setInDate(ssEmpTask.getInDate());
                ssEmpArchiveBO.setEmployeeId(ssEmpTask.getEmployeeId());
                ssEmpArchiveBO.setSsEmpTask(ssEmpTask);
                return ssEmpArchiveBO;
            }else{
                //先调用外部接口查询雇员信息

                //再查询本地数据库中已存在的数据
                return baseMapper.queryByEmpTaskId(empTaskId);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return ssEmpArchiveBO;
    }

    /**
     * 雇员查询
     * @param pageInfo
     * @return
     */
    @Override
    public PageRows<SsEmpArchiveBO> queryEmployee(PageInfo pageInfo) {
        //将json对象转 BO对象
        SsEmpArchiveBO ssEmpArchiveBO = pageInfo.toJavaObject(SsEmpArchiveBO.class);

        return PageKit.doSelectPage(pageInfo,() -> baseMapper.queryEmployee(ssEmpArchiveBO));
    }
    /**
     * 雇员查询导出
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

    private void setEmlpoyeeInfo(SsEmpArchiveBO ssEmpArchiveBO, EmployeeInfoDTO employeeInfoDTO){
        ssEmpArchiveBO.setEmployeeName(null==employeeInfoDTO.getEmployeeName()?null:employeeInfoDTO.getEmployeeName());
        ssEmpArchiveBO.setIdNum(null==employeeInfoDTO.getIdNum()?null:employeeInfoDTO.getIdNum());
        ssEmpArchiveBO.setResidenceAddress(null==employeeInfoDTO.getResidenceAddress()?null:employeeInfoDTO.getResidenceAddress());
        ssEmpArchiveBO.setResidenceAttribute(null==employeeInfoDTO.getResidentType()?null:String.valueOf(employeeInfoDTO.getResidentType()));
        ssEmpArchiveBO.setContactAddress(null==employeeInfoDTO.getAddress()?null:employeeInfoDTO.getAddress());
    }
}
