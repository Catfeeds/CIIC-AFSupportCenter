package com.ciicsh.gto.afsupportcenter.util.ui.core;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by houwanhua on 2018/4/21.
 */
public class CascaderNode {
    // 标题
    private String label;
    // 节点 ID
    private String value;
    // 父节点 ID
    private String pvalue;
    // 子节点
    private List<CascaderNode> children = new ArrayList<>();

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getPvalue() {
        return pvalue;
    }

    public void setPvalue(String pvalue) {
        this.pvalue = pvalue;
    }

    public List<CascaderNode> getChildren() {
        return children;
    }

    public void setChildren(List<CascaderNode> children) {
        this.children = children;
    }
}
