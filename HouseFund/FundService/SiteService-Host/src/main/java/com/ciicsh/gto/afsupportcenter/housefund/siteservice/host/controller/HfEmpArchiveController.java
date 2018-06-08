package com.ciicsh.gto.afsupportcenter.housefund.siteservice.host.controller;


import com.ciicsh.gto.afsupportcenter.housefund.fundservice.bo.HfEmpTaskBo;
import com.ciicsh.gto.afsupportcenter.housefund.fundservice.business.HfEmpArchiveService;
import com.ciicsh.gto.afsupportcenter.housefund.fundservice.bo.HfEmpArchiveBo;
import com.ciicsh.gto.afsupportcenter.housefund.fundservice.business.HfEmpTaskService;
import com.ciicsh.gto.afsupportcenter.housefund.fundservice.constant.HfEmpArchiveConstant;
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

import org.springframework.stereotype.Controller;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.*;

/**
 * <p>
 * 雇员本地公积金档案主表,
由中智代缴过社保的雇员在此表必有一条记录，如果雇员跳槽到另外一家客户，就会在此表产 前端控制器
 * </p>
 */
@Controller
@RequestMapping("/api/fundcommandservice/hfEmpArchive")
public class HfEmpArchiveController extends  BasicController<HfEmpArchiveService>{
    @Autowired
    HfEmpTaskService hfEmpTaskService;

    /**
     * 雇员公积金查询
     *
     * @param
     * @return
     */
    @RequestMapping("/queryEmpArchive")
    @ResponseBody
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
    @ResponseBody
    public JsonResult viewEmpArchiveInfo(@RequestParam("empArchiveId") String empArchiveId, @RequestParam("companyId") String companyId) {
       if(null==empArchiveId)return JsonResultKit.ofError("ID不能为空");
        Map<String, Object> resultMap = new HashMap<String, Object>();
        resultMap= business.viewEmpArchiveInfo(empArchiveId,companyId);
        return JsonResultKit.of(resultMap);
    }

    @RequestMapping("saveEmpAccount")
    @ResponseBody
    public JsonResult<Boolean> saveEmpAccount(@RequestParam Map<String,String> updateDto){

        boolean result=business.saveComAccount(updateDto);
        if(result) {
            return JsonResultKit.of(result);
        }else {
            return JsonResultKit.ofError("");
        }
    }

    @RequestMapping(value = "/xlsImportEmpAccount",consumes = {"multipart/form-data"})
    @ResponseBody
    public JsonResult xlsImportEmpAccount(MultipartFile file) throws Exception {
        List<EmpAccountImpXsl> optList = ExcelUtil.importExcel(file,0,1,EmpAccountImpXsl.class,false);
        JsonResult result = business.xlsImportEmpAccount(optList,file.getOriginalFilename());
        return  result;

    }


}

