package com.ciicsh.gto.afsupportcenter.healthmedical.entity.dto;

import java.util.List;

/**
 * 分页通用字段
 *
 * @author xiweizhen
 */
public class CommonEntity<T> {
    /**
     * 起始页
     */
    private Integer current;

    /**
     * 每页条数
     */
    private Integer size;
    /**
     * 总条数
     */
    private Integer total;
    private List<T> records;

    public Integer getCurrent() {
        return current;
    }

    public void setCurrent(Integer current) {
        this.current = current;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public List<T> getRecords() {
        return records;
    }

    public void setRecords(List<T> records) {
        this.records = records;
    }
}
