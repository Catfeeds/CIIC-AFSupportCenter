package com.ciicsh.gto.afsupportcenter.employmanagement.employcommandservice.business.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.ciicsh.gto.afsupportcenter.employmanagement.employcommandservice.api.dto.MaterialDTO;
import com.ciicsh.gto.afsupportcenter.employmanagement.employcommandservice.api.dto.MaterialUpdateDTO;
import com.ciicsh.gto.afsupportcenter.employmanagement.employcommandservice.bo.AmEmpMaterialBO;
import com.ciicsh.gto.afsupportcenter.employmanagement.employcommandservice.business.IAmEmpMaterialService;
import com.ciicsh.gto.afsupportcenter.employmanagement.employcommandservice.dao.AmEmpMaterialMapper;
import com.ciicsh.gto.afsupportcenter.employmanagement.employcommandservice.entity.AmEmpMaterial;
import com.ciicsh.gto.afsupportcenter.util.page.PageInfo;
import com.ciicsh.gto.afsupportcenter.util.page.PageKit;
import com.ciicsh.gto.afsupportcenter.util.page.PageRows;
import org.springframework.stereotype.Service;

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
    public List<MaterialDTO> queryMaterialByTaskId(Long empTaskId) {
        return baseMapper.queryMaterialByTaskId(empTaskId);
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
