package com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.host.controller;

import com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.api.CommonApiUtils;
import com.ciicsh.gto.afsupportcenter.util.aspect.log.Log;
import com.ciicsh.gto.afsupportcenter.util.constant.DictUtil;
import com.ciicsh.gto.afsupportcenter.util.web.controller.BasicController;
import com.ciicsh.gto.afsupportcenter.util.web.response.JsonResult;
import com.ciicsh.gto.afsupportcenter.util.web.response.JsonResultKit;
import com.ciicsh.gto.basicdataservice.api.dto.DicItemDTO;
import org.apache.commons.collections.KeyValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/soccommandservice/DictAccess")
public class DictAccessController extends BasicController<CommonApiUtils> {

    @PostConstruct
    private void dictInit() {
        List<DicItemDTO> dictItemList;
        try {
            dictItemList = business.listByDicId(DictUtil.DICT_ID_SOCIAL_SECURITY_ACCOUNT_TYPE);
            Map<String, String> accountTypeMap = new HashMap<>();
            dictItemList.stream().forEach((d) -> accountTypeMap.put(d.getDicItemValue(), d.getDicItemText()));
            DictUtil.getInstance().putDictByTypeValue(DictUtil.TYPE_VALUE_SOCIAL_SECURITY_ACCOUNT_TYPE, accountTypeMap, false);

            dictItemList = business.listByDicId(DictUtil.DICT_ID_SOCIAL_SECURITY_STATUS);
            Map<String, String> statusMap = new HashMap<>();
            dictItemList.stream().forEach((d) -> statusMap.put(d.getDicItemValue(), d.getDicItemText()));
            DictUtil.getInstance().putDictByTypeValue(DictUtil.TYPE_VALUE_SOCIAL_SECURITY_STATUS, statusMap, false);

            dictItemList = business.listByDicId(DictUtil.DICT_ID_SOCIAL_SECURITY_EMPLOYEE_CLASSIFY);
            Map<String, String> employeeClassifyMap = new HashMap<>();
            dictItemList.stream().forEach((d) -> employeeClassifyMap.put(d.getDicItemValue(), d.getDicItemText()));
            DictUtil.getInstance().putDictByTypeValue(DictUtil.TYPE_VALUE_SOCIAL_SECURITY_EMPLOYEE_CLASSIFY, employeeClassifyMap, false);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @RequestMapping("/getDictData")
    @Log("获取本页面需要使用的字典数据")
    public JsonResult<Map<String, List<KeyValue>>> getDictData() {
        return JsonResultKit.of(DictUtil.getInstance().getDictItemList());
    }
}
