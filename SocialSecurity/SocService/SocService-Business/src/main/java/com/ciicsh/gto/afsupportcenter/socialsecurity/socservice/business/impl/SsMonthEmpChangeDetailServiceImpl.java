package com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.business.impl;

import com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.bo.SsMonthEmpChangeBO;
import com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.entity.SsMonthEmpChangeDetail;
import com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.dao.SsMonthEmpChangeDetailMapper;
import com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.business.SsMonthEmpChangeDetailService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.entity.custom.GsyExportOpt;
import com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.entity.custom.YysExportOpt;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * <p>
 * 雇员月度变更表明细
该表结果有可能需要调整 服务实现类
 * </p>
 *
 * @author HuangXing
 * @since 2017-12-07
 */
@Service
public class SsMonthEmpChangeDetailServiceImpl extends ServiceImpl<SsMonthEmpChangeDetailMapper, SsMonthEmpChangeDetail> implements SsMonthEmpChangeDetailService {
    @Override
    public List<com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.bo.SsMonthEmpChangeDetailBO> showMonthEmpChangeDetailByStatementId(SsMonthEmpChangeBO ssMonthEmpChangeBO) {
        //通过条件获得社保汇总明细数据
        List<com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.bo.SsMonthEmpChangeDetailBO> detailDTOList = baseMapper.serachMonthEmpChangeDetailByStatementId(ssMonthEmpChangeBO);

        //将原始DTO通过业务逻辑转换成页面展示的DTO
        List<com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.bo.SsMonthEmpChangeDetailBO> resulrDTOList = dealEmpChangeDetailDTO(detailDTOList);
        return resulrDTOList;
    }

    @Override
    public List<YysExportOpt> yysExportQuery(Long monthEmpChangeId) {
        return baseMapper.yysExportQuery(monthEmpChangeId);
    }

    @Override
    public List<GsyExportOpt> gsyExportQuery(Long monthEmpChangeId) {
        return baseMapper.gsyExportQuery(monthEmpChangeId);
    }

    /**
     * <p>Description: 将PO通过业务逻辑转换成页面展示的DTO</p>
     *
     * @author wengxk
     * @date 2017-12-13
     * @param detailDTOList 社保汇总明细数据
     * @return   List<SsMonthEmpChangeDetail>
     */
    private List<com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.bo.SsMonthEmpChangeDetailBO> dealEmpChangeDetailDTO(List<com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.bo.SsMonthEmpChangeDetailBO> detailDTOList){
        //如果为空则直接返回
        if(!Optional.ofNullable(detailDTOList).isPresent()){
            return detailDTOList;
        }
        //用于合并的Map
        Map<String, com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.bo.SsMonthEmpChangeDetailBO> dealMap = new LinkedHashMap<>(detailDTOList.size());
        //用于返回的
        List<com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.bo.SsMonthEmpChangeDetailBO> resultDTOList = new ArrayList<>();
        //循环处理
        for(int i = 0;i < detailDTOList.size();i++){
            com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.bo.SsMonthEmpChangeDetailBO dto = detailDTOList.get(i);
            //从map中获取需要操作的节点
            com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.bo.SsMonthEmpChangeDetailBO doDto = getDtoOfMap(dealMap,dto);
            //将该节点的值写入需要操作的节点中
            putDetailValue(doDto,dto);
        }
        //将map中的值返给resultDTOList
        for (com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.bo.SsMonthEmpChangeDetailBO value : dealMap.values()) {
            resultDTOList.add(value);
        }

        return resultDTOList;
    }
    /**
     * <p>Description: 将dto放入map中,如果有相同的key的dto已存在,则返回已存在的dto</p>
     *
     * @author wengxk
     * @date 2017-12-13
     * @param map 存放数据集的map
     * @param dto 放入的dto
     * @return   SsMonthEmpChangeDetail
     */
    private com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.bo.SsMonthEmpChangeDetailBO getDtoOfMap (Map<String, com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.bo.SsMonthEmpChangeDetailBO> map, com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.bo.SsMonthEmpChangeDetailBO dto){
        //拼装key
        String key = dto.getEmployeeId() + '-' + dto.getChangeType();
        //查看map中是否存在该key的节点
        if(map.containsKey(key)){
            //存在则返回已经在map中的节点
            return map.get(key);
         }else{
            //不存在则将该节点放入map中并返回本节点
            map.put(key,dto);
            return dto;
        }
    }
    /**
     * <p>Description: 将dto中的值赋值到节点特定的位置中</p>
     *
     * @author wengxk
     * @date 2017-12-13
     * @param putDto 存放值节点
     * @param getDto 取值节点
     */
    private void putDetailValue(com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.bo.SsMonthEmpChangeDetailBO putDto, com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.bo.SsMonthEmpChangeDetailBO getDto){
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

        //如果是养老
        if(PENSION.equals(getDto.getSsType())){
            putDto.setComAmountPension(getDto.getComAmount());
            putDto.setEmpAmountPension(getDto.getEmpAmount());
            putDto.setComCompensatedAmountPension(getDto.getComCompensateAmount());
            putDto.setEmpCompensatedAmountPension(getDto.getEmpCompensateAmount());
            putDto.setOnePaymentPension(getDto.getOnePayment());
        }

        //如果是医疗
        if(MEDICAL.equals(getDto.getSsType())){
            putDto.setComAmountMedical(getDto.getComAmount());
            putDto.setEmpAmountMedical(getDto.getEmpAmount());
            putDto.setComCompensatedAmountMedical(getDto.getComCompensateAmount());
            putDto.setEmpCompensatedAmountMedical(getDto.getEmpCompensateAmount());
        }

        //如果是失业
        if(UNEMPLOYMENT.equals(getDto.getSsType())){
            putDto.setComAmountUnemployment(getDto.getComAmount());
            putDto.setEmpAmountUnemployment(getDto.getEmpAmount());
            putDto.setComCompensatedAmountUnemployment(getDto.getComCompensateAmount());
            putDto.setEmpCompensatedAmountUnemployment(getDto.getEmpCompensateAmount());
        }

        //如果是工伤
        if(ACCIDENT.equals(getDto.getSsType())){
            putDto.setComAmountAccident(getDto.getComAmount());
            putDto.setEmpAmountAccident(getDto.getEmpAmount());
            putDto.setComCompensatedAmountAccident(getDto.getComCompensateAmount());
            putDto.setEmpCompensatedAmountAccident(getDto.getEmpCompensateAmount());
        }

        //如果是生育
        if(MATERNITY.equals(getDto.getSsType())){
            putDto.setComAmountMaternity(getDto.getComAmount());
            putDto.setEmpAmountMaternity(getDto.getEmpAmount());
            putDto.setComCompensatedAmountMaternity(getDto.getComCompensateAmount());
            putDto.setEmpCompensatedAmountMaternity(getDto.getEmpCompensateAmount());
        }
    }




}
