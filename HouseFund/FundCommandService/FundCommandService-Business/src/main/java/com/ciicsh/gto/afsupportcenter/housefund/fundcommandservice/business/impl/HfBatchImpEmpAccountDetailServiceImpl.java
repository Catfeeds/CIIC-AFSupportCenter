package com.ciicsh.gto.afsupportcenter.housefund.fundcommandservice.business.impl;

import com.ciicsh.gto.afsupportcenter.housefund.fundcommandservice.entity.HfBatchImpEmpAccountDetail;
import com.ciicsh.gto.afsupportcenter.housefund.fundcommandservice.dao.HfBatchImpEmpAccountDetailMapper;
import com.ciicsh.gto.afsupportcenter.housefund.fundcommandservice.business.IHfBatchImpEmpAccountDetailService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 导入公积金账号
用户更希望使用雇员身份证号+客户编号 定位人员 服务实现类
 * </p>
 */
@Service
public class HfBatchImpEmpAccountDetailServiceImpl extends ServiceImpl<HfBatchImpEmpAccountDetailMapper, HfBatchImpEmpAccountDetail> implements IHfBatchImpEmpAccountDetailService {

}
