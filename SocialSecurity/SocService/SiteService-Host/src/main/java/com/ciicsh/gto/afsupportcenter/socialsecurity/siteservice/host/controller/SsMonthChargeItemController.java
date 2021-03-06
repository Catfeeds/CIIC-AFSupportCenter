package com.ciicsh.gto.afsupportcenter.socialsecurity.siteservice.host.controller;


import com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.bo.SsMonthChargeItemBO;
import com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.business.SsMonthChargeItemService;
import com.ciicsh.gto.afsupportcenter.util.ExcelUtil;
import com.ciicsh.gto.afsupportcenter.util.StringUtil;
import com.ciicsh.gto.afsupportcenter.util.exception.BusinessException;
import com.ciicsh.gto.afsupportcenter.util.web.controller.BasicController;
import com.ciicsh.gto.afsupportcenter.util.web.response.JsonResult;
import com.ciicsh.gto.afsupportcenter.util.web.response.JsonResultKit;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 雇员月度费用明细项目 前端控制器
 */
@Controller
@RequestMapping("/api/soccommandservice/ssMonthChargeItem")
public class SsMonthChargeItemController extends BasicController<SsMonthChargeItemService>{

    /**
     * 雇员月度缴费明细
     * @param ssMonthChargeItemBO
     * @return
     */
    @RequestMapping("/queryEmlpyeeMonthFeeDetail")
    @ResponseBody
    public JsonResult<List<SsMonthChargeItemBO>> queryEmlpyeeMonthFeeDetail(SsMonthChargeItemBO ssMonthChargeItemBO){
        if( null == ssMonthChargeItemBO.getCompanyId() && null == ssMonthChargeItemBO.getSsAccount())
            throw new BusinessException("企业社保账号和客户编号必选一项");
        ssMonthChargeItemBO.setSsMonth(ssMonthChargeItemBO.getSsMonth().substring(0,6));
        List<SsMonthChargeItemBO> ssMonthChargeItemBOList = dealEmpChangeDetailDTO(business.queryEmlpyeeMonthFeeDetail(ssMonthChargeItemBO));

        return JsonResultKit.of(ssMonthChargeItemBOList);
    }

    /**
     * 导出雇员月度缴费明细
     * @param response
     * @param ssMonthChargeItemBO
     */
    @RequestMapping("monthChargeExport")
    public void monthChargeExport(HttpServletResponse response, SsMonthChargeItemBO ssMonthChargeItemBO){
        Date date = new Date();
        String fileNme = "月度缴费明细_"+ StringUtil.getDateString(date)+".xls";
        ssMonthChargeItemBO.setSsMonth(ssMonthChargeItemBO.getSsMonth().substring(0,6));
        List<SsMonthChargeItemBO> ssMonthChargeItemBOList = dealEmpChangeDetailDTO(business.queryEmlpyeeMonthFeeDetail(ssMonthChargeItemBO));
        ExcelUtil.exportExcel(ssMonthChargeItemBOList,SsMonthChargeItemBO.class,fileNme,response);
    }
    ;

    /**
     * 将PO通过业务逻辑转换成页面展示的DTO
     * @param ssMonthChargeItemBOList
     * @return   List<SsMonthChargeItemBO>
     */
    private List<SsMonthChargeItemBO> dealEmpChangeDetailDTO( List<SsMonthChargeItemBO> ssMonthChargeItemBOList){
        //如果为空则直接返回
        if(!Optional.ofNullable(ssMonthChargeItemBOList).isPresent()){
            return ssMonthChargeItemBOList;
        }
        //用于合并的Map
        Map<String,List<SsMonthChargeItemBO>> dealMap = new LinkedHashMap<>();
        //用于返回的
        List<SsMonthChargeItemBO> resultDTOList = new ArrayList<>();
        //循环处理
        for(int i = 0;i < ssMonthChargeItemBOList.size();i++){
            SsMonthChargeItemBO bo = ssMonthChargeItemBOList.get(i);
            //从map中获取需要操作的节点
            groupingMap(dealMap,bo);
        }
        //行转列
        rowToColumn(dealMap,resultDTOList);
        if (resultDTOList.size()==0){
            return resultDTOList;
        }
        SsMonthChargeItemBO sum=new SsMonthChargeItemBO();
        sum.setEmployeeName("合计：");
        sum.setBirthRiskTotalFee(new BigDecimal(0));
        sum.setInjuryOnJobTotalFee(new BigDecimal(0));
        sum.setMedicalTotalFee(new BigDecimal(0));
        sum.setPensionTotalFee(new BigDecimal(0));
        sum.setUnemploymentTotalFee(new BigDecimal(0));
        sum.setTotalAmount(new BigDecimal(0));
        resultDTOList.forEach(item -> {
            sum.setBirthRiskTotalFee(sum.getBirthRiskTotalFee().add(Optional.ofNullable(item.getBirthRiskTotalFee()).orElse(new BigDecimal(0))));
            sum.setInjuryOnJobTotalFee(sum.getInjuryOnJobTotalFee().add(Optional.ofNullable(item.getInjuryOnJobTotalFee()).orElse(new BigDecimal(0))));
            sum.setMedicalTotalFee(sum.getMedicalTotalFee().add(Optional.ofNullable(item.getMedicalTotalFee()).orElse(new BigDecimal(0))));
            sum.setPensionTotalFee(sum.getPensionTotalFee().add(Optional.ofNullable(item.getPensionTotalFee()).orElse(new BigDecimal(0))));
            sum.setUnemploymentTotalFee(sum.getUnemploymentTotalFee().add(Optional.ofNullable(item.getUnemploymentTotalFee()).orElse(new BigDecimal(0))));
            sum.setTotalAmount(sum.getTotalAmount().add(Optional.ofNullable(item.getTotalAmount()).orElse(new BigDecimal(0))));
        });
        resultDTOList.add(sum);
        return resultDTOList;
    }

