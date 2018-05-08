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
    /**
     * 获得公积金汇缴支付列表(编辑页面添加按钮触发)
     * @param pageInfo 查询条件
     * @return 查询结果
     */
    PageRows<HfPaymentAccountBo> getMakePayListsById(PageInfo pageInfo);

    /**
     * 公积金汇缴支付编辑/详情操作数据
     * @param pageInfo 查询条件
     * @return 查询结果
     */
    PageRows<HfPaymentAccountBo> getFundPaysEditOperationData(PageInfo pageInfo);

    /**
     * 编辑页面删除支付详情
     * @param hfPaymentAccountBo 删除的列
     * @return 删除结果
     */
    JsonResult delOperateEditData(HfPaymentAccountBo hfPaymentAccountBo);

}
