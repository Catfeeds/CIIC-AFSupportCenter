package com.ciicsh.gto.afsupportcenter.flexiblebenefit.fbqueryservice.api.dto;

import java.util.List;

/**
 * 分页公共方法
 *
 * @author xiweizhen
 */
public class CommonDTO<T> {
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

    public List<T> getRecords() {
        return records;
    }

    public void setRecords(List<T> records) {
        this.records = records;
    }

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

    @Override
    public String toString() {
        return "CommonDTO{" +
            "current=" + current +
            ", size=" + size +
            ", total=" + total +
            ", records=" + records +
            '}';
    }
}
