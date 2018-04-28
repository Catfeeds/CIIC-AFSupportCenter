package com.ciicsh.gto.afsupportcenter.employmanagement.employcommandservice.business.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.ciicsh.gto.afsupportcenter.employmanagement.employcommandservice.bo.AmArchiveBO;
import com.ciicsh.gto.afsupportcenter.employmanagement.employcommandservice.bo.AmArchiveDocSeqBO;
import com.ciicsh.gto.afsupportcenter.employmanagement.employcommandservice.business.IAmArchiveService;
import com.ciicsh.gto.afsupportcenter.employmanagement.employcommandservice.business.IAmEmpTaskService;
import com.ciicsh.gto.afsupportcenter.employmanagement.employcommandservice.business.IAmEmploymentService;
import com.ciicsh.gto.afsupportcenter.employmanagement.employcommandservice.business.utils.CommonApiUtils;
import com.ciicsh.gto.afsupportcenter.employmanagement.employcommandservice.business.utils.ReasonUtil;
import com.ciicsh.gto.afsupportcenter.employmanagement.employcommandservice.dao.AmArchiveDocSeqMapper;
import com.ciicsh.gto.afsupportcenter.employmanagement.employcommandservice.dao.AmArchiveMapper;
import com.ciicsh.gto.afsupportcenter.employmanagement.employcommandservice.entity.AmArchive;
import com.ciicsh.gto.afsupportcenter.employmanagement.employcommandservice.entity.AmArchiveDocSeq;
import com.ciicsh.gto.afsupportcenter.employmanagement.employcommandservice.entity.AmEmpTask;
import com.ciicsh.gto.afsupportcenter.employmanagement.employcommandservice.entity.AmEmployment;
import com.ciicsh.gto.afsupportcenter.util.StringUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by zhangzhiwen on 2018/1/29.
 */

@Service
public class AmArchiveServiceImpl extends ServiceImpl<AmArchiveMapper, AmArchive> implements IAmArchiveService {

    @Autowired
    private AmArchiveDocSeqMapper amArchiveDocSeqMapper;

    @Autowired
    private IAmEmploymentService amEmploymentService;

    @Autowired
    private IAmEmpTaskService amEmpTaskService;

    @Autowired
    private CommonApiUtils employeeInfoProxy;

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

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public Map<String,Object> saveArchive(AmArchiveBO amArchiveBO) {
        Map<String,Object> map = new HashMap<>();
        AmArchive entity = new AmArchive();
        BeanUtils.copyProperties(amArchiveBO,entity);
        LocalDateTime now = LocalDateTime.now();
        if(entity.getArchiveId()==null){
            entity.setCreatedTime(now);
            entity.setModifiedTime(now);
            entity.setCreatedBy(ReasonUtil.getUserId());
            entity.setModifiedBy(ReasonUtil.getUserId());
            entity.setIsActive(1);
        }else{
            entity.setModifiedTime(now);
            entity.setModifiedBy(ReasonUtil.getUserId());
        }
        AmEmpTask amEmpTask = null;
        if(!StringUtil.isEmpty(entity.getEmployFeedback())){
            AmEmployment amEmployment = amEmploymentService.selectById(entity.getEmploymentId());
            amEmpTask = amEmpTaskService.selectById(amEmployment.getEmpTaskId());
            amEmpTask.setTaskStatus(Integer.parseInt(entity.getEmployFeedback()));
            amEmpTaskService.insertOrUpdate(amEmpTask);
            map.put("taskId",amEmpTask.getTaskId());
        }
        if("11".equals(entity.getEmployFeedback()))
        {
            if(entity.getUkeyBorrowDate()==null)
            {
                entity.setUkeyBorrowDate(LocalDate.now());
            }
        }

        boolean result = this.insertOrUpdateAllColumn(entity);

        map.put("result",new Boolean(result));
        map.put("entity",entity);

        // 修改预留档案编号 seq
        AmArchiveDocSeq seq = new AmArchiveDocSeq();
        seq.setType(1);
        seq.setDocType(amArchiveBO.getYuliuDocType());
        seq.setDocSeq(Integer.parseInt( amArchiveBO.getYuliuDocNum()));
        this.updateByTypeAndDocType(seq);
        // 修改档案编号 seq
        AmArchiveDocSeq seq2 = new AmArchiveDocSeq();
        seq2.setType(2);
        seq2.setDocType(amArchiveBO.getDocType());
        seq2.setDocSeq(Integer.parseInt( amArchiveBO.getDocNum()));
        this.updateByTypeAndDocType(seq2);

        return map;
    }
}
