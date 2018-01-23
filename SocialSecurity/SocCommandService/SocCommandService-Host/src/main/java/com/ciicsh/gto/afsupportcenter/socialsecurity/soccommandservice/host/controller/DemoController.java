package com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.host.controller;

import com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.entity.custom.TestPerson;
import com.ciicsh.gto.afsupportcenter.util.ExcelUtil;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by houwanhua on 2018/1/22.
 */
@RestController
@RequestMapping("/api/soccommandservice/demo")
public class DemoController {
    @RequestMapping("export")
    public void export(HttpServletResponse response){

        //模拟从数据库获取需要导出的数据
        List<TestPerson> personList = new ArrayList<>();

        TestPerson person;
        person = new TestPerson();
        person.setName("路飞");
        person.setSex("1");
        person.setBirthday(new Date());
        personList.add(person);

        person = new TestPerson();
        person.setName("娜美");
        person.setSex("2");
        person.setBirthday(new Date());
        personList.add(person);

        person = new TestPerson();
        person.setName("索隆");
        person.setSex("1");
        person.setBirthday(new Date());
        personList.add(person);

        person = new TestPerson();
        person.setName("小狸猫");
        person.setSex("2");
        person.setBirthday(new Date());
        personList.add(person);

        //导出操作
        ExcelUtil.exportExcel(personList,"花名册","人员",TestPerson.class,"海贼王.xls",response);
    }

    @RequestMapping("importExcel")
    public String importExcel() throws Exception {
        String filePath = "F:/海贼王.xls";
        //解析excel，
        List<TestPerson> personList = ExcelUtil.importExcel(filePath,1,1,TestPerson.class);
        //也可以使用MultipartFile,使用 FileUtil.importExcel(MultipartFile file, Integer titleRows, Integer headerRows, Class<T> pojoClass)导入
        System.out.println("导入数据一共【"+personList.size()+"】行");

        //TODO 数据处理
        return personList.toString();
    }
}
