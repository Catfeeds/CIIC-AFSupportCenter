package com.ciicsh.gto.afsupportcenter.util.ui.core.tree;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;

import com.ciicsh.gto.afsupportcenter.util.exception.BusinessException;
import com.ciicsh.gto.afsupportcenter.util.kit.ReflectKit;

/**
 * TreeNode 树形控件
 */
public class TreeNode {
  // 标题
  private String title;
  // 是否展开直子节点
  private Boolean expand;
  // 禁掉响应
  private Boolean disabled;
  // 禁掉 checkbox
  private Boolean disableCheckbox;
  // 是否选中子节点
  private Boolean selected;
  // 是否勾选(如果勾选，子节点也会全部勾选)
  private Boolean checked;
  // 子节点
  private List<TreeNode> children = new ArrayList<>();


  // 节点 ID
  private String id;
  // 父节点 ID
  private String pid;
  // 节点类型
  private String type;
  // 数据
  private Map<String, Object> data = new HashMap<>();

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public Boolean getExpand() {
    return expand;
  }

  public void setExpand(Boolean expand) {
    this.expand = expand;
  }

  public Boolean getDisabled() {
    return disabled;
  }

  public void setDisabled(Boolean disabled) {
    this.disabled = disabled;
  }

  public Boolean getDisableCheckbox() {
    return disableCheckbox;
  }

  public void setDisableCheckbox(Boolean disableCheckbox) {
    this.disableCheckbox = disableCheckbox;
  }

  public Boolean getSelected() {
    return selected;
  }

  public void setSelected(Boolean selected) {
    this.selected = selected;
  }

  public Boolean getChecked() {
    return checked;
  }

  public void setChecked(Boolean checked) {
    this.checked = checked;
  }

  public List<TreeNode> getChildren() {
    return children;
  }

  public void setChildren(List<TreeNode> children) {
    this.children = children;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getPid() {
    return pid;
  }

  public void setPid(String pid) {
    this.pid = pid;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public Map<String, Object> getData() {
    return data;
  }

  public void setData(Map<String, Object> data) {
    this.data = data;
  }

  public <T> T getData(Class<T> clazz) {
    T bean = ReflectKit.newInstance(clazz);
    try {
      BeanUtils.populate(bean, getData());
    } catch (IllegalAccessException | InvocationTargetException e) {
      BusinessException.unchecked(e);
    }
    return bean;
  }

}
