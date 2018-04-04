package com.ciicsh.gto.afsupportcenter.housefund.fundservice.dao;

import com.ciicsh.gto.afsupportcenter.housefund.fundservice.bo.HfPaymentComBo;
import com.ciicsh.gto.afsupportcenter.housefund.fundservice.bo.payment.HfCreatePaymentAccountBO;
import com.ciicsh.gto.afsupportcenter.housefund.fundservice.dto.HfFundPayCreatePaymentAccountPara;
import com.ciicsh.gto.afsupportcenter.housefund.fundservice.entity.HfPaymentCom;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.springframework.stereotype.Repository;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 公积金汇缴支付公司名单 Mapper 接口
 * </p>
 */
@Repository
public interface HfPaymentComMapper extends BaseMapper<HfPaymentCom> {

    List<HfPaymentComBo> getFundPaysDetailOperationData(HfPaymentComBo hfPaymentBo);

    /**
     * 更新前端传递的list参数查询企业账户
     * @param paymentAccountIds
     * @return
     */
    List<HfCreatePaymentAccountBO> selectPaymentAccount(@Param("paymentAccountIds") List paymentAccountIds);

    Integer updatePaymentAccount(HfFundPayCreatePaymentAccountPara hfFundPayCreatePaymentAccountPara);


}
