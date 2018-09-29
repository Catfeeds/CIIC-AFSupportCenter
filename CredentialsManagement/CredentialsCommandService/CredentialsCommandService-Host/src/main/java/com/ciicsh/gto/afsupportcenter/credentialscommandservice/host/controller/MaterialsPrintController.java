package com.ciicsh.gto.afsupportcenter.credentialscommandservice.host.controller;

import com.ciicsh.gto.afsupportcenter.credentialscommandservice.business.MaterialTypeRelationService;
import com.ciicsh.gto.afsupportcenter.credentialscommandservice.business.TaskMaterialService;
import com.ciicsh.gto.afsupportcenter.credentialscommandservice.business.TaskService;
import com.ciicsh.gto.afsupportcenter.credentialscommandservice.entity.bo.TaskPrintBO;
import com.ciicsh.gto.afsupportcenter.credentialscommandservice.entity.po.MaterialTypeRelation;
import com.ciicsh.gto.afsupportcenter.credentialscommandservice.entity.po.Task;
import com.ciicsh.gto.afsupportcenter.credentialscommandservice.entity.po.TaskMaterial;
import com.ciicsh.gto.afsupportcenter.credentialscommandservice.host.utils.SelectionUtils;
import com.ciicsh.gto.afsupportcenter.credentialscommandservice.host.utils.WordExportUtil;
import com.ciicsh.gto.afsupportcenter.util.result.JsonResult;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: guwei
 * @Description:
 * @Date: Created in 15:45 2018/9/27
 */
@RestController
@RequestMapping("/api/credentialsMaterial")
public class MaterialsPrintController {

    @Autowired
    private MaterialTypeRelationService materialTypeRelationService;

    @Autowired
    private TaskMaterialService taskMaterialService;

    @Autowired
    private TaskService taskService;

    @GetMapping("/download")
    public JsonResult downloadMaterialList(HttpServletResponse response, String taskId) throws Exception {
        TaskPrintBO taskPrintBO = new TaskPrintBO();
        List<TaskPrintBO.Material> materials = new ArrayList<>();
        Task task = taskService.selectById(taskId);
        if (task!=null) {
            if (task.getCredentialsType() == 1 || task.getCredentialsType() == 2) {
                taskPrintBO.setCredentialsTypeName(SelectionUtils.credentialsDeal(task.getCredentialsDealType()));
            } else {
                taskPrintBO.setCredentialsTypeName(SelectionUtils.credentials(task.getCredentialsType()));
            }
        }
        TaskMaterial taskMaterial = taskMaterialService.selectMetarials(taskId);
        if (StringUtils.isNotBlank(taskMaterial.getMaterialIds())) {
            List<MaterialTypeRelation> materialTypeRelationList = materialTypeRelationService.selectMetarialList(taskMaterial.getMaterialIds());
            taskPrintBO.setCount(materialTypeRelationList.size());
            materialTypeRelationList.stream().forEach(item -> {
                TaskPrintBO.Material material = taskPrintBO.new Material();
                material.setMaterialName(item.getMaterialName());
                materials.add(material);
                taskPrintBO.setMaterialList(materials);
            });
        }
        materialPrint(response, taskPrintBO);
        return JsonResult.success(null);
    }

    public void materialPrint(HttpServletResponse response,TaskPrintBO taskPrintBO) throws Exception {
        Map resultMap = new HashMap();
        resultMap.put("task", taskPrintBO);
        WordExportUtil.exportWord(response, resultMap, "材料收缴清单", "材料收缴清单.ftl");
    }
}
