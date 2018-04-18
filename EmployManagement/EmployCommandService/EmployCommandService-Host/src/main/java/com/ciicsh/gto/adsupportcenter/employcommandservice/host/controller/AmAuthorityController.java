package com.ciicsh.gto.adsupportcenter.employcommandservice.host.controller;


import com.ciicsh.gto.adsupportcenter.employcommandservice.host.util.kit.TreeNodeConvertKit;
import com.ciicsh.gto.afsupportcenter.employmanagement.employcommandservice.business.AmAuthorityService;
import com.ciicsh.gto.afsupportcenter.employmanagement.employcommandservice.dto.dataauth.AmCompanyManagementListDTO;
import com.ciicsh.gto.afsupportcenter.employmanagement.employcommandservice.dto.dataauth.AmDataauthCompanyDTO;
import com.ciicsh.gto.afsupportcenter.employmanagement.employcommandservice.dto.dataauth.AmDepartmentDTO;
import com.ciicsh.gto.afsupportcenter.employmanagement.employcommandservice.dto.dataauth.AmUserInfoDTO;
import com.ciicsh.gto.afsupportcenter.util.page.PageRows;
import com.ciicsh.gto.afsupportcenter.util.ui.core.TreeKit;
import com.ciicsh.gto.afsupportcenter.util.ui.core.TreeNode;
import com.ciicsh.gto.afsupportcenter.util.web.response.JsonResult;
import com.ciicsh.gto.afsupportcenter.util.web.response.JsonResultKit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;


/**
 * <p>
 * 雇佣数据限权管理
 * <p>
 * 前端控制器
 * </p>
 *
 * @author LiYueLong
 * @since 2018-4-18
 */
@RestController
@RequestMapping("/api/employcommandservice/amauthority")
public class AmAuthorityController {

    @Autowired
    private AmAuthorityService amAuthorityService;

    /**
     * 客户权限配置列表
     *
     * @return
     */
    @RequestMapping("amauthorityList")
    public JsonResult<PageRows> authorityList() {
        List<AmUserInfoDTO> result = amAuthorityService.queryUsersByDepartmentId();
        PageRows<AmUserInfoDTO> pageRows = new PageRows<>();
        pageRows.setRows(result);
        return JsonResultKit.of(pageRows);
    }


    /**
     * 根据部门ID和对应配置的用户ID获取客户列表
     *
     * @param serviceCenterId
     * @param userId
     * @return
     */
    @RequestMapping("amauthority")
    public JsonResult<AmCompanyManagementListDTO> authority(Long serviceCenterId, String userId) {

        AmCompanyManagementListDTO result = amAuthorityService.queryAfCompanyByUidAndServiceCenterId(userId, serviceCenterId);

        JsonResult<AmCompanyManagementListDTO> jsonResult = new JsonResult<>();
        jsonResult.setCode(result.getCode());
        jsonResult.setData(result);
        jsonResult.setMessage(result.getMessage());

        return jsonResult;

    }

    /**
     * 客户中心权限菜单树
     *
     * @return
     */
    @RequestMapping("amauthorityDeptNodes")
    public JsonResult<List<TreeNode>> authorityDeptNodes() {

        List<AmDepartmentDTO> list = amAuthorityService.querySubDepartmentsOfLevel();

        List<TreeNode> deptNodes = new ArrayList<>();

        list.forEach((e) -> deptNodes.add(TreeNodeConvertKit.DEPT.handle(e)));

        return JsonResultKit.ofList(TreeKit.tree(deptNodes));
    }

    /**
     * 保存客户权限配置
     *
     * @return
     */
    @RequestMapping("amsaveAuthority")
    public JsonResult saveAuthority(AmDataauthCompanyDTO dto) {


        amAuthorityService.saveSsDataauth(dto);

        JsonResult result = new JsonResult();
        result.setMessage("保存成功");
        return result;
    }
}
