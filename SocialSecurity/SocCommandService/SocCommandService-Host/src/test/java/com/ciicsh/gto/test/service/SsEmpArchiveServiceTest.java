package com.ciicsh.gto.test.service;

import com.alibaba.fastjson.JSON;
import com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.business.SsEmpArchiveService;
import com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.host.SocialSecurityApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * <p>Description: 雇员档案 service Test</p>
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = SocialSecurityApplication.class)
public class SsEmpArchiveServiceTest {

    @Autowired
    SsEmpArchiveService service;

    @Test
    public void queryByEmpTaskId() {
        Object result = service.queryByEmpTaskId("1","1");
        System.out.println(JSON.toJSONString(result));
    }

}

