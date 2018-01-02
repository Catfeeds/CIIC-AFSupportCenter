package com.ciicsh.gto.afsupportcenter.flexiblebenefit.entity.page;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;

import java.io.Serializable;
import java.lang.reflect.Type;

/**
 * 分页参数类
 */
public class PageInfo implements Serializable {
    /**
     * 第几页
     */
    private Integer pageNum;
    /**
     * 查询多少条
     */
    private Integer pageSize;
    /**
     * 排序方式
     */
    private String orderBy;

    /**
     * filterEmpty true 过滤空字符串
     */
    private boolean filterEmpty = true;

    // 参数对象，方便转成其他对象
    private JSONObject params = new JSONObject();

    public JSONObject getParams() {
        return params;
    }

    public void setParams(JSONObject params) {
        this.params = params;
    }

    public Integer getPageNum() {
        return pageNum;
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public String getOrderBy() {
        return orderBy;
    }

    public void setOrderBy(String orderBy) {
        this.orderBy = orderBy;
    }

    public boolean isFilterEmpty() {
        return filterEmpty;
    }

    public void setFilterEmpty(boolean filterEmpty) {
        this.filterEmpty = filterEmpty;
    }

    @Override
    public String toString() {
        return "PageInfo{" + "pageNum=" + pageNum + ", pageSize=" + pageSize + ", orderBy='" + orderBy + '\'' + ", params="
                + params + '}';
    }

    /**
     * 自定函数，方便替换算法
     *
     * @return
     */
    public JSONObject toMap() {
        return filter(params, filterEmpty);
    }

    /**
     * 自定函数，方便替换算法
     *
     * @param clazz
     * @return
     */
    public <T> T toJavaObject(Class<T> clazz) {
        return toMap().toJavaObject(clazz);
    }

    /**
     * 自定函数，方便替换算法
     *
     * @param type
     * @param <T>
     * @return
     */
    public <T> T toJavaObject(Type type) {
        return toMap().toJavaObject(type);
    }

    /**
     * 自定函数，方便替换算法
     *
     * @param typeReference
     * @param <T>
     * @return
     */
    public <T> T toJavaObject(TypeReference typeReference) {
        return toJavaObject(typeReference.getType());
    }

    protected JSONObject filter(JSONObject params, boolean filterEmpty) {
        JSONObject jsonObject = new JSONObject();
        if (params != null && filterEmpty) {
            params.forEach((key, val) -> {
                if ("".equals(val)) {
                    jsonObject.put(key, null);
                } else {
                    jsonObject.put(key, val);
                }
            });
        }
        return jsonObject;
    }
}
