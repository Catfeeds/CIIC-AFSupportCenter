package com.ciicsh.gto.afsupportcenter.util.constant;

import org.apache.commons.collections.KeyValue;
import org.apache.commons.collections.keyvalue.DefaultKeyValue;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class DictUtil {
    public static final String DICT_ITEM_ID_SOCIAL = "DIT00136";
    public static final String DICT_ITEM_ID_FUND = "DIT00137";
    public static final String DICT_ITEM_ID_FUND_BASIC = "DIT00057";
    public static final String DICT_ITEM_ID_FUND_ADDED = "DIT00058";

    public static final String DICT_ID_SOC_LOCAL_TASK_CATEGORY = "DIC00007";
    public static final String DICT_ID_SOCIAL_SECURITY_ACCOUNT_TYPE = "DIC00062";
    public static final String DICT_ID_SOCIAL_SECURITY_STATUS = "DIC00063";
    public static final String DICT_ID_SOCIAL_SECURITY_EMPLOYEE_CLASSIFY = "DIC00064";
    public static final String DICT_ID_TASK_PROCESS_STATUS = "DIC00065";
    public static final String DICT_ID_HF_LOCAL_TASK_CATEGORY = "DIC00066";
    public static final String TYPE_VALUE_SOC_LOCAL_TASK_CATEGORY = "SOCLocalTaskCategory";
    public static final String TYPE_VALUE_SOCIAL_SECURITY_ACCOUNT_TYPE = "SocialSecurityAccountType";
    public static final String TYPE_VALUE_SOCIAL_SECURITY_STATUS = "SocialSecurityStatus";
    public static final String TYPE_VALUE_SOCIAL_SECURITY_EMPLOYEE_CLASSIFY = "SocialSecurityEmployeeClassify";
    public static final String TYPE_VALUE_TASK_PROCESS_STATUS = "TaskProcessStatus";
    public static final String TYPE_VALUE_HF_LOCAL_TASK_CATEGORY = "HFLocalTaskCategory";

    private static DictUtil instance;
    private static ConcurrentMap<String, Map<String, String>> dict;

    private DictUtil() {
        dict = new ConcurrentHashMap<>();
    }

    public static synchronized DictUtil getInstance() {
        if (instance == null) {
            instance = new DictUtil();
        }
        return instance;
    }

    public void putDictByTypeValue(String typeValue, Map<String, String> dictItemMap, boolean isReplace) {
        if (isReplace || !dict.containsKey(typeValue)) {
            dict.put(typeValue, dictItemMap);
        }
    }

    public Map<String, String> getDictByTypeValue(String typeValue) {
        return dict.get(typeValue);
    }

    public String getTextByItemValueAndTypeValue(String itemValue, String typeValue, boolean keepWhenNotFound) {
        Map<String, String> map = dict.get(typeValue);
        if (map != null) {
            return map.get(itemValue);
        }
        if (keepWhenNotFound) {
            return itemValue;
        }
        return null;
    }

    public String getValueByItemTextAndTypeValue(String itemText, String typeValue, boolean keepWhenNotFound) {
        Map<String, String> map = dict.get(typeValue);
        if (map != null) {
            Optional<Map.Entry<String, String>> optional = map.entrySet().stream().filter((e) -> itemText.equals(e.getValue())).findFirst();
            if (optional.isPresent()) {
                return optional.get().getKey();
            }
        }
        if (keepWhenNotFound) {
            return itemText;
        }
        return null;
    }

    public Map<String, List<?>> getDictItemList() {
        Map<String, List<?>> rtn = new HashMap<>();

        if (dict.size() > 0) {
            dict.entrySet().stream().forEach(e -> {
                String key = e.getKey();
                Map<String, String> map = e.getValue();

                if (map != null && map.size() > 0) {
                    List<KeyValue> list = new ArrayList<>();
                    map.entrySet().stream().forEach(t -> list.add(new DefaultKeyValue(t.getKey(), t.getValue())));
                    rtn.put(key, list);
                }
            });
        }
        return rtn;
    }
}
