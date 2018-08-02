package com.ciicsh.gto.afsupportcenter.employmanagement.employservice.business.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.ciicsh.gto.afsupportcenter.employmanagement.employservice.api.dto.MaterialDTO;
import com.ciicsh.gto.afsupportcenter.employmanagement.employservice.api.dto.MaterialOperationLogDTO;
import com.ciicsh.gto.afsupportcenter.employmanagement.employservice.api.dto.MaterialUpdateDTO;
import com.ciicsh.gto.afsupportcenter.employmanagement.employservice.bo.AmEmpMaterialBO;
import com.ciicsh.gto.afsupportcenter.employmanagement.employservice.bo.AmEmpMaterialOperationLogBO;
import com.ciicsh.gto.afsupportcenter.employmanagement.employservice.business.IAmEmpMaterialService;
import com.ciicsh.gto.afsupportcenter.employmanagement.employservice.business.utils.ReasonUtil;
import com.ciicsh.gto.afsupportcenter.employmanagement.employservice.dao.AmEmpMaterialMapper;
import com.ciicsh.gto.afsupportcenter.employmanagement.employservice.dao.AmEmpMaterialOperationLogMapper;
import com.ciicsh.gto.afsupportcenter.employmanagement.employservice.dao.AmEmpTaskMapper;
import com.ciicsh.gto.afsupportcenter.employmanagement.employservice.entity.AmEmpMaterial;
import com.ciicsh.gto.afsupportcenter.employmanagement.employservice.entity.AmEmpMaterialOperationLog;
import com.ciicsh.gto.afsupportcenter.employmanagement.employservice.entity.AmEmpTask;
import com.ciicsh.gto.afsupportcenter.util.DateUtil;
import com.ciicsh.gto.afsupportcenter.util.interceptor.authenticate.UserContext;
import com.ciicsh.gto.afsupportcenter.util.page.PageInfo;
import com.ciicsh.gto.afsupportcenter.util.page.PageKit;
import com.ciicsh.gto.afsupportcenter.util.page.PageRows;
import com.ciicsh.gto.afsystemmanagecenter.apiservice.api.SMUserInfoProxy;
import com.ciicsh.gto.afsystemmanagecenter.apiservice.api.dto.auth.SMUserInfoDTO;
import com.ciicsh.gto.employeecenter.apiservice.api.dto.HireMaterialBillDTO;
import com.ciicsh.gto.employeecenter.apiservice.api.dto.HireMaterialTransferRecordDTO;
import com.ciicsh.gto.employeecenter.apiservice.api.dto.JsonResult;
import com.ciicsh.gto.employeecenter.apiservice.api.proxy.SheetInfoProxy;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by zhangzhiwen on 2018/1/26.
 */

@Service
public class AmEmpMaterialServiceImpl extends ServiceImpl<AmEmpMaterialMapper, AmEmpMaterial> implements IAmEmpMaterialService {

    @Autowired
    private AmEmpMaterialOperationLogMapper operationLogMapper;

    @Autowired
    private SheetInfoProxy sheetInfoProxy;

    @Autowired
    private AmEmpTaskMapper amEmpTaskMapper;

    @Autowired
    private SMUserInfoProxy smUserInfoProxy;


