package com.ciicsh.gto.afsupportcenter.healthmedical.business.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.ciicsh.gto.afsupportcenter.healthmedical.business.AfPeTaskService;
import com.ciicsh.gto.afsupportcenter.healthmedical.dao.AfPeTaskMapper;
import com.ciicsh.gto.afsupportcenter.healthmedical.entity.po.AfPeTask;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 体检任务单表 服务实现类
 * </p>
 *
 * @author 顾伟
 * @since 2018-03-07
 */
@Service
public class AfPeTaskServiceImpl extends ServiceImpl<AfPeTaskMapper, AfPeTask> implements AfPeTaskService {

    @Autowired
    private AfPeTaskMapper afPeTaskMapper;
    /**
     * 普通体检
     */
    private final static int PE_COMMON = 1;
    /**
     * 入职体检
     */
    private final static int PE_ENTRY = 2;

    @Override
    public boolean insertBatchTask(List<AfPeTask> list) {
        try {
            list.stream().forEach(i -> {
                if (PE_COMMON == i.getPeType()) {
                    i.setStatus(1);
                } else if (PE_ENTRY == i.getPeType()) {
                    i.setStatus(2);
                }
                afPeTaskMapper.insert(i);
            });
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    @Override
    public boolean update(AfPeTask afPeTask) {
        Wrapper<AfPeTask> wr = new EntityWrapper<AfPeTask>().eq("id_num",afPeTask.getIdNum());
        Integer rows = afPeTaskMapper.update(afPeTask, wr);
        return rows == 1 ? true : false;
    }

    @Override
    public List<AfPeTask> getListByBespeakPeIds() {
        return afPeTaskMapper.selectListByBespeakPeIds();
    }

    @Override
    public List<AfPeTask> getListByStatus() {
        return afPeTaskMapper.selectListByStatus();
    }
}