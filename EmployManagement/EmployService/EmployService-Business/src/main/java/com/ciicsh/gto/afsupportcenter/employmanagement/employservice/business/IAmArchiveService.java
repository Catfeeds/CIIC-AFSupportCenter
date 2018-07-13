package com.ciicsh.gto.afsupportcenter.employmanagement.employservice.business;

import com.baomidou.mybatisplus.service.IService;
import com.ciicsh.gto.afsupportcenter.employmanagement.employservice.bo.AmArchiveBO;
import com.ciicsh.gto.afsupportcenter.employmanagement.employservice.bo.AmArchiveDocSeqBO;
import com.ciicsh.gto.afsupportcenter.employmanagement.employservice.entity.AmArchive;
import com.ciicsh.gto.afsupportcenter.employmanagement.employservice.entity.AmArchiveDocSeq;

import java.util.List;
import java.util.Map;

/**
 * Created by zhangzhiwen on 2018/1/29.
 */
public interface IAmArchiveService extends IService<AmArchive> {

    List<AmArchiveBO>  queryAmArchiveList(Map<String, Object> param);

    List<AmArchiveDocSeqBO> queryAmArchiveDocTypeByType(Integer type);

    AmArchiveDocSeqBO queryAmArchiveDocTypeByTypeAndDocType(Integer type, String docType);

    void updateByTypeAndDocType(AmArchiveDocSeq seq);

    Map<String,Object>  saveArchive(AmArchiveBO amArchiveBO);

    List<AmArchiveDocSeqBO> queryCountHaveAbove(AmArchiveDocSeq seq);

    List<AmArchiveBO>  queryAmArchiveBatch(AmArchiveBO amArchiveBO);

}
