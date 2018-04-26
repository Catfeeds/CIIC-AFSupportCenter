package com.ciicsh.gto.afsupportcenter.employmanagement.employcommandservice.business.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.ciicsh.gto.afsupportcenter.employmanagement.employcommandservice.bo.AmArchiveBO;
import com.ciicsh.gto.afsupportcenter.employmanagement.employcommandservice.bo.AmArchiveDocSeqBO;
import com.ciicsh.gto.afsupportcenter.employmanagement.employcommandservice.business.IAmArchiveService;
import com.ciicsh.gto.afsupportcenter.employmanagement.employcommandservice.dao.AmArchiveDocSeqMapper;
import com.ciicsh.gto.afsupportcenter.employmanagement.employcommandservice.dao.AmArchiveMapper;
import com.ciicsh.gto.afsupportcenter.employmanagement.employcommandservice.entity.AmArchive;
import com.ciicsh.gto.afsupportcenter.employmanagement.employcommandservice.entity.AmArchiveDocSeq;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by zhangzhiwen on 2018/1/29.
 */

@Service
public class AmArchiveServiceImpl extends ServiceImpl<AmArchiveMapper, AmArchive> implements IAmArchiveService {

    @Autowired
    private AmArchiveDocSeqMapper amArchiveDocSeqMapper;

    @Override
    public List<AmArchiveBO> queryAmArchiveList(Map<String, Object> param) {
        return baseMapper.queryAmArchiveList(param);
    }

    @Override
    public List<AmArchiveDocSeqBO> queryAmArchiveDocTypeByType(Integer type) {
        AmArchiveDocSeq seq = new AmArchiveDocSeq();
        seq.setType(type);
        return amArchiveDocSeqMapper.queryListByType(seq);
    }

    @Override
    public AmArchiveDocSeqBO queryAmArchiveDocTypeByTypeAndDocType(Integer type, String docType) {
        AmArchiveDocSeq seq = new AmArchiveDocSeq();
        seq.setType(type);
        seq.setDocType(docType);
        return amArchiveDocSeqMapper.queryListByTypeAndDocType(seq);
    }

    @Override
    public void updateByTypeAndDocType(AmArchiveDocSeq seq) {
        if(seq.getType() == null || seq.getDocType() == null || "".equals(seq.getDocType())){
            return;
        }
        amArchiveDocSeqMapper.updateByTypeAndDocType(seq);
    }
}
