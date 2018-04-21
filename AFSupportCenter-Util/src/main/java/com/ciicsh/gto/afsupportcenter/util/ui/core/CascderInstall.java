package com.ciicsh.gto.afsupportcenter.util.ui.core;

import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by houwanhua on 2018/4/21.
 */
public class CascderInstall {

    public static List<CascaderNode> createCascder(List<CascaderNode> list) {
        // 1. 添加子节点
        List<CascaderNode> roots = getRootNode(list);

        for (CascaderNode n : roots) {
            addChildrenNodes(list, n);
        }
        return roots;
    }

    // 获取所有的根节点
    private static List<CascaderNode> getRootNode(List<CascaderNode> tree) {
        List<CascaderNode> list = new ArrayList<>(tree.size());
        tree.forEach((d) -> {
            String pValue = d.getPvalue();
            // 没有父节点或父节点值小于或等于0   或者客服中心父节点为415
            if (StringUtils.isEmpty(pValue) || Integer.valueOf(pValue) <= 0 || Integer.valueOf(pValue) == 415) {
                list.add(d);
            }
        });
        return list;
    }

    // 添加子节点
    private static void addChildrenNodes(List<CascaderNode> nodes, CascaderNode node) {
        List<CascaderNode> childrenNodes = getChildrenNode(nodes, node);
        if (childrenNodes.isEmpty()) {
            return;
        }

        for (CascaderNode n : childrenNodes) {
            addChildrenNodes(nodes, n);
        }

        if (node.getChildren() != null) {
            node.getChildren().addAll(childrenNodes);
        } else {
            node.setChildren(childrenNodes);
        }
    }

    // 获得子节点
    private static List<CascaderNode> getChildrenNode(List<CascaderNode> nodes, CascaderNode parentNode) {
        List<CascaderNode> list = new ArrayList<>();
        nodes.forEach((d) -> {
            if (d.getPvalue() != null && d.getPvalue().equals(parentNode.getValue())) {
                list.add(d);
            }
        });
        return list;
    }

}
