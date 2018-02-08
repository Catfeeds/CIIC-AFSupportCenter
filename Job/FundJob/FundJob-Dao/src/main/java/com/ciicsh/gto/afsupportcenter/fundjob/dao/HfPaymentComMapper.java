package com.ciicsh.gto.afsupportcenter.fundjob.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.ciicsh.gto.afsupportcenter.fundjob.bo.HfPaymentComBO;
import com.ciicsh.gto.afsupportcenter.fundjob.entity.HfPaymentCom;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 公积金汇缴支付公司账户名单 Mapper 接口
 * </p>
 */
@Mapper
@Component
public interface HfPaymentComMapper extends BaseMapper<HfPaymentCom> {

    //查询未支付客户
    List<HfPaymentComBO> getPaymentComList(Map map);

}
