package com.ciicsh.gto.afsupportcenter.util.logService;

import com.ciicsh.gto.logservice.api.dto.LogResDTO;

/**
 * 日志查询响应DTO
 *
 * @author sunjian
 * @since 2018-3-9
 */
public class LogResponseDTO extends LogResDTO {

    /**
     * 操作对象的id
     */
    private String id;

    /**
     * 操作名称
     */
    private String act;

    /**
     * 操作人
     */
    private String operator;

    /**
     * 操作对象
     */
    private String objectName;

    /**
     * 对象的值
     */
    private Object object;

    /**
     * 原对象的值
     */
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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAct() {
        return act;
    }

    public void setAct(String act) {
        this.act = act;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public String getObjectName() {
        return objectName;
    }

    public void setObjectName(String objectName) {
        this.objectName = objectName;
    }
}
