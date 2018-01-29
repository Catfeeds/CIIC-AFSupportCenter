package com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.host.controller;


import com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.business.SsEmpTaskFrontService;
import com.ciicsh.gto.afsupportcenter.util.web.controller.BasicController;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 雇员任务单前道传递信息,创建任务单的同时，就要把前道的传递信息复制到这表，当前表复制前道cmy_af_emp_socia 前端控制器
 * </p>
 */
@RestController
@RequestMapping("/api/soccommandservice/ssEmpTaskFront")
public class SsEmpTaskFrontController extends BasicController<SsEmpTaskFrontService> {

}

