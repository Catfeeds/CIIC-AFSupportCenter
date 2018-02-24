package com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.business;

import com.baomidou.mybatisplus.service.IService;
import com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.entity.SsEmpMaterial;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 雇员材料收缴 服务类
 * </p>
 *
 * @author HuangXing
 * @since 2017-12-01
 */
public interface SsEmpMaterialService extends IService<SsEmpMaterial> {
    /**
     *  雇员特殊任务办理材料页面详细信息
     * @param empTaskId
     * @return
     */
    List<SsEmpMaterial> accAndEmpDetailQuery(@Param("empTaskId") String empTaskId);
}
