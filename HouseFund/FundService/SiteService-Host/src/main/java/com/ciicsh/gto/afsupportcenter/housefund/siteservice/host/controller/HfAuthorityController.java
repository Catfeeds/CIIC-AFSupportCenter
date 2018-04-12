package com.ciicsh.gto.afsupportcenter.housefund.siteservice.host.controller;


import com.ciicsh.gto.afsupportcenter.housefund.fundservice.business.HfAuthorityService;
import com.ciicsh.gto.afsupportcenter.housefund.fundservice.dto.dataauth.HfCompanyManagementListDTO;
import com.ciicsh.gto.afsupportcenter.housefund.fundservice.dto.dataauth.HfDataauthCompanyDTO;
import com.ciicsh.gto.afsupportcenter.housefund.fundservice.dto.dataauth.HfDepartmentDTO;
import com.ciicsh.gto.afsupportcenter.housefund.fundservice.dto.dataauth.HfUserInfoDTO;
import com.ciicsh.gto.afsupportcenter.housefund.siteservice.host.util.kit.TreeNodeConvertKit;
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
 * 公积金权限管理
 * <p>
 * 前端控制器
 * </p>
 *
 * @author LiYueLong
 * @since 2018-4-11
 */
@RestController
@RequestMapping("/api/fundcommandservice/hfauthority")
public class HfAuthorityController {

    @Autowired
    private HfAuthorityService ssAuthorityService;

    /**
     * 客户权限配置列表
     *
     * @return
     */
    @RequestMapping("hfauthorityList")
    public JsonResult<PageRows> authorityList() {
        List<HfUserInfoDTO> result = ssAuthorityService.queryUsersByDepartmentId();
        PageRows<HfUserInfoDTO> pageRows = new PageRows<>();
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
    @RequestMapping("hfauthority")
    public JsonResult<HfCompanyManagementListDTO> authority(Long serviceCenterId, String userId) {

        HfCompanyManagementListDTO result = ssAuthorityService.queryAfCompanyByUidAndServiceCenterId(userId, serviceCenterId);

        JsonResult<HfCompanyManagementListDTO> jsonResult = new JsonResult<>();
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
    @RequestMapping("hfauthorityDeptNodes")
    public JsonResult<List<TreeNode>> authorityDeptNodes() {

        List<HfDepartmentDTO> list = ssAuthorityService.querySubDepartmentsOfLevel();

        List<TreeNode> deptNodes = new ArrayList<>();

        list.forEach((e) -> deptNodes.add(TreeNodeConvertKit.DEPT.handle(e)));

        return JsonResultKit.ofList(TreeKit.tree(deptNodes));
    }

    /**
     * 保存客户权限配置
     *
     * @return
     */
    @RequestMapping("hfsaveAuthority")
    public JsonResult saveAuthority(HfDataauthCompanyDTO dto) {


        ssAuthorityService.saveSsDataauth(dto);

        JsonResult result = new JsonResult();
        result.setMessage("保存成功");
        return result;
    }
}
