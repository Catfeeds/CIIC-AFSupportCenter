package com.ciicsh.gto.afsupportcenter.housefund.siteservice.host.controller;


import com.ciicsh.gto.afsupportcenter.housefund.fundservice.business.HfAuthorityService;
import com.ciicsh.gto.afsupportcenter.housefund.fundservice.dto.dataauth.*;
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
    private HfAuthorityService hfAuthorityService;

    /**
     * 客户权限配置列表
     *
     * @return
     */
    @RequestMapping("hfauthorityList")
    public JsonResult<PageRows> authorityList() {
        List<HfUserInfoDTO> result = hfAuthorityService.queryUsersByDepartmentId();
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

        HfCompanyManagementListDTO result = hfAuthorityService.queryAfCompanyByUidAndServiceCenterId(userId, serviceCenterId);

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

        List<HfDepartmentDTO> list = hfAuthorityService.querySubDepartmentsOfLevel();

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


        hfAuthorityService.saveSsDataauth(dto);

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
    public JsonResult saveAuthorityWelfareUnit(HfDataauthWelfareUnitDTO dto) {


        hfAuthorityService.saveHfDataauthWelfareUnit(dto);

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
    public JsonResult<HfDataauthWelfareUnitDTO> queryAuthorityWelfareUnit(String userId) {


        HfDataauthWelfareUnitDTO dto = hfAuthorityService.queryHfDataauthWelfareUnit(userId);

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
    public JsonResult<HfDataauthWelfareUnitDTO> queryAuthorityTaskCategory(String userId) {


        HfDataauthWelfareUnitDTO dto = hfAuthorityService.queryAuthorityTaskCategory(userId);

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
    public JsonResult saveAuthorityTaskCategory(HfDataauthWelfareUnitDTO dto) {


        hfAuthorityService.saveHfDataauthTaskCategory(dto);

        JsonResult result = new JsonResult();
        result.setMessage("保存成功");
        return result;
    }
}
