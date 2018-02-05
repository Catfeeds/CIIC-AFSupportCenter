package com.ciicsh.gto.adsupportcenter.employcommandservice.host.controller;

import com.ciicsh.gto.afsupportcenter.employmanagement.employcommandservice.bo.AmResTaskCountBO;
import com.ciicsh.gto.afsupportcenter.employmanagement.employcommandservice.bo.AmResignBO;
import com.ciicsh.gto.afsupportcenter.employmanagement.employcommandservice.bo.AmResignCollection;
import com.ciicsh.gto.afsupportcenter.employmanagement.employcommandservice.business.IAmResignService;
import com.ciicsh.gto.afsupportcenter.employmanagement.employcommandservice.entity.AmResign;
import com.ciicsh.gto.afsupportcenter.util.page.PageInfo;
import com.ciicsh.gto.afsupportcenter.util.page.PageRows;
import com.ciicsh.gto.afsupportcenter.util.web.controller.BasicController;
import com.ciicsh.gto.afsupportcenter.util.web.response.JsonResult;
import com.ciicsh.gto.afsupportcenter.util.web.response.JsonResultKit;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by zhangzhiwen on 2018/2/1.
 */

@RestController
@RequestMapping("/api/employcommandservice/amResignTask")
public class AmResignTaskController extends BasicController<IAmResignService> {

    @RequestMapping("/queryAmResign")
    public JsonResult<PageRows>  queryAmResign(PageInfo pageInfo){
        PageRows<AmResignBO> result = business.queryAmResign(pageInfo);
        return JsonResultKit.of(result);
    }


    @RequestMapping("/queryResignTaskCount")
    public  JsonResult<AmResignCollection>  taskCount(PageInfo pageInfo){

        List<AmResignBO> list = business.taskCount(pageInfo);

        AmResTaskCountBO amEmpTaskCountBO = new AmResTaskCountBO();
        List<AmResTaskCountBO>  temp = new ArrayList<>();
        amEmpTaskCountBO.setAmount(list.size());
        int num =0;
        for(int i=0;i<list.size();i++)
        {
            AmResignBO amResignBO = list.get(i);
            int status = amResignBO.getTaskStatus();
            if(1==status){
                amEmpTaskCountBO.setNoFeedback(amResignBO.getCount());
                num = num + amResignBO.getCount();
            }else if(2==status){
                amEmpTaskCountBO.setRefuseFailed(amResignBO.getCount());
                num = num + amResignBO.getCount();
            }else if(3==status){
                amEmpTaskCountBO.setRefuseBeforeWithFile(amResignBO.getCount());
                num = num + amResignBO.getCount();
            }else if(4==status){
                amEmpTaskCountBO.setRefuseTicketStampNoReturn(amResignBO.getCount());
                num = num + amResignBO.getCount();
            }else if(5==status){
                amEmpTaskCountBO.setRefuseFailed(amResignBO.getCount());
                num = num + amResignBO.getCount();
            }else if(6==status){
                amEmpTaskCountBO.setBeforeBatchNeedRefuse(amResignBO.getCount());
            }else if(7==status){
                amEmpTaskCountBO.setOther(amResignBO.getCount());
                num = num + amResignBO.getCount();
            }
            amEmpTaskCountBO.setAmount(num);

        }
        temp.add(amEmpTaskCountBO);
        AmResignCollection  amResignCollection = new AmResignCollection ();
        amResignCollection.setRow(temp);
        return  JsonResultKit.of(amResignCollection);
    }

    @RequestMapping("/queryAmResignDetail")
    public JsonResult queryAmResignDetail(AmResignBO bo){

        List<AmResignBO> listResignBO = business.queryAmResignDetail(bo);
        Map<String, Object> resultMap = new HashMap<String, Object>();
        if(null!=listResignBO&&listResignBO.size()>0){
            LocalDateTime now = LocalDateTime.now();
            AmResignBO temp = listResignBO.get(0);
            temp.setResignDate(now.toLocalDate());
            resultMap.put("resignBO",temp);
        }

        return JsonResultKit.of(resultMap);
    }

    @RequestMapping("/saveAmResign")
    public JsonResult<Boolean> saveAmResign(AmResign entity) {
        LocalDateTime now = LocalDateTime.now();
        if(entity.getResignId()==null){
            entity.setCreatedTime(now);
            entity.setModifiedTime(now);
            entity.setCreatedBy("sys");
            entity.setModifiedBy("sys");
        }else{
            entity.setModifiedTime(now);
            entity.setModifiedBy("sys");
        }
        boolean result =  business.insertOrUpdate(entity);
        return JsonResultKit.of(result);
    }
}
