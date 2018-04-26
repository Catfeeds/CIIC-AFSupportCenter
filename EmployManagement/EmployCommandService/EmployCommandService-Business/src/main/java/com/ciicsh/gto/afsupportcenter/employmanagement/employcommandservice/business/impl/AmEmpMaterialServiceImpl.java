package com.ciicsh.gto.afsupportcenter.employmanagement.employcommandservice.business.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.ciicsh.gto.afsupportcenter.employmanagement.employcommandservice.api.dto.MaterialDTO;
import com.ciicsh.gto.afsupportcenter.employmanagement.employcommandservice.api.dto.MaterialUpdateDTO;
import com.ciicsh.gto.afsupportcenter.employmanagement.employcommandservice.bo.AmEmpMaterialBO;
import com.ciicsh.gto.afsupportcenter.employmanagement.employcommandservice.business.IAmEmpMaterialService;
import com.ciicsh.gto.afsupportcenter.employmanagement.employcommandservice.business.utils.ReasonUtil;
import com.ciicsh.gto.afsupportcenter.employmanagement.employcommandservice.dao.AmEmpMaterialMapper;
import com.ciicsh.gto.afsupportcenter.employmanagement.employcommandservice.entity.AmEmpMaterial;
import com.ciicsh.gto.afsupportcenter.util.page.PageInfo;
import com.ciicsh.gto.afsupportcenter.util.page.PageKit;
import com.ciicsh.gto.afsupportcenter.util.page.PageRows;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by zhangzhiwen on 2018/1/26.
 */

@Service
public class AmEmpMaterialServiceImpl extends ServiceImpl<AmEmpMaterialMapper, AmEmpMaterial> implements IAmEmpMaterialService {
    @Override
    public PageRows<AmEmpMaterialBO> queryAmEmpMaterial(PageInfo pageInfo) {
        AmEmpMaterialBO amEmpMaterialBO = pageInfo.toJavaObject(AmEmpMaterialBO.class);
        return PageKit.doSelectPage(pageInfo,() -> baseMapper.queryAmEmpMaterial(amEmpMaterialBO));
    }

    @Override
    public List<AmEmpMaterialBO> queryAmEmpMaterialList(AmEmpMaterialBO amEmpMaterialBO) {
        return baseMapper.queryAmEmpMaterial(amEmpMaterialBO);
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
        List<MaterialDTO> list = baseMapper.queryMaterialByTaskId(empTaskId);

        for(MaterialDTO materialDTO:list){
            DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            if(materialDTO.getReceiveDate()!=null){
                materialDTO.setReceiveDateStr(df.format(materialDTO.getReceiveDate()));
            }
        }
        return  list;
    }

    @Override
    public boolean updateMaterialByTaskId(MaterialUpdateDTO materialUpdateDTO) {

        Integer i = baseMapper.updateMaterialByTaskId(materialUpdateDTO);

        if(i>0){
            return  true;
        }
        return false;
    }

}
