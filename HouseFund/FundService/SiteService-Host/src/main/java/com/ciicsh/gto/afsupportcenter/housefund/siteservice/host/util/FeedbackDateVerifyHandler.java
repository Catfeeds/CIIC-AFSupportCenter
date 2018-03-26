package com.ciicsh.gto.afsupportcenter.housefund.siteservice.host.util;

import cn.afterturn.easypoi.excel.entity.result.ExcelVerifyHanlderResult;
import cn.afterturn.easypoi.handler.inter.IExcelModel;
import cn.afterturn.easypoi.handler.inter.IExcelVerifyHandler;
import com.ciicsh.gto.afsupportcenter.housefund.fundservice.bo.transfer.EmpTaskTransferBo;
import com.ciicsh.gto.afsupportcenter.util.logService.LogContext;
import com.ciicsh.gto.afsupportcenter.util.logService.LogService;
import org.springframework.beans.factory.annotation.Autowired;

import java.lang.reflect.Field;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class FeedbackDateVerifyHandler implements IExcelVerifyHandler<IExcelModel> {

    @Autowired
    private LogService logService;
    private List<EmpTaskTransferBo> empTaskTransferBoList;
    private Set<String> employeeIdSet;

    public FeedbackDateVerifyHandler(List<EmpTaskTransferBo> empTaskTransferBoList) {
        this.empTaskTransferBoList = empTaskTransferBoList;
        this.employeeIdSet = new HashSet<>();
    }

    @Override
    public ExcelVerifyHanlderResult verifyHandler(IExcelModel model) {
        ExcelVerifyHanlderResult rtn = new ExcelVerifyHanlderResult(true);
        if (model.getErrorMsg() == null) {
            for (Field field : model.getClass().getDeclaredFields()) {
                String fieldName = field.getName();
                String fieldValue;
                try {
                    field.setAccessible(true);
                    fieldValue = String.valueOf(field.get(model));

                    if ("employeeId".equals(fieldName)) {
                        if (this.employeeIdSet.contains(fieldValue)) {
                            rtn.setSuccess(false);
                            rtn.setMsg("雇员编号重复");
                        } else {
                            if (this.empTaskTransferBoList.stream().filter(e -> fieldValue.equals(e.getEmployeeId()) || e.getFeedbackDate() != null).count() == 0) {
                                rtn.setSuccess(false);
                                rtn.setMsg("雇员编号不存在或回单日期已存在");
                            }
                            this.employeeIdSet.add(fieldValue);
                        }
                    }

                    if ("feedbackDate".equals(fieldName)) {
                        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
                        try {
                            simpleDateFormat.parse(fieldValue);
                        } catch (ParseException e) {
                            rtn.setSuccess(false);
                            rtn.setMsg("回单日期格式不正确（正确格式：YYYY-MM-DD）");
                        }
                    }
                } catch (IllegalAccessException e) {
                    LogContext logContext = LogContext.of().setTitle("文件上传")
                        .setTextContent("上传文件校验并向Model中设值时出现异常(ErrorMsg不存在时)")
                        .setExceptionContent(e);
                    logService.error(logContext);
                }
            }
        }
        return rtn;
    }
}
