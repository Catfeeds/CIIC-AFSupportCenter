package com.ciicsh.gto.afsupportcenter.housefund.fundservice.business.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.ciicsh.gto.afsupportcenter.housefund.fundservice.bo.HfArchiveBasePeriodBo;
import com.ciicsh.gto.afsupportcenter.housefund.fundservice.bo.HfComAccountBo;
import com.ciicsh.gto.afsupportcenter.housefund.fundservice.bo.HfEmpArchiveBo;
import com.ciicsh.gto.afsupportcenter.housefund.fundservice.dto.EmpAccountImpXsl;
import com.ciicsh.gto.afsupportcenter.housefund.fundservice.entity.HfEmpArchive;
import com.ciicsh.gto.afsupportcenter.housefund.fundservice.dao.HfEmpArchiveMapper;
import com.ciicsh.gto.afsupportcenter.housefund.fundservice.business.HfEmpArchiveService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.ciicsh.gto.afsupportcenter.util.page.PageInfo;
import com.ciicsh.gto.afsupportcenter.util.page.PageKit;
import com.ciicsh.gto.afsupportcenter.util.page.PageRows;
import com.ciicsh.gto.afsupportcenter.util.web.response.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * <p>
 * 雇员本地公积金档案主表,
由中智代缴过社保的雇员在此表必有一条记录，如果雇员跳槽到另外一家客户，就会在此表产 服务实现类
 * </p>
 */
@Service
public class HfEmpArchiveServiceImpl extends ServiceImpl<HfEmpArchiveMapper, HfEmpArchive> implements HfEmpArchiveService {

   public PageRows<HfEmpArchiveBo> queryEmpArchive(PageInfo pageInfo){
       HfEmpArchiveBo dto = pageInfo.toJavaObject(HfEmpArchiveBo.class);
       return  PageKit.doSelectPage(pageInfo, () ->  baseMapper.queryEmpArchive(dto));
    }

    public Map<String, Object> viewEmpArchiveInfo(String empArchiveId,String companyId){
        Map<String,Object> resultMap=new HashMap<String,Object>();

        HfEmpArchiveBo viewEmpArchiveBo= baseMapper.viewEmpArchive(empArchiveId);
        HfArchiveBasePeriodBo viewEmpPeriodBo= baseMapper.viewEmpPeriod(empArchiveId,"1");
        HfArchiveBasePeriodBo viewEmpPeriodAddBo= baseMapper.viewEmpPeriod(empArchiveId,"2");
        HfComAccountBo viewComAccountBo= baseMapper.viewComAccount(companyId);
        List listEmpTaskPeriodBo=baseMapper.listEmpTaskPeriod(empArchiveId,"1");//基本
        List listEmpTaskPeriodAddBo=baseMapper.listEmpTaskPeriod(empArchiveId,"2");//补充
        List listEmpTransferBo= baseMapper.listEmpTransfer(empArchiveId);
        resultMap.put("viewEmpArchive",viewEmpArchiveBo);
        resultMap.put("viewEmpPeriod",viewEmpPeriodBo);
        resultMap.put("viewEmpPeriodAdd",viewEmpPeriodAddBo);
        resultMap.put("viewComAccount",viewComAccountBo);
        resultMap.put("listEmpTaskPeriod",listEmpTaskPeriodBo);
        resultMap.put("listEmpTaskPeriodAdd",listEmpTaskPeriodAddBo);
        resultMap.put("listEmpTransfer",listEmpTransferBo);
      return resultMap;
    }
    public boolean saveComAccount(Map<String,String> updateDto){
        try{
            HfEmpArchive empArchive=new HfEmpArchive();
            empArchive.setEmpArchiveId(Long.valueOf(updateDto.get("empArchiveId")));
            empArchive.setHfEmpAccount(updateDto.get("hfEmpAccount"));
            baseMapper.updateById(empArchive);
            if (Optional.ofNullable(updateDto.get("empArchiveIdBc")).isPresent()){
                empArchive=new HfEmpArchive();
                empArchive.setEmpArchiveId(Long.valueOf(updateDto.get("empArchiveIdBc")));
                empArchive.setHfEmpAccount(updateDto.get("hfEmpAccountBc"));
                baseMapper.updateById(empArchive);
            }
        }catch (Exception e){
            return false;
        }
        return true;
    }
    public String xlsImportEmpAccount(List<EmpAccountImpXsl> xls, String fileName){
        StringBuffer retStr= new StringBuffer();
        xls.forEach(
            xlsRecord->{
                Map map=baseMapper.selectEmpByCardIdAndName(xlsRecord.getEmpName(),xlsRecord.getIdNum());
                if (map==null){
                    retStr.append(xlsRecord.getEmpName()).append("|");
                    return;
                }
                HfEmpArchive hfEmpArchive=new HfEmpArchive();
                hfEmpArchive.setHfEmpAccount(xlsRecord.getEmpAccount());
                hfEmpArchive.setEmpArchiveId((Long) map.get("emp_archive_id"));
                baseMapper.updateById(hfEmpArchive);
                System.out.println(xlsRecord.getEmpAccount());
            }
        );
        return retStr.toString();
    }

    @Override
    public int deleteHfEmpArchiveByEmpTaskIds(List<Long> empTaskIdList) {
        return baseMapper.deleteHfEmpArchiveByEmpTaskIds(empTaskIdList);
    }

    @Override
    public Map queryHfEmpArchiveByEmpTaskIds(List<Long> empTaskIdList) {
        return baseMapper.queryHfEmpArchiveByEmpTaskIds(empTaskIdList);
    }
}