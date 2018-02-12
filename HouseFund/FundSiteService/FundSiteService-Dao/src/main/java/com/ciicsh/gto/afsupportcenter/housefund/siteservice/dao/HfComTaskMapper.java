package com.ciicsh.gto.afsupportcenter.housefund.siteservice.dao;

import com.ciicsh.gto.afsupportcenter.housefund.siteservice.bo.HfComTaskBo;
import com.ciicsh.gto.afsupportcenter.housefund.siteservice.entity.HfComTask;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 * 企业任务单总表：有关于企业所有任务单的表单字段都集中记录当前表
 Com：公司简写 Mapper 接口
 * </p>
 *
 * @author 沈健
 * @since 2018-02-07
 */
@Repository
public interface HfComTaskMapper extends BaseMapper<HfComTask> {

    public List<HfComTaskBo> queryNoProcessCompanyTask(HfComTaskBo hfComTaskBo);

}
