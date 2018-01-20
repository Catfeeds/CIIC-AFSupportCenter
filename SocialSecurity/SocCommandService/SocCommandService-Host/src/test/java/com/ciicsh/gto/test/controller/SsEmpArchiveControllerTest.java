package com.ciicsh.gto.test.controller;

import com.alibaba.fastjson.JSON;
import com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.api.dto.TaskSheetRequestDTO;
import com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.api.dto.payment.SsOperatePaymentDTO;
import com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.bo.SsEmpArchiveBO;
import com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.host.SocialSecurityApplication;
import com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.host.controller.SsEmpArchiveController;
import com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.host.controller.SsPaymentController;
import com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.host.controller.TaskSheetController;
import com.ciicsh.gto.afsupportcenter.util.web.response.JsonResult;
import com.ciicsh.gto.sheetservice.api.dto.core.Result;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * <p>Description: 雇员档案 controller Test</p>
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = SocialSecurityApplication.class)
public class SsEmpArchiveControllerTest {

    @Autowired
    SsEmpArchiveController controller;

    @Autowired
    SsPaymentController ssPaymentController;

    @Autowired
    TaskSheetController taskSheetController;

    @Test
    public void queryByEmpTaskId() {
        JsonResult<SsEmpArchiveBO> jsonResult = controller.queryByEmpTaskId("1","1");
        System.out.println(JSON.toJSONString(jsonResult));
    }

    @Test
    public void testDoReviewdePass() {
        SsOperatePaymentDTO ssOperatePaymentDTO = new SsOperatePaymentDTO();
        ssOperatePaymentDTO.setPaymentId(2L);
        ssOperatePaymentDTO.setApplyRemark("申请备注");
        ssOperatePaymentDTO.setRejectionRemark("批退备注");

        JsonResult<String> jr = ssPaymentController.doReviewdePass(ssOperatePaymentDTO);
        System.out.println(JSON.toJSONString(jr));
    }

    @Test
    public void testCompleteTask() {
        TaskSheetRequestDTO dt = new TaskSheetRequestDTO();
        dt.setTaskId("1");
        dt.setAssignee("2");
        dt.setVariable(null);
        try {
            Result ddd = taskSheetController.completeTask(dt);
            System.out.println(JSON.toJSONString(ddd));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
