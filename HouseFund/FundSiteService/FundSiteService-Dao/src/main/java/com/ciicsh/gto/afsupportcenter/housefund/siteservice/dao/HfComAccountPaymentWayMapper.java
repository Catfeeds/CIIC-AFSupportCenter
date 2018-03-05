package com.ciicsh.gto.afsupportcenter.housefund.siteservice.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.ciicsh.gto.afsupportcenter.housefund.siteservice.bo.HfComAccountPaymentWayBo;
import com.ciicsh.gto.afsupportcenter.housefund.siteservice.entity.HfComAccountPaymentWay;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * <p>
  * 企业公积金账户支付方式数据表 Mapper 接口
 * </p>
 *
 * @author 沈健
 * @since 2018-02-28
 */
@Repository
public interface HfComAccountPaymentWayMapper extends BaseMapper<HfComAccountPaymentWay> {

    List<HfComAccountPaymentWayBo> selectAllComTaskPaymentWayData();

}