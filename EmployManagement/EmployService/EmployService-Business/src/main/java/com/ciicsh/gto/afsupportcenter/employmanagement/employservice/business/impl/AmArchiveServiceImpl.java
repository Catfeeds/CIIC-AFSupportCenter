package com.ciicsh.gto.afsupportcenter.employmanagement.employservice.business.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.ciicsh.gto.afsupportcenter.employmanagement.employservice.bo.AmArchiveAdvanceBO;
import com.ciicsh.gto.afsupportcenter.employmanagement.employservice.bo.AmArchiveBO;
import com.ciicsh.gto.afsupportcenter.employmanagement.employservice.bo.AmArchiveDocSeqBO;
import com.ciicsh.gto.afsupportcenter.employmanagement.employservice.business.IAmArchiveAdvanceService;
import com.ciicsh.gto.afsupportcenter.employmanagement.employservice.business.IAmArchiveService;
import com.ciicsh.gto.afsupportcenter.employmanagement.employservice.business.IAmEmpTaskService;
import com.ciicsh.gto.afsupportcenter.employmanagement.employservice.business.IAmEmploymentService;
import com.ciicsh.gto.afsupportcenter.employmanagement.employservice.business.utils.CommonApiUtils;
import com.ciicsh.gto.afsupportcenter.employmanagement.employservice.business.utils.ReasonUtil;
import com.ciicsh.gto.afsupportcenter.employmanagement.employservice.dao.AmArchiveDocSeqMapper;
import com.ciicsh.gto.afsupportcenter.employmanagement.employservice.dao.AmArchiveMapper;
import com.ciicsh.gto.afsupportcenter.employmanagement.employservice.entity.AmArchive;
import com.ciicsh.gto.afsupportcenter.employmanagement.employservice.entity.AmArchiveDocSeq;
import com.ciicsh.gto.afsupportcenter.employmanagement.employservice.entity.AmEmpTask;
import com.ciicsh.gto.afsupportcenter.employmanagement.employservice.entity.AmEmployment;
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

    @Autowired
    private IAmArchiveAdvanceService amArchiveAdvanceService;

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
        amArchiveDocSeqMapper.updateByTypeAndDocType(seq);
    }

    public List<AmArchiveDocSeqBO> queryCountHaveAbove(AmArchiveDocSeq seq){
        return amArchiveDocSeqMapper.queryCountHaveAbove(seq);
    }

    @Override
    public List<AmArchiveBO> queryAmArchiveBatch(AmArchiveBO amArchiveBO) {
        return baseMapper.queryAmArchiveBatch(amArchiveBO);
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
            AmArchive entity1 = selectById(entity.getArchiveId());
            entity.setCreatedBy(entity1.getCreatedBy());
            entity.setCreatedTime(entity1.getCreatedTime());
            entity.setIsActive(1);
            entity.setModifiedTime(now);
            entity.setModifiedBy(ReasonUtil.getUserId());
        }
        AmEmpTask amEmpTask = null;
        if(!StringUtil.isEmpty(entity.getEmployFeedback())){
            AmEmployment amEmployment = amEmploymentService.selectById(entity.getEmploymentId());
            amEmpTask = amEmpTaskService.selectById(amEmployment.getEmpTaskId());
            amEmpTask.setTaskStatus(Integer.parseInt(entity.getEmployFeedback()));

            if("0".equals(amArchiveBO.getIsFrist()))
            {
                if("11".equals(entity.getEmployFeedback()))
                {

                }else{
                    amEmpTask.setFinish(true);
                }
            }
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
        // 如果是匹配到的预增档案信息  修改为已匹配
       /* if(result){
            if(amArchiveBO.getFormAdvance()){
                AmArchiveAdvanceBO bo = amArchiveAdvanceService.queryAmArchiveAdvanceByNameIdcard(amArchiveBO.getEmployeeName(),amArchiveBO.getIdNum(),1);
                if(bo != null){
                    amArchiveAdvanceService.updateAmArchiveAdvance(bo);
                }
            }
        }*/
        map.put("result",new Boolean(result));
        map.put("entity",entity);

        if(result)
        {
            // 修改预留档案编号 seq
            if(amArchiveBO.getYuliuDocNum() != null && amArchiveBO.getYuliuDocType() != null){
                AmArchiveDocSeq seq = new AmArchiveDocSeq();
                seq.setType(1);
                seq.setDocType(amArchiveBO.getYuliuDocType());
                seq.setDocSeq(Integer.parseInt( amArchiveBO.getYuliuDocNum()));
                List<AmArchiveDocSeqBO> list = this.queryCountHaveAbove(seq);
                // 比原有的seq要大
                if(list.size() == 0){
                    this.updateByTypeAndDocType(seq);
                }
            }
            // 修改档案编号 seq
            if(amArchiveBO.getDocNum() != null && amArchiveBO.getDocType() != null){
                AmArchiveDocSeq seq2 = new AmArchiveDocSeq();
                seq2.setType(2);
                seq2.setDocType(amArchiveBO.getDocType());
                seq2.setDocSeq(Integer.parseInt( amArchiveBO.getDocNum()));
                List<AmArchiveDocSeqBO> list = this.queryCountHaveAbove(seq2);
                // 比原有的seq要大
                if(list.size() == 0){
                    this.updateByTypeAndDocType(seq2);
                }
            }
        }

        return map;
    }


    @Override
    public Boolean saveArchiveSend(Long archiveId, Integer post) {
        AmArchive archive = new AmArchive();
        archive.setArchiveId(archiveId);
        archive.setPost(post);
        return baseMapper.updateById(archive) > 0;
    }
}
