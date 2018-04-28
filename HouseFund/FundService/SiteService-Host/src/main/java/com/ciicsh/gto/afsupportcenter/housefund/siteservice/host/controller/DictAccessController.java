package com.ciicsh.gto.afsupportcenter.housefund.siteservice.host.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.toolkit.CollectionUtils;
import com.ciicsh.gto.afsupportcenter.housefund.fundservice.business.utils.CommonApiUtils;
import com.ciicsh.gto.afsupportcenter.housefund.fundservice.dao.HfComAccountPaymentBankMapper;
import com.ciicsh.gto.afsupportcenter.housefund.fundservice.entity.HfComAccountPaymentBank;
import com.ciicsh.gto.afsupportcenter.util.constant.DictUtil;
import com.ciicsh.gto.afsupportcenter.util.constant.SocialSecurityConst;
import com.ciicsh.gto.afsupportcenter.util.logService.LogContext;
import com.ciicsh.gto.afsupportcenter.util.logService.LogService;
import com.ciicsh.gto.afsupportcenter.util.web.controller.BasicController;
import com.ciicsh.gto.afsupportcenter.util.web.response.JsonResult;
import com.ciicsh.gto.afsupportcenter.util.web.response.JsonResultKit;
import com.ciicsh.gto.basicdataservice.api.dto.DicItemDTO;
import org.apache.commons.collections.KeyValue;
import org.apache.commons.collections.keyvalue.DefaultKeyValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/fundcommandservice/DictAccess")
public class DictAccessController extends BasicController<CommonApiUtils> {

    @Autowired
    LogService logService;
    @Autowired
    HfComAccountPaymentBankMapper hfComAccountPaymentBankMapper;

    @PostConstruct
    private void dictInit() {
        initDictUtil();
    }

    private void initDictUtil() {
        List<DicItemDTO> dictItemList;
        try {
            dictItemList = business.listByDicId(DictUtil.DICT_ID_HF_LOCAL_TASK_CATEGORY);
            Map<String, String> hfLocalTaskCategoryMap = new LinkedHashMap<>();
            dictItemList.stream().forEach((d) -> hfLocalTaskCategoryMap.put(d.getDicItemValue(), d.getDicItemText()));
            DictUtil.getInstance().putDictByTypeValue(DictUtil.TYPE_VALUE_HF_LOCAL_TASK_CATEGORY, hfLocalTaskCategoryMap, false);

            dictItemList = business.listByDicId(DictUtil.DICT_ID_SOCIAL_SECURITY_ACCOUNT_TYPE);
            Map<String, String> accountTypeMap = new LinkedHashMap<>();
            dictItemList.stream().forEach((d) -> accountTypeMap.put(d.getDicItemValue(), d.getDicItemText()));
            DictUtil.getInstance().putDictByTypeValue(DictUtil.TYPE_VALUE_SOCIAL_SECURITY_ACCOUNT_TYPE, accountTypeMap, false);

            dictItemList = business.listByDicId(DictUtil.DICT_ID_TASK_PROCESS_STATUS);
            Map<String, String> taskProcessStatusMap = new LinkedHashMap<>();
            dictItemList.stream().forEach((d) -> taskProcessStatusMap.put(d.getDicItemValue(), d.getDicItemText()));
            DictUtil.getInstance().putDictByTypeValue(DictUtil.TYPE_VALUE_TASK_PROCESS_STATUS, taskProcessStatusMap, false);

            dictItemList = business.listByDicId(DictUtil.DICT_ID_SOCIAL_SECURITY_STATUS);
            Map<String, String> hfStatusMap = new LinkedHashMap<>();
            dictItemList.stream().forEach((d) -> hfStatusMap.put(d.getDicItemValue(), d.getDicItemText()));
            DictUtil.getInstance().putDictByTypeValue(DictUtil.TYPE_VALUE_SOCIAL_SECURITY_STATUS, hfStatusMap, false);

            DictUtil.getInstance().putDictByTypeValue(SocialSecurityConst.PROCESS_PERIOD_KEY, SocialSecurityConst.PROCESS_PERIOD_MAP, false);
            DictUtil.getInstance().putDictByTypeValue(SocialSecurityConst.FUND_TYPE_KEY, SocialSecurityConst.FUND_TYPE_MAP, false);
//            DictUtil.getInstance().putDictByTypeValue(SocialSecurityConst.PAY_BANK_KEY, SocialSecurityConst.PAY_BANK_MAP, false);
            DictUtil.getInstance().putDictByTypeValue(SocialSecurityConst.COM_PAYMENT_WAY_KEY, SocialSecurityConst.COM_PAYMENT_WAY_MAP, false);
            DictUtil.getInstance().putDictByTypeValue(SocialSecurityConst.COM_UKEY_STORE_KEY, SocialSecurityConst.COM_UKEY_STORE_MAP, false);
            DictUtil.getInstance().putDictByTypeValue(SocialSecurityConst.OPERATION_REMIND_KEY, SocialSecurityConst.OPERATION_REMIND_MAP, false);
            DictUtil.getInstance().putDictByTypeValue(SocialSecurityConst.REPAIR_REASON_KEY, SocialSecurityConst.REPAIR_REASON_MAP, false);
            DictUtil.getInstance().putDictByTypeValue(SocialSecurityConst.COM_ACCOUNT_STATE_KEY, SocialSecurityConst.COM_ACCOUNT_STATE_MAP, false);
            DictUtil.getInstance().putDictByTypeValue(SocialSecurityConst.HANDLE_STATUS_KEY, SocialSecurityConst.HANDLE_STATUS_MAP, false);
            DictUtil.getInstance().putDictByTypeValue(SocialSecurityConst.REMIT_WAY_KEY, SocialSecurityConst.REMIT_WAY_MAP, false);
            DictUtil.getInstance().putDictByTypeValue(SocialSecurityConst.PAYMENT_TYPE_KEY, SocialSecurityConst.PAYMENT_TYPE_MAP, false);
            DictUtil.getInstance().putDictByTypeValue(SocialSecurityConst.EMP_ARCHIVE_STATUS, SocialSecurityConst.EMP_ARCHIVE_STATUS_MAP, false);
//            DictUtil.getInstance().putDictByTypeValue(SocialSecurityConst.HF_TASK_CATEGORY_KEY, SocialSecurityConst.HF_TASK_CATEGORY_MAP, false);
        } catch (Exception e) {
            LogContext logContext = LogContext.of().setTitle("上海公积金字典项及常量项")
                .setTextContent("加载字典项（访问字典公共接口）或常量项失败")
                .setExceptionContent(e);
            logService.error(logContext);
        }
    }

