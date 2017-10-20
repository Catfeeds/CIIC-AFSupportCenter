package com.ciicsh.gto.afsupportcenter.socialsecurity.kit;

import java.util.Map;

import com.ciicsh.gto.afsupportcenter.util.ui.core.tree.TreeNode;
import com.ciicsh.gto.afsupportcenter.util.ui.core.tree.TreeNodeConvert;

/**
 * Menu TreeNodeConvert
 */
public class MenuNodeConvert implements TreeNodeConvert<SmMenuInfo> {
  public static final MenuNodeConvert GLOBAL = new MenuNodeConvert();

  @Override
  public TreeNode handle(SmMenuInfo e) {
    TreeNode node = new TreeNode();
    node.setId(e.getMenuCode());
    node.setPid(e.getParentMenuCode());
    node.setTitle(e.getMenuName());
    node.setType("menu");
    node.setExpand(true);

    Map<String, Object> data = node.getData();
    data.put("menuId", e.getMenuId());
    data.put("menuLevel", e.getMenuLevel());
    data.put("linkURL", e.getLinkURL());
    data.put("imageURL", e.getImageURL());
    data.put("isVisible", e.getIsVisible());
    return node;
  }
}
