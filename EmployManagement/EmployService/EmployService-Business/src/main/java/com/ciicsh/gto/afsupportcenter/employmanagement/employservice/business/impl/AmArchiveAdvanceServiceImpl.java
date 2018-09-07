package com.ciicsh.gto.afsupportcenter.employmanagement.employservice.business.impl;

import com.baomidou.mybatisplus.enums.SqlLike;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.ciicsh.gto.afsupportcenter.employmanagement.employservice.bo.*;
import com.ciicsh.gto.afsupportcenter.employmanagement.employservice.business.IAmArchiveAdvanceService;
import com.ciicsh.gto.afsupportcenter.employmanagement.employservice.custom.advanceSearchExportOpt;
import com.ciicsh.gto.afsupportcenter.employmanagement.employservice.dao.*;
import com.ciicsh.gto.afsupportcenter.employmanagement.employservice.entity.AmArchiveAdvance;
import com.ciicsh.gto.afsupportcenter.employmanagement.employservice.entity.AmArchiveDocSeq;
import com.ciicsh.gto.afsupportcenter.util.interceptor.authenticate.UserContext;
import com.ciicsh.gto.afsupportcenter.util.page.PageInfo;
import com.ciicsh.gto.afsupportcenter.util.page.PageKit;
import com.ciicsh.gto.afsupportcenter.util.page.PageRows;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * <p>
 * 档案预增表 服务实现类
 * </p>
 *
 * @author lyl
 * @since 2018-05-25
 */

@Service
public class AmArchiveAdvanceServiceImpl extends ServiceImpl<AmArchiveAdvanceMapper, AmArchiveAdvance> implements IAmArchiveAdvanceService {

    @Autowired
    private AmArchiveDocSeqMapper amArchiveDocSeqMapper;

    @Autowired
    private AmEmploymentMapper amEmploymentMapper;

    @Autowired
    private AmEmpTaskMapper amEmpTaskMapper;

    @Autowired
    private AmResignMapper amResignMapper;

    @Override
    public PageRows<AmArchiveAdvanceBO> queryAmArchiveAdvanceList(PageInfo pageInfo) {

        PageRows<AmArchiveAdvanceBO> result = new PageRows<>();
        AmArchiveAdvance pojo = pageInfo.toJavaObject(AmArchiveAdvance.class);
        Wrapper<AmArchiveAdvance> wrapper = new EntityWrapper<>();
        wrapper.like("employee_idcard_no",pojo.getEmployeeIdcardNo(),SqlLike.DEFAULT);
        wrapper.like("employee_name",pojo.getEmployeeName(),SqlLike.DEFAULT);
        if(pojo.getStatus()!=null){
            wrapper.eq("status",pojo.getStatus());
        }
        wrapper.eq("is_active",1);
        PageRows<AmArchiveAdvance> resultPo = PageKit.doSelectPage(pageInfo, () -> baseMapper.selectList(wrapper));
        result.setTotal(resultPo.getTotal());
        List<AmArchiveAdvanceBO> boList = new ArrayList<>();
        for (AmArchiveAdvance po : resultPo.getRows()) {
            AmArchiveAdvanceBO bo = new AmArchiveAdvanceBO();
            BeanUtils.copyProperties(po, bo);
            boList.add(bo);
        }
        result.setRows(boList);
        return result;
    }

    @Override
    public boolean deleteAmArchiveAdvance(AmArchiveAdvanceBO amArchiveAdvanceBO) {
        AmArchiveAdvance po = new AmArchiveAdvance();
        BeanUtils.copyProperties(amArchiveAdvanceBO, po);
        po.setStatus(0);
        po.setModifiedBy(UserContext.getUser().getDisplayName());
        po.setModifiedTime(new Date());
        Integer result = baseMapper.updateById(po);
        return result > 0;
    }

    @Override
    @Transactional(
        rollbackFor = {Exception.class}
    )
    public boolean saveAmArchiveAdvance(AmArchiveAdvanceBO amArchiveAdvanceBO) {
        AmArchiveAdvance po = new AmArchiveAdvance();
        BeanUtils.copyProperties(amArchiveAdvanceBO, po);
        po.setStatus(1);
        Date nowTime = new Date();
        if(amArchiveAdvanceBO.getArchiveAdvanceId() == null || amArchiveAdvanceBO.getArchiveAdvanceId() == 0){
            po.setCreatedBy(UserContext.getUser().getDisplayName());
            po.setCreatedTime(nowTime);
        }
        po.setModifiedBy(UserContext.getUser().getDisplayName());
        po.setModifiedTime(nowTime);
        //boolean result = this.insertOrUpdate(po);
        po.setIsActive(1);
        po.setCreatedTime(nowTime);
        po.setCreatedBy(UserContext.getUser().getDisplayName());
        boolean result = this.insertOrUpdateAllColumn(po);
        if (result) {
            // 修改预留档案编号 seq
            if (po.getReservedArchiveType() != null && po.getReservedArchiveNo() != null) {
                AmArchiveDocSeq seq = new AmArchiveDocSeq();
                seq.setDocType(po.getReservedArchiveType());
                seq.setDocSeq(po.getReservedArchiveNo());
                seq.setType(1);
                List<AmArchiveDocSeqBO> boList = amArchiveDocSeqMapper.queryCountHaveAbove(seq);
                if(boList.size() == 0){
                    amArchiveDocSeqMapper.updateByTypeAndDocType(seq);
                }
            }
        }
        return result;
    }

