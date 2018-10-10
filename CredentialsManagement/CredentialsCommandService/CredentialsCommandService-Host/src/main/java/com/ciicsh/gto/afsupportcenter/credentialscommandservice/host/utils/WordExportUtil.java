package com.ciicsh.gto.afsupportcenter.credentialscommandservice.host.utils;

import com.ciicsh.gto.afsupportcenter.util.StringUtil;
import freemarker.template.Configuration;
import freemarker.template.Template;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.URLEncoder;
import java.util.Date;
import java.util.Map;

/**
 * @Author: guwei
 * @Description:
 * @Date: Created in 10:59 2018/9/28
 */
public class WordExportUtil {

    public static final String TEMPLATE_FILE_VOUCHER_PATH = "template";

    private static Configuration configuration = null;

    static{
        try {
            configuration = new Configuration();
            configuration.setDefaultEncoding("utf-8");
            configuration.setClassLoaderForTemplateLoading(WordExportUtil.class.getClassLoader(),TEMPLATE_FILE_VOUCHER_PATH);
            String templateFolder = WordExportUtil.class.getResource("/template").getPath();
            configuration.setDirectoryForTemplateLoading(new File(templateFolder));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private WordExportUtil() {
        throw new AssertionError();
    }

    public static void exportWord(HttpServletResponse response, Map map, String title, String ftlFile) throws Exception {

        try {
            Template freemarkerTemplate = configuration.getTemplate(ftlFile);
            response.setCharacterEncoding("utf-8");
            response.setContentType("application/msword");
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
