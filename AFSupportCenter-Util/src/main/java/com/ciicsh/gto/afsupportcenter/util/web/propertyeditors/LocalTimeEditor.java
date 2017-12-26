package com.ciicsh.gto.afsupportcenter.util.web.propertyeditors;

import org.apache.commons.lang3.StringUtils;

import java.beans.PropertyEditorSupport;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class LocalTimeEditor extends PropertyEditorSupport {

    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("HH:mm:ss");

    @Override
    public void setAsText(String text) throws IllegalArgumentException {
        if (StringUtils.isEmpty(text)) {
            setValue(null);
        } else {
            setValue(FORMATTER.parse(text));
        }
    }

    @Override
    public String getAsText() {
        Object date = getValue();
        if (date == null) {
            return null;
        }
        return FORMATTER.format((LocalTime) date);
    }
}
