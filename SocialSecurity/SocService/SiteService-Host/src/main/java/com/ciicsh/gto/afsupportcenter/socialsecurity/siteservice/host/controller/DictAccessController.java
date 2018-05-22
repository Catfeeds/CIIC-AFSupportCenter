package com.ciicsh.gto.afsupportcenter.socialsecurity.siteservice.host.controller;

import com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.business.utils.CommonApiUtils;
import com.ciicsh.gto.afsupportcenter.util.constant.DictUtil;
import com.ciicsh.gto.afsupportcenter.util.logService.LogApiUtil;
import com.ciicsh.gto.afsupportcenter.util.logService.LogMessage;
import com.ciicsh.gto.afsupportcenter.util.web.controller.BasicController;
import com.ciicsh.gto.afsupportcenter.util.web.response.JsonResult;
import com.ciicsh.gto.afsupportcenter.util.web.response.JsonResultKit;
import com.ciicsh.gto.basicdataservice.api.dto.DicItemDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/soccommandservice/DictAccess")
public class DictAccessController extends BasicController<CommonApiUtils> {

    @Autowired
    LogApiUtil logApiUtil;

    @PostConstruct
    private void dictInit() {
        initDictUtil();
    }

    private void initDictUtil() {
        List<DicItemDTO> dictItemList;
        try {

            dictItemList = business.listByDicId(DictUtil.DICT_ID_SOC_LOCAL_TASK_CATEGORY);
            Map<String, String> ssLocalTaskCategoryMap = new LinkedHashMap<>();
            dictItemList.stream().forEach((d) -> ssLocalTaskCategoryMap.put(d.getDicItemValue(), d.getDicItemText()));
            DictUtil.getInstance().putDictByTypeValue(DictUtil.TYPE_VALUE_SOC_LOCAL_TASK_CATEGORY, ssLocalTaskCategoryMap, false);

            dictItemList = business.listByDicId(DictUtil.DICT_ID_SOCIAL_SECURITY_ACCOUNT_TYPE);
            Map<String, String> accountTypeMap = new LinkedHashMap<>();
            dictItemList.stream().forEach((d) -> accountTypeMap.put(d.getDicItemValue(), d.getDicItemText()));
            DictUtil.getInstance().putDictByTypeValue(DictUtil.TYPE_VALUE_SOCIAL_SECURITY_ACCOUNT_TYPE, accountTypeMap, false);

            dictItemList = business.listByDicId(DictUtil.DICT_ID_SOCIAL_SECURITY_STATUS);
            Map<String, String> statusMap = new LinkedHashMap<>();
            dictItemList.stream().forEach((d) -> statusMap.put(d.getDicItemValue(), d.getDicItemText()));
            DictUtil.getInstance().putDictByTypeValue(DictUtil.TYPE_VALUE_SOCIAL_SECURITY_STATUS, statusMap, false);

            dictItemList = business.listByDicId(DictUtil.DICT_ID_SOCIAL_SECURITY_EMPLOYEE_CLASSIFY);
            Map<String, String> employeeClassifyMap = new LinkedHashMap<>();
            dictItemList.stream().forEach((d) -> employeeClassifyMap.put(d.getDicItemValue(), d.getDicItemText()));
            DictUtil.getInstance().putDictByTypeValue(DictUtil.TYPE_VALUE_SOCIAL_SECURITY_EMPLOYEE_CLASSIFY, employeeClassifyMap, false);

            dictItemList = business.listByDicId(DictUtil.DICT_ID_WELFARE_UNIT);
            Map<String, String> welfareUnitMap = new LinkedHashMap<>();
            dictItemList.stream().forEach((d) -> welfareUnitMap.put(d.getDicItemValue(), d.getDicItemText()));
            DictUtil.getInstance().putDictByTypeValue(DictUtil.TYPE_VALUE_WELFARE_UNIT, welfareUnitMap, false);
        } catch (Exception e) {
            LogMessage logMessage = LogMessage.create().setTitle("上海社保字典项及常量项")
                .setContent("加载字典项（访问字典公共接口）或常量项失败" + e.getMessage());
            logApiUtil.error(logMessage);
        }
    }

    /**
     * 获取本页面需要使用的字典数据
     *
     * @return
     */
    @RequestMapping("/getDictData")
    public JsonResult<Map<String, List<?>>> getDictData() {
        Map<String, List<?>> map = DictUtil.getInstance().getDictItemList();
        if (map.size() == 0) {
            initDictUtil();
        }
        return JsonResultKit.of(map);
    }
}