    @Override
    @Transactional(
        rollbackFor = {Exception.class}
    )
    public PageRows<AmEmpMaterialBO> queryAmEmpMaterial(PageInfo pageInfo) {
        AmEmpMaterialBO amEmpMaterialBO = pageInfo.toJavaObject(AmEmpMaterialBO.class);

        AmEmpTask task = amEmpTaskMapper.selectById(amEmpMaterialBO.getEmpTaskId());

        PageRows<AmEmpMaterialBO> pageRows = PageKit.doSelectPage(pageInfo, () -> baseMapper.queryAmEmpMaterial(amEmpMaterialBO));
        if(task.getHireTaskId() == null){ // 以前的老任务单没有 交互的 hireTaskId
            return pageRows;
        }

        JsonResult<List<HireMaterialTransferRecordDTO>> jsonResult = sheetInfoProxy.getHireMaterialNewestTransferRecords(task.getHireTaskId());

        HireMaterialTransferRecordDTO dto = jsonResult.getData().get(0);
        if (dto != null && dto.getOperation() == 3) {
            if (pageRows.getRows().size() > 0) {
                Date d = DateUtil.localDateTimeToDate(pageRows.getRows().get(0).getSubmitterDate());
                if(dto.getOperateTime().before(d) || dto.getOperateTime().getTime() == d.getTime()){
                    return pageRows;
                }else{
                    Wrapper<AmEmpMaterial> wrapper = new EntityWrapper<>();
                    wrapper.eq("is_active", 1);
                    wrapper.eq("emp_task_id", amEmpMaterialBO.getEmpTaskId());
                    AmEmpMaterial material = new AmEmpMaterial();
                    material.setActive(false);
                    baseMapper.update(material, wrapper);
                }
            }
            JsonResult<List<HireMaterialBillDTO>> dtoResult = sheetInfoProxy.getHireMaterialBillList(task.getHireTaskId());
            List<HireMaterialBillDTO> dtoList = dtoResult.getData();
            if(dtoList != null && dtoList.size() > 0){
                String extension = "";
                String createdByDisName = "";
                com.ciicsh.gto.afsystemmanagecenter.apiservice.api.dto.JsonResult<List<SMUserInfoDTO>>
                smUserInfo = smUserInfoProxy.getUsersByUserId(dtoList.get(0).getCreatedBy());
                List<SMUserInfoDTO> userList = smUserInfo.getData();
                if(userList != null && userList.size() > 0){
                    extension = userList.get(0).getExtension();
                    createdByDisName = userList.get(0).getDisplayName();
                }
                List<AmEmpMaterial> amEmpMaterialsList = new ArrayList<>();
                for (HireMaterialBillDTO hire : dtoList) {
                    AmEmpMaterial amEmpMaterial = new AmEmpMaterial();
                    AmEmpMaterialBO bo = new AmEmpMaterialBO();
                    amEmpMaterial.setMaterialName(hire.getMaterialName());
                    amEmpMaterial.setSubmitterId(hire.getCreatedBy());
                    amEmpMaterial.setSubmitterName(createdByDisName);
                    amEmpMaterial.setSubmitterDate(DateUtil.dateToLocaleDateTime(dto.getOperateTime()));
                    amEmpMaterial.setEmployeeId(amEmpMaterialBO.getEmployeeId());
                    amEmpMaterial.setOperateType(1);
                    amEmpMaterial.setActive(true);
                    amEmpMaterial.setCreatedBy(UserContext.getUserId());
                    amEmpMaterial.setCreatedTime(LocalDateTime.now());
                    amEmpMaterial.setModifiedBy(UserContext.getUserId());
                    amEmpMaterial.setModifiedTime(LocalDateTime.now());
                    amEmpMaterial.setExtension(extension);
                    amEmpMaterial.setEmpTaskId(amEmpMaterialBO.getEmpTaskId());
                    amEmpMaterialsList.add(amEmpMaterial);
                }
                insertBatch(amEmpMaterialsList);
            }
            return pageRows;
        }else{
            return pageRows;
        }
    }

    @Override
    public List<AmEmpMaterialOperationLogBO> queryAmEmpMaterialOperationLogList(PageInfo pageInfo) {
        AmEmpMaterialBO amEmpMaterialBO = pageInfo.toJavaObject(AmEmpMaterialBO.class);
        AmEmpTask task = amEmpTaskMapper.selectById(amEmpMaterialBO.getEmpTaskId());
        List<AmEmpMaterialOperationLogBO> result = new ArrayList<>();
        if(task.getHireTaskId() == null){
            return result;
        }
        JsonResult<List<HireMaterialTransferRecordDTO>> jsonResult = sheetInfoProxy.getHireMaterialNewestTransferRecords(task.getHireTaskId());
        List<HireMaterialTransferRecordDTO> dtoList = jsonResult.getData();
        for (HireMaterialTransferRecordDTO dto:dtoList) {
            AmEmpMaterialOperationLogBO bo = new AmEmpMaterialOperationLogBO();
            bo.setRemark(dto.getRemark());
            bo.setOperationType(dto.getOperation());
            bo.setOperationTime(DateUtil.dateToLocaleDateTime(dto.getOperateTime()));
            bo.setOperationName(dto.getOperator());
            bo.setOperationBy(dto.getCreatedBy());
            result.add(bo);
        }
        return result;
    }

