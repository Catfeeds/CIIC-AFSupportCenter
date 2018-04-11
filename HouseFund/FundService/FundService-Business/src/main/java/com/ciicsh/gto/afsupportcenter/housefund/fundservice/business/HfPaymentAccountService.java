package com.ciicsh.gto.afsupportcenter.housefund.fundservice.business;

import com.baomidou.mybatisplus.service.IService;
import com.ciicsh.gto.afsupportcenter.housefund.fundservice.bo.HfPaymentAccountBo;
import com.ciicsh.gto.afsupportcenter.housefund.fundservice.bo.payment.HFNetBankComAccountBO;
import com.ciicsh.gto.afsupportcenter.housefund.fundservice.bo.payment.HFNetBankExportBO;
import com.ciicsh.gto.afsupportcenter.housefund.fundservice.entity.HfPaymentAccount;
import com.ciicsh.gto.afsupportcenter.util.page.PageInfo;
import com.ciicsh.gto.afsupportcenter.util.page.PageRows;
import com.ciicsh.gto.afsupportcenter.util.web.response.JsonResult;

import java.util.List;
import java.util.Map;

public interface HfPaymentAccountService extends IService<HfPaymentAccount> {

    boolean updatePaymentInfo(Long pkId, String remark, Integer payStatus);

    /**
     * 获得公积金汇缴支付列表
     * @param pageInfo
     * @return
     */
    PageRows<HfPaymentAccountBo> getMakePayLists(PageInfo pageInfo);

    JsonResult delHfPayment(String paymentId);

    List<HFNetBankComAccountBO> getComAccountByPaymentId(Long paymentId);

    void setIndependentNetBankRepairData(Map<String, String> repairMap,
                                                String hfComAccount,
                                                Integer hfType,
                                                String hfMonth,
                                                List<HFNetBankExportBO> repairList);

    void setIndependentNetBankChangeData(Map<String, String> changeMap,
                                         String hfComAccount,
                                         Integer hfType,
                                         String hfMonth,
                                         List<HFNetBankExportBO> newList,
                                         List<HFNetBankExportBO> inList,
                                         List<HFNetBankExportBO> outList);

    void setBigStorageNetBankRepairData(Map<String, String> repairMap,
                                        String hfComAccount,
                                        Integer hfType,
                                        String hfMonth,
                                        List<HFNetBankExportBO> repairList);

    void setBigStorageNetBankChangeData(Map<String, String> changeMap,
                                        String hfComAccount,
                                        Integer hfType,
                                        String hfMonth,
                                        List<HFNetBankExportBO> newList,
                                        List<HFNetBankExportBO> inList,
                                        List<HFNetBankExportBO> outList);
}
