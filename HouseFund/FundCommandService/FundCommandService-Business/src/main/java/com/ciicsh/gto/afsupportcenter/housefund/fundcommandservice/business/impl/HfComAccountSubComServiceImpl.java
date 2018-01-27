package com.ciicsh.gto.afsupportcenter.housefund.fundcommandservice.business.impl;

import com.ciicsh.gto.afsupportcenter.housefund.fundcommandservice.entity.HfComAccountSubCom;
import com.ciicsh.gto.afsupportcenter.housefund.fundcommandservice.dao.HfComAccountSubComMapper;
import com.ciicsh.gto.afsupportcenter.housefund.fundcommandservice.business.IHfComAccountSubComService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 公积金账户绑定客户：一个公积金账户可以绑定多家子公司
Com：公司简写
Sub：表示公司账户下的 服务实现类
 * </p>
 */
@Service
public class HfComAccountSubComServiceImpl extends ServiceImpl<HfComAccountSubComMapper, HfComAccountSubCom> implements IHfComAccountSubComService {

}