    /**
     * 获取本页面需要使用的字典数据
     * @return
     */
    @RequestMapping("/getDictData")
    public JsonResult<Map<String, List<?>>> getDictData() {
        Map<String, List<?>> map = DictUtil.getInstance().getDictItemList();
        if (map.size() == 0) {
            initDictUtil();
        }
        map.put(SocialSecurityConst.FUND_OUT_UNIT_KEY, SocialSecurityConst.FUND_OUT_UNIT_LIST);
        getPaymentBankMap(map);
        return JsonResultKit.of(map);
    }

    private void getPaymentBankMap(Map<String, List<?>> existsMap) {
        Wrapper<HfComAccountPaymentBank> wrapper = new EntityWrapper<>();
        wrapper.eq("is_active", 1);
        wrapper.orderBy("payment_bank_code");
        List<HfComAccountPaymentBank> hfComAccountPaymentBankList = hfComAccountPaymentBankMapper.selectList(wrapper);

        if (CollectionUtils.isNotEmpty(hfComAccountPaymentBankList)) {
            List<KeyValue> exists = (List<KeyValue>) existsMap.get(SocialSecurityConst.PAY_BANK_KEY);
            if (exists != null) {
                int size = exists.size();
                if (hfComAccountPaymentBankList.size() == size) {
                    return;
                }
                exists.clear();
            } else {
                exists = new ArrayList<>();
                existsMap.put(SocialSecurityConst.PAY_BANK_KEY, exists);
            }

            Map<String, String> map = new HashMap<>();
            for (HfComAccountPaymentBank hfComAccountPaymentBank : hfComAccountPaymentBankList) {
                String key = String.valueOf(hfComAccountPaymentBank.getPaymentBankCode());
                map.put(key, hfComAccountPaymentBank.getPaymentBankValue());
                exists.add(new DefaultKeyValue(key, hfComAccountPaymentBank.getPaymentBankValue()));
            }
            DictUtil.getInstance().putDictByTypeValue(SocialSecurityConst.PAY_BANK_KEY, map, true);
        } else {
            DictUtil.getInstance().putDictByTypeValue(SocialSecurityConst.PAY_BANK_KEY, null, true);
        }
    }
}
