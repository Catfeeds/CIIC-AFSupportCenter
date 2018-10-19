package com.ciicsh.gto.afsupportcenter.employmanagement.employservice.business.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.ciicsh.gto.afsupportcenter.employmanagement.employservice.bo.AmArchiveBO;
import com.ciicsh.gto.afsupportcenter.employmanagement.employservice.bo.AmArchiveDocSeqBO;
import com.ciicsh.gto.afsupportcenter.employmanagement.employservice.bo.AmPostBO;
import com.ciicsh.gto.afsupportcenter.employmanagement.employservice.business.*;
import com.ciicsh.gto.afsupportcenter.employmanagement.employservice.business.utils.CommonApiUtils;
import com.ciicsh.gto.afsupportcenter.employmanagement.employservice.business.utils.ReasonUtil;
import com.ciicsh.gto.afsupportcenter.employmanagement.employservice.dao.AmArchiveDocSeqMapper;
import com.ciicsh.gto.afsupportcenter.employmanagement.employservice.dao.AmArchiveMapper;
import com.ciicsh.gto.afsupportcenter.employmanagement.employservice.entity.*;
import com.ciicsh.gto.afsupportcenter.util.StringUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    @Autowired
    private IAmArchiveLinkService amArchiveLinkService;

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

        AmArchive entity1 = null;
        Boolean isLink = false;
        if(entity.getArchiveId()==null){
            entity.setCreatedTime(now);
            entity.setModifiedTime(now);
            entity.setCreatedBy(ReasonUtil.getUserId());
            entity.setModifiedBy(ReasonUtil.getUserId());
            entity.setIsActive(1);
        }else{
            entity1 = selectById(entity.getArchiveId());
            entity.setCreatedBy(entity1.getCreatedBy());
            entity.setCreatedTime(entity1.getCreatedTime());
            entity.setIsActive(1);
            entity.setModifiedTime(now);
            entity.setModifiedBy(ReasonUtil.getUserId());

            StringBuffer buf = new StringBuffer();
            buf.append(entity.getDocType()==null?"":entity.getDocType());
            buf.append(entity.getDocNum()==null?"":entity.getDocNum());
            buf.append(entity.getEmployFeedback()==null?"":entity.getEmployFeedback());

            StringBuffer buf1 = new StringBuffer();
            buf1.append(entity1.getDocType()==null?"":entity1.getDocType());
            buf1.append(entity1.getDocNum()==null?"":entity1.getDocNum());
            buf1.append(entity1.getEmployFeedback()==null?"":entity1.getEmployFeedback());

            if(!buf.toString().equals(buf1.toString()))
            {
                isLink = true;
            }
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

        boolean result = this.insertOrUpdateAllColumn(entity);

        if(isLink)
        {
            try {
                if(!StringUtil.isEmpty(entity1.getDocNum())||!StringUtil.isEmpty(entity1.getDocType())||!StringUtil.isEmpty(entity1.getEmployFeedback()))
                {
                    AmArchiveLink amArchiveLink = new AmArchiveLink();
                    amArchiveLink.setArchiveId(entity1.getArchiveId());
                    amArchiveLink.setDocNum(entity1.getDocNum());
                    amArchiveLink.setDocType(entity1.getDocType());
                    amArchiveLink.setEmployFeedback(ReasonUtil.getYgfk(entity1.getEmployFeedback()));
                    amArchiveLink.setCreatedBy(entity1.getCreatedBy());
                    amArchiveLink.setCreatedTime(entity1.getModifiedTime());
                    amArchiveLinkService.saveAmArchiveLink(amArchiveLink);
                }
            } catch (Exception e) {

            }
        }

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
    public Boolean saveArchiveSend(AmPostBO amPostBO) {
        AmArchive archive = baseMapper.selectById(amPostBO.getArchiveId());
        archive.setPost(amPostBO.getPost());
        if(null!=amPostBO.getPost()&&amPostBO.getPost()==0)
        {
            archive.setPostSaver("");
        }else{
            archive.setPostSaver(amPostBO.getPostSaver());
        }
        return baseMapper.updateAllColumnById(archive)> 0;
    }
}
