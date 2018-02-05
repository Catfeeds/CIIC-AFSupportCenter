package com.ciicsh.gto.afsupportcenter.healthmedical.api;

import com.ciicsh.gto.afsupportcenter.healthmedical.entity.dto.PaymentBatchDTO;
import com.ciicsh.gto.commonservice.util.dto.Result;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * <p>
 * 雇员付款
 * </p>
 *
 * @author chenpb
 * @since 2018-01-31
 */
@FeignClient("afsupportcenter-insurance-service")
@RequestMapping("/api/support/employeePayment")
public interface EmployeePaymentProxy {
    /**
     * @description 根据传入条件查询联系人
     * @author chenpb
     * @since 2018-01-31
     * @param
     * @return 联系人列表
     */
    @PostMapping(value = "/updatePayment")
    public Result<List> updatePayment(@RequestBody PaymentBatchDTO dto);

}
