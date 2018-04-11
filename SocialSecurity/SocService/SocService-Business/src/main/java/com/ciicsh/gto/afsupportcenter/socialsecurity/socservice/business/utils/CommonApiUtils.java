package com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.business.utils;

import com.ciicsh.gto.afcompanycenter.commandservice.api.dto.employee.AfEmpSocialUpdateDateDTO;
import com.ciicsh.gto.afcompanycenter.commandservice.api.proxy.AfEmployeeSocialProxy;
import com.ciicsh.gto.afsystemmanagecenter.apiservice.api.SSPolicyProxy;
import com.ciicsh.gto.afsystemmanagecenter.apiservice.api.dto.item.GetSSPItemsRequestDTO;
import com.ciicsh.gto.afsystemmanagecenter.apiservice.api.dto.item.GetSSPItemsResposeDTO;
import com.ciicsh.gto.basicdataservice.api.DicItemServiceProxy;
import com.ciicsh.gto.basicdataservice.api.dto.DicItemDTO;
import com.ciicsh.gto.basicdataservice.api.dto.EmptyDicItemDTO;
import com.ciicsh.gto.commonservice.util.dto.Result;
import com.ciicsh.gto.employeecenter.apiservice.api.dto.EmployeeInfoDTO;
import com.ciicsh.gto.employeecenter.apiservice.api.dto.EmployeeQueryDTO;
import com.ciicsh.gto.employeecenter.apiservice.api.dto.EmployeeSearchDTO;
import com.ciicsh.gto.employeecenter.apiservice.api.dto.Page;
import com.ciicsh.gto.employeecenter.apiservice.api.proxy.EmployeeInfoProxy;
import com.ciicsh.gto.salecenter.apiservice.api.dto.company.AfCompanyDetailResponseDTO;
import com.ciicsh.gto.salecenter.apiservice.api.proxy.CompanyProxy;
import com.ciicsh.gto.settlementcenter.invoicecommandservice.api.ComeAccountCommandProxy;
import com.ciicsh.gto.settlementcenter.invoicecommandservice.api.dto.JsonResult;
import com.ciicsh.gto.sheetservice.api.SheetServiceProxy;
import com.ciicsh.gto.sheetservice.api.dto.request.TaskRequestDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by houwanhua on 2018/2/22.
 */
@Component
public class CommonApiUtils {
    private Logger logger = LoggerFactory.getLogger(this.getClass().getSimpleName());

    @Autowired
    SheetServiceProxy sheetServiceProxy;

    @Autowired
    DicItemServiceProxy dicItemServiceProxy;

    @Autowired
    AfEmployeeSocialProxy afEmployeeSocialProxy;

    @Autowired
    EmployeeInfoProxy employeeInfoProxy;

    @Autowired
    SSPolicyProxy ssPolicyProxy;

    @Autowired
    ComeAccountCommandProxy comeAccountCommandProxy;

    @Autowired
    CompanyProxy companyProxy;

    /**
     * 调用客服中心的完成任务接口
     * @param requestDTO
     * @return
     */
    public Result completeTask(@RequestBody TaskRequestDTO requestDTO) throws Exception {
        logger.info("customer系统调用完成任务接口：" + requestDTO.toString());
        com.ciicsh.gto.commonservice.util.dto.Result restResult = sheetServiceProxy.completeTask(requestDTO);
        logger.info("customer系统收到完成任务接口返回：" + String.valueOf("code:" + restResult.getCode() + "message:") + restResult.getMessage());
        return restResult;
    }

    /**
     * 根据ID取得名称
     *
     * @param dicItemId
     * @return
     */
    public DicItemDTO selectByDicItemId(String dicItemId) throws Exception {
        return dicItemServiceProxy.selectByDicItemId(dicItemId);
    }

    /**
     * 根据ID取得名称
     *
     * @param listByDicId
     * @return
     */
    public List<DicItemDTO> listByDicId(String listByDicId) throws Exception {
        return dicItemServiceProxy.listByDicId(listByDicId);
    }

    /**
     * 刷新REDIS中的ID数据
     *
     * @param dicItemDto
     * @return
     */
    public void fresh2Redis(EmptyDicItemDTO dicItemDto) throws Exception {
        dicItemServiceProxy.fresh2Redis(dicItemDto);
    }

    /**
     * 雇员任务单实缴金额回调接口（支持中心调用客服中心）
     *
     * @param var1
     * @return int
     */
    public int updateConfirmDate(@RequestBody List<AfEmpSocialUpdateDateDTO> var1) throws Exception {
        return afEmployeeSocialProxy.updateConfirmDate(var1);
    }

    /**
     * 获取雇员信息（支持中心调用雇员中心）
     *
     * @param var1 传入employeeId和业务类型
     * @return
     */
    public com.ciicsh.gto.employeecenter.util.JsonResult<EmployeeInfoDTO> getEmployeeInfo(
        @RequestBody EmployeeQueryDTO var1) throws Exception {
        return employeeInfoProxy.getEmployeeInfo(var1);
    }

    /**
     * 获取进位方式
     * @param var1
     * @return
     */
    public com.ciicsh.gto.afsystemmanagecenter.apiservice.api.dto.JsonResult<GetSSPItemsResposeDTO> getRoundingType(GetSSPItemsRequestDTO var1){
        return ssPolicyProxy.getSSPItems(var1);
    }

    /**
     * 新增银行账户信息
     * */
    public Map<String,String> addBankAccount(Map<String,Object> map){
        JsonResult jr = comeAccountCommandProxy.addAccount(map);
        Map<String,String> mp= new HashMap<>();
        mp.put("code",jr.getErrorcode());
        if(jr.getErrorcode().equals("0")){
            mp.put("ret",jr.getBankAccountId().toString());
        }else {
            mp.put("ret",jr.getErrormsg());
        }
        return mp;
    }

    /**
     * 根据客户编号获取服务中心
     *
     * @param companyId
     * @return
     */
    public AfCompanyDetailResponseDTO getServiceCenterInfo(String companyId) {
        com.ciicsh.gto.salecenter.apiservice.api.dto.core.JsonResult<AfCompanyDetailResponseDTO> jsonResult = companyProxy.afDetail(companyId);

        if (jsonResult != null && jsonResult.getCode() == 0) {
            return jsonResult.getObject();
        }
        return null;
    }
}
