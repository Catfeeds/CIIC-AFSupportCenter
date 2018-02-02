package com.ciicsh.gto.test.controller;

import com.alibaba.fastjson.JSON;
import com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.api.CommonApiUtils;
import com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.api.dto.SsAccountComRelationDTO;
import com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.api.dto.SsComTaskDTO;
import com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.api.dto.TaskSheetRequestDTO;
import com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.api.dto.payment.SsOperatePaymentDTO;
import com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.bo.SsEmpArchiveBO;
import com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.host.SocialSecurityApplication;
import com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.host.controller.SsAccountComRelationController;
import com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.host.controller.SsComTaskController;
import com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.host.controller.SsEmpArchiveController;
import com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.host.controller.SsPaymentController;
import com.ciicsh.gto.afsupportcenter.util.web.response.JsonResult;
import com.ciicsh.gto.basicdataservice.api.dto.DicItemDTO;
import com.ciicsh.gto.commonservice.util.dto.Result;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

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
    SsAccountComRelationController ssAccountComRelationController;

    @Autowired
    SsComTaskController d1;
    @Autowired
    CommonApiUtils commonApiUtils;

    @Test
    public void queryByEmpTaskId() {
        JsonResult<SsEmpArchiveBO> jsonResult = controller.queryByEmpTaskId("1", "1");
        System.out.println(JSON.toJSONString(jsonResult));
    }


    @Test
    public void saveSsComTask() {
        SsOperatePaymentDTO ssOperatePaymentDTO = new SsOperatePaymentDTO();
        SsComTaskDTO ssComTaskDTO = new SsComTaskDTO();
        ssComTaskDTO.setCompanyId("1");
        ssComTaskDTO.setComAccountId(2L);
        ssComTaskDTO.setTaskCategory("1");

        Result res = d1.saveSsComTask(ssComTaskDTO);
        System.out.println(JSON.toJSONString(res));
    }

    @Autowired
    CommonApiUtils d2;

    @Test
    public void getDict() throws Exception {
        List<DicItemDTO> res = d2.listByDicId("DIC00005");
        System.out.println(JSON.toJSONString(res));
    }

    @Test
    public void testDoReviewdePass() {
        SsOperatePaymentDTO ssOperatePaymentDTO = new SsOperatePaymentDTO();
        ssOperatePaymentDTO.setPaymentId(1L);
        ssOperatePaymentDTO.setApplyRemark("再申请");
        ssOperatePaymentDTO.setRejectionRemark("再批退");

        JsonResult<String> jr = ssPaymentController.doReviewdePass(ssOperatePaymentDTO);
        System.out.println(JSON.toJSONString(jr));
    }

    @Test
    public void testCompleteTask() {
        System.out.println("1----------------------------" + commonApiUtils);
        TaskSheetRequestDTO dt = new TaskSheetRequestDTO();
        dt.setTaskId("100148");
        dt.setAssignee("2");
        dt.setVariable(null);

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

//    @Test
//    public void testupdateConfirmDate() {
//        System.out.println("1----------------------------" + commonApiUtils);
//        List<AfEmpSocialUpdateDateDTO> dt = new ArrayList<>();
//        AfEmpSocialUpdateDateDTO d1= new AfEmpSocialUpdateDateDTO();
//        d1.setEmpAgreementId();
//
////        System.out.println(JSONObject.toJSONString(SocialSecurityConst.DISTRICT_MAP));
//
//        try {
//            Result ddd = commonApiUtils.updateConfirmDate(dt);
//            System.out.println(JSON.toJSONString(ddd));
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
}
