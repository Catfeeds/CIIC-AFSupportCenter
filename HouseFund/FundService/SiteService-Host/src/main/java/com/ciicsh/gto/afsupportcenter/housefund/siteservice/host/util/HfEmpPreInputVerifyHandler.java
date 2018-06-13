package com.ciicsh.gto.afsupportcenter.housefund.siteservice.host.util;

import cn.afterturn.easypoi.excel.entity.result.ExcelVerifyHanlderResult;
import cn.afterturn.easypoi.handler.inter.IExcelModel;
import cn.afterturn.easypoi.handler.inter.IExcelVerifyHandler;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.ciicsh.gto.afsupportcenter.housefund.fundservice.business.HfEmpPreInputService;
import com.ciicsh.gto.afsupportcenter.housefund.fundservice.entity.HfEmpPreInput;
import com.ciicsh.gto.afsupportcenter.util.logService.LogApiUtil;
import com.ciicsh.gto.afsupportcenter.util.logService.LogMessage;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.lang.reflect.Field;
import java.util.HashSet;
import java.util.Set;

public class HfEmpPreInputVerifyHandler implements IExcelVerifyHandler<IExcelModel> {

    private HfEmpPreInputService hfEmpPreInputService;
    private LogApiUtil logApiUtil;
    private Set<String> employeeIdSet;

    public HfEmpPreInputVerifyHandler(HfEmpPreInputService hfEmpPreInputService, LogApiUtil logApiUtil) {
        this.hfEmpPreInputService = hfEmpPreInputService;
        this.logApiUtil = logApiUtil;
        this.employeeIdSet = new HashSet<>();
    }

    @Override
    public ExcelVerifyHanlderResult verifyHandler(IExcelModel model) {
        ExcelVerifyHanlderResult rtn = new ExcelVerifyHanlderResult(true);
        if (model.getErrorMsg() == null) {
            HfEmpPreInput hfEmpPreInput = null;
            HfEmpPreInput existEmpPreInput = null;
            boolean isAccountEmpty = false;

            for (Field field : model.getClass().getDeclaredFields()) {
                String fieldName = field.getName();
                try {
                    field.setAccessible(true);
                    String fieldValue = String.valueOf(field.get(model));

                    if ("employeeId".equals(fieldName)) {
                        if (this.employeeIdSet.contains(fieldValue)) {
                            rtn.setSuccess(false);
                            rtn.setMsg("雇员编号重复");
                            return rtn;
                        } else {
                            EntityWrapper<HfEmpPreInput> wrapper = new EntityWrapper<>();
                            wrapper.where("is_active = 1").and("employee_id = {0}", fieldValue);
                            HfEmpPreInput exist = hfEmpPreInputService.selectOne(wrapper);

                            if (exist == null) {
                                hfEmpPreInput = new HfEmpPreInput();
                                hfEmpPreInput.setEmployeeId(fieldValue);
                            } else {
                                if (StringUtils.isNotEmpty(exist.getHfEmpBasAccount()) &&
                                    StringUtils.isNotEmpty(exist.getHfEmpAddAccount())) {
                                    rtn.setSuccess(false);
                                    rtn.setMsg("该雇员基本公积金和补充公积金都已存在");
                                    return rtn;
                                }
                                existEmpPreInput = new HfEmpPreInput();
                                existEmpPreInput.setInputId(exist.getInputId());
                                existEmpPreInput.setHfEmpBasAccount(exist.getHfEmpBasAccount());
                                existEmpPreInput.setHfEmpAddAccount(exist.getHfEmpAddAccount());
                            }
                            this.employeeIdSet.add(fieldValue);
                        }
                    }

                    if ("employeeName".equals(fieldName)) {
                        if (StringUtils.isNotEmpty(fieldValue) && hfEmpPreInput != null) {
                            hfEmpPreInput.setEmployeeName(fieldValue);
                        }
                    }

                    if ("hfEmpBasAccount".equals(fieldName)) {
                        if (StringUtils.isEmpty(fieldValue) || "null".equals(fieldValue)) {
                            isAccountEmpty = true;
                        } else {
                            if (existEmpPreInput != null) {
                                if (StringUtils.isNotEmpty(existEmpPreInput.getHfEmpBasAccount())) {
                                    rtn.setSuccess(false);
                                    rtn.setMsg("该雇员基本公积金已存在，若更新补充公积金，请仅设定补充公积金");
                                    return rtn;
                                } else {
                                    existEmpPreInput.setHfEmpBasAccount(fieldValue);
                                }
                            } else if (hfEmpPreInput != null) {
                                hfEmpPreInput.setHfEmpBasAccount(fieldValue);
                            }
                        }
                    }

                    if ("hfEmpAddAccount".equals(fieldName)) {
                        boolean isAddEmpty = StringUtils.isEmpty(fieldValue) || "null".equals(fieldValue);

                        if (isAccountEmpty && isAddEmpty) {
                            rtn.setSuccess(false);
                            rtn.setMsg("基本公积金账号和补充公积金账号都为空");
                            return rtn;
                        } else if (!isAddEmpty) {
                            if (existEmpPreInput != null) {
                                if (StringUtils.isNotEmpty(existEmpPreInput.getHfEmpAddAccount())) {
                                    rtn.setSuccess(false);
                                    rtn.setMsg("该雇员补充公积金已存在，若更新基本公积金，请仅设定基本公积金");
                                    return rtn;
                                } else {
                                    existEmpPreInput.setHfEmpAddAccount(fieldValue);
                                }
                            } else if (hfEmpPreInput != null) {
                                hfEmpPreInput.setHfEmpAddAccount(fieldValue);
                            }
                        }
                    }

                    if ("addHfEmpPreInput".equals(fieldName) && hfEmpPreInput != null) {
                        field.set(model, hfEmpPreInput);
                    }

                    if ("modifyHfEmpPreInput".equals(fieldName) && existEmpPreInput != null) {
                        field.set(model, existEmpPreInput);
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
