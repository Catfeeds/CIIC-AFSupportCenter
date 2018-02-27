package com.ciicsh.gto.test.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.ciicsh.gto.afcompanycenter.commandservice.api.dto.employee.AfEmpSocialUpdateDateDTO;
import com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.bo.SsOperatePaymentBO;
import com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.bo.SsEmpTaskBO;
import com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.business.SsComAccountService;
import com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.business.SsEmpTaskService;
import com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.business.SsPaymentComService;
import com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.business.utils.CommonApiUtils;
import com.ciicsh.gto.afsupportcenter.socialsecurity.siteservice.host.SiteServiceApp;
import com.ciicsh.gto.afsupportcenter.socialsecurity.siteservice.host.controller.SsAccountComRelationController;
import com.ciicsh.gto.afsupportcenter.socialsecurity.siteservice.host.controller.SsComTaskController;
import com.ciicsh.gto.afsupportcenter.socialsecurity.siteservice.host.controller.SsEmpArchiveController;
import com.ciicsh.gto.afsupportcenter.socialsecurity.siteservice.host.controller.SsPaymentController;
import com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.dto.SsAccountComRelationDTO;
import com.ciicsh.gto.afsupportcenter.util.web.response.JsonResult;
import com.ciicsh.gto.afsystemmanagecenter.apiservice.api.dto.item.GetSSPItemsRequestDTO;
import com.ciicsh.gto.afsystemmanagecenter.apiservice.api.dto.item.GetSSPItemsResposeDTO;
import com.ciicsh.gto.basicdataservice.api.dto.DicItemDTO;
import com.ciicsh.gto.commonservice.util.dto.Result;
import com.ciicsh.gto.employeecenter.apiservice.api.dto.EmployeeInfoDTO;
import com.ciicsh.gto.employeecenter.apiservice.api.dto.EmployeeSearchDTO;
import com.ciicsh.gto.sheetservice.api.dto.request.TaskRequestDTO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>Description: 雇员档案 controller Test</p>
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = SiteServiceApp.class)
public class SsEmpArchiveControllerTest {

    @Autowired
    SsEmpArchiveController controller;

    @Autowired
    SsPaymentController ssPaymentController;

    @Autowired
    SsAccountComRelationController ssAccountComRelationController;

    @Autowired
    SsComTaskController d1;
    @Autowired
    CommonApiUtils commonApiUtils;

    @Autowired
    SsComAccountService ssComAccountService;

    @Autowired
    SsEmpTaskService ssEmpTaskService;

    @Autowired
    SsPaymentComService ssPaymentComService;

    @Test
    public void queryByEmpTaskId() {
        boolean bol = ssPaymentComService.saveRejectResult(1L, "未通过",1);
        System.out.println(JSON.toJSONString(bol));
    }


//    @Test
//    public void saveSsComTask() {
//        SsOperatePaymentBO ssOperatePaymentDTO = new SsOperatePaymentBO();
//        SsComTaskDTO ssComTaskDTO = new SsComTaskDTO();
//        ssComTaskDTO.setCompanyId("1");
//        ssComTaskDTO.setComAccountId(2L);
//        ssComTaskDTO.setTaskCategory("1");
//
//        com.ciicsh.common.customer.JsonResult json = d1.saveSsComTask(ssComTaskDTO);
//        System.out.println(JSON.toJSONString(json));
//    }

    @Autowired
    CommonApiUtils d2;

    @Test
    public void getDict() throws Exception {
        List<DicItemDTO> res = d2.listByDicId("DIC00005");
        System.out.println(JSON.toJSONString(res));
    }


    @Test
    public void getEmp() throws Exception {
        EmployeeSearchDTO dd= new EmployeeSearchDTO();
        dd.setBusinessType(1);
        dd.setPageSize(10);
        dd.setCurrentPage(1);
        dd.setTotalRecords(0);
        dd.setEmployeeId("1");
        com.ciicsh.gto.employeecenter.util.JsonResult<com.ciicsh.gto.employeecenter.apiservice.api.dto.Page<EmployeeInfoDTO>> res  = d2.searchEmployeeInfo(dd);
        System.out.println(JSON.toJSONString(res));
    }


