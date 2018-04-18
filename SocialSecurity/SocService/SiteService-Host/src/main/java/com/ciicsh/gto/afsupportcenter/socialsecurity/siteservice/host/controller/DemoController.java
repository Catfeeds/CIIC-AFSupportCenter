package com.ciicsh.gto.afsupportcenter.socialsecurity.siteservice.host.controller;

import com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.business.SsMonthEmpChangeDetailService;
import com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.business.SsStatementImpService;
import com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.business.utils.CommonApiUtils;
import com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.entity.custom.GsyExportOpt;
import com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.entity.custom.GsymxOpt;
import com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.entity.custom.StatementExportOpt;
import com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.entity.custom.TestPerson;
import com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.entity.custom.YysExportOpt;
import com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.entity.custom.YysmxOpt;
import com.ciicsh.gto.afsupportcenter.util.ExcelUtil;
import com.ciicsh.gto.afsupportcenter.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by houwanhua on 2018/1/22.
 */
@RestController
@RequestMapping("/api/soccommandservice/demo")
public class DemoController {

    @Autowired
    private SsStatementImpService impService;

    @Autowired
    private SsMonthEmpChangeDetailService monthEmpChangeDetailService;

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
        List<TestPerson> personList = ExcelUtil.importExcel(filePath,1,1,TestPerson.class,false);
        //也可以使用MultipartFile,使用 FileUtil.importExcel(MultipartFile file, Integer titleRows, Integer headerRows, Class<T> pojoClass)导入
        System.out.println("导入数据一共【"+personList.size()+"】行");

