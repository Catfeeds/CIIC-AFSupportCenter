package com.ciicsh.gto.afsupportcenter.housefund.siteservice.host.controller;


import com.ciicsh.gto.afsupportcenter.housefund.fundservice.bo.HfEmpTaskBo;
import com.ciicsh.gto.afsupportcenter.housefund.fundservice.bo.HfEmpTaskExportBo;
import com.ciicsh.gto.afsupportcenter.housefund.fundservice.business.HfEmpArchiveService;
import com.ciicsh.gto.afsupportcenter.housefund.fundservice.bo.HfEmpArchiveBo;
import com.ciicsh.gto.afsupportcenter.housefund.fundservice.business.HfEmpTaskHandleService;
import com.ciicsh.gto.afsupportcenter.housefund.fundservice.business.HfEmpTaskService;
import com.ciicsh.gto.afsupportcenter.housefund.fundservice.dto.EmpAccountImpXsl;
import com.ciicsh.gto.afsupportcenter.util.ExcelUtil;
import com.ciicsh.gto.afsupportcenter.util.page.PageInfo;
import com.ciicsh.gto.afsupportcenter.util.page.PageRows;
import com.ciicsh.gto.afsupportcenter.util.web.controller.BasicController;
import com.ciicsh.gto.afsupportcenter.util.web.response.JsonResult;
import com.ciicsh.gto.afsupportcenter.util.web.response.JsonResultKit;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.*;

/**
 * <p>
 * 雇员本地公积金档案主表,
由中智代缴过社保的雇员在此表必有一条记录，如果雇员跳槽到另外一家客户，就会在此表产 前端控制器
 * </p>
 */
@RestController
@RequestMapping("/api/fundcommandservice/hfEmpArchive")
public class HfEmpArchiveController extends  BasicController<HfEmpArchiveService>{
    @Autowired
    HfEmpTaskService hfEmpTaskService;
    @Autowired
    HfEmpTaskHandleService hfEmpTaskHandleService;

    /**
     * 雇员公积金查询
     *
     * @param
     * @return
     */
    @RequestMapping("/queryEmpArchive")
    public JsonResult<PageRows> queryEmpArchive(PageInfo pageInfo) {
        PageRows<HfEmpArchiveBo> result = business.queryEmpArchive(pageInfo);
        List<HfEmpArchiveBo> hfEmpArchiveBoList = result.getRows();

        if (CollectionUtils.isNotEmpty(hfEmpArchiveBoList)) {
            for (HfEmpArchiveBo hfEmpArchiveBo : hfEmpArchiveBoList) {
                HfEmpTaskBo hfEmpTaskBo = new HfEmpTaskBo();
                hfEmpTaskBo.setCompanyId(hfEmpArchiveBo.getCompanyId());
                hfEmpTaskBo.setEmployeeId(hfEmpArchiveBo.getEmployeeId());
                hfEmpTaskBo.setHfType(hfEmpArchiveBo.getHfType());

                if (hfEmpTaskService.getExistHandleRemarkCount(hfEmpTaskBo) > 0) {
                    hfEmpArchiveBo.setHandleRemark("有备注");
                }

                if ("3".equals(hfEmpArchiveBo.getEmpStatus())) {
                    hfEmpArchiveBo.setHasOut(1);
                }
            }
        }
        return JsonResultKit.of(result);

    }

    @RequestMapping("/impTemplateFile")
    public void impTemplateFile(HttpServletResponse response) {
        String fileNme = "雇员公积金账号导入模板.xls";
        List<EmpAccountImpXsl> opts = new ArrayList();
        ExcelUtil.exportExcel(opts,EmpAccountImpXsl.class,fileNme,response);
    }

    @RequestMapping("/viewEmpArchiveInfo")
    public JsonResult viewEmpArchiveInfo(@RequestParam(required = false) String empArchiveId,
                                         @RequestParam(required = false)String companyId,
                                         @RequestParam(required = false)String employeeId ) {
      // if(empArchiveId == null)return JsonResultKit.ofError("ID不能为空");
        Map<String, Object> resultMap = new HashMap<String, Object>();
        resultMap= business.viewEmpArchiveInfo(empArchiveId,companyId,employeeId);
        return JsonResultKit.of(resultMap);
    }

    @RequestMapping("/saveEmpAccount")
    public JsonResult saveEmpAccount(@RequestParam Map<String,String> updateDto){

        return business.saveComAccount(updateDto);

    }

    @RequestMapping(value = "/xlsImportEmpAccount",consumes = {"multipart/form-data"})
    public JsonResult xlsImportEmpAccount(MultipartFile file) throws Exception {
        List<EmpAccountImpXsl> optList = ExcelUtil.importExcel(file,0,1,EmpAccountImpXsl.class,false);
        JsonResult result = business.xlsImportEmpAccount(optList,file.getOriginalFilename());
        return  result;

    }

    @PostMapping("/queryHistoryEmpTask")
    public JsonResult<HfEmpTaskExportBo> queryHistoryEmpTask(@RequestParam("empTaskId") String empTaskId) {
        Long taskId = Long.parseLong(empTaskId);
        List<HfEmpTaskExportBo> hfEmpTaskList = hfEmpTaskHandleService.queryHistoryEmpTask(true, taskId);
        if (CollectionUtils.isNotEmpty(hfEmpTaskList)) {
            return JsonResultKit.of(hfEmpTaskList.get(0));
        }
        return JsonResultKit.of();
    }

    @PostMapping("/getOriginEmpTaskList")
    public JsonResult<List<HfEmpTaskExportBo>> getOriginEmpTaskList(@RequestParam("empArchiveId") String empArchiveId) {
        Long archiveId = Long.parseLong(empArchiveId);
        List<HfEmpTaskExportBo> hfEmpTaskList = hfEmpTaskHandleService.getOriginEmpTask(archiveId);
        return JsonResultKit.of(hfEmpTaskList);
    }
}

