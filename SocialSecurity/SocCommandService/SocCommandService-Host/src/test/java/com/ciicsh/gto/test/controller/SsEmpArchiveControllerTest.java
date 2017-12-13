package com.ciicsh.gto.test.controller;

import com.alibaba.fastjson.JSON;
import com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.dto.SsEmpArchiveDTO;
import com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.host.SocialSecurityApplication;
import com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.host.controller.SsEmpArchiveController;
import com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.host.controller.SsEmpTaskController;
import com.ciicsh.gto.afsupportcenter.util.web.response.JsonResult;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * <p>Description: 雇员档案 controller Test</p>
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = SocialSecurityApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class SsEmpArchiveControllerTest {

    @Autowired
    SsEmpArchiveController controller;

    @Test
    public void queryByEmpTaskId() {
        JsonResult<SsEmpArchiveDTO> jsonResult = controller.queryByEmpTaskId("1");
        System.out.println(JSON.toJSONString(jsonResult));
    }

}
