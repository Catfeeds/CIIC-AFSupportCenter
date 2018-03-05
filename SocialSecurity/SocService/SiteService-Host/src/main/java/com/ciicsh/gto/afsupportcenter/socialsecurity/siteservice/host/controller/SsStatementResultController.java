package com.ciicsh.gto.afsupportcenter.socialsecurity.siteservice.host.controller;



import com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.bo.SsStatementResultBO;
import com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.business.SsStatementResultService;
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
 * 对账差异结果表 前端控制器
 * </p>
 *
 * @author HuangXing
 * @since 2017-12-11
 */
@RestController
@RequestMapping("/api/soccommandservice/ssStatementResult")
public class SsStatementResultController extends BasicController<SsStatementResultService> {

    /**
     * <p>Description: 对账单结果查询(列表页)</p>
     *
     * @author wengxk
     * @date 2017-12-12
     * @param ssStatementResultDTO 对账单结果检索条件
     * @return  JsonResult<>
     */
    @Log("对账单结果查询")
    @PostMapping("/statementResultQuery")
    public JsonResult<List<SsStatementResultBO>> statementResultQuery(SsStatementResultBO ssStatementResultDTO) {

        //转换格式
        com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.bo.SsStatementResultBO ssStatementResultBO = CommonTransform.convertToEntity(ssStatementResultDTO, com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.bo.SsStatementResultBO.class);


        List<com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.bo.SsStatementResultBO> resultList =business.statementResultQuery(ssStatementResultBO);
        //转换格式
        List<SsStatementResultBO> resultDTOList  = CommonTransform.convertToDTOs(resultList,SsStatementResultBO.class);
        //BeanUtils.copyProperties(resultList,resultDTOList);
//        //循环转格式
//        if(Optional.ofNullable(resultList).isPresent()){
//            for(int i = 0;i < resultList.size();i++){
//                SsStatementResultBO resultDTO = new SsStatementResultBO();
//                BeanUtils.copyProperties(resultList.get(i),resultDTO);
//                resultDTOList.add(resultDTO);
//            }
//        }
        JsonResult<List<SsStatementResultBO>> jsonResult= new JsonResult<>();
        jsonResult.setData(resultDTOList);

        return jsonResult;
    }

    @Log("重算对账结果")
    @PostMapping("/calculateSstatementResult")
    public JsonResult<String> calculateSstatementResult(Long statementId) {
        JsonResult<String> json = new JsonResult<>();
        json.setData("调用成功");
        business.calculateSstatementResult(statementId);
        return json;
    }
}

