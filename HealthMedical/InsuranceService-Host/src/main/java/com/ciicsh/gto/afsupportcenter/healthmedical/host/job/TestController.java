package com.ciicsh.gto.afsupportcenter.healthmedical.host.job;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.ciicsh.gto.afsupportcenter.healthmedical.business.AfPeTaskService;
import com.ciicsh.gto.afsupportcenter.healthmedical.entity.po.AfPeTask;
import com.ciicsh.gto.afsupportcenter.util.result.JsonResult;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Author: guwei
 * @Description:
 * @Date: Created in 17:46 2018/3/12
 */
@RestController
@RequestMapping("/")
public class TestController {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private AfPeTaskService afPeTaskService;

    @GetMapping("test")
    public JsonResult test() {

        String timeUrl = "http://172.16.4.184:8081/Service1.svc/GetTime";
        String time = restTemplate.getForObject(timeUrl, String.class);

        String keyUrl = "http://172.16.4.184:8081/Service1.svc/GetData";
        String key = restTemplate.getForObject(keyUrl, String.class);

        String url = "http://51joying.iyaoshow.com/api/physical/GetTijianInfoByTijianEmployeeID?ComNo=3671&TiJianEmployeeID=81217&_time="
            +time+"&_sign="+key;
        JSONObject response = restTemplate.getForObject(url, JSONObject.class);
        JSONArray data = response.getJSONArray("data");
        JSONObject jsonObject = data.getJSONObject(0);
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
        boolean b = afPeTaskService.update(afPeTask);
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
