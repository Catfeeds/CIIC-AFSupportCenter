package com.ciicsh.gto.afsupportcenter.util.logService;

import com.alibaba.fastjson.JSON;

import java.util.Optional;

/**
 * 对象形式的日志内容Wrapper
 *
 * @author sunjian
 * @since 2018-3-13
 */
public class LogObjectContent {
    private Object object;
    private Object oldObject;


    public Object getOldObject() {
        return oldObject;
    }

    public void setOldObject(Object oldObject) {
        this.oldObject = oldObject;
    }

    public Object getObject() {
        return object;
    }

    public void setObject(Object object) {
        this.object = object;
    }

    @Override
    public String toString() {
        if (oldObject != null || object != null) {
            return JSON.toJSONString(this);
        } else {
            return "";
        }
    }

    public static LogObjectContent parseContent(String content) {
        Optional<String> contentOptional = Optional.ofNullable(content);
        LogObjectContent logContent = null;
        if (contentOptional.isPresent()) {
            try {
                logContent = JSON.parseObject(contentOptional.get(), LogObjectContent.class);
            } catch (Exception e) {
                logContent = null;
            }
        }
        return logContent;
    }
}

