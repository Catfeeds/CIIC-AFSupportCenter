package com.ciicsh.gto.afsupportcenter.util;

import com.ciicsh.gto.afsupportcenter.util.exception.BusinessException;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.io.StreamUtil;
import com.itextpdf.text.pdf.*;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.poi.ss.formula.functions.T;

import java.io.*;
import java.lang.reflect.Field;
import java.util.*;

public class PdfUtil {
    private final static String DEFAULT_FONT_NAME = "simsun.ttc,1";

    /**
     * 根据模板生成Pdf（根据单页模板，生成多页文档）
     *
     * @param templateFilePath 模板文档路径（支持相对路径）
     * @param fontName 字体名
     * @param isInSystemFontFolder 是否系统目录下字体
     * @param fillDataMapList 充填数据Map列表（表体之外部分：属性名请与Pdf模板一致；
     *                        表体部分：key:"fillDataList"; value:List<T>; 属性名请与Pdf模板一致，名称后的数字不需定义）
     * @param outputStream 输出流
     * @throws BusinessException 封装后异常
     */
    public static void createPdfByTemplate(String templateFilePath,
                                           String fontName,
                                           boolean isInSystemFontFolder,
                                           List<Map<String, Object>> fillDataMapList,
                                           OutputStream outputStream) throws BusinessException {
        InputStream is = null;
        ByteArrayOutputStream bos = null;
        Document doc = null;
        try {
            is = StreamUtil.getResourceStream(templateFilePath);
            bos = new ByteArrayOutputStream();
            if (isInSystemFontFolder) {
                fontName = getChineseFont(fontName);
            }
            doc = new Document();
            PdfCopy copy = new PdfCopy(doc, outputStream);
            doc.open();
            BaseFont baseFont = BaseFont.createFont(fontName, BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
            ArrayList<BaseFont> fontList = new ArrayList<>();
            fontList.add(baseFont);
            byte[] bytes = IOUtils.toByteArray(is);
            int pageSize = fillDataMapList.size();

            for (int page = 0; page < pageSize; page++) {
                PdfReader reader = new PdfReader(bytes);
                PdfStamper stamper = new PdfStamper(reader, bos);
                AcroFields form = stamper.getAcroFields();
                form.setSubstitutionFonts(fontList);
                Set<String> formKeySet = form.getFields().keySet();
                Map<String, Object> fillDataMap = fillDataMapList.get(page);
                List<T> fillDataList = (List<T>) fillDataMap.get("fillDataList");

                form.setField("pageTotal",String.valueOf(pageSize));
                form.setField("page", String.valueOf(page + 1));

                for (String key : fillDataMap.keySet()) {
                    if (formKeySet.contains(key)) {
                        form.setField(key, String.valueOf(fillDataMap.get(key)));
                    }
                }

                for (int i = 0; i < fillDataList.size(); i++) {
                    T t = fillDataList.get(i);
                    Field[] fields = t.getClass().getDeclaredFields();

                    for (Field field : fields) {
                        String fieldName = field.getName() + (i + 1);

                        if (formKeySet.contains(fieldName)) {
                            form.setField(fieldName, String.valueOf(field.get(t)));
                        }
                    }
                }
                stamper.setFormFlattening(true);
                stamper.close();
                PdfImportedPage importedPage = copy.getImportedPage(new PdfReader(bos.toByteArray()), 1);
                copy.addPage(importedPage);
            }
            doc.close();
        } catch (IOException | DocumentException | IllegalAccessException e) {
            throw new BusinessException(e);
        } finally {
            if (doc != null && doc.isOpen()) {
                doc.close();
            }
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    // TODO log
                }
            }
            if (bos != null) {
                try {
                    bos.close();
                } catch (IOException e) {
                    // TODO log
                }
            }
        }
    }

    public static String getChineseFont(String fontName){
        if (fontName == null) {
            fontName = DEFAULT_FONT_NAME;
        }

        //宋体（对应css中的 属性 font-family: SimSun; /*宋体*/）
        String fontPath = "C:/Windows/Fonts/" + fontName;

        //判断系统类型，加载字体文件
        Properties prop = System.getProperties();
        String osName = prop.getProperty("os.name").toLowerCase();

        if (osName.contains("linux")) {
            fontPath = "/usr/share/fonts/" + fontName;
        }
        if(!new File(fontPath).exists()){
            throw new RuntimeException("字体文件不存在,影响导出pdf中文显示" + fontPath);
        }
        return fontPath;
    }
}
