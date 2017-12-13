package com.ciicsh.gto.demo;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.core.io.ClassPathResource;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class TestExcel {

    public static void main(String[] args) throws IOException {
//        Workbook workbook = getWorkBook("客户联系人列表1.xlsx", new ClassPathResource("D:\\word\\客户联系人列表1.xlsx").getInputStream());
        Workbook workbook = getWorkBook("客户联系人列表1.xlsx",new FileInputStream("D:\\word\\客户联系人列表1.xlsx"));
        System.out.println(workbook);
    }

    public static Workbook getWorkBook(MultipartFile file) throws IOException {
        //获得文件名
        String fileName = file.getOriginalFilename();
        //获取excel文件的io流
        InputStream is = file.getInputStream();
        return getWorkBook(fileName, is);
    }

    public static Workbook getWorkBook(String fileName, InputStream is) throws IOException {
        //创建Workbook工作薄对象，表示整个excel
        Workbook workbook = null;
        //获取excel文件的io流
        //根据文件后缀名不同(xls和xlsx)获得不同的Workbook实现类对象
        if (fileName.endsWith("xls")) {
            //2003
            workbook = new HSSFWorkbook(is);
        } else if (fileName.endsWith("xlsx")) {
            //2007
            workbook = new XSSFWorkbook(is);
        }
        return workbook;
    }
}
