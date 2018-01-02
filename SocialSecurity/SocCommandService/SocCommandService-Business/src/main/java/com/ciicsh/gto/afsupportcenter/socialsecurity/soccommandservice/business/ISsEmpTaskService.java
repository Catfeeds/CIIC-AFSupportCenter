package com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.business;

import com.baomidou.mybatisplus.service.IService;
import com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.bo.SsEmpTaskBO;
import com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.entity.SsEmpTask;
import com.ciicsh.gto.afsupportcenter.util.page.PageInfo;
import com.ciicsh.gto.afsupportcenter.util.page.PageRows;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 本地社保的雇员任务单 服务类
 * </p>
 *
 * @author HuangXing
 * @since 2017-12-01
 */
public interface ISsEmpTaskService extends IService<SsEmpTask> {

    /**
     * 雇员日常操作查询
     *
     * @param pageInfo
     * @return
     */
    PageRows<SsEmpTaskBO> employeeOperatorQuery(PageInfo pageInfo);

    /**
     * 通过社保档案ID 查询历史任务单
     * @param empArchiveId
     * @return
     */
    List<SsEmpTask> queryTaskByEmpArchiveId(String empArchiveId);
}
