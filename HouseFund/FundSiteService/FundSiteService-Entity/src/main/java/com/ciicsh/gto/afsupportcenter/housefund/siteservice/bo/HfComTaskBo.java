package com.ciicsh.gto.afsupportcenter.housefund.siteservice.bo;

import com.ciicsh.gto.afsupportcenter.housefund.siteservice.entity.HfComTask;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class HfComTaskBo extends HfComTask {

    /**
     * 任务单id
     */
    private Long comTaskId;

    /**
     * 1 开户 2 转入  3 变更 4 终止 5销户
     */
    private String taskCategoryName;

    /**
     * 1 基本公积金、2 补充公积金
     */
    private String hfTypeName;

    /**
     * 客户Id
     */
    private String companyId;

    /**
     * 客户名称
     */
    private String companyName;

    /**
     * 任务处理人用户Id
     */
    private String handleUserId;

    /**
     * 经办人姓名
     */
    private String handleUserName;

    /**
     * 发起人用户名
     */
    private String submitterId;

    /**
     * 发起人姓名
     */
    private String submitterName;

    /**
     * 付款方式:
     1 自付（客户自己汇缴给银行，雇员由中智办理）
     2 我司付款（客户预付）
     3 垫付（前道传递）
     */
    private String comTaskPaymentWayName;

    /**
     * 客户缴费起始年月（前道传递）
     */
    private String comStartMonth;

}