    @Override
    public AmArchiveAdvanceBO queryAmArchiveAdvanceByNameIdcard(String name,String idCard,Integer status) {

        AmArchiveAdvance po = new AmArchiveAdvance();
        po.setEmployeeName(name);
        po.setEmployeeIdcardNo(idCard);
        po.setStatus(status);
        AmArchiveAdvance queryPO = baseMapper.selectOne(po);
        if(queryPO == null){
            return null;
        }
        AmArchiveAdvanceBO result = new AmArchiveAdvanceBO();
        BeanUtils.copyProperties(queryPO,result);
        return result;
    }

    @Override
    public List<advanceSearchExportOpt> queryAdvanceSearchExportOpt(AmArchiveAdvanceBO amArchiveAdvanceBO) {

        List<advanceSearchExportOpt> result = new ArrayList<>();
        AmArchiveAdvance pojo = new AmArchiveAdvance();
        pojo.setEmployeeName(amArchiveAdvanceBO.getEmployeeName());
        pojo.setEmployeeIdcardNo(amArchiveAdvanceBO.getEmployeeIdcardNo());
        pojo.setStatus(amArchiveAdvanceBO.getStatus());
        List<AmArchiveAdvance> list = baseMapper.selectList(new EntityWrapper<>(pojo));
        for (AmArchiveAdvance am: list) {
            advanceSearchExportOpt opt = new advanceSearchExportOpt();
            BeanUtils.copyProperties(am,opt);
            if(am.getStatus() == 1){
                opt.setStruts("未匹配");
            }else if(am.getStatus() == 2){
                opt.setStruts("已匹配");
            }
            result.add(opt);
        }
        return result;
    }

    @Override
    public AmEmploymentBO queryAmArchiveByEmployeeNameIdCard(String employeeName, String idCard) {

        List<String> param = new ArrayList<>();
        param.add("d.employee_name = '"+ employeeName +"'");
        param.add("d.id_num = '"+ idCard +"'");

        // 是否有用工 任务单
        AmEmpTaskBO amEmpTaskBO = new AmEmpTaskBO();
        amEmpTaskBO.setParam(param);
        amEmpTaskBO.setOrderParam(new ArrayList<String>());
        List<AmEmpTaskBO> amEmpTaskBOList = amEmpTaskMapper.queryAmEmpTask(amEmpTaskBO);
        if(amEmpTaskBOList == null || amEmpTaskBOList.size() == 0){
            return null;
        }

        // 是否有退工 任务单
        AmResignBO amResignBO = new AmResignBO();
        amResignBO.setParam(param);
        amResignBO.setOrderParam(new ArrayList<String>());
        List<AmResignBO> amResignBOList = amResignMapper.queryAmResign(amResignBO);
        if(amResignBOList != null && amResignBOList.size() > 0){
            // 有退工任务单 退工日期是否为null
            if(amResignBOList.get(0).getOutDate() != null){
                //amResignBOList.get(0).getJobCentreFeedbackDate();
                return null;
            }
        }

        // 是否有档案
        AmEmploymentBO amEmploymentBO = new AmEmploymentBO();
        amEmploymentBO.setParam(param);
        amEmploymentBO.setOrderParam(new ArrayList<String>());
        List<AmEmploymentBO> boList = amEmploymentMapper.queryAmArchive(amEmploymentBO);

        if(boList != null && boList.size() > 0){
            return boList.get(0);
        }
        return null;
    }

    @Override
    public boolean updateAmArchiveAdvance(AmArchiveAdvanceBO amArchiveAdvanceBO) {
        AmArchiveAdvance po = new AmArchiveAdvance();
        BeanUtils.copyProperties(amArchiveAdvanceBO, po);
        po.setStatus(2);
        po.setModifiedBy(UserContext.getUser().getDisplayName());
        po.setModifiedTime(new Date());
        Integer result = baseMapper.updateById(po);
        return result > 0;
    }

    @Override
    public void updateNewAmArchiveAdvance(AmArchiveAdvanceBO amArchiveAdvanceBO) {
        AmArchiveAdvance po = new AmArchiveAdvance();
        BeanUtils.copyProperties(amArchiveAdvanceBO, po);
        po.setStatus(2);
        po.setModifiedTime(new Date());
        baseMapper.updateById(po);
    }


}
