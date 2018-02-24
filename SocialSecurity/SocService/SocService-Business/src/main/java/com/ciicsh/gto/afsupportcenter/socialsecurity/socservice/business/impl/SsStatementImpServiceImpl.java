package com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.business.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.dao.EmpEmployeeMapper;
import com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.dao.SsMonthEmpChangeMapper;
import com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.dao.SsStatementMapper;
import com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.dao.SsStatementImpMapper;
import com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.business.SsStatementImpService;
import com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.entity.EmpEmployee;
import com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.entity.SsMonthEmpChange;
import com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.entity.SsStatement;
import com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.entity.SsStatementImp;
import com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.entity.custom.GsymxOpt;
import com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.entity.custom.ImportOpt;
import com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.entity.custom.MedicalOpt;
import com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.entity.custom.PensionOpt;
import com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.entity.custom.UnemploymentOpt;
import com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.entity.custom.YysmxOpt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

/**
 * <p>
 * 对账导入雇员明细 服务实现类
 * </p>
 *
 * @author HuangXing
 * @since 2017-12-01
 */
@Service
public class SsStatementImpServiceImpl implements SsStatementImpService {

    @Autowired
    private SsStatementMapper statementMapper;

    @Autowired
    private SsStatementImpMapper ssStatementImpMapper;

    @Autowired
    private EmpEmployeeMapper employeeMapper;

