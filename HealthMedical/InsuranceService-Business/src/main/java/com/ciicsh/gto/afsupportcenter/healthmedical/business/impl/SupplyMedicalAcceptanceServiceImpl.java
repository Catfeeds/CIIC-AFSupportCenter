package com.ciicsh.gto.afsupportcenter.healthmedical.business.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
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
import com.ciicsh.gto.afsupportcenter.healthmedical.entity.dto.SupplyMedicalAcceptanceDTO;
import com.ciicsh.gto.afsupportcenter.healthmedical.entity.po.AcceptanceDetailed;
import com.ciicsh.gto.afsupportcenter.healthmedical.entity.po.AcceptanceSummary;
import com.ciicsh.gto.afsupportcenter.healthmedical.entity.po.SupplyMedicalAcceptance;
import com.ciicsh.gto.afsupportcenter.healthmedical.entity.po.SupplyMedicalInvoice;
import com.ciicsh.gto.afsupportcenter.util.CommonTransform;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

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

    private static String EMPLOYEE = "雇员";
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
    public void acceptance() {
        EntityWrapper<AcceptanceSummary> entityWrapper = new EntityWrapper<>();
        List<AcceptanceSummary> acceptanceSummaryList = acceptanceSummaryService.selectList(entityWrapper);

        EntityWrapper<AcceptanceDetailed> entityWrapper1 = new EntityWrapper<>();
        List<AcceptanceDetailed> acceptanceDetaileds = acceptanceDetailedService.selectList(entityWrapper1);

        List<SupplyMedicalAcceptance> supplyMedicalAcceptances = CommonTransform.convertToDTOs(acceptanceSummaryList, SupplyMedicalAcceptance.class);
        List<SupplyMedicalInvoice> supplyMedicalInvoicees = CommonTransform.convertToDTOs(acceptanceDetaileds, SupplyMedicalInvoice.class);
        this.insertBatch(supplyMedicalAcceptances);
        supplyMedicalInvoiceService.insertBatch(supplyMedicalInvoicees);
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

    public static Integer msgTran(String str) {
        if (EMPLOYEE.equals(str)) {
            return 1;
        } else if (CHILDREN.equals(str)) {
            return 2;
        } else if (SPOUSE.equals(str)) {
            return 3;
        }
        return null;
    }
}
