package com.ciicsh.gto.afsupportcenter.healthmedical.business.impl;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.ciicsh.gto.afsupportcenter.healthmedical.business.AcceptanceDetailedService;
import com.ciicsh.gto.afsupportcenter.healthmedical.business.AcceptanceSummaryService;
import com.ciicsh.gto.afsupportcenter.healthmedical.business.SupplyMedicalAcceptanceService;
import com.ciicsh.gto.afsupportcenter.healthmedical.business.SupplyMedicalInvoiceService;
import com.ciicsh.gto.afsupportcenter.healthmedical.dao.SupplyMedicalAcceptanceMapper;
import com.ciicsh.gto.afsupportcenter.healthmedical.entity.bo.AcceptanceStatisticsBO;
import com.ciicsh.gto.afsupportcenter.healthmedical.entity.bo.AcceptanceSummaryBO;
import com.ciicsh.gto.afsupportcenter.healthmedical.entity.bo.TimeScope;
import com.ciicsh.gto.afsupportcenter.healthmedical.entity.dto.SupplyAcceptanceImportDTO;
import com.ciicsh.gto.afsupportcenter.healthmedical.entity.dto.SupplyMedicalAcceptanceDTO;
import com.ciicsh.gto.afsupportcenter.healthmedical.entity.po.AcceptanceDetailed;
import com.ciicsh.gto.afsupportcenter.healthmedical.entity.po.AcceptanceSummary;
import com.ciicsh.gto.afsupportcenter.healthmedical.entity.po.SupplyMedicalAcceptance;
import com.ciicsh.gto.afsupportcenter.healthmedical.entity.po.SupplyMedicalInvoice;
import com.ciicsh.gto.afsupportcenter.util.CommonTransform;
import com.ciicsh.gto.afsupportcenter.util.poi.OfficeIoResult;
import com.ciicsh.gto.afsupportcenter.util.poi.OfficeIoUtils;
import com.ciicsh.gto.afsupportcenter.util.poi.model.CellSettings;
import com.ciicsh.gto.afsupportcenter.util.poi.model.DatePattern;
import com.ciicsh.gto.afsupportcenter.util.poi.model.SheetSettings;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 补充医疗受理单表 服务实现类
 * </p>
 *
 * @author xiweizhen
 */
@Service
public class SupplyMedicalAcceptanceServiceImpl extends ServiceImpl<SupplyMedicalAcceptanceMapper, SupplyMedicalAcceptance> implements SupplyMedicalAcceptanceService {
    /**
     * 记录日志
     */
    private static Logger logger = LoggerFactory.getLogger(SupplyMedicalAcceptanceServiceImpl.class);

    private static String CHILDREN = "子女";
    private static String SPOUSE = "配偶";

    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private SupplyMedicalInvoiceService supplyMedicalInvoiceService;
    @Autowired
    private AcceptanceSummaryService acceptanceSummaryService;
    @Autowired
    private AcceptanceDetailedService acceptanceDetailedService;

    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public Page<SupplyMedicalAcceptance> queryAcceptancePage(Page<SupplyMedicalAcceptance> page, SupplyMedicalAcceptanceDTO supplyMedicalAcceptanceDTO) {
        page.setRecords(baseMapper.queryAcceptancePage(page, supplyMedicalAcceptanceDTO));
        return page;
    }

