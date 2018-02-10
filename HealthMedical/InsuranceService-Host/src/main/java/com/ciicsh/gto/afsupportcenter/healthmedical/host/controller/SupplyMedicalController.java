package com.ciicsh.gto.afsupportcenter.healthmedical.host.controller;

import com.baomidou.mybatisplus.plugins.Page;
import com.ciicsh.gto.afsupportcenter.healthmedical.business.SupplyMedicalAcceptanceService;
import com.ciicsh.gto.afsupportcenter.healthmedical.business.SupplyMedicalInvoiceService;
import com.ciicsh.gto.afsupportcenter.healthmedical.entity.bo.AcceptanceStatisticsBO;
import com.ciicsh.gto.afsupportcenter.healthmedical.entity.core.Result;
import com.ciicsh.gto.afsupportcenter.healthmedical.entity.core.ResultGenerator;
import com.ciicsh.gto.afsupportcenter.healthmedical.entity.dto.SupplyAcceptanceDetailDTO;
import com.ciicsh.gto.afsupportcenter.healthmedical.entity.dto.SupplyMedicalAcceptanceDTO;
import com.ciicsh.gto.afsupportcenter.healthmedical.entity.dto.SupplyMedicalInvoiceDTO;
import com.ciicsh.gto.afsupportcenter.healthmedical.entity.po.SupplyMedicalAcceptance;
import com.ciicsh.gto.afsupportcenter.healthmedical.entity.po.SupplyMedicalInvoice;
import com.ciicsh.gto.afsupportcenter.util.CommonTransform;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * 补充医疗理赔
 *
 * @author xiweizhen
 */
@RestController
@RequestMapping("/supplyMedicalService")
public class SupplyMedicalController {
    /**
     * 记录日志
     */
    private static Logger logger = LoggerFactory.getLogger(SupplyMedicalController.class);

    @Autowired
    private SupplyMedicalAcceptanceService supplyMedicalAcceptanceService;
    @Autowired
    private SupplyMedicalInvoiceService supplyMedicalInvoiceService;


    /**
     * 分页查询
     *
     * @param supplyMedicalAcceptanceDTO
     * @return
     */
    @PostMapping("/queryAcceptancePage")
    public Result queryAcceptancePage(@RequestBody SupplyMedicalAcceptanceDTO supplyMedicalAcceptanceDTO) {
        try {
            Page<SupplyMedicalAcceptance> page = new Page<>(supplyMedicalAcceptanceDTO.getCurrent(), supplyMedicalAcceptanceDTO.getSize());
            page = supplyMedicalAcceptanceService.queryAcceptancePage(page, supplyMedicalAcceptanceDTO);
            logger.info("补充医疗分页查询");
            return ResultGenerator.genSuccessResult(page);
        } catch (Exception e) {
            return ResultGenerator.genServerFailResult();
        }
    }

    /**
     * 受理单查询--统计信息
     *
     * @param supplyMedicalAcceptanceDTO
     * @return
     */
    @PostMapping("/queryAcceptanceTotal")
    public Result queryAcceptanceTotal(@RequestBody SupplyMedicalAcceptanceDTO supplyMedicalAcceptanceDTO) {
        try {
            AcceptanceStatisticsBO acceptanceStatisticsBO = supplyMedicalAcceptanceService.queryAcceptanceTotal(supplyMedicalAcceptanceDTO);
            logger.info("补充医疗查询汇总信息");
            return ResultGenerator.genSuccessResult(acceptanceStatisticsBO);
        } catch (Exception e) {
            return ResultGenerator.genServerFailResult();
        }
    }

    /**
     * 更新受理单数据
     *
     * @param supplyMedicalAcceptanceDTO
     * @return
     */
    @PostMapping("/updateAcceptance")
    public Result updateAcceptance(@RequestBody SupplyMedicalAcceptanceDTO supplyMedicalAcceptanceDTO) {
        try {
            SupplyMedicalAcceptance supplyMedicalAcceptance = new SupplyMedicalAcceptance();
            BeanUtils.copyProperties(supplyMedicalAcceptanceDTO, supplyMedicalAcceptance);
            boolean flag = supplyMedicalAcceptanceService.updateById(supplyMedicalAcceptance);
            return ResultGenerator.genSuccessResult(flag);
        } catch (Exception e) {
            return ResultGenerator.genServerFailResult();
        }
    }

