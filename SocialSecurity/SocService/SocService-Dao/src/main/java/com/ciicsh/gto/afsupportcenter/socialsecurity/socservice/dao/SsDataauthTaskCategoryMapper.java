package com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.bo.SsDataauthCompanyBO;
import com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.entity.SsDataauthCompany;
import com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.entity.SsDataauthTaskCategory;

import java.util.List;

/**
 * <p>
 * 社保客户权限任务单类型 Mapper 接口
 * </p>
 *
 * @author LiYueLong
 * @since 2018-04-19
 */
public interface SsDataauthTaskCategoryMapper extends BaseMapper<SsDataauthTaskCategory> {

    boolean insertSsDataauthTaskCategory(SsDataauthTaskCategory ssDataauthTaskCategory);

    List<SsDataauthTaskCategory> queryListByUid(SsDataauthTaskCategory ssDataauthTaskCategory);

    boolean delByUid(String userId);
}
