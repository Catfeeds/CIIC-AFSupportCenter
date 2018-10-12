package com.ciicsh.gto.afsupportcenter.housefund.fundservice.business.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.baomidou.mybatisplus.toolkit.CollectionUtils;
import com.ciicsh.gto.afsupportcenter.housefund.fundservice.bo.*;
import com.ciicsh.gto.afsupportcenter.housefund.fundservice.business.HfEmpArchiveService;
import com.ciicsh.gto.afsupportcenter.housefund.fundservice.constant.HfEmpArchiveConstant;
import com.ciicsh.gto.afsupportcenter.housefund.fundservice.dao.HfEmpArchiveMapper;
import com.ciicsh.gto.afsupportcenter.housefund.fundservice.dto.EmpAccountImpXsl;
import com.ciicsh.gto.afsupportcenter.housefund.fundservice.entity.HfEmpArchive;
import com.ciicsh.gto.afsupportcenter.util.StringUtil;
import com.ciicsh.gto.afsupportcenter.util.interceptor.authenticate.UserContext;
import com.ciicsh.gto.afsupportcenter.util.page.PageInfo;
import com.ciicsh.gto.afsupportcenter.util.page.PageKit;
import com.ciicsh.gto.afsupportcenter.util.page.PageRows;
import com.ciicsh.gto.afsupportcenter.util.web.response.JsonResult;
import com.ciicsh.gto.afsupportcenter.util.web.response.JsonResultKit;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * <p>
 * 雇员本地公积金档案主表,
 * 由中智代缴过社保的雇员在此表必有一条记录，如果雇员跳槽到另外一家客户，就会在此表产 服务实现类
 * </p>
 */
@Service
public class HfEmpArchiveServiceImpl extends ServiceImpl<HfEmpArchiveMapper, HfEmpArchive> implements HfEmpArchiveService {
    @Autowired
    HfEmpArchiveService hfEmpArchiveService;

    public PageRows<HfEmpArchiveBo> queryEmpArchive(PageInfo pageInfo) {
        HfEmpArchiveBo dto = pageInfo.toJavaObject(HfEmpArchiveBo.class);
        return PageKit.doSelectPage(pageInfo, () -> baseMapper.queryEmpArchive(dto));
    }

    public Map<String, Object> viewEmpArchiveInfo(String empArchiveId, String companyId,String employeeId) {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        HfEmpArchiveBo viewEmpArchiveBo=null;
        List listEmpTransferBo = null;
        if(empArchiveId == null){
            viewEmpArchiveBo = baseMapper.viewEmpInfo(companyId,employeeId);
            listEmpTransferBo = baseMapper.listEmpTransfer(employeeId);

        }else{
            viewEmpArchiveBo = baseMapper.viewEmpArchive(empArchiveId);
            HfArchiveBasePeriodBo viewEmpPeriodBo = baseMapper.viewEmpPeriod(empArchiveId, "1");//基本
            HfArchiveBasePeriodBo viewEmpPeriodAddBo = baseMapper.viewEmpPeriod(empArchiveId, "2");//补充
            List<HfEmpTaskPeriodBo> listEmpTaskPeriodBo = baseMapper.listEmpTaskPeriod(empArchiveId, "1");//基本
            List<HfEmpTaskPeriodBo> listEmpTaskPeriodAddBo = baseMapper.listEmpTaskPeriod(empArchiveId, "2");//补充
            listEmpTransferBo = baseMapper.listEmpTransfer(employeeId);
            /*
            viewEmpPeriodBo.setEndMonth("");
            List<HfEmpTaskPeriodBo> taskEndMonth=listEmpTaskPeriodBo.stream().filter(HfEmpTaskPeriodBo->HfEmpTaskPeriodBo.getTaskCategory().).limit(1).collect(Collectors.toList());
            if(!taskEndMonth.isEmpty()){
                if (viewEmpPeriodBo == null){
                    viewEmpPeriodBo = new HfArchiveBasePeriodBo();
                }
                viewEmpPeriodBo.setEndMonth(taskEndMonth.get(0).getTaskEndMonth());
            }
            viewEmpPeriodAddBo.setEndMonth("");
            List<HfEmpTaskPeriodBo> taskEndMonthAdd=listEmpTaskPeriodBo.stream().filter(HfEmpTaskPeriodBo->HfEmpTaskPeriodBo.getTaskCategory().equals("4")).limit(1).collect(Collectors.toList());
            if(!taskEndMonthAdd.isEmpty()){
                if (viewEmpPeriodAddBo == null){
                    viewEmpPeriodAddBo = new HfArchiveBasePeriodBo();
                }
                viewEmpPeriodAddBo.setEndMonth(taskEndMonthAdd.get(0).getTaskEndMonth());
            }
*/
            resultMap.put("viewEmpPeriod", viewEmpPeriodBo);
            resultMap.put("viewEmpPeriodAdd", viewEmpPeriodAddBo);
            resultMap.put("listEmpTaskPeriod", listEmpTaskPeriodBo);
            resultMap.put("listEmpTaskPeriodAdd", listEmpTaskPeriodAddBo);
            resultMap.put("listEmpTransfer", listEmpTransferBo);

        }
        HfComAccountBo viewComAccountBo = baseMapper.viewComAccount(companyId);
        if (viewComAccountBo == null){
            viewComAccountBo = new HfComAccountBo();
        }
        HfEmpComBO hfEmpComBO = baseMapper.fetchManager(companyId, viewEmpArchiveBo.getEmployeeId());
        if(hfEmpComBO != null){
            org.springframework.beans.BeanUtils.copyProperties(hfEmpComBO, viewComAccountBo);
        }
        if(viewComAccountBo.getCompanyId() == null){
            viewComAccountBo.setCompanyId(viewEmpArchiveBo.getCompanyId());
            viewComAccountBo.setTitle(viewEmpArchiveBo.getTitle());
        }

        resultMap.put("viewEmpArchive", viewEmpArchiveBo);
        resultMap.put("viewComAccount", viewComAccountBo);
        resultMap.put("listEmpTransfer", listEmpTransferBo);
        return resultMap;
    }

