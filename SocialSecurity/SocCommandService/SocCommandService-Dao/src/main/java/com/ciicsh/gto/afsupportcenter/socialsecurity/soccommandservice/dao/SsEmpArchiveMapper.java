package com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.dao;

import com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.entity.SsEmpArchive;
import com.baomidou.mybatisplus.mapper.BaseMapper;

/**
 * <p>
 * 雇员本地社保档案主表,
由中智代缴过社保的雇员在此表必有一条记录，如果雇员跳槽到另外一家客户，就会在此表产生 Mapper 接口
 * </p>
 *
 * @author HuangXing
 * @since 2017-12-01
 */
public interface SsEmpArchiveMapper extends BaseMapper<SsEmpArchive> {

}
