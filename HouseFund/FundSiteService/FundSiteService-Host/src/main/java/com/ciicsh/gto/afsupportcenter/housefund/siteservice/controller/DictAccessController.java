package com.ciicsh.gto.afsupportcenter.housefund.siteservice.controller;

import com.ciicsh.gto.afsupportcenter.housefund.siteservice.business.utils.CommonApiUtils;
import com.ciicsh.gto.afsupportcenter.util.aspect.log.Log;
import com.ciicsh.gto.afsupportcenter.util.constant.DictUtil;
import com.ciicsh.gto.afsupportcenter.util.constant.SocialSecurityConst;
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
@RequestMapping("/api/fundcommandservice/DictAccess")
public class DictAccessController extends BasicController<CommonApiUtils> {

    @PostConstruct
    private void dictInit() {
        List<DicItemDTO> dictItemList;
        try {
            dictItemList = business.listByDicId(DictUtil.DICT_ID_HF_LOCAL_TASK_CATEGORY);
            Map<String, String> hfLocalTaskCategoryMap = new HashMap<>();
            dictItemList.stream().forEach((d) -> hfLocalTaskCategoryMap.put(d.getDicItemValue(), d.getDicItemText()));
            DictUtil.getInstance().putDictByTypeValue(DictUtil.TYPE_VALUE_HF_LOCAL_TASK_CATEGORY, hfLocalTaskCategoryMap, false);

            dictItemList = business.listByDicId(DictUtil.DICT_ID_SOCIAL_SECURITY_ACCOUNT_TYPE);
            Map<String, String> accountTypeMap = new HashMap<>();
            dictItemList.stream().forEach((d) -> accountTypeMap.put(d.getDicItemValue(), d.getDicItemText()));
            DictUtil.getInstance().putDictByTypeValue(DictUtil.TYPE_VALUE_SOCIAL_SECURITY_ACCOUNT_TYPE, accountTypeMap, false);

            dictItemList = business.listByDicId(DictUtil.DICT_ID_TASK_PROCESS_STATUS);
            Map<String, String> taskProcessStatusMap = new HashMap<>();
            dictItemList.stream().forEach((d) -> accountTypeMap.put(d.getDicItemValue(), d.getDicItemText()));
            DictUtil.getInstance().putDictByTypeValue(DictUtil.TYPE_VALUE_TASK_PROCESS_STATUS, taskProcessStatusMap, false);

            DictUtil.getInstance().putDictByTypeValue(SocialSecurityConst.PROCESS_PERIOD_KEY, SocialSecurityConst.PROCESS_PERIOD_MAP, false);
            DictUtil.getInstance().putDictByTypeValue(SocialSecurityConst.FUND_TYPE_KEY, SocialSecurityConst.FUND_TYPE_MAP, false);
            DictUtil.getInstance().putDictByTypeValue(SocialSecurityConst.PAY_BANK_KEY, SocialSecurityConst.PAY_BANK_MAP, false);
            DictUtil.getInstance().putDictByTypeValue(SocialSecurityConst.COM_PAYMENT_WAY_KEY, SocialSecurityConst.COM_PAYMENT_WAY_MAP, false);
            DictUtil.getInstance().putDictByTypeValue(SocialSecurityConst.COM_UKEY_STORE_KEY, SocialSecurityConst.COM_UKEY_STORE_MAP, false);
            DictUtil.getInstance().putDictByTypeValue(SocialSecurityConst.OPERATION_REMIND_KEY, SocialSecurityConst.OPERATION_REMIND_MAP, false);
            DictUtil.getInstance().putDictByTypeValue(SocialSecurityConst.REPAIR_REASON_KEY, SocialSecurityConst.REPAIR_REASON_MAP, false);
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
