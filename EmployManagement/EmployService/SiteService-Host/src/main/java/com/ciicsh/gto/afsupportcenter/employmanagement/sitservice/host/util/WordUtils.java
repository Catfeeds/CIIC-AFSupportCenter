package com.ciicsh.gto.afsupportcenter.employmanagement.sitservice.host.util;

import com.ciicsh.gto.afsupportcenter.util.StringUtil;
import com.ciicsh.gto.afsupportcenter.util.logService.LogApiUtil;
import com.ciicsh.gto.afsupportcenter.util.logService.LogMessage;
import freemarker.cache.ByteArrayTemplateLoader;
import freemarker.cache.FileTemplateLoader;
import freemarker.cache.StringTemplateLoader;
import freemarker.cache.TemplateLoader;
import freemarker.template.Configuration;
import freemarker.template.Template;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;


public class WordUtils {

    @Autowired
    private static LogApiUtil logApiUtil;

    public static final String TEMPLATE_FILE_VOUCHER_PATH = "template";



    private static Configuration configuration = null;
    static void init() {
        try {
//            logApiUtil.error(LogMessage.create().setTitle("WordUtils.static").setContent("Configuration load start"));
            configuration = new Configuration();
            configuration.setDefaultEncoding("utf-8");


            configuration.setClassLoaderForTemplateLoading(WordUtils.class.getClassLoader(),TEMPLATE_FILE_VOUCHER_PATH);

            // 以上方式不行 只能在本地拿到模板  发布到linux上去 打成jar包 要以这种方式读取模板
//            ClassPathResource classPathResource = new ClassPathResource("template");
//
//            classPathResource.getInputStream();
//
//            File file = classPathResource.getFile();
//            FileTemplateLoader templateLoader = new FileTemplateLoader(file,true);
//            configuration.setTemplateLoader(templateLoader);



            //获取跟目录
//            File path = new File(ResourceUtils.getURL("classpath:template").getPath());
//            File upload = new File(path.getAbsolutePath(),"");
//            System.out.println(path.getPath());
//            System.out.println(path.getName());
//            System.out.println(path.getAbsolutePath());
//            System.out.println(upload.getPath());
//            System.out.println(upload.getName());
//            System.out.println(upload.getAbsolutePath());
//            FileTemplateLoader templateLoader = new FileTemplateLoader(path,true);
//            configuration.setTemplateLoader(templateLoader);
//            System.out.println(configuration);
        } catch (Exception e) {
            e.printStackTrace();
//            logApiUtil.error(LogMessage.create().setTitle("WordUtils.static").setContent(e.getMessage()));
        }
    }

    private WordUtils() {
        throw new AssertionError();
    }

    public static void exportMillCertificateWord(HttpServletRequest request, HttpServletResponse response, Map map,String title,String ftlFile) throws Exception {

        try {
            init();

//            InputStream stream = WordUtils.class.getClassLoader().getResourceAsStream(TEMPLATE_FILE_VOUCHER_PATH + ftlFile);

//            System.out.println(WordUtils.class.getClassLoader().getResourceAsStream(TEMPLATE_FILE_VOUCHER_PATH + ftlFile));



//            POIFSFileSystem fs = new POIFSFileSystem(stream);

//            stream.read();


            Template freemarkerTemplate = configuration.getTemplate(ftlFile);

            response.setCharacterEncoding("utf-8");
            response.setContentType("application/msword");
            // 设置浏览器以下载的方式处理该文件名
            String fileName = title+ StringUtil.getDateString(new Date()) + ".doc";
            response.setHeader("Content-Disposition", "attachment;filename="
                .concat(String.valueOf(URLEncoder.encode(fileName, "UTF-8"))));
            Writer w = new OutputStreamWriter(response.getOutputStream(), "utf-8");
            freemarkerTemplate.process(map,w);
            w.close();
        }catch (Exception ex) {
//            logApiUtil.error(LogMessage.create().setTitle("WordUtils.exportMillCertificateWord").setContent(ex.getMessage()));
            ex.printStackTrace();
            throw ex;
        }
    }




    public static void main(String[] args) {

        Configuration configuration = new Configuration();

        configuration.setClassForTemplateLoading(WordUtils.class, "/");


        try {


            ClassPathResource classPathResource = new ClassPathResource("template");

            classPathResource.getInputStream();

            File file = classPathResource.getFile();


            FileTemplateLoader templateLoader = new FileTemplateLoader(file);
            configuration.setTemplateLoader(templateLoader);






            Template helloTemp= configuration.getTemplate("AM_ALONE_TEMP.ftl");
            StringWriter writer = new StringWriter();
            Map<String,Object> helloMap = new HashMap<String,Object>();
            helloMap.put("name","gokhan");

            helloTemp.process(helloMap,writer);

            System.out.println(writer);
        }catch (Exception e){
            e.printStackTrace();
        }



    }

}
