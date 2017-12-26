package com.ciicsh.gto.afsupportcenter.flexiblebenefit.fbcommandservice.host.controller;


import com.ciicsh.gto.afsupportcenter.flexiblebenefit.fbcommandservice.api.core.Result;
import com.ciicsh.gto.afsupportcenter.flexiblebenefit.fbcommandservice.api.core.ResultGenerator;
import com.ciicsh.gto.afsupportcenter.flexiblebenefit.entity.po.GiftPO;
import com.ciicsh.gto.afsupportcenter.flexiblebenefit.fbcommandservice.api.dto.GiftDTO;
import com.ciicsh.gto.afsupportcenter.flexiblebenefit.fbcommandservice.business.GiftCommandService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

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
            boolean flag = giftCommandService.updateById(giftPO);
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


}