    /**
     * 更新受理单list数据
     *
     * @param supplyMedicalAcceptanceDTOS
     * @return
     */
    @PostMapping("/updateAcceptanceList")
    public Result updateAcceptanceList(@RequestBody List<SupplyMedicalAcceptanceDTO> supplyMedicalAcceptanceDTOS) {
        try {
            List<SupplyMedicalAcceptance> supplyMedicalAcceptances = CommonTransform.convertToDTOs(supplyMedicalAcceptanceDTOS, SupplyMedicalAcceptance.class);
            boolean flag = supplyMedicalAcceptanceService.updateBatchById(supplyMedicalAcceptances);
            return ResultGenerator.genSuccessResult(flag);
        } catch (Exception e) {
            return ResultGenerator.genServerFailResult();
        }
    }

    /**
     * 根据受理单主键查询发票列表
     *
     * @param id
     * @return
     */
    @GetMapping("/queryMedicalInvoiceList/{id}")
    public Result queryMedicalInvoiceList(@PathVariable String id) {
        try {
            List<SupplyMedicalInvoice> supplyMedicalInvoices = supplyMedicalInvoiceService.queryMedicalInvoiceList(id);
            return ResultGenerator.genSuccessResult(supplyMedicalInvoices);
        } catch (Exception e) {
            return ResultGenerator.genServerFailResult();
        }
    }

    /**
     * 更新发票数据
     *
     * @param supplyMedicalInvoiceDTO
     * @return
     */
    @PostMapping("/updateMedicalInvoice")
    public Result updateMedicalInvoice(@RequestBody SupplyMedicalInvoiceDTO supplyMedicalInvoiceDTO) {
        try {
            SupplyMedicalInvoice supplyMedicalInvoice = new SupplyMedicalInvoice();
            BeanUtils.copyProperties(supplyMedicalInvoiceDTO, supplyMedicalInvoice);
            boolean flag = supplyMedicalInvoiceService.updateById(supplyMedicalInvoice);
            return ResultGenerator.genSuccessResult(flag);
        } catch (Exception e) {
            return ResultGenerator.genServerFailResult();
        }
    }

    /**
     * 查询发票详情数据
     *
     * @param acceptanceId
     * @return
     */
    @GetMapping("/queryMedicalInvoiceDetail/{acceptanceId}")
    public Result queryMedicalInvoiceDetail(@PathVariable String acceptanceId) {
        try {
            SupplyAcceptanceDetailDTO supplyMedicalInvoice = new SupplyAcceptanceDetailDTO();
            supplyMedicalInvoice.setEmployee(supplyMedicalInvoiceService.queryEmployeeInfo(acceptanceId));
            supplyMedicalInvoice.setSupplyMedicalInvoices(supplyMedicalInvoiceService.queryMedicalInvoiceList(acceptanceId));

            return ResultGenerator.genSuccessResult(supplyMedicalInvoice);
        } catch (Exception e) {
            return ResultGenerator.genServerFailResult();
        }
    }

    /**
     * 查询发票详情数据
     *
     * @param multipartFile
     * @return
     */
    @PostMapping("/importAcceptanceXls")
    public Result importAcceptanceXls(MultipartFile multipartFile) {
        try {
            supplyMedicalAcceptanceService.importAcceptanceXls(multipartFile.getInputStream());
            return ResultGenerator.genSuccessResult();
        } catch (Exception e) {
            return ResultGenerator.genServerFailResult();
        }
    }

    /**
     * 同步智灵通受理单、发票数据
     *
     * @return
     */
    @Scheduled(cron = "0 0 */2 * * ?")
    public Result syncAcceptanceSummaryDetail() {
        try {
            boolean flag = supplyMedicalAcceptanceService.syncAcceptanceSummaryDetail();
            logger.info("同步智灵通受理单、发票数据");
            return ResultGenerator.genSuccessResult(flag);
        } catch (Exception e) {
            return ResultGenerator.genServerFailResult();
        }
    }

    /**
     * 定时更新数据
     *
     * @return
     */
    @Scheduled(cron = "0 0 2 * * ?")
    public Result updateAcceptanceStatus() {
        try {
            supplyMedicalAcceptanceService.updateAcceptanceStatus();
            logger.info("定时更新受理单状态");
            return ResultGenerator.genSuccessResult();
        } catch (Exception e) {
            return ResultGenerator.genServerFailResult();
        }
    }


}
