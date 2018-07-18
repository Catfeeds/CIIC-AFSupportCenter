package com.ciicsh.gto.afsupportcenter.housefund.fundservice.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.ciicsh.gto.afsupportcenter.housefund.fundservice.bo.HfPaymentComBo;
import com.ciicsh.gto.afsupportcenter.housefund.fundservice.bo.payment.HfCreatePaymentAccountBO;
import com.ciicsh.gto.afsupportcenter.housefund.fundservice.dto.HfFundPayCreatePaymentAccountPara;
import com.ciicsh.gto.afsupportcenter.housefund.fundservice.entity.HfPaymentCom;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 * 公积金汇缴支付公司名单 Mapper 接口
 * </p>
 */
@Repository
public interface HfPaymentComMapper extends BaseMapper<HfPaymentCom> {

    /**
     * 更新前端传递的list参数查询企业账户
     *
     * @param
     * @return
     */
    //List<HfCreatePaymentAccountBO> selectPaymentAccount(@Param("paymentAccountIds") List paymentAccountIds);
    List<HfCreatePaymentAccountBO> selectPaymentAccount(HfFundPayCreatePaymentAccountPara params);

    Integer updatePaymentAccount(HfFundPayCreatePaymentAccountPara hfFundPayCreatePaymentAccountPara);

    List<HfPaymentComBo> getCompanyIdList(@Param("paymentId") Long paymentId, @Param("comAccountId") Long comAccountId);

    HfPaymentComBo getAmountByPaymentId (@Param("paymentId") Long paymentId);

    List<HfPaymentComBo> detailList(@Param("paymentId") Long paymentId);

    String getLastPaymentMonth(@Param("companyId") String companyId, @Param("hfType") Integer hfType);
}
