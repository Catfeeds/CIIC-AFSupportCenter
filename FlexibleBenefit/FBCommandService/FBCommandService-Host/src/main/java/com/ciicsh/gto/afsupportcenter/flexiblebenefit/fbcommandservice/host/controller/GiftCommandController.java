package com.ciicsh.gto.afsupportcenter.flexiblebenefit.fbcommandservice.host.controller;


import com.ciicsh.gto.afsupportcenter.flexiblebenefit.entity.po.ApplyGiftRecordPO;
import com.ciicsh.gto.afsupportcenter.flexiblebenefit.entity.po.ApplyRecordDetailPO;
import com.ciicsh.gto.afsupportcenter.flexiblebenefit.entity.po.ApplyRecordPO;
import com.ciicsh.gto.afsupportcenter.flexiblebenefit.entity.po.GiftPO;
import com.ciicsh.gto.afsupportcenter.flexiblebenefit.fbcommandservice.api.core.Result;
import com.ciicsh.gto.afsupportcenter.flexiblebenefit.fbcommandservice.api.core.ResultGenerator;
import com.ciicsh.gto.afsupportcenter.flexiblebenefit.fbcommandservice.api.dto.GiftApplyDTO;
import com.ciicsh.gto.afsupportcenter.flexiblebenefit.fbcommandservice.api.dto.GiftDTO;
import com.ciicsh.gto.afsupportcenter.flexiblebenefit.fbcommandservice.business.ApplyGiftRecordCommandService;
import com.ciicsh.gto.afsupportcenter.flexiblebenefit.fbcommandservice.business.ApplyRecordCommandService;
import com.ciicsh.gto.afsupportcenter.flexiblebenefit.fbcommandservice.business.ApplyRecordDetailCommandService;
import com.ciicsh.gto.afsupportcenter.flexiblebenefit.fbcommandservice.business.GiftCommandService;
import com.ciicsh.gto.sheetservice.api.SheetServiceProxy;
import com.ciicsh.gto.sheetservice.api.dto.request.MissionRequestDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

/**
 * @author xiweizhen
 */
@RestController
@RequestMapping("/giftCommandService")
public class GiftCommandController {
    /**
     * 记录日志
     */
    private static Logger logger = LoggerFactory.getLogger(GiftCommandController.class);

    @Autowired
    private GiftCommandService giftCommandService;
    @Autowired
    private ApplyRecordCommandService applyRecordCommandService;
    @Autowired
    private ApplyRecordDetailCommandService applyRecordDetailCommandService;
    @Autowired
    private ApplyGiftRecordCommandService applyGiftRecordCommandService;

    /**
     * 对接任务单系统方法
     */
    @Autowired
    private SheetServiceProxy sheetServiceProxy;

    /**
     * 根据主键查询礼品信息
     *
     * @param id
     * @return
     */
    @GetMapping("/findById/{id}")
    public Result findById(@PathVariable Integer id) {
        try {
            GiftPO entity = giftCommandService.findById(id);
            logger.info("根据主键查询礼品信息");
            return ResultGenerator.genSuccessResult(entity);
        } catch (Exception e) {
            logger.info(e.getMessage());
            return ResultGenerator.genServerFailResult();
        }
    }

    /**
     * 礼品新增功能
     *
     * @param
     * @return
     */
    @PostMapping("/giftInsert")
    public Result giftInsert(GiftDTO giftDTO, MultipartFile file) {
        GiftPO giftPO = new GiftPO();
        BeanUtils.copyProperties(giftDTO, giftPO);
        try {
            if (file != null) {
                /**上传图片*/
                String filePathUrl = giftCommandService.fileUpdate(file.getInputStream());
                giftPO.setPictureUrl(filePathUrl);
            }

            boolean flag = giftCommandService.insert(giftPO);
            logger.info("礼品新增");
            return ResultGenerator.genSuccessResult(flag);
        } catch (Exception e) {
            return ResultGenerator.genServerFailResult(e.getMessage());
        }
    }

