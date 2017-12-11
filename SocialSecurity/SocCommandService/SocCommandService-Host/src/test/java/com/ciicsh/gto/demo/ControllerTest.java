package com.ciicsh.gto.demo;

import com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.host.SocialSecurityApplication;
import com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.host.controller.SsEmpTaskController;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * <p>Description: AF Controller Test</p>
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = SocialSecurityApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ControllerTest {

    @Autowired
    SsEmpTaskController controller;

    @Test
    public void saveAfDisposableCharge() {
        System.out.println(controller);
    }

}
