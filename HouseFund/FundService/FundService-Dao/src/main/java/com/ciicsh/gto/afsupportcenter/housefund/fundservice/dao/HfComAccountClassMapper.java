package com.ciicsh.gto.afsupportcenter.housefund.fundservice.dao;

import com.ciicsh.gto.afsupportcenter.housefund.fundservice.entity.HfComAccountClass;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 * 企业公积金账户：存储中智大库、中智外包、独立户企业的账号，含基本公积金和补充公积金 Mapper 接口
 * </p>
 */
@Mapper
@Component
public interface HfComAccountClassMapper extends BaseMapper<HfComAccountClass> {

    List<HfComAccountClass> getAccountClassByPaymentId(@Param("paymentId") Long paymentId);
}
