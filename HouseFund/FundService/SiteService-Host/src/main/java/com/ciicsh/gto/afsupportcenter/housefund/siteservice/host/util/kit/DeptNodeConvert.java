package com.ciicsh.gto.afsupportcenter.housefund.siteservice.host.util.kit;

import com.ciicsh.gto.afsupportcenter.housefund.fundservice.dto.dataauth.HfDepartmentDTO;
import com.ciicsh.gto.afsupportcenter.util.ui.core.TreeNode;
import com.ciicsh.gto.afsupportcenter.util.ui.core.TreeNodeConvert;

/**
 * SsDepartmentDTO TreeNodeConvert
 */
public class DeptNodeConvert implements TreeNodeConvert<HfDepartmentDTO> {

  @Override
  public TreeNode handle(HfDepartmentDTO e) {
    TreeNode node = new TreeNode();
    node.setId(String.valueOf(e.getDepartmentId()));
    node.setPid(String.valueOf(e.getParentDepartmentId()));
    node.setTitle(e.getDepartmentName());
    node.setType("dept");
    node.setExpand(true);

    node.put("departmentId", String.valueOf(e.getDepartmentId()));
    node.put("isValid", e.getIsValid());
    node.put("departmentLevel", e.getDepartmentLevel());
    node.put("departmentName", e.getDepartmentName());
    node.put("description", e.getDescription());
    return node;
  }
}
