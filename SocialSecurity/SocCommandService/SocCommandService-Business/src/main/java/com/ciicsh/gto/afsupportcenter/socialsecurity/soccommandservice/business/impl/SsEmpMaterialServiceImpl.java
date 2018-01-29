package com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.business.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.business.SsEmpMaterialService;
import com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.dao.SsEmpMaterialMapper;
import com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.entity.SsEmpMaterial;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 雇员材料收缴 服务实现类
 * </p>
 *
 * @author HuangXing
 * @since 2017-12-01
 */
@Service
public class SsEmpMaterialServiceImpl extends ServiceImpl<SsEmpMaterialMapper, SsEmpMaterial> implements SsEmpMaterialService {
    /**
     *  雇员特殊任务办理材料页面详细信息
     * @param empTaskId
     * @return
     */
    @Override
    public List<SsEmpMaterial> accAndEmpDetailQuery(String empTaskId){
        return baseMapper.accAndEmpDetailQuery(empTaskId);
    }
}
