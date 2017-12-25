package com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.business.impl;

import com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.entity.SsEmpBaseAdjustDetail;
import com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.dao.SsEmpBaseAdjustDetailMapper;
import com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.business.ISsEmpBaseAdjustDetailService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 雇员社保基数调整记录明细表，
该表细化到每一个社保险种的月度段的基数、比例、公司金额、个人金额、差额（与Em 服务实现类
 * </p>
 *
 * @author HuangXing
 * @since 2017-12-01
 */
@Service
public class SsEmpBaseAdjustDetailServiceImpl extends ServiceImpl<SsEmpBaseAdjustDetailMapper, SsEmpBaseAdjustDetail> implements ISsEmpBaseAdjustDetailService {

}
