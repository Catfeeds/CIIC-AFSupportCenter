package com.ciicsh.gto.afsupportcenter.util.ui.core.tree;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

/**
 * TreeNode Kit
 */
public class TreeKit {

  public static List<TreeNode> tree(List<TreeNode> list) {
    // 1. 添加子节点
    List<TreeNode> roots = getRootNode(list);

    for (TreeNode n : roots) {
      addChildrenNodes(list, n);
    }

    return roots;
  }

  // 获取所有的根节点
  private static List<TreeNode> getRootNode(List<TreeNode> tree) {
    List<TreeNode> list = new ArrayList<>(tree.size());
    tree.forEach((d) -> {
      String pid = d.getPid();
      // 没有父节点或父节点值小于或等于0
      if (StringUtils.isEmpty(pid) || Integer.valueOf(pid) <= 0) {
        list.add(d);
      }
    });
    return list;
  }

  // 获得子节点
  private static List<TreeNode> getChildrenNode(List<TreeNode> nodes, String pid) {
    List<TreeNode> list = new ArrayList<>();
    nodes.forEach((d) -> {
      if (d.getPid() != null && d.getPid().equals(pid)) {
        list.add(d);
      }
    });
    return list;
  }

  // 添加子节点
  private static void addChildrenNodes(List<TreeNode> nodes, TreeNode node) {
    List<TreeNode> childrenNodes = getChildrenNode(nodes, node.getId());
    if (childrenNodes.isEmpty()) {
      return;
    }

    for (TreeNode n : childrenNodes) {
      addChildrenNodes(nodes, n);
    }

    node.setChildren(childrenNodes);
  }
}
