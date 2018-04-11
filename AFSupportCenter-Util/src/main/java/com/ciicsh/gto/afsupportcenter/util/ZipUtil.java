package com.ciicsh.gto.afsupportcenter.util;

import com.ciicsh.gto.afsupportcenter.util.exception.BusinessException;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class ZipUtil {

    public static void createZipFileWithTxtFiles(OutputStream outputStream, Map<String, String> txtFileMap) throws BusinessException {
        createZipFileWithTxtFiles(outputStream, txtFileMap, "UTF-8");
    }

    public static void createZipFileWithTxtFiles(OutputStream outputStream, Map<String, String> txtFileMap, String charsetName) throws BusinessException {
        try(ZipOutputStream zipOutputStream = new ZipOutputStream(outputStream)) {
            for (String key : txtFileMap.keySet()) {
                zipOutputStream.putNextEntry(new ZipEntry(key));
                zipOutputStream.write(txtFileMap.get(key).getBytes(charsetName));
            }
            zipOutputStream.close();
        } catch (IOException e) {
            throw new BusinessException(e);
        }
    }
}
