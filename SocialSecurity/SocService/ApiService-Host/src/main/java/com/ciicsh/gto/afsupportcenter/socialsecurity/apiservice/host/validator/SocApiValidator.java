package com.ciicsh.gto.afsupportcenter.socialsecurity.apiservice.host.validator;/**
 * Created by zhengj on 2018/3/27
 */

import com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.api.dto.SsComTaskDTO;
import com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.business.SsComAccountService;
import com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.business.SsComTaskService;
import com.ciicsh.gto.afsupportcenter.util.constant.SocialSecurityConst;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author: zhengj
 * @date: 2018/3/27 21:07
 **/
@Component
public class SocApiValidator {

    @Autowired
    private SsComAccountService accountService;

    @Autowired
    private SsComTaskService ssComTaskService;

    public String saveComTaskValidator(SsComTaskDTO ssComTaskDTO) {
        if (ssComTaskDTO == null) {
            return "系统错误：ssComTaskDTO is null";
        } else if (StringUtils.isBlank(ssComTaskDTO.getCompanyId())) {
            return "客户Id不能为空！";
        } else if (StringUtils.isBlank(ssComTaskDTO.getTaskCategory())) {
            return "任务类型不能为空！";
        } else if (SocialSecurityConst.COM_TASK_CATEGORY_1 == Integer.parseInt(ssComTaskDTO.getTaskCategory())) {
            //新开类型参数校验
            if (ssComTaskDTO.getExpireDateFront() == null) {
                return "客户社保截至日不能为空！";
            } else if (ssComTaskDTO.getPaymentWay() == null) {
                return "付款方式不能为空！";
            } else if (ssComTaskDTO.getBillReceiver() == null) {
                return "账单接收方不能为空！";
            }
        } else if (SocialSecurityConst.COM_TASK_CATEGORY_2 == Integer.parseInt(ssComTaskDTO.getTaskCategory())) {
            //转入类型参数校验
            if (ssComTaskDTO.getExpireDateFront() == null) {
                return "客户社保截至日不能为空！";
            } else if (ssComTaskDTO.getPaymentWay() == null) {
                return "付款方式不能为空！";
            } else if (ssComTaskDTO.getBillReceiver() == null) {
                return "账单接收方不能为空！";
            } else if (StringUtils.isBlank(ssComTaskDTO.getSsAccount())) {
                return "参保户登记码不能为空！";
            } else if (StringUtils.isBlank(ssComTaskDTO.getBankAccount())) {
                return "牡丹卡号不能为空！";
            } else if (StringUtils.isBlank(ssComTaskDTO.getComAccountName())) {
                return "养老金用公司名称不能为空！";
            }
        } else if (SocialSecurityConst.COM_TASK_CATEGORY_3 == Integer.parseInt(ssComTaskDTO.getTaskCategory())) {
            return "当前版本不支持企业变更任务单！";
        } else if (SocialSecurityConst.COM_TASK_CATEGORY_4 == Integer.parseInt(ssComTaskDTO.getTaskCategory())) {
            return "当前版本不支持企业终止任务单！";
        }
        if (checkIsExistAccount(ssComTaskDTO.getCompanyId())) {
            return "该客户已存在企业社保账户！";
        }
        if (checkIsExistComTask(ssComTaskDTO.getCompanyId())) {
            return "该客户已存在企业社保账户开户或转入任务单！";
        }
        return null;
    }

    /**
     * 校验客户是否已有企业社保账号
     *
     * @param companyId
     * @return
     */
    private boolean checkIsExistAccount(String companyId) {
        return accountService.isExistAccountInfo(companyId);
    }

    private boolean checkIsExistComTask(String companyId) {
        return ssComTaskService.isExistComTask(companyId);
    }

}
