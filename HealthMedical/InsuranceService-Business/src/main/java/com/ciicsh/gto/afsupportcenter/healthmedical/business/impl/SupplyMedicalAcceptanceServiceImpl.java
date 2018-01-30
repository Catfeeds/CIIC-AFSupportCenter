package com.ciicsh.gto.afsupportcenter.healthmedical.business.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.ciicsh.gto.afsupportcenter.healthmedical.business.AcceptanceDetailedService;
import com.ciicsh.gto.afsupportcenter.healthmedical.business.AcceptanceSummaryService;
import com.ciicsh.gto.afsupportcenter.healthmedical.business.SupplyMedicalAcceptanceService;
import com.ciicsh.gto.afsupportcenter.healthmedical.business.SupplyMedicalInvoiceService;
import com.ciicsh.gto.afsupportcenter.healthmedical.dao.SupplyMedicalAcceptanceMapper;
import com.ciicsh.gto.afsupportcenter.healthmedical.entity.bo.TimeScope;
import com.ciicsh.gto.afsupportcenter.healthmedical.entity.po.AcceptanceDetailed;
import com.ciicsh.gto.afsupportcenter.healthmedical.entity.po.AcceptanceSummary;
import com.ciicsh.gto.afsupportcenter.healthmedical.entity.po.SupplyMedicalAcceptance;
import com.ciicsh.gto.afsupportcenter.healthmedical.entity.po.SupplyMedicalInvoice;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 补充医疗受理单表 服务实现类
 * </p>
 *
 * @author xiweizhen
 */
@Service
public class SupplyMedicalAcceptanceServiceImpl extends ServiceImpl<SupplyMedicalAcceptanceMapper, SupplyMedicalAcceptance> implements SupplyMedicalAcceptanceService {
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private SupplyMedicalInvoiceService supplyMedicalInvoiceService;
    @Autowired
    private AcceptanceSummaryService acceptanceSummaryService;
    @Autowired
    private AcceptanceDetailedService acceptanceDetailedService;

    @Override
    public boolean syncAcceptanceSummaryDetail() {
        TimeScope timeScope = new TimeScope("2018-1-26", "2018-1-29");
        String summaryUrl = "http://10.17.3.1/MedicalTPA/FileOperationForJSON.svc/GetMedicalScheme";
        String detailedUrl = "http://10.17.3.1/MedicalTPA/FileOperationForJSON.svc/GetMedicalSchemeDeatils";
        List<AcceptanceSummary> acceptanceSummaryList = restTemplate.postForEntity(summaryUrl, timeScope, List.class).getBody();
        List<AcceptanceDetailed> acceptanceDetailedList = restTemplate.postForEntity(detailedUrl, timeScope, List.class).getBody();

        /**插入补充医疗备份数据表*/
        acceptanceSummaryService.insertBatch(acceptanceSummaryList);
        boolean flag = acceptanceDetailedService.insertBatch(acceptanceDetailedList);

        /**插入补充医疗业务数据表*/
        List<SupplyMedicalAcceptance> supplyMedicalAcceptanceList = new ArrayList<>();
        List<SupplyMedicalInvoice> supplyMedicalInvoiceList = new ArrayList<>();
        BeanUtils.copyProperties(acceptanceSummaryList, supplyMedicalAcceptanceList);
        BeanUtils.copyProperties(acceptanceDetailedList, supplyMedicalInvoiceList);

        /**
         * 判断受理单是否存在，
         * 如果存在且状态是未受理，删除受理单信息和发票信息
         * 其他情况跳过这条信息
         */
        for (SupplyMedicalAcceptance acceptanceEntity : supplyMedicalAcceptanceList) {
            SupplyMedicalInvoice detailEntity = new SupplyMedicalInvoice();
            if (acceptanceEntity.getStatus() == 0) {
                baseMapper.deleteById(acceptanceEntity.getAcceptanceId());
                detailEntity.setAcceptanceId(acceptanceEntity.getAcceptanceId());
                supplyMedicalInvoiceService.deleteByEntity(detailEntity);
                baseMapper.insert(acceptanceEntity);
            }
        }

        return flag;
    }
}