    @Override
    public List<AmEmpMaterialBO> queryAmEmpMaterialList(AmEmpMaterialBO amEmpMaterialBO) {
        return baseMapper.queryAmEmpMaterial(amEmpMaterialBO);
    }

    @Override
    public List<AmEmpMaterialOperationLogBO> queryAmEmpMaterialLogList(AmEmpMaterialBO amEmpMaterialBO) {
        if(amEmpMaterialBO.getEmpTaskId() == null){
            return null;
        }
        List<AmEmpMaterialOperationLogBO> result = new ArrayList<>();
        Wrapper<AmEmpMaterialOperationLog> wrapper = new EntityWrapper<>();
        wrapper.eq("is_active",1);
        wrapper.eq("emp_task_id",amEmpMaterialBO.getEmpTaskId());
        wrapper.orderBy("operation_time",false);
        List<AmEmpMaterialOperationLog> logList = operationLogMapper.selectList(wrapper);
        for (AmEmpMaterialOperationLog log:logList) {
            AmEmpMaterialOperationLogBO bo = new AmEmpMaterialOperationLogBO();
            BeanUtils.copyProperties(log,bo);
            result.add(bo);
        }
        return result;
    }

    @Override
    public PageRows<AmEmpMaterialBO> queryMaterialDic(PageInfo pageInfo) {
        return PageKit.doSelectPage(pageInfo,() -> baseMapper.queryMaterialDic());
    }

    @Override
    public List<AmEmpMaterialBO> queryMaterialDicList() {
        List<AmEmpMaterialBO> list = null;
        try {
            list =  baseMapper.queryMaterialDic();
        } catch (Exception e) {

        }
        List<AmEmpMaterialBO> listReturn = new ArrayList<>();
        List<String> stringList = ReasonUtil.getMaterialDic();

        for(int i=0;i<stringList.size();i++)
        {
            AmEmpMaterialBO temp = new AmEmpMaterialBO();
            temp.setMaterialName(stringList.get(i));
            listReturn.add(temp);
        }

        List<AmEmpMaterialBO> tempList = new ArrayList<>();
        if(null!=list&&list.size()>stringList.size()){
            for(AmEmpMaterialBO amEmpMaterialBO:list)
            {
               if(!stringList.contains(amEmpMaterialBO.getMaterialName()))
               {
                   tempList.add(amEmpMaterialBO);
               }
            }
        }
        if(tempList.size()>0)
        {
            listReturn.addAll(tempList);
        }

        return listReturn;
    }

    @Override
    public List<MaterialDTO> queryMaterialByTaskId(Long empTaskId) {
        List<AmEmpMaterialBO> list = baseMapper.queryMaterialByTaskId(empTaskId);

        List<MaterialDTO> materialDTOList = new ArrayList<>();

        DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        for(AmEmpMaterialBO amEmpMaterialBO:list){

            MaterialDTO materialDTO = new MaterialDTO();
            if(amEmpMaterialBO.getReceiveDate()!=null){
                materialDTO.setReceiveDateStr(df.format(amEmpMaterialBO.getReceiveDate()));
            }
            materialDTO.setReceiveId(amEmpMaterialBO.getReceiveId());
            materialDTO.setMaterialName(amEmpMaterialBO.getMaterialName());
            materialDTO.setReceiveName(amEmpMaterialBO.getReceiveName());

            materialDTOList.add(materialDTO);
        }
        return  materialDTOList;
    }

