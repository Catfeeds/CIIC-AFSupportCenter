package com.ciicsh.gto.test.controller;

import com.alibaba.fastjson.JSON;
import com.ciicsh.gto.afsupportcenter.socialsecurity.siteservice.host.SocSiteServiceApp;
import com.ciicsh.gto.afsupportcenter.socialsecurity.siteservice.host.controller.SsComAccountController;
import com.ciicsh.gto.afsupportcenter.util.page.PageInfo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * <p>Description: 企业社保账户 controller Test</p>
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = SocSiteServiceApp.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class SsComAccountControllerTest {

    @Autowired
    SsComAccountController controller;

    @Test
    public void queryByEmpTaskId() {
        PageInfo pageInfo = new PageInfo();
        pageInfo.setPageNum(1);
        pageInfo.setPageSize(10);
        pageInfo.put("ssAccount", 1212);
        Object jsonResult = controller.accountQuery(pageInfo);
        System.out.println(JSON.toJSONString(jsonResult));
    }

}
