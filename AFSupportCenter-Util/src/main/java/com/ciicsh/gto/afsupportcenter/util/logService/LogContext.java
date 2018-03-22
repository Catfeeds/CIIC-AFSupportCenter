package com.ciicsh.gto.afsupportcenter.util.logService;

import com.ciicsh.gto.logservice.api.dto.LogType;

import javax.validation.constraints.NotNull;
import java.util.HashMap;
import java.util.Map;

/**
 * 日志Context类，用于接收和提取日志信息
 *
 * @author sunjian
 * @since 2018-3-9
 */
public class LogContext {

    public static LogContext of() {
        return new LogContext();
    }

    private Map<String, Object> map = new HashMap<>();

    public LogContext setTitle(String title) {
        map.put("title", title);
        return this;
    }

    public LogContext setLogType(LogType logType) {
        map.put("logType", logType);
        return this;
    }

    public LogContext setAppId(String appId) {
        map.put("appId", appId);
        return this;
    }

    public LogContext setSource(String source) {
        map.put("source", source);
        return this;
    }

    public LogContext setTextContent(String content) {
        map.put("content", content);
        return this;
    }

    public LogContext setExceptionContent(Exception exception) {
        map.put("content", exception);
        return this;
    }

    public LogContext setObjectContent(@NotNull Object object, Object... oldObject) {
        LogObjectContent logContent = new LogObjectContent();
        logContent.setObject(object);
        if (oldObject.length > 0) {
            if (oldObject.length == 1) {
                logContent.setOldObject(oldObject[0]);
            } else {
                throw new IllegalArgumentException("只能传入一个旧对象");
            }
        }
        map.put("content", logContent);
        return this;
    }


    public LogContext addTagOfOperator(String operator) {
        return addTag("operator", operator);
    }

    public LogContext addTagOfObjectName(String objectName) {
        return addTag("objectName", objectName);
    }

    public LogContext addTagOfId(String id) {
        return addTag("id", id);
    }

    public LogContext addTagOfAct(String act) {
        return addTag("action", act);
    }


    public LogContext addTag(String tagName, String tagVlue) {
        Map<String, String> tag = (Map<String, String>) get("tag");
        if (tag == null) {
            tag = new HashMap();
        }
        tag.put(tagName, tagVlue);

        map.put("tag", tag);
        return this;
    }

    public Object get(String key) {
        return key != null ? map.get(key) : null;
    }
}

