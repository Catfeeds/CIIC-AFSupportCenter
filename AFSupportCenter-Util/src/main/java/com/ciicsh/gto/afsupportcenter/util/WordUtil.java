package com.ciicsh.gto.afsupportcenter.util;

import freemarker.template.Configuration;
import freemarker.template.Template;

import javax.servlet.http.HttpServletResponse;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.URLEncoder;
import java.util.Map;

public class WordUtil {

    public static final String TEMPLATE_FILE_VOUCHER_PATH = "template";
    private static WordUtil instance;
    private static Configuration configuration = null;

    private WordUtil() {
        configuration = new Configuration(Configuration.DEFAULT_INCOMPATIBLE_IMPROVEMENTS);
        configuration.setDefaultEncoding("utf-8");
        configuration.setClassLoaderForTemplateLoading(WordUtil.class.getClassLoader(), TEMPLATE_FILE_VOUCHER_PATH);
        //   String templateFolder = "D:\\Projects\\release\\GT1.AFSupportCenter\\SocialSecurity\\SocService\\SiteService-Host\\src\\main\\resources\\template";
        //   configuration.setDirectoryForTemplateLoading(new File(templateFolder));
    }

    public static synchronized WordUtil getInstance() throws Exception {
        if (instance == null) {
            instance = new WordUtil();
        }
        return instance;
    }

    public void exportWord(HttpServletResponse response, Map map, String title, String ftlFile) throws Exception {
        response.setCharacterEncoding("utf-8");
        response.setContentType("application/msword");

        // 设置浏览器以下载的方式处理该文件名
        String fileName = title + ".doc";
        response.setHeader("Content-Disposition", "attachment;filename="
            .concat(String.valueOf(URLEncoder.encode(fileName, "UTF-8"))));

        try (Writer writer = new OutputStreamWriter(response.getOutputStream(), "utf-8")) {
            Template freemarkerTemplate = configuration.getTemplate(ftlFile);
//            Writer writer = new OutputStreamWriter(response.getOutputStream(), "utf-8");
            freemarkerTemplate.process(map, writer);
            writer.close();
        } catch (Exception ex) {
            ex.printStackTrace();
            throw ex;
        }
    }
}
