package com.ciicsh.gto.afsupportcenter.employmanagement.employcommandservice.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.ciicsh.gto.afsupportcenter.employmanagement.employcommandservice.bo.AmArchiveBO;
import com.ciicsh.gto.afsupportcenter.employmanagement.employcommandservice.bo.AmArchiveDocSeqBO;
import com.ciicsh.gto.afsupportcenter.employmanagement.employcommandservice.entity.AmArchive;
import com.ciicsh.gto.afsupportcenter.employmanagement.employcommandservice.entity.AmArchiveDocSeq;

import java.util.List;
import java.util.Map;

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
