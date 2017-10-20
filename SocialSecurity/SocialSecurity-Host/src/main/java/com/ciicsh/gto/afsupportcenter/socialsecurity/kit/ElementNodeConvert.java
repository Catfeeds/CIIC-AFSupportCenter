package com.ciicsh.gto.afsupportcenter.socialsecurity.kit;

import java.util.List;

import com.ciicsh.gto.afsupportcenter.util.ui.core.tree.TreeNode;
import com.ciicsh.gto.afsupportcenter.util.ui.core.tree.TreeNodeConvert;

/**
 * Element TreeNodeConvert
 */
public class ElementNodeConvert implements TreeNodeConvert<SmElementInfo> {
  public static final ElementNodeConvert GLOBAL = new ElementNodeConvert();

  @Override
  public TreeNode handle(SmElementInfo e) {
    TreeNode node = new TreeNode();
    node.setId(e.getElementId());
    node.setPid(e.getMenuCode());
    node.setTitle(e.getElementName());
    node.setType("element");
    node.setExpand(true);

    // 元素显示类型：0: 不可见，1: 只读，2: 可读写
    List<TreeNode> children = node.getChildren();
    String pid = e.getElementId();
    String type = "displayType";
    children.add(newElementTypeNode("不可见", "0", pid, type));
    children.add(newElementTypeNode("只读", "1", pid, type));
    // 元素类型 : 1-表单域, 2-按钮
    if (Byte.valueOf("1").equals(e.getElementType())) {
      children.add(newElementTypeNode("可读写", "2", pid, type));
    }
    node.setChildren(children);
    return node;
  }

  /**
   * 创建 ElementType Node
   * 
   * @param title
   * @param id
   * @param pid
   * @param type
   * @return
   */
  private TreeNode newElementTypeNode(String title, String id, String pid, String type) {
    TreeNode node = new TreeNode();
    node.setTitle(title);
    node.setId(id);
    node.setPid(pid);
    node.setType(type);
    return node;
  }
}