    public JsonResult saveComAccount(Map<String, String> updateDto) {
        boolean isEmpAccountNotExists=true;
        try {
            isEmpAccountNotExists = hfEmpArchiveService.isEmpAccountNotExists(updateDto.get("hfEmpAccount"),1,updateDto.get("employeeId"),false);
            if(isEmpAccountNotExists==false){
                return JsonResultKit.of(0, "基本公积金重复！");
            }
            baseMapper.updateArchiveEmpAccount(updateDto.get("hfEmpAccount"), Long.valueOf(updateDto.get("empArchiveId")));
            if (Optional.ofNullable(updateDto.get("empArchiveIdBc")).isPresent()) {
                isEmpAccountNotExists = hfEmpArchiveService.isEmpAccountNotExists(updateDto.get("hfEmpAccountBc"),2,updateDto.get("employeeId"),false);
                if(isEmpAccountNotExists==false){
                    return JsonResultKit.of(0, "补充公积金重复！");
                }
                baseMapper.updateArchiveEmpAccount(updateDto.get("hfEmpAccountBc"), Long.valueOf(updateDto.get("empArchiveIdBc")));
            }
        } catch (Exception e) {
            return JsonResultKit.of(0, "保存异常！");
        }
        return JsonResultKit.of(200, "保存成功！");
    }

    public JsonResult xlsImportEmpAccount(List<EmpAccountImpXsl> xls, String fileName) {
        StringBuffer retStr = new StringBuffer();
        int type = 0;
        try {
            for (EmpAccountImpXsl xlsRecord : xls) {
                if (StringUtils.isBlank(xlsRecord.getEmpAccount())
                    || xlsRecord.getEmpAccount().length() > 20
                    || !StringUtil.validateInt(xlsRecord.getEmpAccount())) {
                    type = 1;
                    retStr.append(xlsRecord.getEmpName());
                    break;
                }
                Map map = baseMapper.selectEmpByCardIdAndName(xlsRecord.getEmpName(), xlsRecord.getIdNum());
                if (map == null) {
                    type = 2;
                    retStr.append(xlsRecord.getEmpName());
                    break;
                }
                boolean isEmpAccountNotExists = hfEmpArchiveService.isEmpAccountNotExists(xlsRecord.getEmpAccount(),1, map.get("employee_id").toString(),false);
                if(isEmpAccountNotExists==false){
                    type = 4;
                    retStr.append(xlsRecord.getEmpName());
                    break;
                }
                HfEmpArchive hfEmpArchive = new HfEmpArchive();
                hfEmpArchive.setHfEmpAccount(xlsRecord.getEmpAccount());
                hfEmpArchive.setEmpArchiveId((Long) map.get("emp_archive_id"));
                hfEmpArchive.setModifiedBy(UserContext.getUser().getDisplayName());
                hfEmpArchive.setModifiedTime(LocalDateTime.now());
                baseMapper.updateById(hfEmpArchive);
            }
        } catch (Exception e) {
            type = 3;
        }
        String ret = "";
        switch (type) {
            case 1:
                ret = retStr.toString() + "，导入的公积金账号为空或者数字超过长度。";
                break;
            case 2:
                ret = retStr.toString() + "，根据身份证号和姓名无法从系统中找到对应的雇员。";
                break;
            case 3:
                ret = "保存导出数据是发生异常！";
                break;
            case 4:
                ret = retStr.toString() + "基本公积金账号重复！";
                break;
        }
        if (type == 0) {
            return JsonResultKit.of(0, "导入成功！");
        } else {
            return JsonResultKit.of(1, "导入失败!原因： \n" + ret);
        }
    }

