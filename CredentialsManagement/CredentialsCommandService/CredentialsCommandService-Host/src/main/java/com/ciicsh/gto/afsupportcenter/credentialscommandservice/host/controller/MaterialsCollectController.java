package com.ciicsh.gto.afsupportcenter.credentialscommandservice.host.controller;

import com.ciicsh.gto.afsupportcenter.credentialscommandservice.business.MaterialTypeRelationService;
import com.ciicsh.gto.afsupportcenter.credentialscommandservice.business.TaskMaterialService;
import com.ciicsh.gto.afsupportcenter.credentialscommandservice.entity.po.MaterialTypeRelation;
import com.ciicsh.gto.afsupportcenter.credentialscommandservice.entity.po.TaskMaterial;
import com.ciicsh.gto.afsupportcenter.util.result.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @Author: guwei
 * @Description: 材料收缴控制器
 * @Date: Created in 9:44 2018/2/6
 */
@RestController
@RequestMapping("/api/materials")
public class MaterialsCollectController {

    @Autowired
    private TaskMaterialService taskMaterialService;
    @Autowired
    private MaterialTypeRelationService materialTypeRelationService;

    /**
     * 获取任务单材料收缴信息{level:[materialsId],...}
     * @param taskId
     * @return
     */
    @GetMapping("/find/{taskId}")
    public JsonResult getMaterials(@PathVariable("taskId") String taskId) {
        TaskMaterial taskMaterial = taskMaterialService.selectByTaskId(taskId);
        HashMap<String, List<String>> resultMap = new HashMap<>(37);
        List list00 = new ArrayList<>();
        List list11 = new ArrayList<>();
        List list12 = new ArrayList<>();
        List list20 = new ArrayList<>();
        List list30 = new ArrayList<>();
        List list31 = new ArrayList<>();
        List list32 = new ArrayList<>();
        List list33 = new ArrayList<>();
        List list34 = new ArrayList<>();
        List list35 = new ArrayList<>();
        List list40 = new ArrayList<>();
        List list41 = new ArrayList<>();
        List list42 = new ArrayList<>();
        List list43 = new ArrayList<>();
        List list50 = new ArrayList<>();
        List list51 = new ArrayList<>();
        List list52 = new ArrayList<>();
        List list53 = new ArrayList<>();
        List list61 = new ArrayList<>();
        List list62 = new ArrayList<>();
        List list63 = new ArrayList<>();
        List list64 = new ArrayList<>();
        List list70 = new ArrayList<>();
        List list71 = new ArrayList<>();
        List list72 = new ArrayList<>();
        List list80 = new ArrayList<>();
        List list81 = new ArrayList<>();
        List list82 = new ArrayList<>();
        List list83 = new ArrayList<>();
        List list90 = new ArrayList<>();
        List list91 = new ArrayList<>();
        List list92 = new ArrayList<>();
        List list93 = new ArrayList<>();
        List list94 = new ArrayList<>();
        List list101 = new ArrayList<>();
        List list102 = new ArrayList<>();
        if (taskMaterial != null) {
            List<MaterialTypeRelation> materials = materialTypeRelationService.selectList(taskMaterial.getMaterialIds());
            materials.stream().forEach(i -> {
                if ("0-0".equals(i.getLevel())) { list00.add(i.getMaterialId());}
                if ("1-1".equals(i.getLevel())) { list11.add(i.getMaterialId());}
                if ("1-2".equals(i.getLevel())) { list12.add(i.getMaterialId());}
                if ("2-0".equals(i.getLevel())) { list20.add(i.getMaterialId());}
                if ("3-0".equals(i.getLevel())) { list30.add(i.getMaterialId());}
                if ("3-1".equals(i.getLevel())) { list31.add(i.getMaterialId());}
                if ("3-2".equals(i.getLevel())) { list32.add(i.getMaterialId());}
                if ("3-3".equals(i.getLevel())) { list33.add(i.getMaterialId());}
                if ("3-4".equals(i.getLevel())) { list34.add(i.getMaterialId());}
                if ("3-5".equals(i.getLevel())) { list35.add(i.getMaterialId());}
                if ("4-0".equals(i.getLevel())) { list40.add(i.getMaterialId());}
                if ("4-1".equals(i.getLevel())) { list41.add(i.getMaterialId());}
                if ("4-2".equals(i.getLevel())) { list42.add(i.getMaterialId());}
                if ("4-3".equals(i.getLevel())) { list43.add(i.getMaterialId());}
                if ("5-0".equals(i.getLevel())) { list50.add(i.getMaterialId());}
                if ("5-1".equals(i.getLevel())) { list51.add(i.getMaterialId());}
                if ("5-2".equals(i.getLevel())) { list52.add(i.getMaterialId());}
                if ("5-3".equals(i.getLevel())) { list53.add(i.getMaterialId());}
                if ("6-1".equals(i.getLevel())) { list61.add(i.getMaterialId());}
                if ("6-2".equals(i.getLevel())) { list62.add(i.getMaterialId());}
                if ("6-3".equals(i.getLevel())) { list63.add(i.getMaterialId());}
                if ("6-4".equals(i.getLevel())) { list64.add(i.getMaterialId());}
                if ("7-0".equals(i.getLevel())) { list70.add(i.getMaterialId());}
                if ("7-1".equals(i.getLevel())) { list71.add(i.getMaterialId());}
                if ("7-2".equals(i.getLevel())) { list72.add(i.getMaterialId());}
                if ("8-0".equals(i.getLevel())) { list80.add(i.getMaterialId());}
                if ("8-1".equals(i.getLevel())) { list81.add(i.getMaterialId());}
                if ("8-2".equals(i.getLevel())) { list82.add(i.getMaterialId());}
                if ("8-3".equals(i.getLevel())) { list83.add(i.getMaterialId());}
                if ("9-0".equals(i.getLevel())) { list90.add(i.getMaterialId());}
                if ("9-1".equals(i.getLevel())) { list91.add(i.getMaterialId());}
                if ("9-2".equals(i.getLevel())) { list92.add(i.getMaterialId());}
                if ("9-3".equals(i.getLevel())) { list93.add(i.getMaterialId());}
                if ("9-4".equals(i.getLevel())) { list94.add(i.getMaterialId());}
                if ("10-1".equals(i.getLevel())) { list101.add(i.getMaterialId());}
                if ("10-2".equals(i.getLevel())) { list102.add(i.getMaterialId());}
            });
        }
        resultMap.put("lev00",list00);
        resultMap.put("lev11",list11);
        resultMap.put("lev12",list12);
        resultMap.put("lev20",list20);
        resultMap.put("lev30",list30);
        resultMap.put("lev31",list31);
        resultMap.put("lev32",list32);
        resultMap.put("lev33",list33);
        resultMap.put("lev34",list34);
        resultMap.put("lev35",list35);
        resultMap.put("lev40",list40);
        resultMap.put("lev41",list41);
        resultMap.put("lev42",list42);
        resultMap.put("lev43",list43);
        resultMap.put("lev50",list50);
        resultMap.put("lev51",list51);
        resultMap.put("lev52",list52);
        resultMap.put("lev53",list53);
        resultMap.put("lev61",list61);
        resultMap.put("lev62",list62);
        resultMap.put("lev63",list63);
        resultMap.put("lev64",list64);
        resultMap.put("lev70",list70);
        resultMap.put("lev71",list71);
        resultMap.put("lev72",list72);
        resultMap.put("lev80",list80);
        resultMap.put("lev81",list81);
        resultMap.put("lev82",list82);
        resultMap.put("lev83",list83);
        resultMap.put("lev90",list90);
        resultMap.put("lev91",list91);
        resultMap.put("lev92",list92);
        resultMap.put("lev93",list93);
        resultMap.put("lev94",list94);
        resultMap.put("lev101",list101);
        resultMap.put("lev102",list102);
        return JsonResult.success(resultMap);
    }

    /**
     * 生成材料收缴菜单
     * @param credentialsType
     * @param credentialsDealType
     * @return
     */
    @GetMapping("/create")
    public JsonResult materialsMenu(String credentialsType, String credentialsDealType) {
        List<MaterialTypeRelation> materials = materialTypeRelationService.selectMaterials(credentialsType,credentialsDealType);
        return JsonResult.success(materials);
    }

    /**
     * 获取选中的二级菜单
     * @param taskId
     * @return
     */
    @GetMapping("/findMenu/{taskId}")
    public JsonResult getlv2Menu(@PathVariable("taskId") String taskId) {
        TaskMaterial taskMaterial = taskMaterialService.selectByTaskId(taskId);
        return JsonResult.success(taskMaterial);
    }
}
