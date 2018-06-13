package com.ciicsh.gto.afsupportcenter.socialsecurity.siteservice.host.util;

import cn.afterturn.easypoi.excel.entity.result.ExcelVerifyHanlderResult;
import cn.afterturn.easypoi.handler.inter.IExcelModel;
import cn.afterturn.easypoi.handler.inter.IExcelVerifyHandler;
import com.ciicsh.gto.afsupportcenter.util.logService.LogApiUtil;
import com.ciicsh.gto.afsupportcenter.util.logService.LogMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

public class MyExcelVerifyHandler implements IExcelVerifyHandler<IExcelModel> {
    private LogApiUtil logApiUtil;

    private Map<String, Integer> fieldLengthMap;
    private Map<String, Object> setValueMap;
    private List<String> skipFields;
    private Map<String, Object> verifyConfigMap;
    private final static int DEFAULT_LIMIT_LENGTH = 30;
    private final static String ORDER_NUM = "orderNum";
    private int orderNum;

    /**
     * @param fieldLengthMap  特殊限制长度字段Map(特殊限制长度至少大于3)
     * @param setValueMap     需要设值的字段Map
     * @param skipFields      跳过字段名称
     * @param verifyConfigMap 需要校验的字段配置Map
     */
    public MyExcelVerifyHandler(LogApiUtil logApiUtil, Map<String, Integer> fieldLengthMap, Map<String, Object> setValueMap, List<String> skipFields, Map<String, Object> verifyConfigMap) {
        this.logApiUtil = logApiUtil;
        this.fieldLengthMap = fieldLengthMap;
        this.setValueMap = setValueMap;
        this.skipFields = skipFields;
        this.verifyConfigMap = verifyConfigMap;
    }

    /**
     * 将大于数据库长度的字段进行裁剪处理
     *
     * @param model
     */
    @Override
    public ExcelVerifyHanlderResult verifyHandler(IExcelModel model) {
        ExcelVerifyHanlderResult rtn = new ExcelVerifyHanlderResult(true);

        if (model.getErrorMsg() != null) {
            for (Field field : model.getClass().getDeclaredFields()) {
                String fieldName = field.getName();
                if (skipFields != null && skipFields.contains(fieldName)) {
                    continue;
                }
                String fieldValue;
                try {
                    field.setAccessible(true);
                    fieldValue = String.valueOf(field.get(model));

                    if (setValueMap != null && setValueMap.containsKey(fieldName)) {
                        Object value = setValueMap.get(fieldName);
                        field.set(model, value);
                        continue;
                    }

                    if (ORDER_NUM.equals(fieldName)) {
                        field.set(model, orderNum++);
                        continue;
                    }

                    if (verifyConfigMap != null && verifyConfigMap.containsKey(fieldName)) {
                        rtn.setSuccess(verifyConfigMap.get(fieldName).equals(fieldValue));
                        rtn.setMsg(String.valueOf(verifyConfigMap.get("msg")));
                        continue;
                    }

                    if (StringUtils.isEmpty(fieldValue)) {
                        continue;
                    }

                    if (fieldLengthMap != null) {
                        Integer fieldLimitLength = fieldLengthMap.get(fieldName);
                        if (fieldLimitLength != null && fieldValue.length() > fieldLimitLength) {
                            fieldValue = fieldValue.substring(0, fieldLimitLength - 3) + "...";
                            field.set(model, fieldValue);
                            continue;
                        }
                    }

                    if (fieldValue.length() <= DEFAULT_LIMIT_LENGTH) {
                        continue;
                    }
                    fieldValue = fieldValue.substring(0, DEFAULT_LIMIT_LENGTH - 3) + "...";
                    field.set(model, fieldValue);
                } catch (IllegalAccessException e) {
                    LogMessage logMessage = LogMessage.create().setTitle("文件上传")
                        .setContent("上传文件校验并向Model中设值时出现异常(ErrorMsg存在时)" + e.getMessage());
                    logApiUtil.error(logMessage);
                }
            }
        } else {
            for (Field field : model.getClass().getDeclaredFields()) {
                String fieldName = field.getName();
                if (skipFields != null && skipFields.contains(fieldName)) {
                    continue;
                }
                String fieldValue;
                try {
                    field.setAccessible(true);
                    fieldValue = String.valueOf(field.get(model));

                    if (setValueMap != null && setValueMap.containsKey(fieldName)) {
                        Object value = setValueMap.get(fieldName);
                        field.set(model, value);
                        continue;
                    }

                    if (ORDER_NUM.equals(fieldName)) {
                        field.set(model, orderNum++);
                        continue;
                    }

                    if (verifyConfigMap != null && verifyConfigMap.containsKey(fieldName)) {
                        rtn.setSuccess(verifyConfigMap.get(fieldName).equals(fieldValue));
                        rtn.setMsg(String.valueOf(verifyConfigMap.get("msg")));
                        continue;
                    }
                } catch (IllegalAccessException e) {
                    LogMessage logMessage = LogMessage.create().setTitle("文件上传")
                        .setContent("上传文件校验并向Model中设值时出现异常(ErrorMsg不存在时)" + e.getMessage());
                    logApiUtil.error(logMessage);
                }
            }
        }

        return rtn;
    }
}
