package com.ciicsh.gto.afsupportcenter.healthmedical.host.job;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.ciicsh.gto.afsupportcenter.healthmedical.business.AfPeTaskService;
import com.ciicsh.gto.afsupportcenter.healthmedical.entity.dto.AfPeTaskDTO;
import com.ciicsh.gto.afsupportcenter.healthmedical.entity.po.AfPeTask;
import com.ciicsh.gto.afsupportcenter.util.result.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
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
    @Scheduled(cron = "0 12/1 * * * ?")
    public JsonResult updateAfPeTaskByBespeakPeId() {
        //<editor-fold desc=" 1 调用中盈接口，获取体检订单信息">
        List<AfPeTask> afPeTaskList = afPeTaskService.getListByStatus();
        //todo 调方法获取sign
        afPeTaskList.stream().forEach((AfPeTask i) -> {
            String url = "http://51joying.iyaoshow.com/api/physical/GetTijianInfoByTijianEmployeeID?ComNo="+i.getComNo()+"&TiJianEmployeeID="
                +i.getBespeakPeId()+"&_time="+System.currentTimeMillis()+"&_sign=";
            JSONObject jo = restTemplate.getForObject(url, JSONObject.class);
            JSONArray data = jo.getJSONObject("data").getJSONArray("data");
            JSONObject obj = data.getJSONObject(0);
            //<editor-fold desc=" 2 更新 gt1预约体检订单">
            AfPeTask afPeTask = new AfPeTask();
            // todo 赋值字段
            afPeTaskService.update(afPeTask);
        });
        return JsonResult.success(null);
    }

}