    @Override
    public int deleteHfEmpArchiveByEmpTaskIds(List<Long> empTaskIdList) {
        return baseMapper.deleteHfEmpArchiveByEmpTaskIds(empTaskIdList);
    }

    @Override
    public Map queryHfEmpArchiveByEmpTaskIds(List<Long> empTaskIdList) {
        return baseMapper.queryHfEmpArchiveByEmpTaskIds(empTaskIdList);
    }

    @Override
    public String getEmpAccountByEmployeeId(String employeeId, Integer hfType) {
        return baseMapper.getEmpAccountByEmployeeId(employeeId, hfType);
    }

    @Override
    public List<HfEmpInfoBO> getHfEmpArchiveInfo(List<HfEmpInfoParamBO> paramBoList) {
        List<HfEmpInfoBO> resultBoList = new ArrayList<>();
        for (HfEmpInfoParamBO paramBO : paramBoList) {
            // 如果detailBOList查无数据，MapperMethod.class返回的是一个new ArrayList<>()
            List<HfEmpInfoDetailBO> detailBOList = baseMapper.getHfEmpInfo(paramBO.getEmployeeId(), paramBO.getCompanyId(), paramBO.getHfMonthBelong());
            BigDecimal empAmountTotal = BigDecimal.ZERO;
            // detailBOList不会出现null，如果查无数据，detailBOList.size()==0,返回查询入参
            for (HfEmpInfoDetailBO detailBO : detailBOList) {
                empAmountTotal = empAmountTotal.add(detailBO.getEmpAmount());
            }
            HfEmpInfoBO resultBO = new HfEmpInfoBO();
            resultBO.setEmployeeId(parseValue(paramBO.getEmployeeId()));
            resultBO.setCompanyId(parseValue(paramBO.getCompanyId()));
            resultBO.setHfMonthBelong(parseValue(paramBO.getHfMonthBelong()));
            resultBO.setHfMonth(detailBOList != null && detailBOList.size() > 0 ? detailBOList.get(0).getHfMonth() : "");
            resultBO.setEmpAmountTotal(empAmountTotal);
            resultBO.setHfEmpInfoDetailBOList(detailBOList);
            resultBoList.add(resultBO);
        }
        return resultBoList;
    }

    @Override
    public HfEmpInfoBO getHfEmpInfoById(String companyId, String employeeId) {
        HfEmpInfoBO hfEmpInfoBO = baseMapper.getHfEmpInfoById(companyId,employeeId);
        return hfEmpInfoBO;
    }

    @Override
    public boolean isEmpAccountNotExists(String empAccount, int hfType, String employeeId, boolean isIncludeClosed) {
        Wrapper<HfEmpArchive> wrapper = new EntityWrapper<>();
        wrapper.where("is_active = 1")
            .and("hf_emp_account = {0}", empAccount)
            .and("hf_type = {0}", hfType);
        wrapper.orderBy("created_time", false);
        List<HfEmpArchive> hfEmpArchiveList = selectList(wrapper);

        if (CollectionUtils.isNotEmpty(hfEmpArchiveList)) {
            HfEmpArchive hfEmpArchive = hfEmpArchiveList.get(0);

            if (StringUtils.isNotEmpty(employeeId) && employeeId.equals(hfEmpArchive.getEmployeeId())
                && (!isIncludeClosed || hfEmpArchive.getArchiveStatus() == HfEmpArchiveConstant.ARCHIVE_STATUS_CLOSED)
                ) {
                return true;
            }

            return false;
        }
        return true;
    }

    private String parseValue(String value) {
        return value == null ? "" : value;
    }
}
