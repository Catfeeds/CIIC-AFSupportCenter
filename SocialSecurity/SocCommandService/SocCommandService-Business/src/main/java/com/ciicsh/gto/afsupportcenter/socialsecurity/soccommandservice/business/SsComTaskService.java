package com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.business;

import com.baomidou.mybatisplus.service.IService;
import com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.bo.SsComTaskBO;
import com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.entity.SsAccountComRelation;
import com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.entity.SsAccountRatio;
import com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.entity.SsComAccount;
import com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.entity.SsComTask;
import com.ciicsh.gto.afsupportcenter.util.page.PageInfo;
import com.ciicsh.gto.afsupportcenter.util.page.PageRows;

import java.util.List;

/**
 * <p>
 * 独立库客户任务单 服务类
 * </p>
 *
 * @author HuangXing
 * @since 2017-12-01
 */
public interface SsComTaskService extends IService<SsComTask> {
    /**
     * 获得企业任务单 未处理
     * xsj
     * @return
     */
    public PageRows<SsComTaskBO>  queryNoProgressCompanyTask(PageInfo pageInfo);


    /**
     * 获得企业任务单未处理导出数据
     */
    List<SsComTaskBO> getNoProgressCompanyTasks(SsComTaskBO taskBo);

    /**
     * 获得企业任务单 处理中
     * @param pageInfo
     * @return
     */
    public PageRows<SsComTaskBO> queryProgressingCompanyTask(PageInfo pageInfo);


    /**
     * 获得企业任务单处理中导出数据
     */
    List<SsComTaskBO> getProgressingCompanyTasks(SsComTaskBO taskBo);

    /**
     * 获得企业任务单 已完成
     * @param pageInfo
     * @return
     */
    public PageRows<SsComTaskBO> queryFinshedCompanyTask(PageInfo pageInfo);

    /**
     * 获得企业任务单 批退
     * @param pageInfo
     * @return
     */
    public PageRows<SsComTaskBO> queryRefusedCompanyTask(PageInfo pageInfo);

    /**
     * 批量修改 批退任务
     * @param ssComTaskList
     * @return
     */
    public boolean updatePatchRefuseTask(List<SsComTask> ssComTaskList);

    /**
     *  查询企业信息和材料信息
     * @param SsComTaskBO
     * @return
     */
    public SsComTaskBO queryComInfoAndMaterial(SsComTaskBO SsComTaskBO);

    /**
     *  查询企业信息和 前道传过来的JSON（包含社保截止和付款方式）
     * @param ssComTaskBO
     * @return
     */
    public SsComTaskBO queryComInfoAndPayWay(SsComTaskBO ssComTaskBO);


    /**
     * 企业任务开户办理 在内做事物
     */
    public boolean addOrUpdateCompanyTask(SsComTask ssComTask, SsComAccount ssComAccount, SsAccountRatio ssAccountRatio,SsAccountComRelation ssAccountComRelation);

    /**
     * 查询账户信息 和材料信息
     * @param ssComTaskBO
     * @return
     */
    public SsComTaskBO queryAccountInfoAndMaterial(SsComTaskBO ssComTaskBO);

    /**
     * 更新或者处理任务 终止 转移 变更
     * @param ssComTaskBO
     * @param object
     * @return
     */
    public boolean updateOrHandlerTask(SsComTaskBO ssComTaskBO, Object object);

    /**
     * 任务单撤销
     * @param ssComTask
     * @return
     */
    public int updateTaskStatusForRevoke(SsComTask ssComTask);

    /**
     * 判断企业任务单是否存在
     *
     * @param ssComTask
     * @return
     */
    public int countComTaskByCond(SsComTaskBO ssComTask);

    boolean insertComTask(SsComTask ssComTask);
}
