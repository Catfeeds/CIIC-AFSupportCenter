package com.ciicsh.gto.afsupportcenter.employmanagement.employservice.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.ciicsh.gto.afsupportcenter.employmanagement.employservice.bo.AmArchiveDocSeqBO;
import com.ciicsh.gto.afsupportcenter.employmanagement.employservice.entity.AmArchiveDocSeq;

import java.util.List;

/**
 * <p>
 * 雇员档案序号表
 * </p>
 */
public interface AmArchiveDocSeqMapper extends BaseMapper<AmArchiveDocSeq> {

    List<AmArchiveDocSeqBO>  queryListByType(AmArchiveDocSeq seq);


    AmArchiveDocSeqBO queryListByTypeAndDocType(AmArchiveDocSeq seq);

    void updateByTypeAndDocType(AmArchiveDocSeq seq);

    List<AmArchiveDocSeqBO> queryCountHaveAbove(AmArchiveDocSeq seq);
}
