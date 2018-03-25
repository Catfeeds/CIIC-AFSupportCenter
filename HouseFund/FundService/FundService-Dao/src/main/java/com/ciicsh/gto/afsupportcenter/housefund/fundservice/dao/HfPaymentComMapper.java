package com.ciicsh.gto.afsupportcenter.housefund.fundservice.dao;

import com.ciicsh.gto.afsupportcenter.housefund.fundservice.bo.HfPaymentComBo;
import com.ciicsh.gto.afsupportcenter.housefund.fundservice.entity.HfPaymentCom;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 * 公积金汇缴支付公司名单 Mapper 接口
 * </p>
 */
@Repository
public interface HfPaymentComMapper extends BaseMapper<HfPaymentCom> {

    List<HfPaymentComBo> getFundPaysDetailOperationData(HfPaymentComBo hfPaymentBo);

}
