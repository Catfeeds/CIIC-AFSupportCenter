package com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.business;


import com.baomidou.mybatisplus.service.IService;
import com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.entity.SsEmpTaskFront;

/**
 * <p>
 * 雇员任务单前道传递信息,创建任务单的同时，就要把前道的传递信息复制到这表，当前表复制前道cmy_af_emp_socia 服务类
 * </p>
 *
 * @author xsj
 * @since 2018-01-08
 */
public interface ISsEmpTaskFrontService extends IService<SsEmpTaskFront> {

}