    /**
     * 行转列
     * @param dealMap
     * @param resultDTOList
     */
    private void rowToColumn(Map<String, List<SsMonthChargeItemBO>> dealMap, List<SsMonthChargeItemBO> resultDTOList) {
        dealMap.forEach((k,v)->{
            resultDTOList.add(putDetailValue(v));
        });
    }

    /**
     * 以同一个员工 进行分组
     * @param map
     * @param bo
     */
    private void groupingMap (Map<String,List<SsMonthChargeItemBO>> map, SsMonthChargeItemBO bo){
        //拼装key
        //String key = bo.getEmployeeId();
        String key = bo.getMonthChargeId().toString();
        //查看map中是否存在该key的节点
        if(map.containsKey(key)){
            //存在则返回已经在map中的节点
            List<SsMonthChargeItemBO> ssMonthChargeItemBOList = map.get(key);
            ssMonthChargeItemBOList.add(bo);
        }else{
            //不存在则将该节点放入map中并返回本节点
            List<SsMonthChargeItemBO> ssMonthChargeItemBOList = new ArrayList<SsMonthChargeItemBO>();
            ssMonthChargeItemBOList.add(bo);
            map.put(key,ssMonthChargeItemBOList);
        }
    }

    /**
     * 将分组后的员工 多条转一条
     * @param ssMonthChargeItemBOList
     * @return
     */
    private SsMonthChargeItemBO putDetailValue(List<SsMonthChargeItemBO> ssMonthChargeItemBOList){
        //定义社保类型
        //养老保险
        final String PENSION = "DIT00042";
        //医疗保险
        final String MEDICAL = "DIT00043";
        //失业保险
        final String UNEMPLOYMENT = "DIT00046";
        //工伤保险
        final String ACCIDENT = "DIT00044";
        //生育保险
        final String MATERNITY = "DIT00045";
        SsMonthChargeItemBO  ssMonthChargeItemBO = ssMonthChargeItemBOList.get(0);
        ssMonthChargeItemBOList.forEach(p->{
            //如果是养老
            if(PENSION.equals(p.getSsType())){
                ssMonthChargeItemBO.setPensionComFee(p.getComAmount());
                ssMonthChargeItemBO.setPensionEmpFee(p.getEmpAmount());
                ssMonthChargeItemBO.setPensionTotalFee(p.getSubTotalAmount());
            }else if(MEDICAL.equals(p.getSsType())){//如果是医疗
                ssMonthChargeItemBO.setMedicalComFee(p.getComAmount());
                ssMonthChargeItemBO.setMedicalEmpFee(p.getEmpAmount());
                ssMonthChargeItemBO.setMedicalTotalFee(p.getSubTotalAmount());
            }else if(UNEMPLOYMENT.equals(p.getSsType())){//如果是失业
                ssMonthChargeItemBO.setUnemploymentComFee(p.getComAmount());
                ssMonthChargeItemBO.setUnemploymentEmpFee(p.getEmpAmount());
                ssMonthChargeItemBO.setUnemploymentTotalFee(p.getSubTotalAmount());
            }else if(ACCIDENT.equals(p.getSsType())){//如果是工伤
                ssMonthChargeItemBO.setInjuryOnJobComFee(p.getComAmount());
                ssMonthChargeItemBO.setInjuryOnJobEmpFee(p.getEmpAmount());
                ssMonthChargeItemBO.setInjuryOnJobTotalFee(p.getSubTotalAmount());
            }else if(MATERNITY.equals(p.getSsType())){//如果是生育
                ssMonthChargeItemBO.setBirthRiskComFee(p.getComAmount());
                ssMonthChargeItemBO.setBirthRiskEmpFee(p.getEmpAmount());
                ssMonthChargeItemBO.setBirthRiskTotalFee(p.getSubTotalAmount());
            }
        });
        return ssMonthChargeItemBO;
    }
}

