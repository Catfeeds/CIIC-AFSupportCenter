package com.ciicsh.gto.afsupportcenter.employmanagement.employcommandservice.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.ciicsh.gto.afsupportcenter.employmanagement.employcommandservice.bo.AmDataauthCompanyBO;
import com.ciicsh.gto.afsupportcenter.employmanagement.employcommandservice.entity.AmDataauthCompany;

import java.util.List;

/**
 * <p>
 * 雇佣客户权限 Mapper 接口
 * </p>
 *
 * @author LiYueLong
 * @since 2018-04-18
 */
public interface AmDataauthCompanyMapper extends BaseMapper<AmDataauthCompany> {

    boolean insertAmDataauthCompany(AmDataauthCompany amDataauthCompany);

    List<AmDataauthCompanyBO> queryListByUidAndSerCenterId(AmDataauthCompany amDataauthCompany);

    boolean delByUidAndSerCenterId(AmDataauthCompany amDataauthCompany);
}
