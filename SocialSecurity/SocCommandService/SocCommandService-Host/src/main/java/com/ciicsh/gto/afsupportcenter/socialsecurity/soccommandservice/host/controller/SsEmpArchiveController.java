package com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.host.controller;


import com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.business.ISsEmpArchiveService;
import com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.bo.SsEmpArchiveBO;
import com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.business.ISsEmpBasePeriodService;
import com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.business.ISsEmpTaskService;
import com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.entity.SsEmpBasePeriod;
import com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.entity.SsEmpTask;
import com.ciicsh.gto.afsupportcenter.util.aspect.log.Log;
import com.ciicsh.gto.afsupportcenter.util.page.PageInfo;
import com.ciicsh.gto.afsupportcenter.util.page.PageRows;
import com.ciicsh.gto.afsupportcenter.util.web.controller.BasicController;
import com.ciicsh.gto.afsupportcenter.util.web.response.JsonResult;
import com.ciicsh.gto.afsupportcenter.util.web.response.JsonResultKit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 雇员本地社保档案主表,
 * 由中智代缴过社保的雇员在此表必有一条记录，如果雇员跳槽到另外一家客户，就会在此表产生 前端控制器
 * </p>
 *
 * @author HuangXing
 * @since 2017-12-01
 */
@RestController
@RequestMapping("/api/soccommandservice/ssEmpArchive")
public class SsEmpArchiveController extends BasicController<ISsEmpArchiveService> {
    @Autowired
    private ISsEmpBasePeriodService iSsEmpBasePeriodService;
    @Autowired
    private ISsEmpTaskService iSsEmpTaskService;
    /**
     * 根据雇员任务 ID 查询 雇员本地社保档案信息
     *
     * @param empTaskId
     * @return
     */
    @RequestMapping("/queryByEmpTaskId")
    public JsonResult<SsEmpArchiveBO> queryByEmpTaskId(String empTaskId) {
        SsEmpArchiveBO dto = business.queryByEmpTaskId(empTaskId);
        return JsonResultKit.of(dto);
    }

    /**
     * 雇员查询
     *
     * @param
     * @return
     */
    @RequestMapping("/employeeQuery")
    @Log("查询雇员列表信息")
    public JsonResult<PageRows> employeeQuery(PageInfo pageInfo) {

        PageRows<SsEmpArchiveBO> result = business.queryEmployee(pageInfo);

        return JsonResultKit.of(result);
    }

    /**
     * 雇员详情信息查询
     *
     * @param
     * @return
     */
    @RequestMapping("/employeeDetailInfoQuery")
    @Log("雇员详情信息查询")
    public JsonResult employeeDetailInfoQuery(String empArchiveId) {

        if(null==empArchiveId)return JsonResultKit.ofError("ID为空");
        //查询客户基本信息和雇员信息
        SsEmpArchiveBO ssEmpArchiveBO =  business.queryEmployeeDetailInfo(empArchiveId);
        //查询社保汇缴信息
        List<SsEmpBasePeriod> empBasePeriodList= iSsEmpBasePeriodService.queryPeriodByEmpArchiveId(empArchiveId);
        //查询变动历史(任务单)
        List<SsEmpTask> ssEmpTasksList = iSsEmpTaskService.queryTaskByEmpArchiveId(empArchiveId);
        Map<String, Object> resultMap = new HashMap<String, Object>();
        resultMap.put("ssEmpArchive",ssEmpArchiveBO);
        resultMap.put("empBasePeriod",empBasePeriodList);
        resultMap.put("ssEmpTasks",ssEmpTasksList);

        return JsonResultKit.of(resultMap);
    }
}

