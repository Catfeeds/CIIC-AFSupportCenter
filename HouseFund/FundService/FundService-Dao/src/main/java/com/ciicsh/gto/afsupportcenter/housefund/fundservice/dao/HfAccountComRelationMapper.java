package com.ciicsh.gto.afsupportcenter.housefund.fundservice.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;

import com.ciicsh.gto.afsupportcenter.housefund.fundservice.entity.HfAccountComRelation;
import org.springframework.stereotype.Repository;

/**
 * <p>
  * 公积金账户绑定客户：一个公积金账户可以绑定多家子公司
Com：公司简写
Sub：表示公司账户下的 Mapper 接口
 * </p>
 *
 * @author 沈健
 * @since 2018-02-07
 */
@Repository
public interface HfAccountComRelationMapper extends BaseMapper<HfAccountComRelation> {

    Integer queryIfComAccountIdExists(Long comAccountId);

}