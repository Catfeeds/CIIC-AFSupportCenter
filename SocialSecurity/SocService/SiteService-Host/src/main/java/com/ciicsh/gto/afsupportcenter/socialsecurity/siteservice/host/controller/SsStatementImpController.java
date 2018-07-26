package com.ciicsh.gto.afsupportcenter.socialsecurity.siteservice.host.controller;


import com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.business.SsStatementImpService;
import com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.entity.custom.GsymxOpt;
import com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.entity.custom.YysmxOpt;
import com.ciicsh.gto.afsupportcenter.util.ExcelUtil;
import com.ciicsh.gto.afsupportcenter.util.web.controller.BasicController;
import com.ciicsh.gto.afsupportcenter.util.web.response.JsonResult;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

/**
 * 对账导入雇员明细 前端控制器
 */
@RestController
@RequestMapping("/api/soccommandservice/ssStatementImp")
public class SsStatementImpController  extends BasicController<SsStatementImpService> {

    @RequestMapping(value = "/optImport",consumes = {"multipart/form-data"})
    @ResponseBody
    public JsonResult<String> optImport(String ssMonth,String fileType,Long comAccountId,MultipartFile file) throws Exception {

        JsonResult<String> json = new JsonResult<String>();
        json = yysmxOptImport(file,ssMonth,fileType,comAccountId);
 /*       switch (fileType){
            case "YYS":
                json = yysmxOptImport(file,ssMonth,fileType,comAccountId);
                break;
            case "GSY":
                json = gsymxOptImport(file,ssMonth,fileType,comAccountId);
                break;
            default:
                break;
        }*/
        return json;
    }



    private JsonResult<String> yysmxOptImport(MultipartFile file,String ssMonth,String fileType,Long comAccountId) throws Exception {
        List<YysmxOpt> optList;
        JsonResult<String> json = new JsonResult<String>();

        try {
            optList = ExcelUtil.importExcel(file, 1, 2, YysmxOpt.class, false);
            if (null != optList && optList.size() > 0) {
                optList = optList.stream().filter(x -> x != null && x.getSettlement() != null && !x.getSettlement().equals("合计")).collect(Collectors.toList());
            }
        } catch (NoSuchElementException e) {
            json.setCode(1);
            json.setMessage("养医失导入失败!文件格式不正确");
            return json;
        } catch (Exception e) {
            json.setCode(1);
            json.setMessage("养医失导入失败!" + e.getMessage());
            return json;
        }

        if (CollectionUtils.isEmpty(optList)) {
            json.setCode(1);
            json.setMessage("养医失导入失败!没有数据或文件格式不正确");
            return json;
        }

        Boolean result = business.yysImport(optList,ssMonth,fileType,comAccountId,file.getOriginalFilename());
        if(result){
            json.setCode(0);
            json.setMessage("养医失导入成功!");
        }
        else {
            json.setCode(1);
            json.setMessage("养医失导入失败!");
        }
        return json;
    }


    private JsonResult<String> gsymxOptImport(MultipartFile file,String ssMonth,String fileType,Long comAccountId) throws Exception {
        List<GsymxOpt> optList;
        JsonResult<String> json = new JsonResult<String>();
        try {
            optList = ExcelUtil.importExcel(file, 1, 1, GsymxOpt.class, false);
        } catch (NoSuchElementException e) {
            json.setCode(1);
            json.setMessage("工生育导入失败!文件格式不正确");
            return json;
        } catch (Exception e) {
            json.setCode(1);
            json.setMessage("工生育导入失败!" + e.getMessage());
            return json;
        }

        if(null != optList && optList.size() > 0){
            optList = optList.stream().filter(x->x!=null&&x.getSettlement()!=null&&!x.getSettlement().equals("合计")).collect(Collectors.toList());
        }

        if (CollectionUtils.isEmpty(optList)) {
            json.setCode(1);
            json.setMessage("工生育导入失败!没有数据或文件格式不正确");
            return json;
        }

        Boolean result = business.gsyImport(optList,ssMonth,fileType,comAccountId,file.getOriginalFilename());
        if(result){
            json.setCode(0);
            json.setMessage("工生育导入成功!");
        }
        else {
            json.setCode(1);
            json.setMessage("工生育导入失败!");
        }
        return json;
    }

}

