package com.ciicsh.gto.afsupportcenter.housefund.fundservice.business;

import com.baomidou.mybatisplus.service.IService;
import com.ciicsh.gto.afsupportcenter.housefund.fundservice.bo.HfComAccountPaymentWayBo;
import com.ciicsh.gto.afsupportcenter.housefund.fundservice.bo.HfComTaskBo;
import com.ciicsh.gto.afsupportcenter.housefund.fundservice.bo.HfComTaskEndTypeBo;
import com.ciicsh.gto.afsupportcenter.housefund.fundservice.bo.HfComTaskTaskStatusBo;
import com.ciicsh.gto.afsupportcenter.housefund.fundservice.entity.HfComTask;
import com.ciicsh.gto.afsupportcenter.util.page.PageInfo;
import com.ciicsh.gto.afsupportcenter.util.page.PageRows;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface HfComTaskService extends IService<HfComTask> {

    /**
     * 保存企业任务单
     * @param hfComTask
     * @return
     */
    Integer addComTask(HfComTask hfComTask);

    /**
     * 判断企业任务单是否存在
     * @return
     */
    Integer isExistComTask(String companyId, Integer hfType, Integer taskCategory);

    /**
     * 获得企业任务单列表
     * @param pageInfo
     * @return
     */
    PageRows<HfComTaskBo> queryCompanyTasks(PageInfo pageInfo);

    /**
     * 获得企业任务单列表
     * @param hfComTaskBo
     * @return
     */
    List<HfComTaskBo> getCompanyTasks(HfComTaskBo hfComTaskBo);

    /**
     * 获得企业任务单支付方式数据
     * @return
     */
    List<HfComAccountPaymentWayBo> queryComTaskPaymentWayData();

    /**
     * 获得企业任务单任务状态数据
     * @return
     */
    List<HfComTaskTaskStatusBo> queryComTaskTaskStatusData();

    /**
     * 获得企业任务单终止类型数据
     * @return
     */
    List<HfComTaskEndTypeBo> queryComTaskEndTypeData();

    /**
     * 添加/更新企业任务单及相关表单
     * @param map
     * @return
     */
    boolean upsertCompanyTaskRelated(Map<String, String> map);

    /**
     * 添加/更新企业任务单
     * @param map
     * @return
     */
    boolean upsertCompanyTask(Map<String, String> map);

    boolean stopCompAccountTask(Map<String, String> map);

    /**
     * 批退
     * @param map
     * @return
     */
    boolean rejection(Map<String, String> map);

}
