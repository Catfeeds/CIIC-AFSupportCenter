package com.ciicsh.gto.afsupportcenter.util.logService;


import com.ciicsh.gto.afsupportcenter.util.interceptor.authenticate.UserContext;
import com.ciicsh.gto.logservice.api.LogServiceProxy;
import com.ciicsh.gto.logservice.api.dto.*;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 日志服务实现
 *
 * @author sunjian
 * @since 2018-3-9
 */
@Service
public class LogServiceImpl implements LogService {
    private static ExecutorService executorService = Executors.newSingleThreadExecutor();

    @Autowired
    private LogServiceProxy logServiceProxy;

    @Autowired
    private AppConfig appConfig;

    @Override
    public void debug(LogContext logContext) {
        addLog(LogLevel.DEBUG, logContext);
    }

    @Override
    public void info(LogContext logContext) {
        addLog(LogLevel.INFO, logContext);
    }

    @Override
    public void warn(LogContext logContext) {
        addLog(LogLevel.WARN, logContext);
    }

    @Override
    public void error(LogContext logContext) {
        addLog(LogLevel.ERROR, logContext);
    }

    @Override
    public void fatal(LogContext logContext) {
        addLog(LogLevel.FATAL, logContext);
    }

    private void addLog(LogLevel logLevel, LogContext logContext) {
        if (UserContext.getUser() != null) {
            logContext.addTagOfOperator(UserContext.getUser().getDisplayName());
        }
        executorService.execute(() -> {
            switch (logLevel) {
                case DEBUG:
                    logServiceProxy.debug(convertContextToDTO(logContext));
                    break;
                case INFO:
                    logServiceProxy.info(convertContextToDTO(logContext));
                    break;
                case WARN:
                    logServiceProxy.warn(convertContextToDTO(logContext));
                    break;
                case ERROR:
                    logServiceProxy.error(convertContextToDTO(logContext));
                    break;
                case FATAL:
                    logServiceProxy.fatal(convertContextToDTO(logContext));
                    break;
                default:
                    break;
            }
        });
    }

    /**
     * convert LogResDTO to LogResponseDTO
     *
     * @param logResDTO
     * @return LogResponseDTO
     */
    private LogResponseDTO getLogResponseDTO(LogResDTO logResDTO) {
        LogResponseDTO logResponseDTO = new LogResponseDTO();
        BeanUtils.copyProperties(logResDTO, logResponseDTO);

        //set id,action,target,operator
        Optional<Map<String, String>> tagsOptional = Optional.ofNullable(logResDTO.getTags());
        if (tagsOptional.isPresent()) {
            Map<String, String> tags = tagsOptional.get();
            tags.forEach((key, value) -> {
                switch (key) {
                    case "id":
                        logResponseDTO.setId(value);
                        break;
                    case "action":
                        logResponseDTO.setAct(value);
                        break;
                    case "objectName":
                        logResponseDTO.setObjectName(value);
                        break;
                    case "operator":
                        logResponseDTO.setOperator(value);
                    default:
                        break;
                }
            });
        }

        //set newObject and oldObject
        LogObjectContent logContent = LogObjectContent.parseContent(logResDTO.getContent());
        if (logContent != null) {
            logResponseDTO.setObject(logContent.getObject());
            logResponseDTO.setOldObject(logContent.getOldObject());
        }
        return logResponseDTO;
    }

    /**
     * Convert LogContext to LogDTO
     *
     * @param logContext
     * @return LogDTO
     */
    private LogDTO convertContextToDTO(LogContext logContext) {
        LogDTO logDTO = new LogDTO();
        logDTO.setAppId(appConfig.getAppId());
        logDTO.setSource(String.valueOf(Optional.ofNullable(logContext.get("source")).orElse("support-center")));
        logDTO.setTitle(String.valueOf(Optional.ofNullable(logContext.get("title")).orElse("")));
        logDTO.setLogType((LogType) Optional.ofNullable(logContext.get("logType")).orElse(LogType.APP));
        logDTO.setTags((Map<String, String>) logContext.get("tag"));

        //set Content
        Optional<Object> contentOptional = Optional.ofNullable(logContext.get("content"));
        if (contentOptional.isPresent()) {
            Object content = contentOptional.get();
            if (Exception.class.isAssignableFrom(content.getClass())) {
                logDTO.setContent(getStackTraceAsString(((Exception) content)));
            } else {
                logDTO.setContent(content.toString());
            }
        }
        return logDTO;
    }

    private String getStackTraceAsString(Throwable throwable) {
        StringWriter stringWriter = new StringWriter();
        throwable.printStackTrace(new PrintWriter(stringWriter));
        return stringWriter.toString();
    }
}
