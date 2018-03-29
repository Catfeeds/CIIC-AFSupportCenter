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
        String result = null;
        if (ssComTaskDTO == null) {
            result = "系统错误：ssComTaskDTO is null";
        } else if (StringUtils.isBlank(ssComTaskDTO.getCompanyId())) {
            result = "客户Id不能为空！";
        } else if (StringUtils.isBlank(ssComTaskDTO.getTaskCategory())) {
            result = "任务类型不能为空！";
        } else if (SocialSecurityConst.COM_TASK_CATEGORY_1 == Integer.parseInt(ssComTaskDTO.getTaskCategory())) {
            //新开类型参数校验
            if (ssComTaskDTO.getExpireDateFront() == null) {
                result = "客户社保截至日不能为空！";
            } else if (ssComTaskDTO.getPaymentWay() == null) {
                result = "付款方式不能为空！";
            } else if (ssComTaskDTO.getBillReceiver() == null) {
                result = "账单接收方不能为空！";
            }
        } else if (SocialSecurityConst.COM_TASK_CATEGORY_2 == Integer.parseInt(ssComTaskDTO.getTaskCategory())) {
            //转入类型参数校验
            if (ssComTaskDTO.getExpireDateFront() == null) {
                result = "客户社保截至日不能为空！";
            } else if (ssComTaskDTO.getPaymentWay() == null) {
                result = "付款方式不能为空！";
            } else if (ssComTaskDTO.getBillReceiver() == null) {
                result = "账单接收方不能为空！";
            } else if (StringUtils.isBlank(ssComTaskDTO.getSsAccount())) {
                result = "参保户登记码不能为空！";
            } else if (StringUtils.isBlank(ssComTaskDTO.getBankAccount())) {
                result = "牡丹卡号不能为空！";
            } else if (StringUtils.isBlank(ssComTaskDTO.getComAccountName())) {
                result = "养老金用公司名称不能为空！";
            }
        } else if (SocialSecurityConst.COM_TASK_CATEGORY_3 == Integer.parseInt(ssComTaskDTO.getTaskCategory())) {
            result = "当前版本不支持企业变更任务单！";
        } else if (SocialSecurityConst.COM_TASK_CATEGORY_4 == Integer.parseInt(ssComTaskDTO.getTaskCategory())) {
            result = "当前版本不支持企业终止任务单！";
        }
        if (checkIsExistAccount(ssComTaskDTO.getCompanyId())) {
            result = "该客户已存在企业社保账户！";
        }
        if (checkIsExistComTask(ssComTaskDTO.getCompanyId())) {
            result = "该客户已存在企业社保账户开户或转入任务单！";
        }
        return result;
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

    private boolean checkIsExistComTask(String companyId){
        return ssComTaskService.isExistComTask(companyId);
    }

}
