package com.ciicsh.gto.afsupportcenter.healthmedical.business;


import com.ciicsh.gto.afsupportcenter.healthmedical.InsuranceApplication;
import com.ciicsh.gto.afsupportcenter.healthmedical.entity.po.UninsuredMedical;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = InsuranceApplication.class)
public class UninsuredMedicalServiceTest {

    @Autowired
    private UninsuredMedicalService uninsuredMedicalService;

    @Test
    public void test() {
        UninsuredMedical uninsuredMedical = uninsuredMedicalService.selectById(1);
        System.out.println(uninsuredMedical.toString());
    }
}
