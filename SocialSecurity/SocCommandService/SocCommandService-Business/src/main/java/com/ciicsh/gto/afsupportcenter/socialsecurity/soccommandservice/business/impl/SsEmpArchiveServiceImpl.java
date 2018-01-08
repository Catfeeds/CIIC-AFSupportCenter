package com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.business.impl;

import com.alibaba.fastjson.JSONObject;
import com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.bo.SsComTaskBO;
import com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.bo.SsEmpArchiveBO;
import com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.bo.SsEmpTaskBO;
import com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.business.ISsEmpTaskService;
import com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.dao.SsEmpTaskMapper;
import com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.entity.SsEmpArchive;
import com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.dao.SsEmpArchiveMapper;
import com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.business.ISsEmpArchiveService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.entity.SsEmpTask;
import com.ciicsh.gto.afsupportcenter.util.StringUtil;
import com.ciicsh.gto.afsupportcenter.util.page.PageInfo;
import com.ciicsh.gto.afsupportcenter.util.page.PageKit;
import com.ciicsh.gto.afsupportcenter.util.page.PageRows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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
public class SsEmpArchiveServiceImpl extends ServiceImpl<SsEmpArchiveMapper, SsEmpArchive> implements ISsEmpArchiveService {
    @Autowired
    ISsEmpTaskService iSsEmpTaskService;
    @Override
    public SsEmpArchiveBO  queryByEmpTaskId(String empTaskId,String operatorType) {
        SsEmpArchiveBO ssEmpArchiveBO =new SsEmpArchiveBO();
        try{
            if("1".equals(operatorType) || "2".equals(operatorType)){
                //调用外部接口 查询雇员信息

                SsEmpTask ssEmpTask = (SsEmpTask)iSsEmpTaskService.selectById(empTaskId);
                String taskFromContecxt = ssEmpTask.getTaskFormContent();
                //获取JSON
                if(!StringUtil.isEmpty(taskFromContecxt)){
                    ssEmpArchiveBO = setEmlpoyeeInfo(ssEmpArchiveBO,ssEmpTask,taskFromContecxt);
                }
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

    @Override
    public SsEmpArchiveBO queryEmployeeDetailInfo(String empArchiveId) {

        return baseMapper.queryEmployeeDetailInfo(empArchiveId);
    }

    private SsEmpArchiveBO setEmlpoyeeInfo(SsEmpArchiveBO ssEmpArchiveBO, SsEmpTask ssEmpTask, String taskFromContecxt){
        ssEmpArchiveBO = JSONObject.parseObject(ssEmpTask.getTaskFormContent(),SsEmpArchiveBO.class);
        ssEmpArchiveBO.setEmployeeId(ssEmpTask.getEmployeeId());
        ssEmpArchiveBO.setSsEmpTask(ssEmpTask);
        return ssEmpArchiveBO;
    }
}
