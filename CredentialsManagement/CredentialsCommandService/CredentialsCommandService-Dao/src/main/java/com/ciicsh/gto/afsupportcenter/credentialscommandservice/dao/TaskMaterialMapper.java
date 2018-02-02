package com.ciicsh.gto.afsupportcenter.credentialscommandservice.dao;


import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.ciicsh.gto.afsupportcenter.credentialscommandservice.entity.po.TaskMaterial;
import org.springframework.stereotype.Repository;

/**
 * <p>
  * 证件任务单材料 Mapper 接口
 * </p>
 *
 * @author guwei
 * @since 2018-01-15
 */
@Repository
public interface TaskMaterialMapper extends BaseMapper<TaskMaterial> {

    /**
     * 更新材料收缴信息
     * @param taskMaterial
     * @return
     */
    int updateTaskMaterials(TaskMaterial taskMaterial);
}