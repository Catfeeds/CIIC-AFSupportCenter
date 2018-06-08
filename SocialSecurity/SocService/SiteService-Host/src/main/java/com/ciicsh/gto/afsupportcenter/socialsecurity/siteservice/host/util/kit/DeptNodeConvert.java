package com.ciicsh.gto.afsupportcenter.socialsecurity.siteservice.host.util.kit;

import com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.dto.dataauth.SsDepartmentDTO;
import com.ciicsh.gto.afsupportcenter.util.ui.core.CascaderNode;
import com.ciicsh.gto.afsupportcenter.util.ui.core.TreeNode;
import com.ciicsh.gto.afsupportcenter.util.ui.core.TreeNodeConvert;

/**
 * SsDepartmentDTO TreeNodeConvert
 */
public class DeptNodeConvert implements TreeNodeConvert<SsDepartmentDTO> {

  @Override
  public TreeNode handle(SsDepartmentDTO e) {
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
    public CascaderNode toCascaderNode(SsDepartmentDTO e){
        CascaderNode cascaderNode = new CascaderNode();
        cascaderNode.setLabel(e.getDepartmentName());
        cascaderNode.setValue(String.valueOf(e.getDepartmentId()));
        cascaderNode.setPvalue(String.valueOf(e.getParentDepartmentId()));
        return cascaderNode;
    }
}
