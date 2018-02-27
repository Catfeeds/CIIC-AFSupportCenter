package com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.entity.SsEmpMaterial;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 雇员材料收缴 Mapper 接口
 * </p>
 *
 * @author HuangXing
 * @since 2017-12-01
 */
public interface SsEmpMaterialMapper extends BaseMapper<SsEmpMaterial> {

    List<SsEmpMaterial> accAndEmpDetailQuery(@Param("empTaskId") String empTaskId);

}
