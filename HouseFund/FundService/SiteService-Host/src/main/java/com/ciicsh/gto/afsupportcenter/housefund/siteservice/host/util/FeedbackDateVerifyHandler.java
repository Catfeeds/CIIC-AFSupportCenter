package com.ciicsh.gto.afsupportcenter.housefund.siteservice.host.util;

import cn.afterturn.easypoi.excel.entity.result.ExcelVerifyHanlderResult;
import cn.afterturn.easypoi.handler.inter.IExcelModel;
import cn.afterturn.easypoi.handler.inter.IExcelVerifyHandler;
import com.baomidou.mybatisplus.toolkit.CollectionUtils;
import com.ciicsh.gto.afsupportcenter.housefund.fundservice.bo.transfer.EmpTaskTransferBo;
import com.ciicsh.gto.afsupportcenter.util.logService.LogApiUtil;
import com.ciicsh.gto.afsupportcenter.util.logService.LogMessage;
import org.springframework.beans.factory.annotation.Autowired;

import java.lang.reflect.Field;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class FeedbackDateVerifyHandler implements IExcelVerifyHandler<IExcelModel> {

    @Autowired
    private LogApiUtil logApiUtil;
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
            List<EmpTaskTransferBo> list = null;
            for (Field field : model.getClass().getDeclaredFields()) {
                String fieldName = field.getName();
                try {
                    field.setAccessible(true);

                    if ("empTaskIds".equals(fieldName)) {
                        if (list != null) {
                            List<Long> empTaskIdList = new ArrayList<>(list.size());
                            for (EmpTaskTransferBo bo : list) {
                                empTaskIdList.add(bo.getEmpTaskId());
                            }
                            field.set(model, empTaskIdList);
                        }
                    }

                    String fieldValue = String.valueOf(field.get(model));

                    if ("employeeId".equals(fieldName)) {
                        if (this.employeeIdSet.contains(fieldValue)) {
                            rtn.setSuccess(false);
                            rtn.setMsg("雇员编号重复");
                        } else {
                            list = this.empTaskTransferBoList.stream()
                                .filter(e -> fieldValue.equals(e.getEmployeeId()) && e.getFeedbackDate() == null)
                                .collect(Collectors.toList());
                            if (CollectionUtils.isEmpty(list)) {
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
                            rtn.setMsg("回单日期格式不正确（请设置为文本类型，且正确格式：YYYY-MM-DD）");
                        }
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
