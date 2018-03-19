package com.ciicsh.gto.afsupportcenter.util;

import com.ciicsh.gto.afsupportcenter.util.exception.BusinessException;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.io.StreamUtil;
import com.itextpdf.text.pdf.*;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;

import java.io.*;
import java.lang.reflect.Field;
import java.util.*;

public class PdfUtil {
    public final static String DEFAULT_FONT_FILE_NAME = "simsun.ttc";
    public final static String DEFAULT_FONT_NAME = "STSongStd-Light";
    public final static String DEFAULT_FONT_ENCODING = "UniGB-UCS2-H";

    /**
     * 根据模板生成Pdf（根据单页模板，生成多页文档）
     *
     * @param templateFilePath 模板文档路径（支持相对路径）
     * @param fontName 字体名
     * @param fontEncoding 字体编码
     * @param isInSystemFontFolder 是否系统目录下字体
     * @param hasPageInfo 是否包含翻页信息
     * @param fillDataMapList 充填数据Map列表（表体之外部分：属性名请与Pdf模板一致；
     *                        表体部分(可省略)：key:"fillDataList"; value:List<T>； 属性名请与Pdf模板一致，名称后的数字不需定义）
     * @param outputStream 输出流
     * @throws BusinessException 封装后异常
     */
    public static void createPdfByTemplate(String templateFilePath,
                                           String fontName,
                                           String fontEncoding,
                                           boolean isInSystemFontFolder,
                                           boolean hasPageInfo,
                                           List<Map<String, Object>> fillDataMapList,
                                           OutputStream outputStream) throws BusinessException {
        try (InputStream is = StreamUtil.getResourceStream(templateFilePath);
             ByteArrayOutputStream bos = new ByteArrayOutputStream()) {
            ArrayList<BaseFont> fontList = null;
            if (StringUtils.isNotEmpty(fontName)) {
                fontList = new ArrayList<>();
                if (isInSystemFontFolder) {
                    fontName = getChineseFont(fontName) + ",1";
                }
                if (StringUtils.isEmpty(fontEncoding)) {
                    fontEncoding = BaseFont.IDENTITY_H;
                }
                BaseFont baseFont = BaseFont.createFont(fontName, fontEncoding, BaseFont.EMBEDDED);
                fontList.add(baseFont);
            }
            Document doc = new Document();
            PdfCopy copy = new PdfCopy(doc, outputStream);
            doc.open();

            byte[] bytes = IOUtils.toByteArray(is);
            int pageSize = fillDataMapList.size();

            for (int page = 0; page < pageSize; page++) {
                PdfReader reader = new PdfReader(bytes);
                PdfStamper stamper = new PdfStamper(reader, bos);
                AcroFields form = stamper.getAcroFields();
                if (fontList != null) {
                    form.setSubstitutionFonts(fontList);
                }
                Set<String> formKeySet = form.getFields().keySet();
                Map<String, Object> fillDataMap = fillDataMapList.get(page);

                if (hasPageInfo) {
                    form.setField("totalPage", String.valueOf(pageSize));
                    form.setField("page", String.valueOf(page + 1));
                }

                for (String key : fillDataMap.keySet()) {
                    if (formKeySet.contains(key)) {
                        Object value = fillDataMap.get(key);
                        if (value != null) {
                            form.setField(key, String.valueOf(value));
                        } else {
                            form.setField(key, "");
                        }
                    }
                }

                if (fillDataMap.containsKey("fillDataList")) {
                    List fillDataList = (List) fillDataMap.get("fillDataList");
                    for (int i = 0; i < fillDataList.size(); i++) {
                        Object obj = fillDataList.get(i);
                        Field[] fields = obj.getClass().getDeclaredFields();

                        for (Field field : fields) {
                            field.setAccessible(true);
                            String fieldName = field.getName() + (i + 1);

                            if (formKeySet.contains(fieldName)) {
                                Object fieldValue = field.get(obj);
                                if (fieldValue != null) {
                                    form.setField(fieldName, String.valueOf(fieldValue));
                                } else {
                                    form.setField(fieldName, "");
                                }
                            }
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
        }
    }

    public static String getChineseFont(String fontName){
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
