package com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.business.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.dao.SsCreateEmpChangeReportMapper;
import com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.dao.SsMonthEmpChangeDetailMapper;
import com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.dao.SsMonthEmpChangeMapper;
import com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.dto.SsCreateEmpChangeReportAdjustBaseDetailBO;
import com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.dto.SsCreateEmpChangeReportBaseDetailBO;
import com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.dto.SsCreateEmpChangeReportRefundBO;
import com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.entity.SsMonthCharge;
import com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.dao.SsMonthChargeMapper;
import com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.business.ISsMonthChargeService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.entity.SsMonthEmpChange;
import com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.entity.SsMonthEmpChangeDetail;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 月度缴费明细
系统在每月26日晚上自动生成每月的标准和非标明细数据，用户也可重新生成， 服务实现类
 * </p>
 *
 * @author linhui
 * @since 2017-12-23
 */
@Service
public class SsMonthChargeServiceImpl extends ServiceImpl<SsMonthChargeMapper, SsMonthCharge> implements ISsMonthChargeService {

    @Autowired
    SsCreateEmpChangeReportMapper createEmpChangeReportMapper;
    @Autowired
    SsMonthEmpChangeMapper monthEmpChangeMapper;
    @Autowired
    SsMonthEmpChangeDetailMapper monthEmpChangeDetailMapper;


    //生成月度雇员社保数据(标准)
    @Override
    public void createMonthStandard(String comAccuntId, String ssMonth, String computeType) {
        //计算全量缴费明细（标准）
        monthEmpChargeStandard(comAccuntId,  ssMonth,  computeType);

    }

    //生成月度雇员社保数据(非标)
    @Override
    public void createMonthNonStandard(String comAccuntId, String ssMonth, String computeType) {
        //计算变更汇总报表（非标）
            monthEmpChangeReport(comAccuntId,  ssMonth,  computeType);
        //计算全量缴费明细（非标）
            monthEmpChargeNonStandard(comAccuntId,  ssMonth,  computeType);
    }

    //生成月度全量汇缴明细（标准）
    private void monthEmpChargeStandard(String comAccuntId, String ssMonth, String computeType) {

    }
    //生成月度全量汇缴明细（非标）
    private void monthEmpChargeNonStandard(String comAccuntId, String ssMonth, String computeType) {

    }

    //计算变更汇总报表（非标）
    private void monthEmpChangeReport(String comAccuntId, String ssMonth, String computeType) {
        EntityWrapper<SsMonthEmpChange> wrapper = new EntityWrapper<>();

        List listChangeReport = monthEmpChangeMapper.selectList(wrapper);
//        if(!Optional.ofNullable(listChangeReport).isPresent()){
//            return detailDTOList;
//        }

        List<SsCreateEmpChangeReportBaseDetailBO> listEmpBaseDetail = createEmpChangeReportMapper.searchEmpBaseDetailByYYS(comAccuntId, ssMonth);

        listEmpBaseDetail.forEach((SsCreateEmpChangeReportBaseDetailBO baseDetailBO) -> {
            SsMonthEmpChangeDetail mecDetail = new SsMonthEmpChangeDetail();
            //开始实现过滤逻辑
            BeanUtils.copyProperties(mecDetail, baseDetailBO);
            monthEmpChangeDetailMapper.insert(mecDetail);
        });

        List<SsCreateEmpChangeReportAdjustBaseDetailBO> listEmpAdjustBaseDetail = createEmpChangeReportMapper.searchEmpBaseAdjustDetailByYYS(comAccuntId, ssMonth);

        List<SsCreateEmpChangeReportBaseDetailBO> listEmpStop = createEmpChangeReportMapper.searchEmpStopByYYS(comAccuntId, ssMonth);

        List<SsCreateEmpChangeReportRefundBO> listEmpRefund = createEmpChangeReportMapper.searchEmpRefundByYYS(comAccuntId, ssMonth);
    }


    private void createEmpBaseDetail() {

    }

    private void createEmpBaseAdjust() {

    }

    private void createEmpStop() {

    }

    private void createEmpRefund() {

    }
}
