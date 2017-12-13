package com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.host.controller;


import com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.business.ISalCompanyService;
import com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.dto.SsEmpTaskDTO;
import com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.entity.SalCompany;
import com.ciicsh.gto.afsupportcenter.util.aspect.log.Log;
import com.ciicsh.gto.afsupportcenter.util.page.PageInfo;
import com.ciicsh.gto.afsupportcenter.util.page.PageRows;
import com.ciicsh.gto.afsupportcenter.util.web.controller.BasicController;
import com.ciicsh.gto.afsupportcenter.util.web.response.JsonResult;
import com.ciicsh.gto.afsupportcenter.util.web.response.JsonResultKit;
import org.apache.commons.io.IOUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * <p>
 * 客户基础表 前端控制器
 * </p>
 *
 * @author HuangXing
 * @since 2017-12-11
 */
@RestController
@RequestMapping("/api/soccommandservice/salCompany")
@Log("客户基础信息")
public class SalCompanyController extends BasicController<ISalCompanyService> {

    /**
     * 查询客户基础信息
     *
     * @param pageInfo
     * @return
     */
    @RequestMapping("/companyQuery")
    @Log("查询")
    public JsonResult<List<SalCompany>> companyQuery(PageInfo pageInfo) {
        PageRows<SalCompany> pageRows = business.companyQuery(pageInfo);
        return JsonResultKit.ofPage(pageRows);
    }

    @RequestMapping(value = "/exprot")
    public void download(HttpServletRequest request, HttpServletResponse response, SsEmpTaskDTO dto) {
        String fileName = "test.xlsx";
        handleResponse(fileName, response);
        try {
            Workbook workbook = getWorkBook(fileName, new FileInputStream("D:\\word\\" + fileName));
            workbook.write(response.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //处理文件上传
    @RequestMapping("/upload")
    public String upload(@RequestParam("file") MultipartFile file) {
        return "";
    }

    public static void handleResponse(String fileName, HttpServletResponse response) {
        if (fileName.endsWith("xlsx")) { // 2007 xlsx
            response.setHeader("Content-Type", "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        } else { // 2003 xls
            response.setHeader("Content-Type", "application/vnd.ms-excel");
        }
        //编码
        response.setCharacterEncoding("UTF-8");
        // 下载文件的默认名称
        response.setHeader("Content-Disposition", "attachment;filename=" + fileName);
    }

    public static Workbook getWorkBook(MultipartFile file) throws IOException {
        // 获得文件名
        String fileName = file.getOriginalFilename();
        // 获取 excel 文件的 io 流
        InputStream is = file.getInputStream();
        return getWorkBook(fileName, is);
    }

    public static Workbook getWorkBook(String fileName, InputStream is) throws IOException {
        // 创建Workbook工作薄对象，表示整个 excel
        try {
            // 根据文件后缀名不同 ( xls 和 xlsx ) 获得不同的 Workbook 实现类对象
            if (fileName.endsWith("xlsx")) { // 2007 xlsx
                return new XSSFWorkbook(is);
            } else { // 2003 xls
                return new HSSFWorkbook(is);
            }
        } finally {
            IOUtils.closeQuietly(is);
        }
    }
}

