package com.ciicsh.gto.test.controller;

import com.alibaba.fastjson.JSON;
import com.ciicsh.gto.afcompanycenter.commandservice.api.dto.employee.AfEmpSocialUpdateDateDTO;
import com.ciicsh.gto.afsupportcenter.housefund.siteservice.FundSiteServiceApp;
import com.ciicsh.gto.afsupportcenter.housefund.siteservice.api.dto.HfComTaskDTO;
import com.ciicsh.gto.afsupportcenter.housefund.siteservice.api.dto.ResultDTO;
import com.ciicsh.gto.afsupportcenter.housefund.siteservice.business.HfPaymentService;
import com.ciicsh.gto.afsupportcenter.housefund.siteservice.business.utils.CommonApiUtils;
import com.ciicsh.gto.afsupportcenter.housefund.siteservice.controller.HfApiController;
import com.ciicsh.gto.afsupportcenter.housefund.siteservice.controller.HfEmpArchiveController;
import com.ciicsh.gto.afsupportcenter.housefund.siteservice.entity.HfPayment;
import com.ciicsh.gto.afsupportcenter.util.web.response.JsonResult;
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
@SpringBootTest(classes = FundSiteServiceApp.class)
public class HfApiControllerTest {

    @Autowired
    HfEmpArchiveController controller;

    @Autowired
    HfApiController d1;

    @Autowired
    CommonApiUtils commonApiUtils;

    @Autowired
    HfPaymentService hfPaymentService;

    @Test
    public void saveSsComTask() {
        HfComTaskDTO ssComTaskDTO = new HfComTaskDTO();
        ssComTaskDTO.setCompanyId("1");
        ssComTaskDTO.setComAccountId(2L);
        ssComTaskDTO.setTaskCategory(1);

        com.ciicsh.common.entity.JsonResult res = d1.saveHfComTask(ssComTaskDTO);
        System.out.println(JSON.toJSONString(res));
    }

    @Test
    public void testDoReviewdePass() {
        HfPayment ssOperatePaymentDTO = new HfPayment();
        ssOperatePaymentDTO.setPaymentId(1);
        ssOperatePaymentDTO.setApplyRemark("再申请");
        ssOperatePaymentDTO.setRejectionRemark("再批退");

        JsonResult<String> jr = hfPaymentService.doReviewdePass(ssOperatePaymentDTO);
        System.out.println(JSON.toJSONString(jr));
    }

    @Test
    public void testCompleteTask() {
//        SocReportMessage mm = new SocReportMessage();
//        mm.setComAccountId(3L);
//        mm.setSsMonth("201801");
//
//        sender.sendSocReportMsg(mm);
//        System.out.println("1----------------------------" + commonApiUtils);
//        TaskSheetRequestDTO dt = new TaskSheetRequestDTO();
//        dt.setTaskId("100148");
//        dt.setAssignee("2");
//        dt.setVariable(null);
//
//        try {
//            Result ddd = commonApiUtils.completeTask(dt);
//            System.out.println(JSON.toJSONString(ddd));
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
    }

    @Test
    public void testupdateConfirmDate() {
        System.out.println("1----------------------------" + commonApiUtils);
        List<AfEmpSocialUpdateDateDTO> dt = new ArrayList<>();
        AfEmpSocialUpdateDateDTO d1 = new AfEmpSocialUpdateDateDTO();
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
}
