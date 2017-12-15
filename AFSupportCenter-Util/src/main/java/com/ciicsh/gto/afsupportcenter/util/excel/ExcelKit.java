package com.ciicsh.gto.afsupportcenter.util.excel;

import com.ciicsh.gto.afsupportcenter.util.exception.BusinessException;
import org.apache.commons.io.IOUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;

public class ExcelKit {

    public static final String CONTENT_TYPE_EXCEL2007 = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";
    public static final String CONTENT_TYPE_EXCEL2003 = "application/vnd.ms-excel";

    /**
     * @param file     上传文件
     * @param response HttpServletResponse
     * @return
     */
    public static Workbook createWorkbookForResponse(MultipartFile file, HttpServletResponse response) {
        // 获得文件名
        String fileName = file.getOriginalFilename();
        // 获取 excel 文件的 io 流
        try {
            InputStream is = file.getInputStream();
            return createWorkbookForResponse(fileName, is, response);
        } catch (IOException e) {
            throw BusinessException.unchecked(e);
        }
    }

    /**
     * 处理 HttpServletResponse
     *
     * @param workbook excel
     * @param response HttpServletResponse
     */
    public static void handleResponse(Workbook workbook, HttpServletResponse response) {
        if (workbook == null) {
            return;
        }

        if (workbook instanceof XSSFWorkbook) {// 2007 xlsx
            response.setHeader("Content-Type", CONTENT_TYPE_EXCEL2007);
        } else if (workbook instanceof HSSFWorkbook) {// 2003 xls
            response.setHeader("Content-Type", CONTENT_TYPE_EXCEL2003);
        } else {
            BusinessException.rethrow("不支持此类型 class:" + workbook.getClass().getName());
        }
    }

    /**
     * @param fileName 文件名
     * @param is       文件流，使用之后自动关闭
     * @param response HttpServletResponse
     * @return
     */
    public static Workbook createWorkbookForResponse(String fileName, InputStream is, HttpServletResponse response) {
        Workbook workbook;
        try {
            // 创建 Workbook 工作薄对象，表示整个 excel
            // 根据文件后缀名不同 ( xls 和 xlsx ) 获得不同的 Workbook 实现类对象
            if (fileName.endsWith("xlsx")) { // 2007 xlsx
                workbook = new XSSFWorkbook(is);
            } else { // 2003 xls
                workbook = new HSSFWorkbook(is);
            }
            handleResponse(workbook, response);
            // 下载文件的默认名称
            response.setHeader("Content-Disposition", "attachment;filename=" + fileName);
            return workbook;
        } catch (IOException e) {
            throw BusinessException.unchecked(e);
        } finally {
            IOUtils.closeQuietly(is);
        }
    }
}