    @Autowired
    private SsMonthEmpChangeMapper monthEmpChangeMapper;

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public Boolean yysImport(List<YysmxOpt> opts, String ssMonth, String fileType, Long comAccountId, String fileName) {
        this.delStatementImp(comAccountId,ssMonth,fileType);
        Integer recordNum = opts.size();
        SsStatement statement = this.getStatement(ssMonth,fileType,comAccountId,fileName,recordNum);
        Integer result = statementMapper.insert(statement);
        if(result > 0){
            this.updateMonthEmpChange(statement.getStatementId(),comAccountId,ssMonth,fileType);
            opts.forEach(yysmxOpt->addStatementImpsByYys(comAccountId,statement.getStatementId(),yysmxOpt));
        }
        return true;
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public Boolean gsyImport(List<GsymxOpt> opts, String ssMonth, String fileType, Long comAccountId, String fileName) {
        this.delStatementImp(comAccountId,ssMonth,fileType);
        Integer recordNum = opts.size();
        SsStatement statement = this.getStatement(ssMonth,fileType,comAccountId,fileName,recordNum);
        Integer result = statementMapper.insert(statement);
        if(result > 0){
            this.updateMonthEmpChange(statement.getStatementId(),comAccountId,ssMonth,fileType);
            opts.forEach(gsymxOpt->addStatementImpsByGsy(comAccountId,statement.getStatementId(),gsymxOpt));
        }
        return true;
    }

    private void  delStatementImp(Long comAccountId,String ssMonth,String fileType){
        SsStatement statement = new SsStatement();
        statement.setComAccountId(comAccountId);
        statement.setSsMonth(ssMonth);
        statement.setImpFileType(fileType);
        EntityWrapper<SsStatement> ew = new EntityWrapper<>(statement);
        List<SsStatement> ssStatements = statementMapper.selectList(ew);

        if(null != ssStatements && ssStatements.size() > 0){
            ssStatements.forEach(p->{
                statementMapper.deleteById(p.getStatementId());
                ssStatementImpMapper.delByStatementId(p.getStatementId());
            });
        }
    }

    private void updateMonthEmpChange(Long statementId,Long comAccountId,String ssMonth,String fileType){
        SsMonthEmpChange monthEmpChange = new SsMonthEmpChange();
        monthEmpChange.setComAccountId(comAccountId);
        monthEmpChange.setSsMonth(ssMonth);
        monthEmpChange.setComputeType(fileType);
        EntityWrapper<SsMonthEmpChange> ew = new EntityWrapper<>(monthEmpChange);
        List<SsMonthEmpChange> monthEmpChanges = monthEmpChangeMapper.selectList(ew);
        if(null != monthEmpChanges && monthEmpChanges.size() > 0){
            monthEmpChanges.forEach(x->{
                x.setStatementId(statementId);
                monthEmpChangeMapper.updateById(x);
            });
        }

    }


    private void addStatementImpsByYys(Long comAccountId,Long statementId,YysmxOpt yysmxOpt){
        EmpEmployee employee = employeeMapper.getEmployeeByName(comAccountId,yysmxOpt.getEmpName(),yysmxOpt.getJobNumber().toString());
        ImportOpt opt = new ImportOpt();
        String employeeId = employee != null ? employee.getEmployeeId() : "";
        opt.setEmployeeId(employeeId);
        opt.setEmployeeName(yysmxOpt.getEmpName());
        opt.setSsSerial(yysmxOpt.getJobNumber().toString());
        opt.setChangeType(this.getChangeType(yysmxOpt.getChangeType()));
        opt.setChangeTypeName(yysmxOpt.getChangeType());
        opt.setBaseAmount(yysmxOpt.getPersonalPaymentBase());
        List<SsStatementImp> imps = new ArrayList<>();
        imps.add(this.getStatementImpByPension(statementId,opt,yysmxOpt.getPensionOpt()));
        imps.add(this.getStatementImpByMedical(statementId,opt,yysmxOpt.getMedicalOpt()));
        imps.add(this.getStatementImpByUnemployment(statementId,opt,yysmxOpt.getUnemploymentOpt()));
        if(null != imps && imps.size() > 0){
            imps.forEach(x->ssStatementImpMapper.insert(x));
        }
    }


    private void addStatementImpsByGsy(Long comAccountId,Long statementId,GsymxOpt gsymxOpt){
        EmpEmployee employee = employeeMapper.getEmployeeByName(comAccountId,gsymxOpt.getEmpName(),gsymxOpt.getJobNumber().toString());
        ImportOpt opt = new ImportOpt();
        String employeeId = employee != null ? employee.getEmployeeId() : "";
        opt.setEmployeeId(employeeId);
        opt.setEmployeeName(gsymxOpt.getEmpName());
        opt.setSsSerial(gsymxOpt.getJobNumber().toString());
        opt.setChangeType(this.getChangeType(gsymxOpt.getChangeType()));
        opt.setChangeTypeName(gsymxOpt.getChangeType());
        opt.setBaseAmount(gsymxOpt.getPaymentBase());
        List<SsStatementImp> imps = new ArrayList<>();
        imps.add(this.getStatementImpBySsType(statementId,opt,"DIT00044","工伤保险",gsymxOpt.getAccidentAmount(),gsymxOpt.getAccidentRepayAmount()));
        imps.add(this.getStatementImpBySsType(statementId,opt,"DIT00045","生育保险",gsymxOpt.getMaternityAmount(),gsymxOpt.getMaternityRepayAmount()));
        if(null != imps && imps.size() > 0){
            imps.forEach(x->ssStatementImpMapper.insert(x));
        }
    }


    private SsStatementImp getStatementImpBySsType(Long statementId, ImportOpt opt, String ssType, String ssTypeName, BigDecimal comAmount,BigDecimal comRepayAccount){
        SsStatementImp ssStatementImp = new SsStatementImp();
        ssStatementImp.setStatementId(statementId);
        ssStatementImp.setEmployeeId(opt.getEmployeeId());
        ssStatementImp.setEmpName(opt.getEmployeeName());
        ssStatementImp.setChangeType(opt.getChangeType());
        ssStatementImp.setChangeTypeName(opt.getChangeTypeName());
        ssStatementImp.setBaseAmount(opt.getBaseAmount());
        ssStatementImp.setSsType(ssType);
        ssStatementImp.setSsTypeName(ssTypeName);
        ssStatementImp.setComAmount(comAmount != null ? comAmount : BigDecimal.valueOf(0));
        ssStatementImp.setEmpAmount(BigDecimal.valueOf(0));
        ssStatementImp.setComCompensateAmount(comRepayAccount != null ? comRepayAccount : BigDecimal.valueOf(0));
        ssStatementImp.setEmpCompensateAmount(BigDecimal.valueOf(0));
        ssStatementImp.setOnePayment(BigDecimal.valueOf(0));
        ssStatementImp.setActive(true);
        ssStatementImp.setCreatedTime(LocalDateTime.now());
        ssStatementImp.setCreatedBy("system");
        ssStatementImp.setModifiedTime(LocalDateTime.now());
        ssStatementImp.setModifiedBy("system");
        return ssStatementImp;
    }


    private SsStatementImp getStatementImpByPension(Long statementId, ImportOpt opt, PensionOpt pensionOpt){

        SsStatementImp ssStatementImp = new SsStatementImp();
        ssStatementImp.setStatementId(statementId);
        ssStatementImp.setEmployeeId(opt.getEmployeeId());
        ssStatementImp.setEmpName(opt.getEmployeeName());
        ssStatementImp.setChangeType(opt.getChangeType());
        ssStatementImp.setChangeTypeName(opt.getChangeTypeName());
        ssStatementImp.setBaseAmount(opt.getBaseAmount());
        ssStatementImp.setSsType("DIT00042");
        ssStatementImp.setSsTypeName("养老保险");
        ssStatementImp.setComAmount(pensionOpt.getComAccount() != null ? pensionOpt.getComAccount() : BigDecimal.valueOf(0));
        ssStatementImp.setEmpAmount(pensionOpt.getEmpAccount() != null ? pensionOpt.getEmpAccount() : BigDecimal.valueOf(0));
        ssStatementImp.setComCompensateAmount(pensionOpt.getComRepayAccount() != null ? pensionOpt.getComRepayAccount() : BigDecimal.valueOf(0));
        ssStatementImp.setEmpCompensateAmount(pensionOpt.getEmpRepayAccount() != null ? pensionOpt.getEmpRepayAccount() : BigDecimal.valueOf(0));
        ssStatementImp.setOnePayment(pensionOpt.getOncePaymentAccount() != null ? pensionOpt.getOncePaymentAccount() : BigDecimal.valueOf(0));
        ssStatementImp.setActive(true);
        ssStatementImp.setCreatedTime(LocalDateTime.now());
        ssStatementImp.setCreatedBy("system");
        ssStatementImp.setModifiedTime(LocalDateTime.now());
        ssStatementImp.setModifiedBy("system");
        return ssStatementImp;
    }

    private SsStatementImp getStatementImpByMedical(Long statementId, ImportOpt opt, MedicalOpt medicalOpt){

        SsStatementImp ssStatementImp = new SsStatementImp();
        ssStatementImp.setStatementId(statementId);
        ssStatementImp.setEmployeeId(opt.getEmployeeId());
        ssStatementImp.setEmpName(opt.getEmployeeName());
        ssStatementImp.setChangeType(opt.getChangeType());
        ssStatementImp.setChangeTypeName(opt.getChangeTypeName());
        ssStatementImp.setBaseAmount(opt.getBaseAmount());
        ssStatementImp.setSsType("DIT00043");
        ssStatementImp.setSsTypeName("医疗保险");
        ssStatementImp.setComAmount(medicalOpt.getComAccount() != null ? medicalOpt.getComAccount() : BigDecimal.valueOf(0));
        ssStatementImp.setEmpAmount(medicalOpt.getEmpAccount() != null ? medicalOpt.getEmpAccount() : BigDecimal.valueOf(0));
        ssStatementImp.setComCompensateAmount(medicalOpt.getComRepayAccount() != null ? medicalOpt.getComRepayAccount() : BigDecimal.valueOf(0));
        ssStatementImp.setEmpCompensateAmount(medicalOpt.getEmpRepayAccount() != null ? medicalOpt.getEmpRepayAccount() : BigDecimal.valueOf(0));
        ssStatementImp.setOnePayment(BigDecimal.valueOf(0));
        ssStatementImp.setActive(true);
        ssStatementImp.setCreatedTime(LocalDateTime.now());
        ssStatementImp.setCreatedBy("system");
        ssStatementImp.setModifiedTime(LocalDateTime.now());
        ssStatementImp.setModifiedBy("system");
        return ssStatementImp;
    }

    private SsStatementImp getStatementImpByUnemployment(Long statementId, ImportOpt opt, UnemploymentOpt unemploymentOpt){

        SsStatementImp ssStatementImp = new SsStatementImp();
        ssStatementImp.setStatementId(statementId);
        ssStatementImp.setEmployeeId(opt.getEmployeeId());
        ssStatementImp.setEmpName(opt.getEmployeeName());
        ssStatementImp.setChangeType(opt.getChangeType());
        ssStatementImp.setChangeTypeName(opt.getChangeTypeName());
        ssStatementImp.setBaseAmount(opt.getBaseAmount());
        ssStatementImp.setSsType("DIT00046");
        ssStatementImp.setSsTypeName("失业保险");
        ssStatementImp.setComAmount(unemploymentOpt.getComAccount() != null ? unemploymentOpt.getComAccount() : BigDecimal.valueOf(0));
        ssStatementImp.setEmpAmount(unemploymentOpt.getEmpAccount() != null ? unemploymentOpt.getEmpAccount() : BigDecimal.valueOf(0));
        ssStatementImp.setComCompensateAmount(unemploymentOpt.getComRepayAccount() != null ? unemploymentOpt.getComRepayAccount() : BigDecimal.valueOf(0));
        ssStatementImp.setEmpCompensateAmount(unemploymentOpt.getEmpRepayAccount() != null ? unemploymentOpt.getEmpRepayAccount() : BigDecimal.valueOf(0));
        ssStatementImp.setOnePayment(BigDecimal.valueOf(0));
        ssStatementImp.setActive(true);
        ssStatementImp.setCreatedTime(LocalDateTime.now());
        ssStatementImp.setCreatedBy("system");
        ssStatementImp.setModifiedTime(LocalDateTime.now());
        ssStatementImp.setModifiedBy("system");
        return ssStatementImp;
    }


    private SsStatement getStatement(String ssMonth, String fileType, Long comAccountId, String fileName,Integer recordNum){
        SsStatement ssStatement = new SsStatement();
        ssStatement.setComAccountId(comAccountId);
        ssStatement.setSsMonth(ssMonth);
        ssStatement.setImpFileType(fileType);
        ssStatement.setImpFileName(fileName);
        ssStatement.setImpRecordSum(recordNum);
        ssStatement.setActive(true);
        ssStatement.setCreatedTime(LocalDateTime.now());
        ssStatement.setCreatedBy("system");
        ssStatement.setModifiedTime(LocalDateTime.now());
        ssStatement.setModifiedBy("system");
        return ssStatement;
    }


    private Integer getChangeType(String changeType){
        Integer ssType = 0;
        if(changeType.indexOf("标准") != -1){
            ssType = this.changeTable().get("标准");
        }
        else if(changeType.indexOf("新进") != -1){
            ssType = this.changeTable().get("新进");
        }
        else if(changeType.indexOf("转入") != -1){
            ssType = this.changeTable().get("转入");
        }
        else if(changeType.indexOf("补缴") != -1){
            ssType = this.changeTable().get("补缴");
        }
        else if(changeType.indexOf("调整") != -1){
            ssType = this.changeTable().get("调整");
        }
        else if(changeType.indexOf("转出") != -1){
            ssType = this.changeTable().get("转出");
        }
        else if(changeType.indexOf("封存") != -1){
            ssType = this.changeTable().get("封存");
        }
        else if(changeType.indexOf("退账") != -1){
            ssType = this.changeTable().get("退账");
        }
        else if(changeType.indexOf("倒调") != -1){
            ssType = this.changeTable().get("倒调");
        }
        else{
            ssType = 0;
        }
        return ssType;
    }

    private Hashtable<String,Integer> changeTable(){
        Hashtable<String,Integer> table = new Hashtable<>();
        table.put("标准",1);
        table.put("新进",2);
        table.put("转入",3);
        table.put("补缴",4);
        table.put("调整",5);
        table.put("转出",6);
        table.put("封存",7);
        table.put("退账",8);
        table.put("倒调",9);
        return table;
    }
}