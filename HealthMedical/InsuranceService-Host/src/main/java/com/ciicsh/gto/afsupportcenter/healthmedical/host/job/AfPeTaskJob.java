package com.ciicsh.gto.afsupportcenter.healthmedical.host.job;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.ciicsh.gto.afsupportcenter.healthmedical.business.AfPeTaskService;
import com.ciicsh.gto.afsupportcenter.healthmedical.entity.dto.AfPeTaskDTO;
import com.ciicsh.gto.afsupportcenter.healthmedical.entity.po.AfPeTask;
import com.ciicsh.gto.afsupportcenter.healthmedical.host.controller.UninsuredController;
import com.ciicsh.gto.afsupportcenter.util.result.JsonResult;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: guwei
 * @Description:
 * @Date: Created in 9:47 2018/3/8
 */
@Component
public class AfPeTaskJob {
    /**
     * 记录日志
     */
    private static Logger logger = LoggerFactory.getLogger(AfPeTaskJob.class);

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private AfPeTaskService afPeTaskService;

    /**
     * 定时获取老系统预约体检订单编号
     * 每小时执行一次
     * @return
     */
    @Scheduled(cron = "0 */1 * * * ?")
    public JsonResult findBespeakPeId() {
        //<editor-fold desc=" 1 调用老系统接口，获取预约体检订单编号">
        List<AfPeTask> afPeTaskList = afPeTaskService.getListByBespeakPeIds();
        afPeTaskList.stream().forEach((AfPeTask i) -> {
            String url = "http://10.17.3.1:9999/FileOperationForJSON.svc/GetPhysicalExaminationInformation/"+i.getIdNum();
            JSONArray response = restTemplate.getForObject(url, JSONArray.class);
            JSONObject jo = response.getJSONObject(0);
            //<editor-fold desc=" 2 更新 gt1预约体检订单编号">
            if (jo != null) {
                AfPeTask afPeTask = new AfPeTask();
                afPeTask.setIdNum(jo.getString("EmployeeID"));
                afPeTask.setBespeakPeId(jo.getString("ReservationNumber"));
                afPeTask.setComNo(jo.getString("CompanyID"));
                afPeTaskService.update(afPeTask);
            }
        });
        logger.info("定时获取老系统预约体检订单编号");
        return JsonResult.success(null);
    }

    /**
     * 定时获取老系统预约体检订单编号
     * 每小时执行一次
     * @return
     */
    @Scheduled(cron = "30 */1 * * * ?")
    public JsonResult updateAfPeTaskByBespeakPeId() {
        //<editor-fold desc=" 1 调用中盈接口，获取体检订单信息">
        List<AfPeTask> afPeTaskList = afPeTaskService.getListByStatus();
        if (afPeTaskList != null && afPeTaskList.size()>0) {
            afPeTaskList.stream().forEach((AfPeTask i) -> {
                String timeUrl = "http://172.16.4.184:8081/Service1.svc/GetTime";
                String time = restTemplate.getForObject(timeUrl, String.class);

                String keyUrl = "http://172.16.4.184:8081/Service1.svc/GetData";
                String key = restTemplate.getForObject(keyUrl, String.class);

                String url = "http://51joying.iyaoshow.com/api/physical/GetTijianInfoByTijianEmployeeID?ComNo="+i.getComNo()
                    +"TiJianEmployeeID="+i.getBespeakPeId()+"&_time="+time+"&_sign="+key;
                JSONObject response = restTemplate.getForObject(url, JSONObject.class);
                logger.info(time+"&&&&&&"+key);
                logger.info(response.getString("msg"));
                JSONArray data = response.getJSONArray("data");
                JSONObject jsonObject = data.getJSONObject(0);
                if (jsonObject.size()>0){
                    JSONArray PhysicalInfos = jsonObject.getJSONArray("PhysicalInfo");
                    JSONObject obj = PhysicalInfos.getJSONObject(0);
                    AfPeTask afPeTask = new AfPeTask();
                    afPeTask.setStatus(changeStatus(obj.getInteger("IsArrived")));
                    afPeTask.setTiJianDate(changeDate(obj.getString("TiJianDate")));
                    afPeTask.setEffectEndDate(changeDate(obj.getString("TijianEndDate")));
                    afPeTask.setDaoJianDate(changeDate(obj.getString("DaoJianDate")));
                    afPeTask.setReportDate(changeDate(obj.getString("ReportDate")));
                    afPeTask.setSendTime(changeDate(obj.getString("SendTime")));
                    afPeTask.setProductName(obj.getString("ProductName"));
                    afPeTask.setSaleValue(obj.getBigDecimal("SaleValue"));
                    afPeTask.setPeOrginzation(obj.getString("ShopName"));
                    afPeTask.setPeAddress(obj.getString("ShopAddress"));
                    afPeTaskService.update(afPeTask);
                }
            });
        }

        logger.info("定时同步体检任务单信息");
        return JsonResult.success(null);
    }

    /**
     * 日期转换 yyyy-MM-dd
     * @param tiJianDate
     * @return
     */
    private Date changeDate(String tiJianDate) {
        if (StringUtils.isNotBlank(tiJianDate)) {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            try {
                Date date = sdf.parse(tiJianDate);
                return date;
            } catch (ParseException e) {
                return null;
            }
        } else {
            return null;
        }

    }

    /**
     * 中盈体检状态 ==> gt1体检状态
     * @param status
     * @return
     */
    private Integer changeStatus(Integer status) {
        if (0 == status) { return 3;}
        else if (1 == status) {return 4;}
        else if (2 == status) {return 6;}
        else if (3 == status) {return 9;}
        else if (4 == status) {return 8;}
        else if (5 == status) {return 7;}
        else if (6 == status) {return 5;}
        else if (7 == status) {return 10;}
        return null;
    }


}
