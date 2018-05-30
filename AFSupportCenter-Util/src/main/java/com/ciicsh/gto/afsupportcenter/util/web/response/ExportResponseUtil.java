package com.ciicsh.gto.afsupportcenter.util.web.response;

import com.ciicsh.gto.afsupportcenter.util.exception.BusinessException;
import com.ciicsh.gto.afsupportcenter.util.web.request.RequestHolder;
import sun.misc.BASE64Encoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class ExportResponseUtil {
    public static void encodeExportFileName(HttpServletResponse response, String originFileName) {
        try {
            HttpServletRequest request = RequestHolder.getRequest();
            if (request != null) {
                final String userAgent = request.getHeader("USER-AGENT");
                String fileName;

                if (userAgent.contains("Firefox")) {
                    //是火狐浏览器，使用BASE64编码
                    fileName = "=?utf-8?b?" + new BASE64Encoder().encode(originFileName.getBytes("utf-8")) + "?=";
                } else {
                    //给文件名进行URL编码
                    fileName = URLEncoder.encode(originFileName, "utf-8");
                }
                response.setHeader("Content-Disposition", "attachment;filename=" + fileName);
            }
        } catch (UnsupportedEncodingException e) {
            throw new BusinessException(e);
        }
    }
}
