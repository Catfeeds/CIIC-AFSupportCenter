package com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.host.controller;


import com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.api.dto.statement.SsMonthEmpChangeDTO;
import com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.api.dto.statement.SsMonthEmpChangeDetailDTO;
import com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.bo.SsMonthEmpChangeBO;
import com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.bo.SsMonthEmpChangeDetailBO;
import com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.business.ISsMonthEmpChangeDetailService;
import com.ciicsh.gto.afsupportcenter.util.CommonTransform;
import com.ciicsh.gto.afsupportcenter.util.aspect.log.Log;
import com.ciicsh.gto.afsupportcenter.util.web.controller.BasicController;
import com.ciicsh.gto.afsupportcenter.util.web.response.JsonResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * 雇员月度变更表明细
该表结果有可能需要调整 前端控制器
 * </p>
 *
 * @author HuangXing
 * @since 2017-12-01
 */
@RestController
@RequestMapping("/api/soccommandservice/ssMonthEmpChangeDetail")
public class SsMonthEmpChangeDetailController  extends BasicController<ISsMonthEmpChangeDetailService> {
    /**
     * <p>Description: 社保汇总明细数据展示</p>
     *
     * @author wengxk
     * @date 2017-12-13
     * @param ssMonthEmpChangeDTO 社保总汇检索条件
     * @return  JsonResult<SsMonthEmpChangeBO>
     */
    @Log("社保汇总明细数据展示")
    @PostMapping("/showMonthEmpChangeDetail")
    public JsonResult<List<SsMonthEmpChangeDetailDTO>> showMonthEmpChangeDetail(SsMonthEmpChangeDTO ssMonthEmpChangeDTO) {

        //转换格式
        SsMonthEmpChangeBO ssMonthEmpChangeBO = CommonTransform.convertToEntity(ssMonthEmpChangeDTO,SsMonthEmpChangeBO.class);
        List<SsMonthEmpChangeDetailBO> resultBoList = business.showMonthEmpChangeDetailByStatementId(ssMonthEmpChangeBO);

        //转换格式
        List<SsMonthEmpChangeDetailDTO> resultDtoList = CommonTransform.convertToDTOs(resultBoList,SsMonthEmpChangeDetailDTO.class);
        JsonResult<List<SsMonthEmpChangeDetailDTO>> result = new JsonResult<>();
        result.setData(resultDtoList);

        return result;
    }
}

