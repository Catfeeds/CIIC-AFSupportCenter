package com.ciicsh.gto.afsupportcenter.util.logservice;

import com.ciicsh.gto.logservice.api.LogServiceProxy;
import com.ciicsh.gto.logservice.api.dto.LogDTO;
import com.ciicsh.gto.logservice.api.dto.LogLevel;
import com.ciicsh.gto.logservice.api.dto.LogType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by houwanhua on 2018/3/21.
 */
@Component
public class LogApiUtil {
    private static ExecutorService executorService = Executors.newSingleThreadExecutor();

    @Value("${app.id}")
    private String appId;

    @Value("${spring.application.name}")
    private String source;

    @Autowired
    LogServiceProxy logService;

    public void info(LogMessage message){
        execute(LogLevel.INFO,message);
    }

    public void debug(LogMessage message){
        execute(LogLevel.DEBUG,message);
    }

    public void warn(LogMessage message){
        execute(LogLevel.WARN,message);
    }

    public void error(LogMessage message){
        execute(LogLevel.ERROR,message);
    }

    public void fatal(LogMessage message){
        execute(LogLevel.FATAL,message);
    }

    private void execute(LogLevel level,LogMessage message){
        executorService.execute(()->{
            switch (level){
                case INFO:
                    logService.info(setLogDto(message));
                    break;
                case DEBUG:
                    logService.debug(setLogDto(message));
                    break;
                case WARN:
                    logService.warn(setLogDto(message));
                    break;
                case ERROR:
                    logService.error(setLogDto(message));
                    break;
                case FATAL:
                    logService.fatal(setLogDto(message));
                    break;
                default:
                    break;
            }
        });
    }

    private LogDTO setLogDto(LogMessage message){
        LogDTO dto = new LogDTO();
        dto.setAppId(appId);
        dto.setTitle(message.getTitle());
        dto.setContent(message.getContent());
        dto.setSource(source);
        dto.setLogType(LogType.APP);
        if(null != message.getTags()){
            dto.setTags(message.getTags());
        }
        return dto;
    }
}