    @Override
    public boolean syncAcceptanceSummaryDetail() {
        TimeScope timeScope = new TimeScope("2018-1-26 14:00:00", "2018-1-26 15:00:00");
        String summaryUrl = "http://10.17.3.1/MedicalTPA/FileOperationForJSON.svc/GetMedicalScheme";
        List list = restTemplate.postForEntity(summaryUrl, timeScope, List.class).getBody();
//        String str = JSON.toJSONString(list);
//        List<AcceptanceSummaryBO> acceptanceSummaryBOS = JSONObject.parseArray(str, AcceptanceSummaryBO.class);
        List<AcceptanceSummaryBO> acceptanceSummaryBOS = (List<AcceptanceSummaryBO>) list.stream().map(li -> objectMapper.convertValue(li, AcceptanceSummaryBO.class)).collect(Collectors.toList());

        /*插入补充医疗备份数据表*/
        for (AcceptanceSummaryBO acceptanceSummaryBO : acceptanceSummaryBOS) {
            /*查询受理单数据*/
            AcceptanceSummary acceptanceSummary = acceptanceSummaryService.selectById(acceptanceSummaryBO.getAcceptanceId());

            AcceptanceSummary acceptanceSummary1 = new AcceptanceSummary();
            List<AcceptanceDetailed> acceptanceDetaileds = acceptanceSummaryBO.getMedicalSchemeDeatils();
            BeanUtils.copyProperties(acceptanceSummaryBO, acceptanceSummary1);

            //如果存在受理单数据，删除受理单、发票备份数据
            if (acceptanceSummary != null) {
                acceptanceSummaryService.deleteById(acceptanceSummaryBO.getAcceptanceId());
                acceptanceDetailedService.deleteBatchIds(acceptanceDetaileds);
            }
            acceptanceSummaryService.insert(acceptanceSummary1);
            acceptanceDetailedService.insertBatch(acceptanceDetaileds);

            /**
             * 判断受理单是否存在，
             * 1、不存在这条受理单插入数据
             * 2、如果存在且状态是未受理，删除受理单信息和发票信息
             * 3、存在数据且状态不是未受理，跳过这条信息
             */
            SupplyMedicalAcceptance supplyMedicalAcceptance = baseMapper.selectById(acceptanceSummaryBO.getAcceptanceId());
            SupplyMedicalAcceptance supplyMedicalAcceptance1 = new SupplyMedicalAcceptance();
            BeanUtils.copyProperties(acceptanceSummaryBO, supplyMedicalAcceptance1);
            List<SupplyMedicalInvoice> supplyMedicalInvoices = CommonTransform.convertToDTOs(acceptanceDetaileds, SupplyMedicalInvoice.class);

            /*受理单类别转化*/
            supplyMedicalAcceptance1.setType(msgTran(acceptanceSummaryBO.getType()));

            //如果存在受理单数据，删除受理单、发票数据
            if (supplyMedicalAcceptance != null && supplyMedicalAcceptance.getStatus() == 0) {
                baseMapper.deleteById(acceptanceSummaryBO.getAcceptanceId());
                supplyMedicalInvoiceService.deleteByAcceptanceId(acceptanceSummaryBO.getAcceptanceId());
            } else if (supplyMedicalAcceptance != null && supplyMedicalAcceptance.getStatus() != 0) {
                logger.info("已审批的受理单数据" + supplyMedicalAcceptance.toString());
                continue;
            }
            baseMapper.insert(supplyMedicalAcceptance1);
            supplyMedicalInvoiceService.insertBatch(supplyMedicalInvoices);
        }

        return true;
    }

    @Override
    public AcceptanceStatisticsBO queryAcceptanceTotal(SupplyMedicalAcceptanceDTO supplyMedicalAcceptanceDTO) {
        AcceptanceStatisticsBO acceptanceStatisticsBO = baseMapper.queryAcceptanceTotal(supplyMedicalAcceptanceDTO);
        acceptanceStatisticsBO.setInvoiceTotal(supplyMedicalInvoiceService.queryInvoiceCount(supplyMedicalAcceptanceDTO));
        return acceptanceStatisticsBO;
    }

    public static void main(String[] args) {
        importAcceptanceXls();
    }

