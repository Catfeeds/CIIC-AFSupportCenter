package com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.business.impl;

import com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.dto.SsMonthEmpChangeDTO;
import com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.dto.SsMonthEmpChangeDetailDTO;
import com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.entity.SsMonthEmpChangeDetailPO;
import com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.dao.SsMonthEmpChangeDetailMapper;
import com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.business.ISsMonthEmpChangeDetailService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
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
public class SsMonthEmpChangeDetailServiceImpl extends ServiceImpl<SsMonthEmpChangeDetailMapper, SsMonthEmpChangeDetailPO> implements ISsMonthEmpChangeDetailService {
    @Override
    public List<SsMonthEmpChangeDetailDTO> showMonthEmpChangeDetailByStatementId(SsMonthEmpChangeDTO ssMonthEmpChangeDTO) {
        //通过条件获得社保汇总明细数据
        List<SsMonthEmpChangeDetailDTO> detailDTOList = baseMapper.serachMonthEmpChangeDetailByStatementId(ssMonthEmpChangeDTO);

        //将原始DTO通过业务逻辑转换成页面展示的DTO
        List<SsMonthEmpChangeDetailDTO> resulrDTOList = dealEmpChangeDetailDTO(detailDTOList);
        return resulrDTOList;
    }

    /**
     * <p>Description: 将PO通过业务逻辑转换成页面展示的DTO</p>
     *
     * @author wengxk
     * @date 2017-12-13
     * @param detailDTOList 社保汇总明细数据
     * @return   List<SsMonthEmpChangeDetailDTO>
     */
    private List<SsMonthEmpChangeDetailDTO> dealEmpChangeDetailDTO( List<SsMonthEmpChangeDetailDTO> detailDTOList){
        //如果为空则直接返回
        if(!Optional.ofNullable(detailDTOList).isPresent()){
            return detailDTOList;
        }
        //用于合并的Map
        Map<String,SsMonthEmpChangeDetailDTO> dealMap = new HashMap<>(detailDTOList.size());
        //用于返回的
        List<SsMonthEmpChangeDetailDTO> resultDTOList = new ArrayList<>();
        //循环处理
        for(int i = 0;i < detailDTOList.size();i++){
            SsMonthEmpChangeDetailDTO dto = detailDTOList.get(i);
            //从map中获取需要操作的节点
            SsMonthEmpChangeDetailDTO doDto = getDtoOfMap(dealMap,dto);
            //将该节点的值写入需要操作的节点中
            putDetailValue(doDto,dto);
        }
        //将map中的值返给resultDTOList
        for (SsMonthEmpChangeDetailDTO value : dealMap.values()) {
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
     * @return   SsMonthEmpChangeDetailDTO
     */
    private SsMonthEmpChangeDetailDTO getDtoOfMap ( Map<String,SsMonthEmpChangeDetailDTO> map,SsMonthEmpChangeDetailDTO dto){
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
    private void putDetailValue(SsMonthEmpChangeDetailDTO putDto,SsMonthEmpChangeDetailDTO getDto){
        //定义社保类型
        //养老保险
        final int PENSION = 1;

        //医疗保险
        final int MEDICAL = 2;

        //失业保险
        final int UNEMPLOYMENT = 5;

        //工伤保险
        final int ACCIDENT = 3;

        //生育保险
        final int MATERNITY = 4;

        //如果是养老
        if(PENSION == getDto.getSsType()){
            putDto.setComAmountPension(getDto.getComAmount());
            putDto.setEmpAmountPension(getDto.getEmpAmount());
            putDto.setComCompensatedAmountPension(getDto.getComCompensateAmount());
            putDto.setEmpCompensatedAmountPension(getDto.getEmpCompensateAmount());
            putDto.setOnePaymentPension(getDto.getOnePayment());
        }

        //如果是医疗
        if(MEDICAL == getDto.getSsType()){
            putDto.setComAmountMedical(getDto.getComAmount());
            putDto.setEmpAmountMedical(getDto.getEmpAmount());
            putDto.setComCompensatedAmountMedical(getDto.getComCompensateAmount());
            putDto.setEmpCompensatedAmountMedical(getDto.getEmpCompensateAmount());
        }

        //如果是失业
        if(UNEMPLOYMENT == getDto.getSsType()){
            putDto.setComAmountUnemployment(getDto.getComAmount());
            putDto.setEmpAmountUnemployment(getDto.getEmpAmount());
            putDto.setComCompensatedAmountUnemployment(getDto.getComCompensateAmount());
            putDto.setEmpCompensatedAmountUnemployment(getDto.getEmpCompensateAmount());
        }

        //如果是工伤
        if(ACCIDENT == getDto.getSsType()){
            putDto.setComAmountAccident(getDto.getComAmount());
            putDto.setEmpAmountAccident(getDto.getEmpAmount());
            putDto.setComCompensatedAmountAccident(getDto.getComCompensateAmount());
            putDto.setEmpCompensatedAmountAccident(getDto.getEmpCompensateAmount());
        }

        //如果是生育
        if(MATERNITY == getDto.getSsType()){
            putDto.setComAmountMaternity(getDto.getComAmount());
            putDto.setEmpAmountMaternity(getDto.getEmpAmount());
            putDto.setComCompensatedAmountMaternity(getDto.getComCompensateAmount());
            putDto.setEmpCompensatedAmountMaternity(getDto.getEmpCompensateAmount());
        }
    }




}
