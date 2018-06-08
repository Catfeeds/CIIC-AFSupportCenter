package com.ciicsh.gto.afsupportcenter.socialsecurity.siteservice.host.controller;


import com.ciicsh.gto.afsupportcenter.socialsecurity.siteservice.host.util.kit.TreeNodeConvertKit;
import com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.business.SsAuthorityService;
import com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.dto.dataauth.*;
import com.ciicsh.gto.afsupportcenter.util.page.PageRows;
import com.ciicsh.gto.afsupportcenter.util.ui.core.CascaderNode;
import com.ciicsh.gto.afsupportcenter.util.ui.core.CascderInstall;
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
 * 社保限权管理
 * <p>
 * 前端控制器
 * </p>
 *
 * @author LiYueLong
 * @since 2018-4-03
 */
@RestController
@RequestMapping("/api/soccommandservice/authority")
public class SsAuthorityController {

    @Autowired
    private SsAuthorityService ssAuthorityService;

    /**
     * 客户权限配置列表
     *
     * @return
     */
    @RequestMapping("authorityList")
    public JsonResult<PageRows> authorityList() {
        List<SsUserInfoDTO> result = ssAuthorityService.queryUsersByDepartmentId();
        PageRows<SsUserInfoDTO> pageRows = new PageRows<>();
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
    @RequestMapping("authority")
    public JsonResult<SsCompanyManagementListDTO> authority(Long serviceCenterId, String userId) {

        SsCompanyManagementListDTO result = ssAuthorityService.queryAfCompanyByUidAndServiceCenterId(userId, serviceCenterId);

        JsonResult<SsCompanyManagementListDTO> jsonResult = new JsonResult<>();
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
    @RequestMapping("authorityDeptNodes")
    public JsonResult<List<TreeNode>> authorityDeptNodes() {

        List<SsDepartmentDTO> list = ssAuthorityService.querySubDepartmentsOfLevel();

        List<TreeNode> deptNodes = new ArrayList<>();

        list.forEach((e) -> deptNodes.add(TreeNodeConvertKit.DEPT.handle(e)));

        return JsonResultKit.ofList(TreeKit.tree(deptNodes));
    }
    /**
     * 获取下拉框客服中心数据
     * @return
     */
    @RequestMapping("getSsCustomerData")
    public JsonResult<List<CascaderNode>> getCascaderData(){
        List<SsDepartmentDTO> list = ssAuthorityService.querySubDepartmentsOfLevel(new Integer[] {4, 5});
        List<CascaderNode> cascaderNodes = new ArrayList<>();
        list.forEach((e) -> cascaderNodes.add(TreeNodeConvertKit.DEPT.toCascaderNode(e)));
        return JsonResultKit.ofList(CascderInstall.createCascder(cascaderNodes));
    }
    /**
     * 保存客户权限配置
     *
     * @return
     */
    @RequestMapping("saveAuthority")
    public JsonResult saveAuthority(SsDataauthCompanyDTO dto) {


        ssAuthorityService.saveSsDataauth(dto);

        JsonResult result = new JsonResult();
        result.setMessage("保存成功");
        return result;
    }

    /**
     * 保存客户权限配置 类型为福利办理方
     *
     * @return
     */
    @RequestMapping("saveAuthorityWelfareUnit")
    public JsonResult saveAuthorityWelfareUnit(SsDataauthWelfareUnitDTO dto) {


        ssAuthorityService.saveSsDataauthWelfareUnit(dto);

        JsonResult result = new JsonResult();
        result.setMessage("保存成功");
        return result;
    }

    /**
     * 查询配置权限 类型为 福利办理方
     *
     * @return
     */
    @RequestMapping("queryAuthorityWelfareUnit")
    public JsonResult<SsDataauthWelfareUnitDTO> queryAuthorityWelfareUnit(String userId) {


        SsDataauthWelfareUnitDTO dto = ssAuthorityService.querySsDataauthWelfareUnit(userId);

        JsonResult result = new JsonResult();

        result.setData(dto);
        return result;
    }


    /**
     * 查询配置权限 类型为 任务单类型
     *
     * @return
     */
    @RequestMapping("queryAuthorityTaskCategory")
    public JsonResult<SsDataauthWelfareUnitDTO> queryAuthorityTaskCategory(String userId) {


        SsDataauthWelfareUnitDTO dto = ssAuthorityService.queryAuthorityTaskCategory(userId);

        JsonResult result = new JsonResult();

        result.setData(dto);
        return result;
    }


    /**
     * 保存客户权限配置 类型为任务单类型
     *
     * @return
     */
    @RequestMapping("saveAuthorityTaskCategory")
    public JsonResult saveAuthorityTaskCategory(SsDataauthWelfareUnitDTO dto) {


        ssAuthorityService.saveSsDataauthTaskCategory(dto);

        JsonResult result = new JsonResult();
        result.setMessage("保存成功");
        return result;
    }

}
