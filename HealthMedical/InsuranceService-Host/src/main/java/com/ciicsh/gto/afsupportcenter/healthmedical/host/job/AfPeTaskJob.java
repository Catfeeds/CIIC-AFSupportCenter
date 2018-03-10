package com.ciicsh.gto.afsupportcenter.healthmedical.host.job;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.ciicsh.gto.afsupportcenter.healthmedical.business.AfPeTaskService;
import com.ciicsh.gto.afsupportcenter.healthmedical.entity.dto.AfPeTaskDTO;
import com.ciicsh.gto.afsupportcenter.healthmedical.entity.po.AfPeTask;
import com.ciicsh.gto.afsupportcenter.util.result.JsonResult;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
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

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private AfPeTaskService afPeTaskService;

    /**
     * 定时获取老系统预约体检订单编号
     * 每小时执行一次
     * @return
     */
    @Scheduled(cron = "0 0/15 * * * ?")
    public JsonResult findBespeakPeId() {
        //<editor-fold desc=" 1 调用老系统接口，获取预约体检订单编号">
        List<AfPeTask> afPeTaskList = afPeTaskService.getListByBespeakPeIds();
        afPeTaskList.stream().forEach((AfPeTask i) -> {
            String url = "http://10.17.3.1:9999/FileOperationForJSON.svc/GetPhysicalExaminationInformation/"+i.getIdNum();
            JSONObject jo = restTemplate.getForObject(url, JSONObject.class);
            //<editor-fold desc=" 2 更新 gt1预约体检订单编号">
            AfPeTask afPeTask = new AfPeTask();
            afPeTask.setIdNum(jo.getString("EmployeeID"));
            afPeTask.setBespeakPeId(jo.getString("ReservationNumber"));
            afPeTask.setComNo(jo.getString("CompanyID"));
            afPeTaskService.update(afPeTask);
        });
        return JsonResult.success(null);
    }

    /**
     * 定时获取老系统预约体检订单编号
     * 每小时执行一次
     * @return
     */
    @Scheduled(cron = "0 0 12/1 * * ?")
    public JsonResult updateAfPeTaskByBespeakPeId() {
        //<editor-fold desc=" 1 调用中盈接口，获取体检订单信息">
        List<AfPeTask> afPeTaskList = afPeTaskService.getListByStatus();
        //todo 调方法获取sign
        afPeTaskList.stream().forEach((AfPeTask i) -> {
            String url = "http://51joying.iyaoshow.com/api/physical/GetTijianInfoByTijianEmployeeID?ComNo="+i.getComNo()+"&TiJianEmployeeID="
                +i.getBespeakPeId()+"&_time="+System.currentTimeMillis()+"&_sign=";
            //<editor-fold desc=" 2 更新 gt1预约体检订单">
            JSONObject jo = restTemplate.getForObject(url, JSONObject.class);
            JSONArray data = jo.getJSONObject("data").getJSONArray("data");
            //体检信息
            JSONObject obj = data.getJSONObject(0).getJSONArray("PhysicalInfo").getJSONObject(0);
            AfPeTask afPeTask = new AfPeTask();
            afPeTask.setStatus(changeStatus(obj.getInteger("IsArrived")));
            afPeTask.setTiJianDate(changeDate(obj.getString("TiJianDate")));
            afPeTask.setDaoJianDate(changeDate(obj.getString("DaoJianDate")));
            afPeTask.setReportDate(changeDate(obj.getString("ReportDate")));
            afPeTask.setSendTime(changeDate(obj.getString("SendTime")));
            afPeTask.setProductName(obj.getString("ProductName"));
            afPeTask.setSaleValue(obj.getBigDecimal("SaleValue"));
            afPeTask.setPeOrginzation(obj.getString("ShopName"));
            afPeTask.setPeAddress(obj.getString("ShopAddress"));
            afPeTaskService.update(afPeTask);
        });
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
        return null;
    }


}
