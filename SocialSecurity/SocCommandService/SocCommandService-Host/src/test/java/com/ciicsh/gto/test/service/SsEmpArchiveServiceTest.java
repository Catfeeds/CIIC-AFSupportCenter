package com.ciicsh.gto.test.service;

import com.alibaba.fastjson.JSON;
import com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.business.SsComAccountService;
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

    @Autowired
    SsComAccountService ssComAccountService;

    @Test
    public void queryByEmpTaskId() {
        Object result = service.queryByEmpTaskId("1", "1");
        System.out.println(JSON.toJSONString(result));
    }

//    @Test
//    public void addBankAcc() {
//        Map<String, Object> map = new HashMap<>();
//        map.put("account", "6201200");
//        map.put("account_name", "abc");
//        map.put("bank_name", "上海银行");
//        map.put("bank_id", "001");
//        map.put("province_code", "002");
//        map.put("city_code", "01");
//        map.put("account_type", "4");
//        map.put("finance_account_id", "1");
//        map.put("subject_no", "1");
//        try {
//            com.ciicsh.gto.settlementcenter.invoicecommandservice.api.dto.JsonResult result = ssComAccountService
//                .addBankAccount(map);
//            System.out.println(JSON.toJSONString(result));
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }


}

