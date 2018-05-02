package com.ciicsh.gto.afsupportcenter.util.logservice;

import java.util.Map;

/**
 * Created by houwanhua on 2018/3/22.
 */
public class LogMessage {

    public static LogMessage create() {
        return new LogMessage();
    }

    private String title;
    private String content;
    private Map<String, String> tags;


    public LogMessage setTitle(String title) {
        this.title = title;
        return this;
    }

    public LogMessage setContent(String content) {
        this.content = content;
        return this;
    }

    public LogMessage setTags(Map<String, String> tags) {
        this.tags = tags;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public Map<String, String> getTags() {
        return tags;
    }
}
