package com.ciicsh.gto.afsupportcenter.healthmedical.entity.bo;

/**
 * 智灵通查询传入实体
 *
 * @author xiweizhen
 */
public class TimeScope {
    public String TimeFrom;
    public String TimeTo;

    public TimeScope() {
    }

    public TimeScope(String timeFrom, String timeTo) {
        TimeFrom = timeFrom;
        TimeTo = timeTo;
    }

    public String getTimeFrom() {
        return TimeFrom;
    }

    public void setTimeFrom(String timeFrom) {
        TimeFrom = timeFrom;
    }

    public String getTimeTo() {
        return TimeTo;
    }

    public void setTimeTo(String timeTo) {
        TimeTo = timeTo;
    }
}