    @Override
    @Transactional(
        rollbackFor = {Exception.class}
    )
    public boolean updateMaterialByTaskId(MaterialUpdateDTO materialUpdateDTO) {


        AmEmpMaterial material = new AmEmpMaterial();
        material.setModifiedTime(LocalDateTime.now());
        AmEmpMaterialOperationLogBO logBO = new AmEmpMaterialOperationLogBO();
        logBO.setEmpTaskId(materialUpdateDTO.getEmpTaskId());
        logBO.setOperationType(materialUpdateDTO.getOperationType());
        logBO.setRemark(materialUpdateDTO.getRemark());
        if(materialUpdateDTO.getOperationType() == 1){
            material.setReceiveId(materialUpdateDTO.getReceiveId());
            material.setReceiveName(materialUpdateDTO.getReceiveName());
            material.setReceiveDate(DateUtil.dateToLocaleDate(materialUpdateDTO.getReceiveDate()));

            // 签收
            logBO.setOperationBy(materialUpdateDTO.getReceiveId());
            logBO.setOperationName(materialUpdateDTO.getReceiveName());
            logBO.setOperationTime(DateUtil.dateToLocaleDateTime(materialUpdateDTO.getReceiveDate()));
        }else if(materialUpdateDTO.getOperationType() == 2){
            material.setRejectId(materialUpdateDTO.getRejectId());
            material.setRejectName(materialUpdateDTO.getRejectName());
            material.setRejectDate(DateUtil.dateToLocaleDate(materialUpdateDTO.getRejectDate()));

            // 批退
            logBO.setOperationBy(materialUpdateDTO.getRejectId());
            logBO.setOperationName(materialUpdateDTO.getRejectName());
            logBO.setOperationTime(DateUtil.dateToLocaleDateTime(materialUpdateDTO.getRejectDate()));
        }
        Wrapper<AmEmpMaterial> wrapper = new EntityWrapper<>();
        wrapper.eq("is_active",1);
        wrapper.eq("emp_task_id", materialUpdateDTO.getEmpTaskId());
        baseMapper.update(material,wrapper);
        return insertAmEmpMaterialOperationLog(logBO);
    }

    @Override
    public boolean updateMaterialBatch(AmEmpMaterialBO amEmpMaterialBO) {
        Integer i = baseMapper.updateMaterialBatch(amEmpMaterialBO);
        if(i>0){
            return  true;
        }
        return false;
    }

    @Override
    public boolean insertAmEmpMaterialOperationLog(AmEmpMaterialOperationLogBO logBO) {
        Date nowDate = new Date();
        logBO.setCreatedBy(ReasonUtil.getUserId());
        logBO.setCreatedTime(nowDate);
        logBO.setModifiedBy(ReasonUtil.getUserId());
        logBO.setModifiedTime(nowDate);
        logBO.setIsActive(1);
        operationLogMapper.insert(logBO);
        return true;
    }

    @Override
    public MaterialOperationLogDTO queryMaterialLastOperationLog(String empTaskId) {
        Wrapper<AmEmpMaterialOperationLog> ew = new EntityWrapper<>();
        ew.eq("emp_task_id",empTaskId);
        ew.eq("is_active",1);
        ew.orderBy("operation_time",false);
        ew.last("limit 1");
        List<AmEmpMaterialOperationLog> list = operationLogMapper.selectList(ew);
        MaterialOperationLogDTO dto = new MaterialOperationLogDTO();
        if(list != null && list.size() > 0){
            BeanUtils.copyProperties(list.get(0),dto);
        }
        return dto;
    }

    @Override
    public List<MaterialOperationLogDTO> queryMaterialOperationLogList(String empTaskId) {
        List<MaterialOperationLogDTO> result = new ArrayList<>();
        Wrapper<AmEmpMaterialOperationLog> ew = new EntityWrapper<>();
        ew.eq("emp_task_id",empTaskId);
        ew.eq("is_active",1);
        ew.orderBy("operation_time",false);
        List<AmEmpMaterialOperationLog> list = operationLogMapper.selectList(ew);
        for (AmEmpMaterialOperationLog log:list) {
            MaterialOperationLogDTO dto = new MaterialOperationLogDTO();
            BeanUtils.copyProperties(log,dto);
            result.add(dto);
        }
        return result;
    }

    @Override
    public String receiveMaterial(String taskId, Integer operation, String remark) {
        HireMaterialTransferRecordDTO dto = new HireMaterialTransferRecordDTO();
        dto.setTaskId(taskId);
        dto.setOperation(operation);
        dto.setRemark(remark);
        dto.setOperator(UserContext.getUser().getDisplayName());
        dto.setOperateTime(new Date());
        return sheetInfoProxy.feedbackHireMaterialOperationInfo(dto).getData();
    }
}