    private static void importAcceptanceXls() {
        File file = new File("C:\\Users\\xiweizhen.CIIC\\Desktop\\test\\02-JY201801231002.xls");

        try {
            OfficeIoResult officeIoResult = importXls(file);

            List list = officeIoResult.getImportList();
            if (list != null && list.size() > 0) {
                exportXls(list);

            }
            if (!officeIoResult.isCompleted()) {
                officeIoResult.getErrors();
                OfficeIoResult result = OfficeIoUtils.exportErrorRecord(officeIoResult.getSheetSettings(), officeIoResult.getErrRecordRows());
                FileOutputStream out = new FileOutputStream("d:\\successError.xlsx");
                result.getResultWorkbook().write(out);
                out.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static Integer msgTran(String str) {
        String EMPLOYEE = "雇员";
        if (EMPLOYEE.equals(str)) {
            return 1;
        } else if (CHILDREN.equals(str)) {
            return 2;
        } else if (SPOUSE.equals(str)) {
            return 3;
        }
        return null;
    }


    private static OfficeIoResult importXls(File file) {
        SheetSettings sheet = new SheetSettings("sheet1", SupplyAcceptanceImportDTO.class);
        sheet = acceptanceCellSettings(sheet);

        OfficeIoResult officeIoResult = OfficeIoUtils.importXlsx(file, new SheetSettings[]{sheet});
        return officeIoResult;
    }

    private static void exportXls(List list) throws IOException {
        SheetSettings sheet = new SheetSettings("导入成功的数据", SupplyAcceptanceImportDTO.class);
        sheet = acceptanceCellSettings(sheet);
        sheet.setExportData(list);

        FileOutputStream out = new FileOutputStream("d:\\successList.xlsx");
        OfficeIoResult result = OfficeIoUtils.exportXlsx(new SheetSettings[]{sheet});
        result.getResultWorkbook().write(out);
        out.close();
    }

    private static SheetSettings acceptanceCellSettings(SheetSettings sheetSettings) {
        sheetSettings.setCellSettings(new CellSettings[]{
            new CellSettings("acceptanceId", "受理批号"),
            new CellSettings("dossierNumber", "案卷号"),
            new CellSettings("employeeId", "雇员编号"),
            new CellSettings("employeeName", "雇员姓名"),
            new CellSettings("col1", "身份代码"),
            new CellSettings("col2", "身份证"),
            new CellSettings("col3", "医保标志"),
            new CellSettings("invoiceNumber", "发票张数"),
            new CellSettings("claimAmount", "索赔金额"),
            new CellSettings("clinicDate", "就诊日期").addPattern(DatePattern.DATE_FORMAT_DAY),
            new CellSettings("col4", "受理日期"),
            new CellSettings("medicalInstitution", "就诊机构"),
            new CellSettings("diseaseName", "疾病名称"),
            new CellSettings("col5", "状态"),
            new CellSettings("approvedAmount", "核赔金额"),
            new CellSettings("closedStatus", "结案状态"),
            new CellSettings("col6", "拒赔原因"),
            new CellSettings("col7", "结案日期"),
            new CellSettings("col8", "财务年度累计已赔金额"),
            new CellSettings("col9", "保单年度累计已赔金额"),
            new CellSettings("col10", "国寿理赔金额"),
            new CellSettings("col11", "国寿结案日期"),
            new CellSettings("closedRemark", "结案备注"),
            new CellSettings("col12", "发票类型"),
            new CellSettings("accountAmount", "账户金额"),
            new CellSettings("selfPayAmount", "自付金额"),
            new CellSettings("wholePlanAmount", "统筹金额"),
            new CellSettings("attachAmount", "附加金额"),
            new CellSettings("ownExpenseAmount", "自费金额"),
            new CellSettings("col13", "不属医保报销金额"),
            new CellSettings("applicationAmount", "申请金额"),
            new CellSettings("approvedAmount", "核准金额"),
            new CellSettings("col14", "他方补偿金额"),
            new CellSettings("col15", "他方补偿原因"),
            new CellSettings("col16", "部分拒付金额"),
            new CellSettings("col17", "部分拒付原因"),
            new CellSettings("col18", "调整给付金额"),
            new CellSettings("col19", "调整给付原因"),
            new CellSettings("col20", "理赔金额"),
            new CellSettings("insuredName", "连带被保险人姓名"),
            new CellSettings("col21", "分类自负金额"),
            new CellSettings("col22", "投保公司"),
            new CellSettings("col23", "报销类别"),
            new CellSettings("col24", "中智赔付"),
            new CellSettings("insuranceCompanyMoney", "保险公司赔付"),
        });
        return sheetSettings;
    }

}
