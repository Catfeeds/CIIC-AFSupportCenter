package com.ciicsh.gto.afsupportcenter.housefund.siteservice.host.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.toolkit.CollectionUtils;
import com.ciicsh.gto.afsupportcenter.housefund.fundservice.business.utils.CommonApiUtils;
import com.ciicsh.gto.afsupportcenter.housefund.fundservice.dao.HfComAccountPaymentBankMapper;
import com.ciicsh.gto.afsupportcenter.housefund.fundservice.entity.HfComAccountPaymentBank;
import com.ciicsh.gto.afsupportcenter.util.aspect.log.Log;
import com.ciicsh.gto.afsupportcenter.util.constant.DictUtil;
import com.ciicsh.gto.afsupportcenter.util.constant.SocialSecurityConst;
import com.ciicsh.gto.afsupportcenter.util.logService.LogContext;
import com.ciicsh.gto.afsupportcenter.util.logService.LogService;
import com.ciicsh.gto.afsupportcenter.util.web.controller.BasicController;
import com.ciicsh.gto.afsupportcenter.util.web.response.JsonResult;
import com.ciicsh.gto.afsupportcenter.util.web.response.JsonResultKit;
import com.ciicsh.gto.basicdataservice.api.dto.DicItemDTO;
import org.apache.commons.collections.keyvalue.DefaultKeyValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import java.util.*;

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
//            DictUtil.getInstance().putDictByTypeValue(SocialSecurityConst.HF_TASK_CATEGORY_KEY, SocialSecurityConst.HF_TASK_CATEGORY_MAP, false);
            Map<String, List<?>> map = DictUtil.getInstance().getDictItemList();
            map.put(SocialSecurityConst.FUND_OUT_UNIT_KEY, SocialSecurityConst.FUND_OUT_UNIT_LIST);
        } catch (Exception e) {
            LogContext logContext = LogContext.of().setTitle("上海公积金字典项及常量项")
                .setTextContent("加载字典项（访问字典公共接口）或常量项失败")
                .setExceptionContent(e);
            logService.error(logContext);
        }
    }

    @RequestMapping("/getDictData")
    @Log("获取本页面需要使用的字典数据")
    public JsonResult<Map<String, List<?>>> getDictData() {
        Map<String, List<?>> map = DictUtil.getInstance().getDictItemList();
        if (map.size() == 0) {
            initDictUtil();
        }
        getPaymentBankMap(map);
        return JsonResultKit.of(map);
    }

    private void getPaymentBankMap(Map<String, List<?>> map) {
        int size = 0;
        List<?> exists = map.get(SocialSecurityConst.PAY_BANK_KEY);
        if (exists != null) {
            size = exists.size();
        }
        Wrapper<HfComAccountPaymentBank> wrapper = new EntityWrapper<>();
        wrapper.eq("is_active", 1);
        wrapper.orderBy("payment_bank_code");
        List<HfComAccountPaymentBank> hfComAccountPaymentBankList = hfComAccountPaymentBankMapper.selectList(wrapper);

        if (CollectionUtils.isNotEmpty(hfComAccountPaymentBankList)) {
            if (hfComAccountPaymentBankList.size() == size) {
                return;
            }
            List<DefaultKeyValue> defaultKeyValues = new ArrayList<>(hfComAccountPaymentBankList.size());

            hfComAccountPaymentBankList.stream().forEach(e -> defaultKeyValues.add(new DefaultKeyValue(String.valueOf(e.getPaymentBankCode()), e.getPaymentBankValue())));
            map.put(SocialSecurityConst.PAY_BANK_KEY, defaultKeyValues);
        } else {
            map.put(SocialSecurityConst.PAY_BANK_KEY, null);
        }
    }
}
