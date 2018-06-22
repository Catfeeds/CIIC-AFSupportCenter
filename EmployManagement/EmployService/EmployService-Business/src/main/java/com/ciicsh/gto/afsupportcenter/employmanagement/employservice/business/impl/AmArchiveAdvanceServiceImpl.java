package com.ciicsh.gto.afsupportcenter.employmanagement.employservice.business.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.ciicsh.gto.afsupportcenter.employmanagement.employservice.bo.AmArchiveAdvanceBO;
import com.ciicsh.gto.afsupportcenter.employmanagement.employservice.bo.AmArchiveDocSeqBO;
import com.ciicsh.gto.afsupportcenter.employmanagement.employservice.bo.AmEmploymentBO;
import com.ciicsh.gto.afsupportcenter.employmanagement.employservice.business.IAmArchiveAdvanceService;
import com.ciicsh.gto.afsupportcenter.employmanagement.employservice.custom.advanceSearchExportOpt;
import com.ciicsh.gto.afsupportcenter.employmanagement.employservice.dao.AmArchiveAdvanceMapper;
import com.ciicsh.gto.afsupportcenter.employmanagement.employservice.dao.AmArchiveDocSeqMapper;
import com.ciicsh.gto.afsupportcenter.employmanagement.employservice.dao.AmEmploymentMapper;
import com.ciicsh.gto.afsupportcenter.employmanagement.employservice.entity.AmArchiveAdvance;
import com.ciicsh.gto.afsupportcenter.employmanagement.employservice.entity.AmArchiveDocSeq;
import com.ciicsh.gto.afsupportcenter.util.interceptor.authenticate.UserContext;
import com.ciicsh.gto.afsupportcenter.util.page.PageInfo;
import com.ciicsh.gto.afsupportcenter.util.page.PageKit;
import com.ciicsh.gto.afsupportcenter.util.page.PageRows;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    @Override
    public PageRows<AmArchiveAdvanceBO> queryAmArchiveAdvanceList(PageInfo pageInfo) {

        PageRows<AmArchiveAdvanceBO> result = new PageRows<>();
        AmArchiveAdvance pojo = pageInfo.toJavaObject(AmArchiveAdvance.class);
        PageRows<AmArchiveAdvance> resultPo = PageKit.doSelectPage(pageInfo, () -> baseMapper.selectList(new EntityWrapper<>(pojo)));
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
        po.setModifiedBy(UserContext.getUserName());
        po.setModifiedTime(new Date());
        Integer result = baseMapper.updateById(po);
        return result > 0;
    }

    @Override
    public boolean saveAmArchiveAdvance(AmArchiveAdvanceBO amArchiveAdvanceBO) {
        AmArchiveAdvance po = new AmArchiveAdvance();
        BeanUtils.copyProperties(amArchiveAdvanceBO, po);
        po.setStatus(1);
        Date nowTime = new Date();
        if(amArchiveAdvanceBO.getArchiveAdvanceId() == null || amArchiveAdvanceBO.getArchiveAdvanceId() == 0){
            po.setCreatedBy(UserContext.getUserName());
            po.setCreatedTime(nowTime);
        }
        po.setModifiedBy(UserContext.getUserName());
        po.setModifiedTime(nowTime);
        boolean result = this.insertOrUpdate(po);
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
        AmEmploymentBO amEmploymentBO = new AmEmploymentBO();
        List<String> param = new ArrayList<>();
        param.add("d.employee_name = '"+ employeeName +"'");
        param.add("d.id_num = '"+ idCard +"'");
        amEmploymentBO.setParam(param);
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
        po.setModifiedBy(UserContext.getUserName());
        po.setModifiedTime(new Date());
        Integer result = baseMapper.updateById(po);
        return result > 0;
    }

}
