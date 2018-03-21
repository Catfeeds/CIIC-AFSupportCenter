package com.ciicsh.gto.afsupportcenter.housefund.fundservice.business.utils;

import com.ciicsh.gto.logservice.api.LogServiceProxy;
import com.ciicsh.gto.logservice.api.dto.LogDTO;
import com.ciicsh.gto.logservice.api.dto.LogType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * Created by houwanhua on 2018/3/21.
 */
@Component
public class LogApiUtil {
    private Logger logger = LoggerFactory.getLogger(this.getClass().getSimpleName());

    @Autowired
    LogServiceProxy logService;


    private LogDTO setLogDto(String appId,String title,String content,String source,Map<String, String> tags){
        LogDTO dto = new LogDTO();
        dto.setAppId(appId);
        dto.setTitle(title);
        dto.setContent(content);
        dto.setSource(source);
        dto.setLogType(LogType.APP);
        if(null != tags){
            dto.setTags(tags);
        }
        return dto;
    }



    public void debug(String appId,String title,String content,String source){
        LogDTO dto = setLogDto(appId,title,content,source,null);
        logService.debug(dto);
    }

    public void debug(String appId,String title,String content,String source,Map<String, String> tags){
        LogDTO dto = setLogDto(appId,title,content,source,tags);
        logService.debug(dto);
    }

    public void info(String appId,String title,String content,String source){
        LogDTO dto = setLogDto(appId,title,content,source,null);
        logService.info(dto);
    }

    public void info(String appId,String title,String content,String source,Map<String, String> tags){
        LogDTO dto = setLogDto(appId,title,content,source,tags);
        logService.info(dto);
    }

    public void warn(String appId,String title,String content,String source){
        LogDTO dto = setLogDto(appId,title,content,source,null);
        logService.warn(dto);
    }

    public void warn(String appId,String title,String content,String source,Map<String, String> tags){
        LogDTO dto = setLogDto(appId,title,content,source,tags);
        logService.warn(dto);
    }


    public void error(String appId,String title,String content,String source){
        LogDTO dto = setLogDto(appId,title,content,source,null);
        logService.error(dto);
    }

    public void error(String appId,String title,String content,String source,Map<String, String> tags){
        LogDTO dto = setLogDto(appId,title,content,source,tags);
        logService.error(dto);
    }


    public void fatal(String appId,String title,String content,String source){
        LogDTO dto = setLogDto(appId,title,content,source,null);
        logService.fatal(dto);
    }

    public void fatal(String appId,String title,String content,String source,Map<String, String> tags){
        LogDTO dto = setLogDto(appId,title,content,source,tags);
        logService.fatal(dto);
    }
}
