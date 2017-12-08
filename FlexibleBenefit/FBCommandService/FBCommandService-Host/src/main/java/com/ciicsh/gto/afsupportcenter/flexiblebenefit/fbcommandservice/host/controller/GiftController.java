package com.ciicsh.gto.afsupportcenter.flexiblebenefit.fbcommandservice.host.controller;


import com.ciicsh.gto.afsupportcenter.flexiblebenefit.fbcommandservice.business.GiftService;
import com.ciicsh.gto.afsupportcenter.flexiblebenefit.fbcommandservice.entity.po.GiftPO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
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
    public int giftInsert(GiftPO entity, MultipartFile file, HttpServletRequest request) {
        try {
            if (file != null && !file.isEmpty()) {
                String filePath = request.getSession().getServletContext().getRealPath("/") + file.getOriginalFilename();
                // 转存文件
                file.transferTo(new File(filePath));
                File f = new File(filePath);
                /**上传图片*/
                String filePathUrl = giftService.fileUpdate(f);

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
        File f = new File("c://test");
        try {
            file.transferTo(f);
        } catch (IOException e) {
            e.printStackTrace();
        }
        String filepath = giftService.fileUpdate(f);
        if ("".equals(filepath)) {
            logger.info("command服务--礼品图片添加失败");
        } else {
            logger.info("command服务--礼品图片添加成功");
        }
        return filepath;
    }


}
