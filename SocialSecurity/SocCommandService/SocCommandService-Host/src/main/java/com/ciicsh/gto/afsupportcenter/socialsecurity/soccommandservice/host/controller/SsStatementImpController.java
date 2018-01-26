package com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.host.controller;


import com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.business.ISsStatementImpService;
import com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.entity.custom.GsymxOpt;
import com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.entity.custom.OptImportArgs;
import com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.entity.custom.YysmxOpt;
import com.ciicsh.gto.afsupportcenter.util.ExcelUtil;
import com.ciicsh.gto.afsupportcenter.util.web.controller.BasicController;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ciicsh.gto.afsupportcenter.util.web.response.JsonResult;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 对账导入雇员明细 前端控制器
 * </p>
 *
 * @author HuangXing
 * @since 2017-12-01
 */
@RestController
@RequestMapping("/api/soccommandservice/ssStatementImp")
public class SsStatementImpController  extends BasicController<ISsStatementImpService> {

    @RequestMapping(value = "/optImport",consumes = {"multipart/form-data"})
    public JsonResult<String> optImport(@RequestBody OptImportArgs args) throws Exception {
        JsonResult<String> json = new JsonResult<String>();
        switch (args.getFileType()){
            case "YYS":
                json = yysmxOptImport(args.getFile(),args.getSsMonth(),args.getFileType(),args.getComAccountId());
                break;
            case "GSY":
                json = gsymxOptImport(args.getFile(),args.getSsMonth(),args.getFileType(),args.getComAccountId());
                break;
            default:
                break;
        }
        return json;
    }



    private JsonResult<String> yysmxOptImport(MultipartFile file,String ssMonth,String fileType,Long comAccountId) throws Exception {
        List<YysmxOpt> optList = ExcelUtil.importExcel(file,1,2,YysmxOpt.class,false);
        if(null != optList && optList.size() > 0){
            optList = optList.stream().filter(x->!x.getSettlement().equals("合计")).collect(Collectors.toList());
        }

        JsonResult<String> json = new JsonResult<String>();
        Boolean result = business.yysImport(optList,ssMonth,fileType,comAccountId,file.getOriginalFilename());
        if(result){
            json.setCode(0);
            json.setMessage("养医失导入成功!");
        }
        else {
            json.setCode(0);
            json.setMessage("养医失导入失败!");
        }
        return json;
    }


    private JsonResult<String> gsymxOptImport(MultipartFile file,String ssMonth,String fileType,Long comAccountId) throws Exception {
        List<GsymxOpt> optList = ExcelUtil.importExcel(file,1,1,GsymxOpt.class,false);
        if(null != optList && optList.size() > 0){
            optList = optList.stream().filter(x->!x.getSettlement().equals("合计")).collect(Collectors.toList());
        }

        JsonResult<String> json = new JsonResult<String>();
        Boolean result = business.gsyImport(optList,ssMonth,fileType,comAccountId,file.getOriginalFilename());
        if(result){
            json.setCode(0);
            json.setMessage("导入成功!");
        }
        else {
            json.setCode(0);
            json.setMessage("导入失败!");
        }
        return json;
    }

}

