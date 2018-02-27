package com.ciicsh.gto.afsupportcenter.socialsecurity.siteservice.host.controller;


import com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.bo.SsMonthEmpChangeBO;
import com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.business.SsMonthEmpChangeService;
import com.ciicsh.gto.afsupportcenter.util.CommonTransform;
import com.ciicsh.gto.afsupportcenter.util.aspect.log.Log;
import com.ciicsh.gto.afsupportcenter.util.web.controller.BasicController;
import com.ciicsh.gto.afsupportcenter.util.web.response.JsonResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * <p>
 * 雇员月度变更主表 前端控制器
 * </p>
 *
 * @author HuangXing
 * @since 2017-12-01
 */
@RestController
@RequestMapping("/api/soccommandservice/ssMonthEmpChange")
public class SsMonthEmpChangeController  extends BasicController<SsMonthEmpChangeService> {
    /**
     * <p>Description: 社保汇总基本数据查询</p>
     *
     * @author wengxk
     * @date 2017-12-13
     * @param ssMonthEmpChangeDTO 社保总汇检索条件
     * @return  JsonResult<SsMonthEmpChangeBO>
     */
    @Log("社保汇总基本数据查询")
    @PostMapping("/serachMonthEmpChange")
    public JsonResult<SsMonthEmpChangeBO> serachMonthEmpChange(SsMonthEmpChangeBO ssMonthEmpChangeDTO) {

        //转换格式
        com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.bo.SsMonthEmpChangeBO ssMonthEmpChangeBO = CommonTransform.convertToEntity(ssMonthEmpChangeDTO, com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.bo.SsMonthEmpChangeBO.class);

        com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.bo.SsMonthEmpChangeBO resultBO = business.serachMonthEmpChangeByStatementId(ssMonthEmpChangeBO);
        //转换格式
        SsMonthEmpChangeBO resultDto =  CommonTransform.convertToDTO(resultBO,SsMonthEmpChangeBO.class);
        JsonResult<SsMonthEmpChangeBO> result = new JsonResult<>();
        result.setData(resultDto);

        return result;
    }
}

