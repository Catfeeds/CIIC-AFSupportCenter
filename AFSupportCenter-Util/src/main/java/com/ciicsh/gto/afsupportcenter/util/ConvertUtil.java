package com.ciicsh.gto.afsupportcenter.util;

import com.baomidou.mybatisplus.plugins.Page;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.DirectFieldAccessor;
import org.springframework.util.CollectionUtils;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * <p>Description: DTO BO PO 之间转换</p>
 *
 * @author Rock Jiang
 * @version 1.0
 * @date 2017/12/8 0008
 */
public class ConvertUtil {
    /**
     * List内对象属性复制
     *
     * @param sourceList 源List
     * @param clazz      返回List对象类型
     * @return
     */
    public static <T> List listConvert(List sourceList, Class<T> clazz) {
        //源List为空时直接返回
        if (CollectionUtils.isEmpty(sourceList)) {
            return new ArrayList();
        }

        List targetList = new ArrayList();
        Object target;
        for (Object source : sourceList) {
            target = BeanUtils.instantiateClass(clazz);
            BeanUtils.copyProperties(source, target);
            targetList.add(target);
        }

        return targetList;
    }

    /**
     * Page对象转换
     *
     * @param sourcePage 源page对象
     * @param clazz      目标page对象中记录(records)的类型
     * @param <T>
     * @return
     */
    public static <T> Page<T> pageConvert(Page sourcePage, Class<T> clazz) {
        Page<T> targetPage = new Page<>();

        //复制Page属性，忽略records属性
        BeanUtils.copyProperties(sourcePage, targetPage, "records");

        if (!CollectionUtils.isEmpty(sourcePage.getRecords())) {
            List<T> targetPageRecords = listConvert(sourcePage.getRecords(), clazz);
            targetPage.setRecords(targetPageRecords);
        }

        return targetPage;
    }

    /**
     * DTO类型的Page 创建时间 修改时间格式化显示
     *
     * @param page
     * @param <T>
     * @return
     */
    public static <T> Page<T> dtoTimeToStringConvert(Page<T> page) {
        if (!CollectionUtils.isEmpty(page.getRecords())) {
            List<T> records = page.getRecords();
            int recordsSize = records.size();
            LocalDateTime localDateTime;
            Date date;
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
            DirectFieldAccessor fieldAccessor;
            for (int i = 0; i < recordsSize; i++) {
                fieldAccessor = new DirectFieldAccessor(records.get(i));
                //创建时间
                Object createdTimeObj = fieldAccessor.getPropertyValue("createdTime");
                //修改时间
                Object modifiedTimeObj = fieldAccessor.getPropertyValue("modifiedTime");

                SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");

                if (Optional.ofNullable(createdTimeObj).isPresent()) {
                    if (createdTimeObj instanceof LocalDateTime) {
                        localDateTime = (LocalDateTime) createdTimeObj;
                        fieldAccessor.setPropertyValue("createdTimeStr", localDateTime.format(formatter));
                    }

                    if (createdTimeObj instanceof Date) {
                        date = (Date) createdTimeObj;
                        fieldAccessor.setPropertyValue("createdTimeStr", sdf.format(date));
                    }
                }

                if (Optional.ofNullable(modifiedTimeObj).isPresent()) {
                    if (modifiedTimeObj instanceof LocalDateTime) {
                        localDateTime = (LocalDateTime) modifiedTimeObj;
                        fieldAccessor.setPropertyValue("modifiedTimeStr", localDateTime.format(formatter));
                    }

                    if (createdTimeObj instanceof Date) {
                        date = (Date) modifiedTimeObj;
                        fieldAccessor.setPropertyValue("createdTimeStr", sdf.format(date));
                    }
                }

            }
        }

        return page;
    }

}