package com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.entity.SsHfAutoOffsetFail;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

@Mapper
@Component
public interface SsHfAutoOffsetFailMapper extends BaseMapper<SsHfAutoOffsetFail> {
}
