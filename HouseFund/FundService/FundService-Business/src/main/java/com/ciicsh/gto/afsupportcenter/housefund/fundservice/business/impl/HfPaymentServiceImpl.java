package com.ciicsh.gto.afsupportcenter.housefund.fundservice.business.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.ciicsh.gto.afsupportcenter.housefund.fundservice.business.HfPaymentService;
import com.ciicsh.gto.afsupportcenter.housefund.fundservice.dao.HfPaymentComMapper;
import com.ciicsh.gto.afsupportcenter.housefund.fundservice.dao.HfPaymentMapper;
import com.ciicsh.gto.afsupportcenter.housefund.fundservice.entity.HfPayment;
import com.ciicsh.gto.afsupportcenter.util.StringUtil;
import com.ciicsh.gto.afsupportcenter.util.web.response.JsonResult;
import com.ciicsh.gto.settlementcenter.payment.cmdapi.PayapplyServiceProxy;
import com.ciicsh.gto.settlementcenter.payment.cmdapi.dto.PayApplyProxyDTO;
import com.ciicsh.gto.settlementcenter.payment.cmdapi.dto.PayapplyCompanyProxyDTO;
import com.ciicsh.gto.settlementcenter.payment.cmdapi.dto.PayapplyEmployeeProxyDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 公积金汇缴支付批次表 服务实现类
 * </p>
 */
@Service
public class HfPaymentServiceImpl extends ServiceImpl<HfPaymentMapper, HfPayment> implements HfPaymentService {

    @Autowired
    private HfPaymentComMapper ssPaymentComMapper;

    @Autowired
    private PayapplyServiceProxy payapplyServiceProxy;

    /**
     * 上海公积金付款申请接口
     *
     * @param hfPayment
     * @return
     */
    @Override
    public JsonResult<String> doReviewdePass(HfPayment hfPayment) {
        JsonResult<String> json = new JsonResult<>();
        json.setCode(0);

        //验证
        //根据ID获取到记录
        hfPayment = baseMapper.selectOne(hfPayment);

        //验证客户费用ID是否能取到数据
        if (hfPayment == null) {
            json.setCode(2);
            json.setMessage("没有与选中数据匹配的支付批次");
            return json;
        }

        //验证状态,只有3 ,可付 5,内部审批批退 状态的数据可申请支付
        if (4 != hfPayment.getPaymentState()) {
            json.setCode(3);
            json.setMessage("只有申请中状态的批次能审核通过");
            return json;
        }
        //验证结束,调用外部审批接口
        PayApplyProxyDTO resDto = financePayApi(hfPayment);
        com.ciicsh.gto.settlementcenter.payment.cmdapi.common.JsonResult<PayApplyProxyDTO> jsRes =
            payapplyServiceProxy.addShHouseFundPayApply(resDto);

        json.setCode(Integer.parseInt(jsRes.getCode()));
        json.setMessage(jsRes.getMsg());
        return json;
    }

    /**
     * 执行调用财务API
     *
     * @param hfPayment
     */
    private PayApplyProxyDTO financePayApi(HfPayment hfPayment) {
        PayApplyProxyDTO dto = new PayApplyProxyDTO();
        //TODO SQL修改
        List<PayapplyCompanyProxyDTO> paymentComList = baseMapper.getPaymentComList(hfPayment.getPaymentId()
                .longValue(),
            hfPayment.getPaymentMonth());
        //TODO SQL修改
        List<PayapplyEmployeeProxyDTO> paymentEmpList = baseMapper.getPaymentEmpList(hfPayment.getPaymentId()
                .longValue(),
            hfPayment.getPaymentMonth());
        dto.setCompanyList(paymentComList);
        dto.setEmployeeList(paymentEmpList);

        dto.setDepartmentName("福利保障部公积金");
        dto.setIsFinancedept(0);
        dto.setBusinessType(2);//业务类型
        dto.setPayWay(3);//转账
        dto.setPayAmount(hfPayment.getTotalApplicationAmonut());//申请支付金额
        dto.setReceiver("公积金中心");//收款方名称
        dto.setApplyer(hfPayment.getRequestUser());  //申请人
        dto.setApplyDate(StringUtil.getNow());//申请日期
        dto.setBusinessPkId(hfPayment.getPaymentId().longValue());//业务方主键ID(整型)

        //支付独立公积金费用+支付月份  1 大库、2 外包、3独立户
        if (hfPayment.getHfAccountType() == 1) {
            dto.setPayReason("支付大库公积金费用" + hfPayment.getPaymentMonth());
        } else if (hfPayment.getHfAccountType() == 2) {
            dto.setPayReason("支付外包公积金费用" + hfPayment.getPaymentMonth());
        } else if (hfPayment.getHfAccountType() == 3) {
            dto.setPayReason("支付独立户公积金费用" + hfPayment.getPaymentMonth());
        }
        dto.setPayPurpose(dto.getPayReason());

        return dto;
    }

}