    /**
     * 礼品修改
     *
     * @param
     * @return
     */
    @PostMapping("/giftUpdate")
    public Result giftUpdate(GiftDTO giftDTO, MultipartFile file) {
        GiftPO giftPO = new GiftPO();
        BeanUtils.copyProperties(giftDTO, giftPO);
        try {
            if (file != null && !file.isEmpty()) {
                /**上传图片*/
                String filePathUrl = giftCommandService.fileUpdate(file.getInputStream());

                /**如果修改图片，清除原图片，再保存新图片*/
//                if (entity.getPictureUrl() != null && !"".equals(entity.getPictureUrl())) {
//                    giftCommandService.deletePicture(entity.getPictureUrl());
//                }
                giftPO.setPictureUrl(filePathUrl);
            }
            boolean flag = giftCommandService.updateGift(giftPO);
            logger.info("礼品修改");
            return ResultGenerator.genSuccessResult(flag);
        } catch (Exception e) {
            return ResultGenerator.genServerFailResult();
        }
    }

    /**
     * 礼品新增功能
     *
     * @param file
     * @return
     */
    @PostMapping("/updateFile")
    public Result updateFile(MultipartFile file) {
        try {
            String filePathUrl = giftCommandService.fileUpdate(file.getInputStream());
            logger.info("上传图片");
            return ResultGenerator.genSuccessResult(filePathUrl);
        } catch (Exception e) {
            return ResultGenerator.genServerFailResult();
        }
    }

    /**
     * 礼品申请功能
     *
     * @param giftApplyDTO
     * @return
     */
    @PostMapping("/insertGiftApply")
    public Result insertGiftApply(@RequestBody GiftApplyDTO giftApplyDTO) {
        try {
            ApplyRecordPO applyRecordPO = new ApplyRecordPO();
            ApplyRecordDetailPO applyRecordDetailPO = new ApplyRecordDetailPO();
            ApplyGiftRecordPO applyGiftRecordPO = new ApplyGiftRecordPO();
            GiftPO giftPO = new GiftPO();

            BeanUtils.copyProperties(giftApplyDTO, applyRecordPO);
            BeanUtils.copyProperties(giftApplyDTO, applyRecordDetailPO);
            BeanUtils.copyProperties(giftApplyDTO, applyGiftRecordPO);
            BeanUtils.copyProperties(giftApplyDTO, giftPO);

            //申请类型为礼品--1
            applyRecordPO.setApplyType(1);
            applyRecordCommandService.insert(applyRecordPO);
            logger.info("新增活动申请ApplyRecordPO主键" + applyRecordPO.getApplyRecordId());

            /**返回申请主键*/
            applyRecordDetailPO.setApplyRecordId(applyRecordPO.getApplyRecordId());
            applyRecordDetailCommandService.insert(applyRecordDetailPO);
            logger.info("新增活动申请ApplyRecordPO主键" + applyRecordDetailPO.getApplyRecordDetailId());

            /**返回申请详情主键*/
            applyGiftRecordPO.setApplyRecordDetailId(applyRecordDetailPO.getApplyRecordDetailId());
            boolean flag = applyGiftRecordCommandService.insert(applyGiftRecordPO);

            /**礼品数量变更*/
            Integer number = giftPO.getNumber();
            Integer applyNum = applyGiftRecordPO.getApplyNum();
            giftPO.setNumber(number - applyNum);
            giftCommandService.updateGift(giftPO);

            //对接任务单系统
            MissionRequestDTO missionRequestDTO = new MissionRequestDTO();
            missionRequestDTO.setMissionId(applyRecordDetailPO.getApplyRecordDetailId().toString());
            missionRequestDTO.setProcessDefinitionKey("gift_apply");
//            Map<String, Object> variable = new HashMap<>(10);
//            variable.put("action", "approval");
//            missionRequestDTO.setVariable(variable);
            com.ciicsh.gto.sheetservice.api.dto.core.Result restResult = sheetServiceProxy.startProcess(missionRequestDTO);
            Map<String, String> startProcessResponseMap = (Map<String, String>) restResult.getObject();
            logger.info(String.valueOf("code:" + restResult.getCode() + ",message:") + restResult.getMessage());
            logger.info(startProcessResponseMap.toString());
            logger.info("sale系统收到启动流程接口返回：" + missionRequestDTO.toString());

            logger.info("新增礼品申请");
            return ResultGenerator.genSuccessResult(flag);
        } catch (Exception e) {
            return ResultGenerator.genServerFailResult();
        }
    }

}
