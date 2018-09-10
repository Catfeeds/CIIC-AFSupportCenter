package com.ciicsh.gto.afsupportcenter.util;

import freemarker.template.Configuration;
import freemarker.template.Template;

import javax.servlet.http.HttpServletResponse;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.URLEncoder;
import java.util.Date;
import java.util.Map;


public class WordUtils {

    public static final String TEMPLATE_FILE_VOUCHER_PATH = "template";



    private static Configuration configuration = null;

    static{
        try {
            configuration = new Configuration();
            configuration.setDefaultEncoding("utf-8");
            configuration.setClassLoaderForTemplateLoading(WordUtils.class.getClassLoader(),TEMPLATE_FILE_VOUCHER_PATH);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private WordUtils() {
        throw new AssertionError();
    }

    public static void exportWord(HttpServletResponse response, Map map,String title,String ftlFile) throws Exception {

        try {
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
            ex.printStackTrace();
            throw ex;
        }
    }
}
