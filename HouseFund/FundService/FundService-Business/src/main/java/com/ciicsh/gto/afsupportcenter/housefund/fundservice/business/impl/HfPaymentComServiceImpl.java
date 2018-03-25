package com.ciicsh.gto.afsupportcenter.housefund.fundservice.business.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.ciicsh.gto.afsupportcenter.housefund.fundservice.bo.HfPaymentComBo;
import com.ciicsh.gto.afsupportcenter.housefund.fundservice.dao.HfPaymentComMapper;
import com.ciicsh.gto.afsupportcenter.housefund.fundservice.entity.HfPaymentCom;
import com.ciicsh.gto.afsupportcenter.housefund.fundservice.business.HfPaymentComService;
import com.ciicsh.gto.afsupportcenter.util.page.PageInfo;
import com.ciicsh.gto.afsupportcenter.util.page.PageKit;
import com.ciicsh.gto.afsupportcenter.util.page.PageRows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 公积金汇缴支付公司名单 服务实现类
 * </p>
 */
@Service
public class HfPaymentComServiceImpl extends ServiceImpl<HfPaymentComMapper, HfPaymentCom> implements
    HfPaymentComService {

    @Autowired
    private HfPaymentComMapper hfPaymentComMapper;

    /**
     * 获得公积金汇缴支付详细操作数据
     * @param pageInfo
     * @return
     */
    @Override
    public PageRows<HfPaymentComBo> getFundPaysDetailOperationData(PageInfo pageInfo) {
        HfPaymentComBo hfPaymentComBo = pageInfo.toJavaObject(HfPaymentComBo.class);
        return PageKit.doSelectPage(pageInfo, () -> hfPaymentComMapper.getFundPaysDetailOperationData(hfPaymentComBo));
    }
}
