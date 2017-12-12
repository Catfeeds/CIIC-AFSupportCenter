package com.ciicsh.gto.afsupportcenter.flexiblebenefit.fbcommandservice.host.controller;


import com.ciicsh.gto.afsupportcenter.flexiblebenefit.fbcommandservice.business.GiftService;
import com.ciicsh.gto.afsupportcenter.flexiblebenefit.fbcommandservice.entity.po.GiftPO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * @author xiweizhen
 */
@RestController
@RequestMapping("/giftCommandService")
public class GiftController {
    /**
     * 记录日志
     */
    private static Logger logger = LoggerFactory.getLogger(GiftController.class);

    @Autowired
    private GiftService giftService;

    /**
     * 根据主键查询礼品信息
     *
     * @param id
     * @return
     */
    @GetMapping("/findById/{id}")
    public GiftPO findById(@PathVariable Integer id) {
        GiftPO entity = giftService.findById(id);
        return entity;
    }

    /**
     * 礼品新增功能
     *
     * @param
     * @return
     */
    @PostMapping("/giftInsert")
    public int giftInsert(GiftPO entity, MultipartFile file) {
        try {
            if (file != null && !file.isEmpty()) {
                /**上传图片*/
                String filePathUrl = giftService.fileUpdate(file.getInputStream());

                /**如果修改图片，清除原图片，再保存新图片*/
                if (entity.getPictureUrl() != null && !"".equals(entity.getPictureUrl())) {
                    giftService.deletePicture(entity.getPictureUrl());
                }
                entity.setPictureUrl(filePathUrl);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        int t = giftService.insertGift(entity);
        if (t == 1) {
            logger.info("command服务--礼品添加成功");
        } else {
            logger.info("command服务--礼品添加失败");
        }
        return t;
    }

    /**
     * 礼品新增功能
     *
     * @param file
     * @return
     */
    @PostMapping("/updateFile")
    public String updateFile(MultipartFile file) {
        String filepath = "";
        try {
            filepath = giftService.fileUpdate(file.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
        if ("".equals(filepath)) {
            logger.info("command服务--礼品图片添加失败");
        } else {
            logger.info("command服务--礼品图片添加成功");
        }
        return filepath;
    }


}