        //TODO 数据处理
        return personList.toString();
    }


    @RequestMapping("yysmxOptImport")
    public void yysmxOptImport() throws Exception {
        String filePath = "F:/00972350_180123_养医失明细.xls";
        //解析excel，
        File tempFile = new File(filePath.trim());
        String fileName = tempFile.getName();
        List<YysmxOpt> optList = ExcelUtil.importExcel(filePath,1,2,YysmxOpt.class,false);
        optList = optList.stream().filter(x->!x.getSettlement().equals("合计")).collect(Collectors.toList());

        optList = optList.stream().filter(x->!x.getSettlement().equals("合计")).collect(Collectors.toList());

        impService.yysImport(optList,"201801","YYS",Long.valueOf(2),fileName);

        System.out.println("导入数据一共【"+optList.size()+"】行");
        //TODO 数据处理
    }

    @RequestMapping("gsymxOptImport")
    public void gsymxOptImport() throws Exception {
        String filePath = "F:/00972350_180123_工伤生育明细.xls";
        //String filePath = "F:/GSYMX.xls";

        File tempFile = new File(filePath.trim());
        String fileName = tempFile.getName();

        //解析excel，
        List<GsymxOpt> optList = ExcelUtil.importExcel(filePath,1,1,GsymxOpt.class,false);

        optList = optList.stream().filter(x->!x.getSettlement().equals("合计")).collect(Collectors.toList());

        impService.gsyImport(optList,"201801","GSY",Long.valueOf(2),fileName);

        System.out.println("导入数据一共【"+optList.size()+"】行");
        //TODO 数据处理
    }


    @RequestMapping("/yysExport")
    public void yysExport(HttpServletResponse response){
        Date date = new Date();
        String fileNme = "YYS_"+ StringUtil.getDateString(date)+".xls";
        List<YysExportOpt> opts = new ArrayList<>();
        YysExportOpt opt;
        opt = new YysExportOpt();
        opt.setMonthEmpChangeId((long) 1);
        opt.setSsMonth("201801");
        opt.setComAccountId((long)1);
        opt.setComAccountName("上海微软");
        opt.setEmployeeId("18A322665");
        opt.setEmployeeName("张三");
        opt.setChangeType(2);
        opt.setChangeTypeName("新进");
        opt.setBaseAmount(BigDecimal.valueOf(5000));
        opt.setPensionComAmount(BigDecimal.valueOf(1200));
        opt.setPensionEmpAmount(BigDecimal.valueOf(1200));
        opt.setPensionComRepayAmount(BigDecimal.valueOf(300));
        opt.setPensionEmpRepayAmount(BigDecimal.valueOf(300));
        opt.setPensionOnePayment(BigDecimal.valueOf(3000));

        opt.setMedicalComAmount(BigDecimal.valueOf(1200));
        opt.setMedicalEmpAmount(BigDecimal.valueOf(1200));
        opt.setMedicalComRepayAmount(BigDecimal.valueOf(300));
        opt.setMedicalEmpRepayAmount(BigDecimal.valueOf(300));

        opt.setUnemploymentComAmount(BigDecimal.valueOf(1200));
        opt.setUnemploymentEmpAmount(BigDecimal.valueOf(1200));
        opt.setUnemploymentComRepayAmount(BigDecimal.valueOf(300));
        opt.setUnemploymentEmpRepayAmount(BigDecimal.valueOf(300));
        opts.add(opt);

        opt = new YysExportOpt();
        opt.setMonthEmpChangeId((long) 2);
        opt.setSsMonth("201801");
        opt.setComAccountId((long)1);
        opt.setComAccountName("上海微软");
        opt.setEmployeeId("18A322665");
        opt.setEmployeeName("李四");
        opt.setChangeType(3);
        opt.setChangeTypeName("转出");
        opt.setBaseAmount(BigDecimal.valueOf(5000));
        opt.setPensionComAmount(BigDecimal.valueOf(1200));
        opt.setPensionEmpAmount(BigDecimal.valueOf(1200));
        opt.setPensionComRepayAmount(BigDecimal.valueOf(300));
        opt.setPensionEmpRepayAmount(BigDecimal.valueOf(300));
        opt.setPensionOnePayment(BigDecimal.valueOf(3000));

        opt.setMedicalComAmount(BigDecimal.valueOf(1200));
        opt.setMedicalEmpAmount(BigDecimal.valueOf(1200));
        opt.setMedicalComRepayAmount(BigDecimal.valueOf(300));
        opt.setMedicalEmpRepayAmount(BigDecimal.valueOf(300));

        opt.setUnemploymentComAmount(BigDecimal.valueOf(1200));
        opt.setUnemploymentEmpAmount(BigDecimal.valueOf(1200));
        opt.setUnemploymentComRepayAmount(BigDecimal.valueOf(300));
        opt.setUnemploymentEmpRepayAmount(BigDecimal.valueOf(300));
        opts.add(opt);
        ExcelUtil.exportExcel(opts,YysExportOpt.class,fileNme,response);
    }

    @RequestMapping("/gsyExport")
    public void gsyExport(HttpServletResponse response){
        Date date = new Date();
        String fileNme = "GSY_"+ StringUtil.getDateString(date)+".xls";
        List<GsyExportOpt> opts = new ArrayList<>();
        GsyExportOpt opt;

        opt = new GsyExportOpt();
        opt.setMonthEmpChangeId((long) 1);
        opt.setSsMonth("201801");
        opt.setComAccountId((long)1);
        opt.setComAccountName("上海微软");
        opt.setEmployeeId("18A322665");
        opt.setEmployeeName("张三");
        opt.setChangeType(2);
        opt.setChangeTypeName("新进");
        opt.setBaseAmount(BigDecimal.valueOf(5000));
        opt.setAccidentComAmount(BigDecimal.valueOf(1200));
        opt.setAccidentComRepayAmount(BigDecimal.valueOf(300));
        opt.setMaternityComAmount(BigDecimal.valueOf(1200));
        opt.setMaternityComRepayAmount(BigDecimal.valueOf(300));
        opts.add(opt);

        opt = new GsyExportOpt();
        opt.setMonthEmpChangeId((long) 2);
        opt.setSsMonth("201801");
        opt.setComAccountId((long)1);
        opt.setComAccountName("上海微软");
        opt.setEmployeeId("18A322665");
        opt.setEmployeeName("李四");
        opt.setChangeType(3);
        opt.setChangeTypeName("转出");
        opt.setBaseAmount(BigDecimal.valueOf(5000));
        opt.setAccidentComAmount(BigDecimal.valueOf(1200));
        opt.setAccidentComRepayAmount(BigDecimal.valueOf(300));
        opt.setMaternityComAmount(BigDecimal.valueOf(1200));
        opt.setMaternityComRepayAmount(BigDecimal.valueOf(300));
        opts.add(opt);

        ExcelUtil.exportExcel(opts,GsyExportOpt.class,fileNme,response);
    }

    @RequestMapping("/statementExport")
    public void statementExport(HttpServletResponse response) {
        Date date = new Date();
        String fileNme = "社保对账_"+ StringUtil.getDateString(date)+".xls";
        List<StatementExportOpt> opts = new ArrayList<>();

        StatementExportOpt opt;
        opt = new StatementExportOpt();
        opt.setImpFileName("14555_YYS.xls");
        opt.setSsMonth("201801");
        opt.setComAccountId((long)1);
        opt.setComAccountName("上海微软");
        opt.setDownLoadMonthChange("养医失月度明细下载");
        opt.setMonthChangeType("YYS(养医失)");
        opt.setDiffSumByEmp("200");
        opt.setStatementUserId("张三");
        opt.setStatementTime(LocalDateTime.now());
        opt.setImpFileType("YYS");
        opts.add(opt);

        opt = new StatementExportOpt();
        opt.setImpFileName("14556_GSY.xls");
        opt.setSsMonth("201801");
        opt.setComAccountId((long)1);
        opt.setComAccountName("上海微软");
        opt.setDownLoadMonthChange("工伤生育月度明细下载");
        opt.setMonthChangeType("GSY(工伤生育)");
        opt.setDiffSumByEmp("200");
        opt.setStatementUserId("李四");
        opt.setStatementTime(LocalDateTime.now());
        opt.setImpFileType("GSY");
        opts.add(opt);

        ExcelUtil.exportExcel(opts,StatementExportOpt.class,fileNme,response);
    }
    @Autowired
    public CommonApiUtils commonApiUtils;

    @RequestMapping("/test1")
    public void test1(){
        Map<String,Object> bankAccountMap =new HashMap<String,Object>();

        bankAccountMap.put("com_account_id", "2323");
        bankAccountMap.put("account", "");
        bankAccountMap.put("account_name", "");
        bankAccountMap.put("bank_name", "");

        bankAccountMap.put("bank_id", "2");//默认工商银行
//          bankAccountMap.put("province_code", "002");
//          bankAccountMap.put("city_code", "01");
        bankAccountMap.put("account_type", "4");
        //bankAccountMap.put("subject_no", "1");
        //插入银行账号信息并返回结果，如果接口返回0 表示 接口调用失败，正常返回 bankAccountId 主键
        Map<String,String> mp=new HashMap<>();

        mp= commonApiUtils.addBankAccount(bankAccountMap);
        System.out.println(mp.size());
    }

}
