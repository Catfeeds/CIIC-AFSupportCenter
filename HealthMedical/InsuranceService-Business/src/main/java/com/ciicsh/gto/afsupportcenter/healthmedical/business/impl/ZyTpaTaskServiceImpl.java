package com.ciicsh.gto.afsupportcenter.healthmedical.business.impl;

import com.ciicsh.gto.afsupportcenter.healthmedical.business.AfTpaTaskService;
import com.ciicsh.gto.afsupportcenter.healthmedical.entity.po.ZyTpaTaskPO;
import com.ciicsh.gto.afsupportcenter.healthmedical.entity.po.AfTpaTaskPO;
import com.ciicsh.gto.afsupportcenter.healthmedical.entity.dto.insuretask.*;
import com.ciicsh.gto.afsupportcenter.healthmedical.business.impl.insuretask.*;
import com.ciicsh.gto.afsupportcenter.healthmedical.dao.ZyTpaTaskMapper;
import com.ciicsh.gto.afsupportcenter.healthmedical.business.ZyTpaTaskService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * TPA任务单记录表 服务实现类
 * </p>
 *
 * @author zhaogang
 * @since 2018-02-11
 */
@Service
public class ZyTpaTaskServiceImpl extends ServiceImpl<ZyTpaTaskMapper, ZyTpaTaskPO> implements ZyTpaTaskService {

    @Autowired
    private AfTpaTaskService afTpaTaskService;
    /**
    待处理任务单-生成中盈任务单
    */
    @Override
    public void getZyTpaTaskListBymonth(String month, String zyInsurance_Policy_ID) {

        //<editor-fold desc="1.依据投保时间段读取前道过来的投保任务单">
        List<AfTpaTaskPO> poAfTaskList = afTpaTaskService.getAfTpaTaskByInsureDate("", "", "4");
        //</editor-fold>

        // get 保单名称 by  zyInsurance_Policy_ID from table hm_insurance_policy
        String insurancePolicyName = "定制高端医疗保险(年单)";


        List<ZyTpaTaskPO> poList = null;
        //<editor-fold desc="2.生成中盈投保格式任务单并入库记录">
        poAfTaskList.forEach(poAfTask -> {
            ZyTpaTaskPO poZyTask = null;
            poZyTask.setEmployeeId(poAfTask.getEmployeeId());
            poZyTask.setCompanyId(poAfTask.getEmployeeId());
            poZyTask.setZyProductId(zyInsurance_Policy_ID);
            poZyTask.setZyProductName(insurancePolicyName);
            poZyTask.setStartConfirmDate(poAfTask.getStartConfirmDate());


            // 依据保单把前道任务单转换成中盈投保格式任务单
            switch (zyInsurance_Policy_ID) {
                case "4":      // 大陆高端医疗_转换逻辑
                case "5":
                case "6":
                    DaluGaoDuanConvertor tranfer = new DaluGaoDuanConvertor();
                    DaLuGaoDuanDTO xlsdata = tranfer.getXlsData(poAfTask, insurancePolicyName);
                    poZyTask.setXlsData("");



                    break;
                case "7":

                    break;
                case "8":

                    break;
                case "9":

                    break;
                default:

            }
            poList.add(poZyTask);
        });
        //</editor-fold>


        //<editor-fold desc="3.中盈任务单入库">

        //</editor-fold>

        //<editor-fold desc="4.返回中盈投保xlsData">

        //</editor-fold>
    }
}
