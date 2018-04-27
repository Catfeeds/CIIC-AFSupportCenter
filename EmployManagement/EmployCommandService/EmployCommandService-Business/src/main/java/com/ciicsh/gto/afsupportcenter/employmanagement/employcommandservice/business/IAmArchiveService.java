package com.ciicsh.gto.afsupportcenter.employmanagement.employcommandservice.business;

import com.baomidou.mybatisplus.service.IService;
import com.ciicsh.gto.afsupportcenter.employmanagement.employcommandservice.bo.AmArchiveBO;
import com.ciicsh.gto.afsupportcenter.employmanagement.employcommandservice.bo.AmArchiveDocSeqBO;
import com.ciicsh.gto.afsupportcenter.employmanagement.employcommandservice.entity.AmArchive;
import com.ciicsh.gto.afsupportcenter.employmanagement.employcommandservice.entity.AmArchiveDocSeq;

import java.util.List;
import java.util.Map;

/**
 * Created by zhangzhiwen on 2018/1/29.
 */
public interface IAmArchiveService extends IService<AmArchive> {

    List<AmArchiveBO>  queryAmArchiveList(Map<String,Object> param);

    List<AmArchiveDocSeqBO> queryAmArchiveDocTypeByType(Integer type);

    AmArchiveDocSeqBO queryAmArchiveDocTypeByTypeAndDocType(Integer type, String docType);

    void updateByTypeAndDocType(AmArchiveDocSeq seq);

    Map<String,Object>  saveArchive(AmArchiveBO amArchiveBO);

}