    @Test
    public void testDoReviewdePass() {
        SsOperatePaymentBO ssOperatePaymentDTO = new SsOperatePaymentBO();
        ssOperatePaymentDTO.setPaymentId(1L);
        ssOperatePaymentDTO.setApplyRemark("再申请");
        ssOperatePaymentDTO.setRejectionRemark("再批退");

        JsonResult<String> jr = ssPaymentController.doReviewdePass(ssOperatePaymentDTO);
        System.out.println(JSON.toJSONString(jr));
    }

    @Test
    public void testCompleteTask() {
        System.out.println("1----------------------------" + commonApiUtils);
        TaskRequestDTO dt = new TaskRequestDTO();
        dt.setTaskId("100148");
        dt.setAssignee("2");

//        System.out.println(JSONObject.toJSONString(SocialSecurityConst.DISTRICT_MAP));

        try {
            Result ddd = commonApiUtils.completeTask(dt);
            System.out.println(JSON.toJSONString(ddd));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testDoSaveRe() {
        SsAccountComRelationDTO dto = new SsAccountComRelationDTO();
        dto.setCompanyId("KH0000066");
        dto.setComAccountId(66L);
        dto.setSaveflag("1");

        JsonResult<String> jr = ssAccountComRelationController.saveAccountComRelation(dto);
        System.out.println(JSON.toJSONString(jr));
    }

    @Test
    public void addBankAcc() {
        boolean result = false;

        SsEmpTaskBO qd = new SsEmpTaskBO();
        qd.setTaskId("311096");

        List<SsEmpTaskBO> resList = ssEmpTaskService.queryByTaskId(qd);
        if (resList.size() == 0) {
            result = true;
        }
//        Map<String, Object> map = new HashMap<>();
//        map.put("com_account_id", "1235");
//        map.put("account", "6201200");
//        map.put("account_name", "abc");
//        map.put("bank_name", "上海银行1");
//        map.put("bank_id", "2");
//        map.put("province_code", "002");
//        map.put("city_code", "01");
//        map.put("account_type", "4");
//        map.put("finance_account_id", "1");
//        map.put("subject_no", "1");
//        try {
//            boolean result = ssComAccountService
//                .addBankAccount(map);
//            System.out.println(JSON.toJSONString(result));
//            System.out.println(JSON.toJSONString(result));
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
    }

    @Test
    public void testupdateConfirmDate() {
        System.out.println("1----------------------------" + commonApiUtils);
        List<AfEmpSocialUpdateDateDTO> dt = new ArrayList<>();
        AfEmpSocialUpdateDateDTO d1= new AfEmpSocialUpdateDateDTO();
        d1.setEmpAgreementId(604L);
        d1.setPersonalConfirmAmount(new BigDecimal(123));
        d1.setCompanyConfirmAmount(new BigDecimal(234));
        dt.add(d1);

        try {
            int ddd = commonApiUtils.updateConfirmDate(dt);
            System.out.println(JSON.toJSONString(ddd));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testGetRoundType() {
        GetSSPItemsRequestDTO getSSPItemsRequestDTO = new GetSSPItemsRequestDTO();
        getSSPItemsRequestDTO.setSsPolicyId("201");
        getSSPItemsRequestDTO.setPayAccountType(1);
        getSSPItemsRequestDTO.setEffectiveMonth("201803");

        com.ciicsh.gto.afsystemmanagecenter.apiservice.api.dto.JsonResult<GetSSPItemsResposeDTO> jsonResult =commonApiUtils.getRoundingType(getSSPItemsRequestDTO);

        System.out.println("结果"+JSONObject.toJSON(jsonResult).toString());
    }
}
