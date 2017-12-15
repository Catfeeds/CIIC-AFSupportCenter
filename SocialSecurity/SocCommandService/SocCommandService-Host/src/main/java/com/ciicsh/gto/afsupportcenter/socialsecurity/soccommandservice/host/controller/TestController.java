package com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.host.controller;

import com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.dto.SsEmpTaskDTO;
import com.ciicsh.gto.afsupportcenter.util.aspect.log.Log;
import com.ciicsh.gto.afsupportcenter.util.excel.ExcelKit;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * 本 controller 测试专用，不做其他业务处理使用
 */
@RestController
@RequestMapping("/api/soccommandservice/test")
@Log("demo 测试")
public class TestController {

    @RequestMapping(value = "/exprot")
    public void download(HttpServletRequest request, HttpServletResponse response, SsEmpTaskDTO dto) {
        String fileName = "test.xlsx";
        try {
            FileInputStream is = new FileInputStream("D:\\word\\" + fileName);
            Workbook workbook = ExcelKit.createWorkbookForResponse(fileName, is, response);
            workbook.write(response.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //处理文件上传
    @PostMapping("/upload")
    public String upload(@RequestParam("file") MultipartFile file, @RequestParam(name = "companyId", required = false) String companyId) {
        return "";
    }
}
