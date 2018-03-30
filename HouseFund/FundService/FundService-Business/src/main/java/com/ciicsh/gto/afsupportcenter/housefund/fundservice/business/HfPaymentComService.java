package com.ciicsh.gto.afsupportcenter.housefund.fundservice.business;

import com.baomidou.mybatisplus.service.IService;
import com.ciicsh.gto.afsupportcenter.housefund.fundservice.entity.HfPaymentCom;
import com.ciicsh.gto.afsupportcenter.util.web.response.JsonResult;

import java.util.ArrayList;
import java.util.List;

public interface HfPaymentComService extends IService<HfPaymentCom> {
    JsonResult createPaymentCom(List paymentAccountIds);
}
