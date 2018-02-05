package com.ciicsh.gto.afsupportcenter.housefund.siteservice.business.impl;

import com.ciicsh.gto.afsupportcenter.housefund.siteservice.bo.HfArchiveBasePeriodBo;
import com.ciicsh.gto.afsupportcenter.housefund.siteservice.bo.HfComAccountBo;
import com.ciicsh.gto.afsupportcenter.housefund.siteservice.bo.HfEmpArchiveBo;
import com.ciicsh.gto.afsupportcenter.housefund.siteservice.entity.HfEmpArchive;
import com.ciicsh.gto.afsupportcenter.housefund.siteservice.dao.HfEmpArchiveMapper;
import com.ciicsh.gto.afsupportcenter.housefund.siteservice.business.HfEmpArchiveService;
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

    @Autowired
    HfEmpArchiveMapper hfEmpArchiveMapper;


   public PageRows<HfEmpArchiveBo> queryEmpArchive(PageInfo pageInfo){
       HfEmpArchiveBo dto = pageInfo.toJavaObject(HfEmpArchiveBo.class);
       return  PageKit.doSelectPage(pageInfo, () ->  hfEmpArchiveMapper.queryEmpArchive(dto));
    }

    public Map<String, Object> viewEmpArchiveInfo(String empArchiveId,String companyId){
        Map<String,Object> resultMap=new HashMap<String,Object>();

        HfEmpArchiveBo viewEmpArchiveBo= hfEmpArchiveMapper.viewEmpArchive(empArchiveId);
        HfArchiveBasePeriodBo viewEmpPeriodBo= hfEmpArchiveMapper.viewEmpPeriod(empArchiveId,"1");
        HfArchiveBasePeriodBo viewEmpPeriodAddBo= hfEmpArchiveMapper.viewEmpPeriod(empArchiveId,"2");
        HfComAccountBo viewComAccountBo= hfEmpArchiveMapper.viewComAccount(companyId);
        List listEmpTaskPeriodBo=hfEmpArchiveMapper.listEmpTaskPeriod(empArchiveId,"1");//基本
        List listEmpTaskPeriodAddBo=hfEmpArchiveMapper.listEmpTaskPeriod(empArchiveId,"2");//补充
        List listEmpTransferBo= hfEmpArchiveMapper.listEmpTransfer(empArchiveId);
        resultMap.put("viewEmpArchive",viewEmpArchiveBo);
        resultMap.put("viewEmpPeriod",viewEmpPeriodBo);
        resultMap.put("viewEmpPeriodAdd",viewEmpPeriodAddBo);
        resultMap.put("viewComAccount",viewComAccountBo);
        resultMap.put("listEmpTaskPeriod",listEmpTaskPeriodBo);
        resultMap.put("listEmpTaskPeriodAdd",listEmpTaskPeriodAddBo);
        resultMap.put("listEmpTransfer",listEmpTransferBo);
      return resultMap;
    }
    public boolean saveComAccunt(Map<String,String> updateDto){
        try{
            HfEmpArchive empArchive=new HfEmpArchive();
            empArchive.setEmpArchiveId(Long.valueOf(updateDto.get("empArchiveId")));
            empArchive.setHfEmpAccount(updateDto.get("hfEmpAccount"));
            hfEmpArchiveMapper.updateById(empArchive);
            if (Optional.ofNullable(updateDto.get("empArchiveIdBc")).isPresent()){
                empArchive=new HfEmpArchive();
                empArchive.setEmpArchiveId(Long.valueOf(updateDto.get("empArchiveIdBc")));
                empArchive.setHfEmpAccount(updateDto.get("hfEmpAccountBc"));
                hfEmpArchiveMapper.updateById(empArchive);
            }
        }catch (Exception e){
            return false;
        }
        return true;
    }

}